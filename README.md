# 🗳️ Coopere Voto – API de Votação Cooperativa

Solução RESTful para gerenciamento de sessões de votação em cooperativas, desenvolvida como parte de um desafio técnico.  
A aplicação permite o cadastro de pautas, abertura de sessões de votação com tempo configurável, recebimento de votos únicos por associado e apuração do resultado.


## 🚀 Como Executar o Projeto

> É necessário ter o **Docker** e o **Docker Compose** instalados, além disso o docker descktop deve está rodando para a inicialização dos containers.

1. Clone o repositório:

```bash
git clone https://github.com/saulocapistrano/coopere-voto.git
cd coopere-voto
````

2. Execute o script:

```bash
chmod +x start.sh
./start.sh
```

3. Acesse a documentação Swagger:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)



## ✅ Funcionalidades Implementadas

### 📌 Pautas

* `POST /api/v1/pautas` – Cadastrar nova pauta
* `GET /api/v1/pautas` – Listar pautas
* `GET /api/v1/pautas/{id}` – Buscar pauta por ID
* `PUT /api/v1/pautas/{id}` – Atualizar pauta
* `DELETE /api/v1/pautas/{id}` – Excluir pauta

### 🗳️ Sessões de Votação

* `POST /api/v1/pautas/{id}/sessao` – Abrir uma sessão (tempo default = 1 minuto)
* `GET /api/v1/sessoes` – Listar sessões
* `GET /api/v1/sessoes/{id}` – Detalhar uma sessão

### 🧾 Votos

* `POST /api/v1/votos` – Registrar voto único por pauta (por CPF)

Exemplo de payload:

```json
{
  "pautaId": 1,
  "cpf": "12345678900",
  "voto": "SIM" // ou "NAO"
}
```

> O campo `"voto"` aceita os valores `"SIM"` ou `"NAO"` (em caixa alta).

### 📊 Resultado da Votação

* `GET /api/v1/pautas/{pautaId}/resultado` – Obter o resultado consolidado da pauta



## 🧪 Testes Automatizados

Execute os testes com:

```bash
mvn test
```

Para visualizar a cobertura com Jacoco:

```bash
open target/site/jacoco/index.html
```


## 🐳 Docker & Infraestrutura

O projeto utiliza Docker para orquestrar os seguintes serviços:

* PostgreSQL 15 (`coopere-postgres`)
* Kafka (Bitnami image – opcional para escalabilidade futura)
* Aplicação Java 17 Spring Boot (`coopere-api`)

Comando para subir os serviços:

```bash
docker-compose up --build
```


## 🧱 Stack Utilizada

* Java 17
* Spring Boot 3
* Spring Data JPA
* PostgreSQL (produção)
* H2 Database (testes)
* Liquibase (controle de migrations)
* MapStruct (DTO <-> entidade)
* Swagger/OpenAPI 3 (documentação)
* Docker + Docker Compose
* JUnit 5 + Mockito + Jacoco


## 🏗️ Estrutura do Projeto

* `controller` – Camada REST
* `service` – Regras de negócio
* `usecase` – Casos de uso encapsulados
* `repository` – Integração com JPA
* `dto` – Entrada e saída da API
* `entity` – Mapeamento de tabelas


## Decisões de Arquitetura e Tecnologias

Durante o desenvolvimento da API Coopere Voto, foram feitas escolhas técnicas estratégicas com foco em **padronização**, **facilidade de deploy**, **testabilidade** e **boas práticas modernas** de desenvolvimento de software:

* **Docker**: Simula um ambiente em nuvem localmente, garantindo que qualquer desenvolvedor ou avaliador possa executar a aplicação com consistência, independentemente da máquina.

* **Java 17**: Versão LTS com melhorias significativas na linguagem e suporte estendido. Alinha-se às exigências de produção modernas e oferece maior performance.

* **Spring Boot 3**: Framework robusto e amplamente adotado para desenvolvimento de microsserviços e APIs REST, com suporte à nova stack Jakarta EE.

* **PostgreSQL**: Banco de dados relacional confiável e poderoso, ideal para sistemas transacionais como o controle de votos.

* **Kafka** *(previsto, não implementado)*: Considerado para cenários futuros de escalabilidade, como eventos de auditoria, votação em tempo real e integração com outros serviços.

* **H2 Database**: Usado apenas nos testes, é leve e não requer configuração, permitindo execução rápida e isolada do ambiente.

* **Liquibase**: Garante controle e versionamento de todas as mudanças no schema do banco de dados. Ideal para ambientes CI/CD e controle de versões.

* **Lombok**: Reduz a verbosidade do Java, eliminando a necessidade de escrever manualmente getters, setters, builders e construtores.

* **Swagger/OpenAPI**: Documentação automática e interativa, facilitando o uso e o entendimento da API por terceiros.

* **MapStruct**: Ferramenta de mapeamento de DTOs com alta performance, que mantém as camadas desacopladas sem sacrificar desempenho.

* **JUnit 5 + Mockito**: Frameworks de testes modernos utilizados para validar as regras de negócio com testes unitários e mocks.

* **Jacoco**: Ferramenta de cobertura de testes utilizada para garantir que os testes estão cobrindo os fluxos críticos da aplicação.

* **Redis**: A aplicação utiliza Redis como mecanismo de cache para melhorar a performance em chamadas repetitivas de verificação de CPF.


## Considerações Finais e Melhorias Futuras

- A estrutura atual já está preparada para adição de eventos Kafka (ex: notificar voto registrado), embora não tenha sido utilizado nesta entrega.
- Poderia ser implementado um mecanismo de auditoria (`created_at`, `updated_at`) com Spring Data Auditing.
- Os modelos de domínio foram mapeados diretamente com JPA para facilitar a entrega. Em um projeto mais robusto, adotaria-se uma separação total do domínio puro com mapeamento via adapters e mappers, seguindo os princípios do DDD e da arquitetura hexagonal.



## 👨‍💻 Autor

**Saulo José Neco Capistrano**

[🔗 LinkedIn](https://www.linkedin.com/in/saulocapistrano-software-architect) | [🔗 GitHub](https://github.com/saulocapistrano)


## 📃 Licença

Este projeto foi desenvolvido exclusivamente para fins de avaliação técnica e não possui fins comerciais.

