# Student Management System

![image](https://github.com/user-attachments/assets/033d13ce-00d2-425c-a269-f9ef0e9cf9d7)


## Задание

Создать веб-приложение для управления данными о студентах. Приложение должно позволять:

1. Добавлять студента -(POST)
URL: http://localhost:8081/api/students
2. Удалять студента по уникальному номеру -(DELETE)
URL: http://localhost:8081/api/students/{uniqueNumber}
3. Выводить список студентов -(GET)
URL: http://localhost:8081/api/students
Приложение состоит из клиентской и серверной части. Серверная часть реализована на Java с использованием Spring Boot,
а клиентская часть - на JavaScript без использования сторонних библиотек и фреймворков. 

## Технологический стек

- **Серверная часть**: Java, Spring Boot
- **Клиентская часть**: HTML, CSS, JavaScript
- **База данных**: MySQL
- **API**: REST
  
## Запуск приложения

1. **Запуск серверной части**:
   - Перейдите в каталог с исходным кодом серверной части и соберите проект с помощью Maven:
     ```bash
     mvn clean package
     ```
   - Запустите серверное приложение:
     ```bash
     java -jar target/university-system-0.0.1-SNAPSHOT.jar
     ```

2. **Запуск клиентской части**:
   - Убедитесь, что серверное приложение запущено. Клиентская часть будет доступна по адресу `http://localhost:8081/index.html`.

## Примеры запросов
### Использование 
### Получение списка студентов (GET)

- **URL**: `http://localhost:8081/api/students`
- **Описание**: Возвращает список всех студентов.
- **Пример запроса в PowerShell**:
  ```powershell
  Invoke-WebRequest -Uri http://localhost:8081/api/students -Method GET
- **Добавление студента**:
  ```powershell
  $body = @{
      firstName = "Ivan"
      lastName = "Ivanov"
      middleName = "Ivanovich"
      dateOfBirth = "2000-01-01"
      studentGroup = "A1"
      uniqueNumber = "12345"
  } | ConvertTo-Json

 Invoke-WebRequest -Uri http://localhost:8081/api/students -Method POST -Body $body -ContentType "application/json"
