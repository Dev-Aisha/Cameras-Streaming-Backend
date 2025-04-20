#  Cameras Streaming Backend 🎥🎦

This is the backend service for the **Cameras Streaming Web Application**.  

---

## 🚀 Features

- Add and retrieve camera data from PostgreSQL
- Convert RTSP stream URLs to HLS using FFmpeg
- Expose RESTful APIs for camera list and HLS stream URL
- Dockerized: Run app and DB with Docker

---

##  Technologies Used 🛠

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Flyway** (for database migration)
- **FFmpeg** (to convert RTSP to HLS)
- **Gradle** (build tool)
- **Docker & Docker Compose**

---

## 📁 Project Structure

```
📦 cameras-backend/
├── src/
│   └── main/java/com/example/cameras/...
├── Dockerfile
├── docker-compose.yml
├── build.gradle
└── README.md
```

---

## 🐳 Run with Docker

### ✅ Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop) installed

### 🔄 Steps

1. Build the `.jar` file:

```bash
./gradlew build
```

2. Run using Docker Compose:

```bash
docker-compose up --build
```

This will:
- Start the Spring Boot app on `localhost:8080`
- Start PostgreSQL on internal port `5432`
- Create the DB and run Flyway migrations

---


##  How to Run the Project Locally 🚀

### 1. Prerequisites

- PostgreSQL is installed and running
- FFmpeg is installed locally
- Java 21 is set up

### 2. Create Database 🗄️

Open terminal and create the database:

```bash
createdb camerasdb
```

### 3. Configure `application.properties`

Create or update the file at:

```
src/main/resources/application.properties
```

With the following content:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/camerasdb
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

> Make sure Flyway will create your tables when the app starts.

### 4. Run the Application

```bash
./gradlew bootRun
```

---

## 📬 API Endpoints

| Method | Endpoint                | Description                        |
|--------|-------------------------|------------------------------------|
| GET    | `/api/cameras`          | Get all cameras                    |
| POST   | `/api/cameras`          | Add a new camera                   |
| GET    | `/api/cameras/streamUrl?id={id}` | Get converted HLS stream URL by camera ID |


---

## 🔗 Example API Endpoints

### ➤ Get HLS stream for a camera

```
GET /api/cameras/streamUrl?id=1
```

**Sample Response:**

```json
{
  "hlsUrl": "/hls/camera-1/stream.m3u8"
}
```

You can now use this URL in your frontend `<video>` element.

### ➤ POST Request (Add Camera)

```http
POST http://localhost:8080/api/cameras
Content-Type: application/json
```

**Sample Response:**

```json
{
  "name": "Main Gate",
  "latitude": 24.7136,
  "longitude": 46.6753,
  "streamUrl": "rtsp://rtspstream:k8r83xMlPTYZ4RzaLqGiH@zephyr.rtsp.stream/pattern"
}
```


**Screenshots:**
<img width="1470" alt="Screenshot 1446-10-19 at 11 32 27 AM" src="https://github.com/user-attachments/assets/bfc5393d-1726-4f55-ac06-f8c2bcc3c1ca" />

---

<img width="1470" alt="Screenshot 1446-10-19 at 11 32 34 AM" src="https://github.com/user-attachments/assets/ed3e3dd2-92b4-4ea5-aa98-a67c1f980b38" />



---

## 🎞 Streaming with FFmpeg

We use **FFmpeg** to convert `rtsp://` links into `.m3u8` HLS format.

Install FFmpeg:

```bash
brew install ffmpeg
```

When the API `/streamUrl` is called, FFmpeg generates `.m3u8` + `.ts` files  
and stores them in the following path:

```
src/main/resources/static/hls/
```
---

## 📂 HLS Output

- FFmpeg will create `.m3u8` and `.ts` files dynamically in the `/hls` folder.
- These files are publicly accessible via:  
  `http://localhost:8080/hls/camera-1/stream.m3u8`

---