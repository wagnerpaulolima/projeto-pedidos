### Pasta microservicos-gestao-pedidos
Esta pasta, tem um projeto Eureka para registrar os serviços, tem um projeto api-gateway para servir de balanceador de carga dos serviços, 
tem um projeto de pedido-service, para após se logar no apigatway, poder cadastrar um pedido com seus produtos, salvando no postgresql e no mongodb.
Tem um projeto de consulta-pedido-service, para uma vez logado no apigatway, poder consultar um pedido, consultando no mongodb e colocando em cache redis
para novas consultas futuras. 

### Descrição
Este projeto foi feito em Java 17 com Spring Boot, banco mongodb, postgresql, redis e autenticação em memória JWT com usuários fakes em memória.
Temos um Sistema A que pode ter permissão de cadastrar um pedido com sua lista de produtos para um endpoint. Neste ponto grava no postgresql e no mongodb
Temos outro usuário Sistema B que pode consultar um pedido, neste momento a consulta é feita inicialmente no mongo db e realizada um cache no redis para futuras consultas.
Desta forma, o sistema pode ter duas estratégias um para cadastro e outro para consulta.
Os bancos de dados podem ser rodados usando o docker-compose-db.

Uma outra estruta baseada em microserviços, envolvendo Serviço de descoberta de serviços como Eureka, Gateway como balanceador de carga e docker-compose com replicar para 2 serviços, sendo um serviço para cadastro e outro para consulta, estava sendo implementado,
mas devido problemas de rede, impactactam na conclusão desta abordagem em relação ao tempo para disponibilizar uma solução.

### Comandos para rodar o dockercompose para os bancos
docker-compose -f docker-compose-db.yml up -d

### Comandos para visualizar o redis
docker exec -it redis redis-cli -a "1234"
KEYS *

### Comando para visulzar o mongo na linha de comando com mongosh
mongosh "mongodb://rootmongo:1234@localhost:27017/pedidos"

db.pedidos.find().pretty()


### Comando para fazer login Sistema A
curl --location 'http://localhost:8080/auth/login' ^
--header 'Content-Type: application/json' ^
--header 'Cookie: JSESSIONID=1913725DCB11648E7378630083496D8D' ^
--data '{
"username": "sistemaA",
"password": "1234"
}'

## Comando para fazer login Sistema B
curl --location 'http://localhost:8080/auth/login' ^
--header 'Content-Type: application/json' ^
--header 'Cookie: JSESSIONID=1913725DCB11648E7378630083496D8D' ^
--data '{
"username": "sistemaB",
"password": "1234"
}'

### Comando para cadastrar
curl --location 'http://localhost:8080/api/cadastrar' ^
--header 'Content-Type: application/json' ^
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaXN0ZW1hQSIsInJvbGUiOiJDUkVBVEUiLCJpYXQiOjE3MzEzNDc1NzgsImV4cCI6MTczMTM4MzU3OH0.5dn-gde1pv5m2KVhOt6GwL3NplTc390OycBOCvJHKTU' \
--header 'Cookie: JSESSIONID=1913725DCB11648E7378630083496D8D' ^
--data '{
"status": "PENDENTE",
"produtos": [
{
"descricao": "Produto A",
"valor": 50.0
},
{
"descricao": "Produto B",
"valor": 30.0
}
]
}'

### Comando para consultar um pedido via CURL

curl --location "http://localhost:8080/api/consultar/12" ^
--header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaXN0ZW1hQiIsInJvbGUiOiJSRUFEIiwiaWF0IjoxNzMxMzUwNTU3LCJleHAiOjE3MzEzODY1NTd9.bzJNgl8lqct1xWt4fwN6aAnuDBG2506S4t_Z4PiYcgU" ^
--header "Cookie: JSESSIONID=CB3649F66051540C61A5A8B01438CF38"
