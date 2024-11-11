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
