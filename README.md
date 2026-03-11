# Event-Driven-Order-System
Plataforma de microserviços orientada a eventos construída com Spring Boot, Spring Security (JWT), Apache Kafka e PostgreSQL.

O projeto demonstra uma arquitetura moderna baseada em Event Driven Architecture e Microservices, onde serviços independentes se comunicam através de mensageria assíncrona.

Este projeto foi criado para estudo e demonstração de conceitos avançados de backend utilizados em sistemas distribuídos.

## Arquitetura do Sistema

A aplicação é composta por dois microserviços independentes que se comunicam através do Kafka.
```
                CLIENT
          (Web / Mobile App)
                    │
                    │ HTTP
                    ▼
              API GATEWAY
                    │
        ┌───────────┴───────────┐
        ▼                       ▼
    USER SERVICE           ORDER SERVICE
(Auth + User Data)       (Order Manager)
      │                        │
      ▼                        ▼
PostgreSQL DB           PostgreSQL DB
      │
      │
      ▼
APACHE KAFKA
(Event Streaming Platform)
      │
      ▼
USER SERVICE (Consumer)
```

## Arquitetura Utilizada
O sistema utiliza dois padrões arquiteturais principais.

### Microservices Architecture

Cada serviço possui:

- banco de dados próprio
- responsabilidades isoladas
- deploy independente

### Event Driven Architecture
A comunicação entre serviços acontece através de eventos publicados no Kafka.

Exemplo de fluxo:
```
Order Service cria pedido
           │
           ▼
Evento publicado no Kafka
           │
           ▼
User Service consome evento
           │
           ▼
Atualiza estatísticas do usuário
```

## Tecnologias Utilizadas
### Backend
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/-SPRING-000000?style=for-the-badge&logo=spring&logoColor=%6DB33F)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
### Mensageria
![APACHE KAFKA](https://img.shields.io/badge/-APACHE%20KAFKA-3670A0?style=for-the-badge&logo=apache-kafka&logoColor=%ffdd54)
### Banco de Dados
![Postgresql](https://img.shields.io/badge/-POSTGRESQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=%23FFFFFF)

## Microserviços

### 1. User Service

Responsável por:
   - cadastro de usuários
   - autenticação
   - geração de JWT
   - estatísticas de usuário
   - consumo de eventos de pedidos
   
Funcionalidades
   - registro de usuário 
   - login com JWT 
   - consulta de dados do usuário 
   - consumo de eventos Kafka
   
Banco de Dados

Tabela de usuários:
```
- id 
- name 
- email 
- password 
- created_at
```

Tabela de estatísticas:
```
- id 
- user_id 
- total_orders 
- last_order_date
```
   
### 2. Order Service

Responsável por:

- gerenciamento de pedidos
- publicação de eventos no Kafka

Funcionalidades

- criação de pedidos
- consulta de pedidos
- publicação de eventos

Banco de Dados

Tabela de pedidos:
```
- id
- user_id
- product
- price
- status
- created_at
```

## Comunicação entre Serviços
A comunicação ocorre através do Kafka utilizando eventos de domínio.
### Evento Publicado
order-created

Payload do evento:
```
{
    "orderId": 1,
    "userId": 2,
    "price": 199.90,
    "timestamp": "2026-01-10T10:22:00"
}
```

Fluxo do evento:
```
Order Service
     │
     │ Producer
     ▼
Kafka Topic
"order-created"
     │
     │ Consumer
     ▼
User Service
```
## Autenticação
O sistema utiliza JWT (JSON Web Token).

Fluxo de autenticação:
```
Client
  │
  │ POST /auth/login
  ▼
User Service
  │
  | valida credenciais
  ▼
JWT Token gerado
  │
  ▼
Client usa token nas requisições
```
Header utilizado:
```
Authorization: Bearer TOKEN
```

## Endpoints da API
### Auth / User Service
Registrar usuário:
```
POST /auth/register
```
Login:
```
POST /auth/login
```
Obter usuário autenticado:
```
GET /users/me
```
### Order Service
Criar pedido:
```
POST /orders
```
Listar pedidos do usuário:
```
GET /orders/my
```
## Estrutura do Projeto

```
springboot-event-driven-order-platform
│
├── user-service
│ ├── controller
│ ├── service
│ ├── repository
│ ├── entity
│ ├── security
│ ├── DTO
│ ├── exception
│ └── kafka
│
├── order-service 
│ ├── controller
│ ├── service 
│ ├── repository 
│ ├── entity
│ ├── DTO
│ ├── exception
│ └── kafka
│ 
└── architecture.md
```
## Objetivo do Projeto
Este projeto tem como objetivo demonstrar na prática:
- construção de APIs REST seguras 
- arquitetura de microserviços 
- comunicação assíncrona com Kafka 
- isolamento de banco de dados 
- arquitetura orientada a eventos