# Étape 1 : Utiliser une image JDK pour la compilation
FROM docker.io/gradle:8.4-jdk17 AS build
WORKDIR /app

# Copier les fichiers du projet
COPY . .

# Construire l'application
RUN gradle clean build -x test

# Étape 2 : Utiliser une image JDK allégée pour exécuter l'application
FROM docker.io/openjdk:17-jdk-slim

WORKDIR /app

# Copier le JAR généré depuis l’étape de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exposer le port de l'application (remplace par le bon port si nécessaire)
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "app.jar"]
