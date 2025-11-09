# GeoProtect API

## Overview

**GeoProtect** is a RESTful API developed as part of a university project. Its main function is to manage and query geographic "Risk Zones." The application allows for the registration of polygonal areas representing risk zones and provides a service to check if a given coordinate (latitude and longitude) is located within any of these zones.

## Technologies

- **Backend:**
  - Java 21
  - Spring Boot 3.5.6
  - Spring Web (for the REST API)
  - Spring Data JPA (for data persistence)
  - Hibernate Spatial (for handling geospatial data)
- **Database:**
  - PostgreSQL with the PostGIS extension
- **Infrastructure:**
  - Docker and Docker Compose
- **Build:**
  - Maven

## Group Members

*   Jordan Estevan Rodrigues Dos Santos - 125221103657
*   Gustavo Lopes Silva - 12522212116
*   Vilson da Silva Juvencio Junior - 12522218378
