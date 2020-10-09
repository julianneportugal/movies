Movies Application
======

Movies é um projeto criado para fornecer uma interface de integração ao sistema SWAPI onde os dados devem ser convertidos para o formato JSON esperado pela Globoplay.

Funcionalidades
-----

O projeto possui as seguintes funcionalidade:

 * Consulta às informações do filme da saga Star Wars pelo id do filme através de um GET em /starwars/movies?id=<id_film>. Ex: http://localhost:8080/starwars/movies?id=1
 * Listagem das informações dos filmes da saga Star Wars através de um GET em /starwars/movies. Ex: http://localhost:8080/starwars/movies
 * Remoção do cache no redis das informações do filme da saga Star Wars pelo id do filme através de um DELETE em /starwars/movies/<id_film>. Ex: http://localhost:8080/starwars/movies/1
 
Como Executar o projeto com Docker Compose
-----

Na pasta do projeto execute os seguintes comandos:

docker-compose build

docker-compose up -d

Como gerar o jar do projeto
-----

Para gerar o jar basta rodar o comando na raiz do projeto:

`gradlew clean bootJar`

E obter o arquivo jar gerado em:

`/build/libs/movies-0.0.1-SNAPSHOT.jar`

Ferramentas utilizadas no desenvolvimento
-----

 * Docker - https://www.docker.com/
 * Gradle - https://gradle.org/
 * Spring Boot - https://start.spring.io/
 * Redis - https://redis.io/
 * Eclipse Mars - https://www.eclipse.org/mars/