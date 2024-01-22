# RabbitMQ: Descomplicando o Processo de Mensageria

##### **Para iniciar meu estudo sobre o RabbitMQ, busquei compreender o que é e em quais contextos aplicá-lo. O RabbitMQ é um sistema de mensageria de código aberto, criado para facilitar a comunicação entre diferentes partes de um sistema. Surgiu com a proposta de evitar perda de dados (mensagens) quando um sistema se encontra indisponível, por meio de sua implementação em filas (queues).**

#### **Aprofundando no mundo de mensageria do RabbitMQ**
##### **⦁ PROBLEMA**
##### **Imagine uma empresa de grande porte no setor financeiro que possui um sistema único(não distribuído, no qual uma parte depende da outra para funcionar) e, em determinado dia, esse sistema fica indisponível por algum tempo, resultando na perda de inúmeros dados. Essa situação pode acarretar sérios problemas posteriormente.**
##### **⦁ EXPLICANDO O USO DO RABBITMQ**
##### **Diante desse cenário, a implementação do RabbitMQ proporciona a segurança da persistência dos dados. Mesmo quando o sistema fica indisponível, o RabbitMQ recebe e armazena os dados em uma fila, garantindo que as mensagens sejam processadas assim que o sistema esteja novamente disponível. Essa abordagem assegura a integridade dos dados, prevenindo potenciais perdas e contribuindo para a eficiência do sistema como um todo.**
===================================================================================
##### **FLUXO DO PROCESSO DE ENVIO E RECEBIMENTO DE UMA MENSAGEM**
![fluxo](https://enzochang.com/rabbitmq-introduction/rabbitmq_architecture.png)
O modelo mais amplamente utilizado para implementar o RabbitMQ é o AMQP 0-9-1 (Advanced Message Queuing Protocol). O fluxo desse modelo se inicia com a publicação das mensagens, que são encaminhadas para as exchanges. Estas, por sua vez, distribuem as mensagens para as filas, as quais podem ter regras de ligação específicas ou não. Essas filas estão conectadas aos consumidores, que processam as mensagens de acordo com suas necessidades.
##### **DICIONÁRIO**
* Produtor (Producer): Encarregado de criar o conteúdo ou mensagem.
* Exchange (Exchanger): Comparável a uma agência dos correios, pois é responsável por distribuir (encaminhar) as mensagens para as filas.
* Rotas (Routes): São os caminhos que conectam as exchanges às queues. Essas rotas podem ser específicas, com identificadores únicos, ou livres.
* Fila (Queue): Também conhecida como fila, é responsável por entregar as mensagens aos consumidores ou armazená-las antes do processamento.
* Consumidor (Consumer): Encarregado de processar (consumir) as mensagens.
================================================================================
##### **TIPOS DE EXCHANGE**
* Direct: Comporta-se ao atribuir ou encaminhar a mensagem diretamente ao consumidor que possui a mesma rota das filas associadas a esse exchange.
![image](https://github.com/GabriellaFerr00/rabbitmq-project/assets/86236510/53902edd-7667-4ba2-8551-ec076bdbcdd6)
* Fanout: É a exchange mais simples e amplamente utilizada, pois envia a mensagem para todas as filas conectadas a ela, sem a necessidade de uma chave ou rota específica.
![image](https://github.com/GabriellaFerr00/rabbitmq-project/assets/86236510/0d77c23c-40d4-48db-8e46-529002b63ddb)
* Topic: Similar ao comportamento da Direct, mas as mensagens são encaminhadas com base em rotas definidas, tanto na mensagem quanto na associação da fila ao exchange. Diferentemente da Direct, a exchange tipo Topic tem uma característica padrão de receber cópias de todas as mensagens, independentemente da rota, tornando-a mais generalista.
![image](https://github.com/GabriellaFerr00/rabbitmq-project/assets/86236510/9e33dfee-76d2-42a7-a5ab-d01de1be52a1)
* Headers: Este é o tipo menos utilizado de exchange. Seu comportamento difere dos outros tipos, pois não utiliza rotas (chaves) para vincular mensagens a filas específicas; em vez disso, as mensagens são enviadas utilizando informações no cabeçalho.
================================================================================
##### **PROPRIEDADES DAS QUEUES NO MODELO AMQP 0-9-1**
* Nome
* Durável (a fila sobreviverá à reinicialização do corretor)
* Exclusivo (usado por apenas uma conexão e a fila será excluída quando essa conexão for fechada)
* Exclusão automática (a fila que teve pelo menos um consumidor é excluída quando o último consumidor cancela a assinatura)
* Argumentos (opcional; usados ​​por plug-ins e recursos específicos do corretor, como TTL de mensagem, limite de comprimento de fila, etc.)
================================================================================
#### **PROJETO**
##### **Versões usadas**
* Java: 17
* Spring Boot: 3.2.1
* Docker: 3.2
* Tipo de exchange: Direct
================================================================================
#### **DEPENDÊNCIAS POR ARQUIVO**
#### **producer-service E consumer-service**
DEPENDÊNCIAS              | REFERÊNCIAS
------------------------- | -----------
SPRING BOOT STATED AMQP   | [SPRING AMQP](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp)
SPRING BOOT STARTED WEB   | [SPRING WEB](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
COMMONS                   | .[ARQUIVO COMMONS](https://github.com/GabriellaFerr00/rabbitmq-project/tree/main/commons/commons)
LOMBOK                    | [LOMBOK](https://mvnrepository.com/artifact/org.projectlombok/lombok)

================================================================================
#### **Requisitos para rodas o projeto**
* Ter instalado na sua máquina o JDK 17: [Dowload JDK](https://www.oracle.com/br/java/technologies/downloads/#java17).
* IDE da sua preferência para rodar o código, no meu caso usei o Intellij: [Dowload Intellij IDEA](https://pages.github.com/](https://www.jetbrains.com/idea/download/?section=windows)https://www.jetbrains.com/idea/download/?section=windows).
* Software para consumir as requisições HTTP, no meu caso usei o postman: [Dowload Postman](https://www.postman.com/downloads/).
* Docker desktop para subir o container com a imagem do projeto: [Dowload Docker](https://www.docker.com/products/docker-desktop/).
* Ferramenta de versionamento, usei o git: [Dowload Git](https://git-scm.com/)
================================================================================
#### **Para rodar o projeto localmente, siga este passo a passo:**
* Clone o repositório do projeto para a sua máquina: [Clone HTTPS] https://github.com/GabriellaFerr00/rabbitmq-project.git.
* Abra esse projeto na IDE que você preferir(No intellij ele automaticamente baixa as dependências e configurações necessárias para o projeto rodar)
##### **OBS: Você vai abrir em uma aba o arquivo producer-service e em outra o consumer-service, pois, são projetos com finalidades diferentes, dessa forma o producer vai produzir a mensagem e o consumer consumir a mesma.**
* Abrir o aplicativo do docker desktop, para conseguir subir o container
* Navege pelas pastas até chegar na pasta do projeto e pelo CMD ou GIT escreva o comando "docker-compose up" para subir o container
* Rode os seus projetos simultaneamente, com o botão direito do mouse em cima do ProducerServiceApplication e ConsumerServiceApplication clique em RUN ou em Ctrl+Shift+F10
* Abra o postman e crie uma nova requisição, defina como post e execute o endpoint (http://localhost:8080/products), e no corpo da requisição selecione BODY no formato JSON e preencha os campos id, name e price e aperte send. Observe no console do consumer e receba esses dados, se receber significa que o seu projeto está funcionando perfeitamente.
