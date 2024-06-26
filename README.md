# Power Crédito

O projeto consiste em um sistema com Micro Serviços para simular a emissão de cartões de crédito possibilitando cadastrar cliente e visualizar suas informações e cartões, cadastrar e visualizar cartões e suas bandeiras, validar cartão de crédito para um determinado cliente com base na sua renda e solicitar emissão de um cartão para o cliente.

## Pré-requisitos

Certifique-se de ter as seguintes tecnologias instaladas antes de começar:

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/): Linguagem de programação para desenvolvimento de aplicações.
- [Maven](https://maven.apache.org/): Biblioteca para desenvolvimento de aplicações web.
- [Docker](https://www.docker.com/): Virtualizador de aplicações em containers.
- [RabbitMQ](https://www.rabbitmq.com/): Queue broker para comunicação assíncrona;

## Configuração do Ambiente de Desenvolvimento

1. Clone o repositório:

```bash
git clone link-do-repositório
```

2. Para executar o ambiente você precisa ter o Java Jdk configurado na sua máquina. 

O gerenciador de dependências utilizado foi o Maven, ao abrir o projeto na IDE certifique-se de que as demências foram carregadas corretamente.

3. Inicie o Rabbit MQ através de um conteiner do docker:
`docker pull rabbitmq:management`

Para executar:
`docker run -d --name meu-rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management`

4. Configure a fila que vai ser utilizada no Rabbit Mq:
Os serviços que estão se comunicando de forma assíncrona são o MsAvaliadorDeCredito e o MsCartoes.
Certifique-se de que as configurações do application.yml estejam de acordo com a queue do RabbitMQ.

4. Inicie os serviços:
Para iniciar os serviços siga a seguinte ordem:
- EurekaServer
- Os serviços de cliente, cartões e avaliador
- MSCloudGeteway

## Tecnologias Utilizadas

No Back-End projeto faz uso das seguintes tecnologias e ferramentas:

- [Spring boot](https://spring.io/): Estrutura Java de código aberto usada para programar aplicativos.
- [Java 21](https://www.oracle.com/br/java/technologies/downloads/): Linguagem de programação para desenvolvimento de aplicações.
- [Maven](https://maven.apache.org/): Biblioteca para desenvolvimento de aplicações web.
- [Docker](https://www.docker.com/): Virtualizador de aplicações em containers.
- [RabbitMQ](https://www.rabbitmq.com/): Queue broker para comunicação assíncrona;
- [Spring Cloud](https://spring.io/projects/spring-cloud): Estrutura Java de código aberto usada para programar aplicativos.
- [Eurka Server](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html): Service Discovery
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): Framework do Spring Data, para tornar a integração de aplicações Spring com a JPA (Java Persistence API)

## Autores

- [Willian Costa](https://github.com/costaowillian) - Desenvolvedor.
