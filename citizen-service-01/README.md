# Citizen Service - Vətəndaş Servisi

Bu mikroservis Azərbaycan Respublikasının vətəndaşları haqqında əsas demoqrafik məlumatları idarə edir.

## Texnologiyalar

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Bean Validation**

## Xüsusiyyətlər

### Əsas Funksionallıq
- ✅ Vətəndaş əlavə etmək
- ✅ Vətəndaş məlumatlarını oxumaq (ID və ya FIN kod ilə)
- ✅ Vətəndaş məlumatlarını yeniləmək
- ✅ Vətəndaş silmək
- ✅ Vətəndaş axtarışı (çoxlu kriterii ilə)
- ✅ Səhifələmə (Pagination)

### Texniki Xüsusiyyətlər
- ✅ UUID Primary Key
- ✅ JSONB məlumat növü (contact_info, address)
- ✅ Avtomatik Timestamp-lər
- ✅ Database İndekslər
- ✅ Validasiya
- ✅ Exception Handling
- ✅ Unit Testlər
- ✅ Docker Dəstəyi

## API Endpoint-ləri

### Vətəndaş Yaratmaq
```
POST /api/citizens
Content-Type: application/json

{
  "finCode": "ABC1234",
  "firstName": "Əli",
  "lastName": "Məmmədov",
  "patronymic": "Həsən oğlu",
  "birthDate": "1985-05-15",
  "gender": "MALE",
  "contactInfo": {
    "phones": ["+994501234567"],
    "email": "ali.memmedov@email.com"
  },
  "address": {
    "country": "Azərbaycan",
    "city": "Bakı",
    "street": "Nizami küçəsi 123"
  },
  "isActive": true
}
```

### Vətəndaş Oxumaq
```
GET /api/citizens/{id}
GET /api/citizens/fin/{finCode}
```

### Vətəndaş Yeniləmək
```
PUT /api/citizens/{id}
Content-Type: application/json

{
  "firstName": "Yeni Ad",
  "contactInfo": {
    "phones": ["+994501234567", "+994552345678"],
    "email": "yeni.email@example.com"
  }
}
```

### Vətəndaş Silmək
```
DELETE /api/citizens/{id}
```

### Bütün Vətəndaşları Siyahıya Almaq
```
GET /api/citizens?page=0&size=20&sortBy=lastName&sortDir=asc
```

### Vətəndaş Axtarışı
```
GET /api/citizens/search?firstName=Əli&lastName=Məmmədov&isActive=true&page=0&size=20
```

## Konfiqurasiya

### application.yml
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/citizen_db
    username: postgres
    password: password
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
```

## İşə Salınması

### 1. Docker ilə
```bash
docker-compose up -d
```

### 2. Lokal olaraq
```bash
# PostgreSQL-i işə salın
docker run --name postgres -e POSTGRES_DB=citizen_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:15

# Aplikasiyanı build edin
mvn clean package

# İşə salın
java -jar target/citizen-service-01-1.0.0.jar
```

## Test

```bash
# Unit testləri işə salın
mvn test

# Integration testləri
mvn verify
```

## Validasiya Qaydaları

- **FIN Kod**: 7 simvoldan ibarət, unikal
- **Ad/Soyad**: Maksimum 100 simvol, boş ola bilməz
- **Ata adı**: Maksimum 100 simvol, boş ola bilər
- **Doğum tarixi**: Boş ola bilməz
- **Cins**: MALE, FEMALE, UNKNOWN

## Database Schema

```sql
CREATE TABLE citizens (
    id UUID PRIMARY KEY,
    fin_code VARCHAR(7) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100),
    birth_date DATE NOT NULL,
    gender VARCHAR(10),
    contact_info JSONB,
    address JSONB,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_citizen_fin_code ON citizens(fin_code);
CREATE INDEX idx_citizen_last_name ON citizens(last_name);
```

## Performans Optimizasiyası

- Database indekslər fin_code və last_name üçün
- JSONB istifadəsi JSON məlumatlar üçün
- Səhifələmə (Pagination) support
- Connection pooling
- Lazy loading

## Təhlükəsizlik

- Input validasiya
- SQL Injection protection (JPA/Hibernate)
- Error handling
- Logging

## Gələcək İnkişaf

- [ ] Caching (Redis)
- [ ] Metrics (Micrometer)
- [ ] Security (JWT/OAuth2)
- [ ] API Documentation (OpenAPI/Swagger)
- [ ] Event sourcing
- [ ] Integration testlər

## Dəstək

Suallarınız üçün development komandasına müraciət edin.