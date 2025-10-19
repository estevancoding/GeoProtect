# GeoProtect API

## Visão Geral

O **GeoProtect** é uma API RESTful desenvolvida como parte de um projeto universitário. Sua principal função é gerenciar e consultar "Zonas de Risco" geográficas. A aplicação permite o cadastro de áreas poligonais que representam zonas de risco e oferece um serviço para verificar se uma determinada coordenada (latitude e longitude) está localizada dentro de alguma dessas zonas.

Este projeto visa aplicar conceitos modernos de desenvolvimento de software, desde a criação de uma API robusta até a preparação para implantação em contêineres e na nuvem.

## Tecnologias Utilizadas

- **Backend:**
  - Java 21
  - Spring Boot 3.5.6
  - Spring Web (para a API REST)
  - Spring Data JPA (para persistência de dados)
  - Hibernate Spatial (para manipulação de dados geoespaciais)
- **Banco de Dados:**
  - PostgreSQL com a extensão PostGIS
- **Testes:**
  - JUnit 5 e Mockito (a serem implementados)
- **Infraestrutura:**
  - Docker e Docker Compose
- **Build:**
  - Maven

## Integrantes do Grupo

Jordan Estevan Rodrigues Dos Santos - 125221103657
Gustavo Lopes Silva - 12522212116
Vilson da Silva Juvencio Junior - 12522218378
