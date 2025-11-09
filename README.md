# GeoProtect: Risk Zone Simulation Platform

## Overview

**GeoProtect** is a full-stack system developed as a university project. The platform allows for the visualization of geographic risk zones on an interactive map and real-time location simulation. The application consists of a RESTful backend for data management and a reactive frontend for user interaction.

The system is secured by JWT-based authentication, ensuring that only authorized users can access the data.

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

## Prerequisites

To run this project, you will need to have the following installed on your machine:
- Java JDK 21 or higher.
- Apache Maven 3.8 or higher.
- Node.js 18 or higher (with NPM).
- Docker and Docker Compose (for the database).

---

## Environment Setup

### 1. Database
The PostgreSQL database with the PostGIS extension is managed by Docker. To start it, run the following command in the project root:
```bash
docker-compose up -d
```

### 2. Environment Variables (Backend)
The backend application uses environment variables for security settings. You can set them in your operating system or directly in your IDE (IntelliJ).

| Variable | Description | Default Value (Development) |
| :--- | :--- | :--- |
| `JWT_SECRET` | Secret key for signing JWTs. | `my-secret-key-for-dev-env-only-change-in-prod` |
| `ADMIN_EMAIL` | Email for the default admin user. | `admin@geoprotect.com` |
| `ADMIN_PASSWORD` | Password for the default admin user. | `password` |

---

## Authors

*   Jordan Estevan Rodrigues Dos Santos
*   Gustavo Lopes Silva
*   Vilson da Silva Juvencio Junior
