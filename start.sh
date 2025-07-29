echo "🧹 Removendo containers existentes..."
docker-compose down --remove-orphans

echo "⏳ Gerando build da aplicação..."
./mvnw clean package

if [ $? -ne 0 ]; then
  echo "❌ Erro ao gerar o build. Abortando."
  exit 1
fi

echo "🐳 Subindo containers com Docker Compose..."
docker-compose up --build
