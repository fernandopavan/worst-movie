API RESTful para possibilitar a leitura da lista de indicados e vencedores da
categoria Pior Filme do Golden Raspberry Awards.

Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois
prêmios mais rápido

### Para rodar a API (Java JDK 21 e maven)


1. Clonar o projeto no seu computador

```bash
  git clone https://github.com/fernandopavan/worst-movie.git
```

2. No diretório do projeto, instale as dependências e inicie o mesmo

```bash
  mvn clean install
```

```bash
  mvn spring-boot:run
```

3. Upload do csv com os filmes

```bash
  curl --location --request POST 'http://localhost:8080/api/worst-movie/upload' \
	--form 'file=@"/C:/........./movielist.csv"'
```

4. Retornar o produtor com maior intervalo entre dois prêmios consecutivos e o que obteve dois prêmios mais rápido

```bash
  curl --location --request GET 'http://localhost:8080/api/worst-movie'
```


### Documentação da API

Swagger: http://localhost:8080/api/swagger-ui/index.html
