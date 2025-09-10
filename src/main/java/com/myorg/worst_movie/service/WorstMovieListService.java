package com.myorg.worst_movie.service;

import com.myorg.worst_movie.dto.WorstMovieDetailDTO;
import com.myorg.worst_movie.dto.WorstMovieResponseDTO;
import com.myorg.worst_movie.model.Movie;
import com.myorg.worst_movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@AllArgsConstructor
@Service
public class WorstMovieListService {

    private final MovieRepository repository;

    public WorstMovieResponseDTO getWorstProducers() {
        Map<String, List<Movie>> moviesByProducerMap = getProducersMap();

        List<WorstMovieDetailDTO> winners = new ArrayList<>();

        moviesByProducerMap.forEach((key, value) -> {
            List<WorstMovieDetailDTO> producersDetails = getProducerDetails(value);
            winners.addAll(producersDetails);
        });

        return WorstMovieResponseDTO.builder()
                .min(getLastTwoMinInterval(winners))
                .max(getLastTwoMaxInterval(winners))
                .build();
    }

    private List<WorstMovieDetailDTO> getProducerDetails(List<Movie> moviesByProducer) {
        Map<Integer, List<Integer>> intervalWinProducer = getMapByIntervalProducer(moviesByProducer);

        List<WorstMovieDetailDTO> winnerDTOList = new ArrayList<>();

        intervalWinProducer.forEach((key, value) ->
                winnerDTOList.add(WorstMovieDetailDTO.builder()
                        .producer(moviesByProducer.getFirst().getProducers())
                        .interval(key)
                        .previousWin(value.getFirst())
                        .followingWin(value.getLast())
                        .build()));

        return winnerDTOList;
    }

    private Map<Integer, List<Integer>> getMapByIntervalProducer(List<Movie> moviesByProducer) {
        List<Integer> yearsSorted = moviesByProducer.stream()
                .map(Movie::getYear)
                .sorted()
                .toList();

        Map<Integer, List<Integer>> producersInterval = new HashMap<>();

        for (int i = 0; i < yearsSorted.size() - 1; i++) {
            Integer first = yearsSorted.get(i);
            Integer second = yearsSorted.get(i + 1);
            int yearsInterval = Math.abs(first - second);

            producersInterval.put(yearsInterval, Arrays.asList(first, second));
        }

        return producersInterval;
    }

    private List<WorstMovieDetailDTO> getLastTwoMinInterval(List<WorstMovieDetailDTO> winners) {
        return winners.stream()
                .sorted(Comparator.comparing(WorstMovieDetailDTO::getInterval))
                .limit(2)
                .toList();
    }

    private List<WorstMovieDetailDTO> getLastTwoMaxInterval(List<WorstMovieDetailDTO> winners) {
        return winners.stream()
                .sorted(Comparator.comparing(WorstMovieDetailDTO::getInterval).reversed())
                .limit(2)
                .toList();
    }

    private Map<String, List<Movie>> getProducersMap() {
        return repository.findAllByWinner(true)
                .stream()
                .collect(groupingBy(Movie::getProducers));
    }

}
