#!/bin/bash
echo "⏳ Construindo o projeto..."
./mvnw clean package -DskipTests

echo "🐳 Subindo containers com Docker Compose..."
docker-compose up --build
