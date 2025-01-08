# Blog Sphere

## Description

Blog Sphere est une plateforme innovante pour les blogueurs, visant à simplifier et rendre le blogging plus accessible pour les auteurs et les lecteurs .

## Structure du Projet

Le projet est divisé en deux parties principales :

- **Backend** : [`backend`](backend) - une application Spring Boot utilisant Maven.
- **Frontend** : [`frontend`](frontend) - une application Angular.

## Prérequis

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Node.js](https://nodejs.org/)
- [Angular CLI](https://angular.io/cli)

## Installation

### Backend

1. Naviguez dans le dossier backend :

    ```sh
    cd backend
    ```

2. Construisez le projet avec Maven :

    ```sh
    mvn clean install
    ```

### Frontend

1. Naviguez dans le dossier frontend :

    ```sh
    cd frontend
    ```

2. Installez les dépendances :

    ```sh
    npm install
    ```

## Utilisation

### Démarrer le Backend

1. Dans le dossier backend, lancez l'application :

    ```sh
    mvn spring-boot:run
    ```

### Démarrer le Frontend

1. Dans le dossier frontend, démarrez le serveur de développement :

    ```sh
    ng serve
    ```

2. Accédez à l'application via [http://localhost:4200](http://localhost:4200).
