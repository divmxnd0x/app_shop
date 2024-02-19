# Application for e-commerce shop
## Used technologies
- Nuxt 3
- Spring framework
- MongoDB
- Docker
- Python
# How to run application
## Database
required database is MongoDB.
If You don't want to install database locally, You can use docker compose 
file in DB directory
\
After DB installation, You can optionally run python script (DB/mongoImporter.py)
for installing sample data and images to database
## Backend
In backend directory:
```bash
./gradlew clean bootRun
```

## Frontend
In frontend directory:
```bash
npm install
```
```bash
npm run build
```
```bash
node ./.output/server/index.mjs
```