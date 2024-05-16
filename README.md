## Requisitos de Uso:
- Possuir o **Docker** instalado na máquina;
- Possuir **git** instalado na máquina;
## Como Usar:
- Abra o terminal na pasta desejada e utilize o comando **git clone** para clonar este repositório;
- Entre na pasta do repositório clonado e crie um arquivo **.env** com base no arquivo **.env-exemple**;
- Execute o comando **docker compose up -d**, para para subir os containers;
- Se tudo ocorrer com sucesso, 06 containers devem estar ativos: **db-mongo, discovery-server, service-users, service-login, service-imc, api-gateway**;
- Para acessar os **recursos** do sistema utilize qualquer cliente HTTP com a seguinte URL: **localhost:8765/{RECURSO}**;
- Existem 03 **recursos** neste sistema: "**/users**", "**/login**" e "**/imc**";
- Os **recursos** tambem possuem uma documentação no swagger, para acessar utilize os seguintes endereço: **localhost:8765/docs-users/index.html**, **localhost:8765/docs-login/index.html** e **localhost:8765/docs-imc/index.html**;
- Os modelos de **body** para as requisições estão disponiveis na documentação de cada recurso nos endereços acima;
- Apenas os recursos "**/users/register**" e "**/login**" não necessitam de autenticação, para os demais é necessario passar um **token** no cabeçalho **Authorization**;
- Ao iniciar o sistema o banco de dados estará vazio, para cadastrar um usuario utilize o recurso "**/users/register**";<br><br>
Obs1: O token é gerado pelo recurso **"/login"**;<br>
Obs2: O token possui uma validade de **30 minutos**;<br>
Obs3: Para parar o sistema execute **docker compose down**.
## Tecnologias Utilizadas:
- Spring Boot 3.2.5;
- Java 21;
- Maven 4.0.0;
- Docker;
- JWT;
