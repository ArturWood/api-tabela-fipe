<img src="https://github.com/ArturWood/games-list/assets/111249818/434c56b3-9dc9-412a-91f7-2edc3f389c14" width=300px alt="Java Logo" />
<img src="https://github.com/ArturWood/games-list/assets/111249818/d8539fd2-938e-4126-b3d4-7236a1ffdbef" width=500px alt="SpringFramework Logo" />

# FIPE API - Aplicação back-end Java 17 com Spring Framework para fornecer informações sobre veículos com base na Tabela FIPE

Esta é uma aplicação Java com Spring Framework que permite realizar consultas com base na Tabela FIPE, recuperando informações como valor, modelo, ano sobre veículos, como carros, motos e caminhões.

## Pré-requisitos

- Java Development Kit (JDK) versão 17
- IDE Java (como Eclipse ou IntelliJ) ou um editor de texto para escrever o código
- Conexão à internet para consumir a API Via CEP
- Postman (opcional, para testar os endpoints localmente)

## Configuração

1. Clone este repositório em sua máquina local:

```bash
# clonar repositório
git clone https://github.com/ArturWood/api-tabela-fipe.git

# entrar na pasta do projeto
cd api-fipe

# executar o projeto
./mvnw spring-boot:run
```

## Endpoints

A aplicação expõe o seguintes endpoints:

- `GET /tabela-fipe/{tipo}`: Retorna um DTO com obter uma lista de marcas disponíveis para um determinado tipo de veículo (carros, motos ou caminhões).
- `GET /tabela-fipe/{tipo}/{marcaId}`: Retorna um DTO com uma lista de modelos disponíveis para uma marca específica de um determinado tipo de veículo.
- `GET /tabela-fipe/{tipo}/{marcaId}/{modeloId}`: Retorna um DTO com uma lista de informações do modelo específico.

## Estrutura do Projeto

O projeto possui a seguinte estrutura de arquivos:

```bash
└───src
    └───main/java
    └───br
        └───com
            └───apifipe
                ├───infra
                ├───domain
                ├───controller
                └───service
└── .gitignore
└── api-tabela-fipe.postman_collection.json
└── pom.xml
```

- O pacote `controller` contém a classe `FipeController` que define os endpoints da API.
- O pacote `service` contém a classe `FipeService` que realiza a chamada à API FIPE para obter os dados.
- O pacote `domain` contém os records que representam os dados que trafegam pela API.
- O pacote `infra` contém a classe `ExceptionEntityHandler` responsavel por lidar com as exceptions lançadas pelo controller ou service.
- O arquivo `.gitignore` especifica os arquivos e pastas que devem ser ignorados pelo controle de versão do Git.
- O arquivo `api-tabela-fipe.postman_collection.json` é uma coleção do postman para consultas e exemlos dos endpoints da API.
- O arquivo `pom.xml` para download das dependencias necessarias para o projeto usando maven.

## Documentação

No projeto foi adicionado a dependência `springdoc` para facilitar a documentação e visualização dos endpoints (acessar rodando localmente);<br>
Além das dependências para desenvolvimento com Spring Framework - Web, Bean;<br>
Links para uso e documentação:

https://deividfortuna.github.io/fipe/<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/web.html<br>
http://localhost:8080/swagger-ui/index.html

![Swagger](https://github.com/ArturWood/api-tabela-fipe/assets/111249818/5d06efe7-5b6d-4324-9d4c-b54ce190fc66)

![Postman](https://github.com/ArturWood/api-tabela-fipe/assets/111249818/3f7a3275-46d4-4330-be33-0f6b123d4525)
