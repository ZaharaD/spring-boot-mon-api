# --- Stage 1: Build ---
# Utilise une image Maven avec Java 17 pour compiler le projet
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Définit le dossier de travail dans le conteneur
WORKDIR /app

# Copie le pom.xml pour télécharger les dépendances (optimisation)
COPY pom.xml .
# Copie le reste du code source
COPY src ./src

# Lance la compilation avec Maven (sans les tests pour aller plus vite)
RUN mvn clean package -DskipTests


# --- Stage 2: Run ---
# Utilise une image Java 17 plus légère pour exécuter l'application
FROM eclipse-temurin:17-jre-jammy

# Définit le dossier de travail
WORKDIR /app

# Copie le fichier JAR compilé depuis l'étape de build
COPY --from=builder /app/target/ordexa-backend-1.0.0.jar app.jar

# Expose le port 8080 (le port par défaut de Spring Boot)
EXPOSE 8080

# Définit la commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"] 