# GeoProtect

## Overview

**GeoProtect** is a **RESTful API** for managing and querying geographic risk zones. Developed as a university project, its core is a Spring Boot backend designed for geospatial data processing.

A separate **React frontend** application is included as a **demonstration and testing interface**. This frontend allows for the visualization of geographic risk zones on an interactive map and real-time location simulation.

The entire system is secured by JWT-based authentication, ensuring that only authorized users can access the API's functionalities.

---

## Key Features

- **Backend (RESTful API):**
  - User authentication with JSON Web Tokens (JWT).
  - Secure endpoints for listing and checking risk zones.
  - Integration with a geospatial database for high-performance queries.
  - Initial data loading from a GeoJSON file.

- **Frontend (Simulation Dashboard):**
  - Secure login screen.
  - Interactive map that displays risk zones loaded from the backend.
  - Feature to add a marker by clicking on the map.
  - Real-time verification to check if the marker's location is within a risk zone (with feedback in the backend console).
  - Automatic logout in case of an expired or invalid token.

---

## Tech Stack

| Category | Technology |
| :--- | :--- |
| **Backend** | Java 21, Spring Boot, Spring Web, Spring Security, Spring Data JPA |
| **Database** | PostgreSQL + PostGIS |
| **Geospatial Data** | Hibernate Spatial, JTS (Java Topology Suite) |
| **Frontend** | React, TypeScript, Vite |
| **Map** | Leaflet, React-Leaflet |
| **Build & Dependencies** | Maven (Backend), NPM (Frontend) |
| **Authentication** | JSON Web Token (JWT) |

---

## Authors

*   Jordan Estevan Rodrigues Dos Santos
*   Gustavo Lopes Silva
*   Vilson da Silva Juvencio Junior
