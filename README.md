![Language](https://img.shields.io/badge/Language-Russian-blue.svg)
[![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-green)](https://spring.io/projects/spring-boot)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/0aadbf40a54d4b5a8e6ec031137f3066)](https://www.codacy.com/gh/skubatko/topjava26-project-Kubatko/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=skubatko/topjava26-project-Kubatko&amp;utm_campaign=Badge_Grade)

Курс TopJava26. Выпускной проект
===

## Техническое задание

Необходимо разработать REST API сервис с применением Hibernate/Spring/SpringMVC (лучше на Spring Boot). Приложение не
должно содержать Frontend части.

Основная задача: Сконструировать систему голосования для выбора места обеда.

Основные параметры системы:

- 2 типа пользователей: Администраторы и Пользователи
- Администраторы могут предлагать рестораны с указанием меню обеда на выбранный день
  - меню обычно содержит от 2 до 5 блюд с указанием названия и цены блюда
- Меню меняется ежедневно. Администраторы должны следить за обновлением
- Пользователи могут голосовать за ресторан, где они хотят пообедать
- У каждого пользователя есть ровно один голос
- Если пользователь голосует повторно:
  - Если голос отдан до 11:00, считается, что пользователь поменял своё мнение
  - Если голос отдан в 11:00 и позже, голос не засчитывается
- Каждый ресторан предоставляет своё меню ежедневно

В качестве итога предоставьте ссылку на репозиторий GitHub. Репозиторий должен содержать код, файл README.md с описанием
API и примером команд для его тестирования (лучше - ссылку на Swagger).

## Сборка и запуск

### Системные требования

Java 17

### Сборка приложения

Собирается из корня проекта командой:

```bash
./mvnw clean install
```

### Запуск

#### Локально

Локальный запуск осуществляется после сборки приложения из корня проекта командой:

```bash
java -jar target/topjava26-project.jar
```

#### Отладка

Запуск для отладки осуществляется с профилем dev, при помощи параметра командной строки `--spring.profiles.active=dev`:

```bash
java -jar target/topjava26-project.jar --spring.profiles.active=dev
```

#### Контейнер

Возможен запуск в контейнере. Используйте для этого `Dockerfile` из корня проекта.

---

## Работа с приложением

### Swagger

Для локального запуска доступен по ссылке [http://localhost:8080](http://localhost:8080).

Для локального запуска в режиме отладки доступен по ссылке [http://localhost:8081](http://localhost:8081).

### IDEA

Набор тестовых запросов находится в файле [tests.http](rest/tests.http).

### Примеры curl команд

#### Register User

```bash
curl -s -i -X 'POST' \
http://localhost:8080/api/user/v1/profiles \
-H 'Content-Type: application/json' \
-d '{
"name":"New User",
"email":"test@mail.ru",
"password":"test-password",
"enabled": true,
"roles": [{"role":"ROLE_USER"}]}'
```

---

#### User

##### Profiles

###### get Profile

```bash
curl -s http://localhost:8080/api/user/v1/profiles --user test@mail.ru:test-password
```

##### update Profile

```bash
curl -s -X 'PUT' \
http://localhost:8080/api/user/v1/profiles \
-H 'Content-Type: application/json' \
-d '{
"id": 4,
"name":"updatedUser",
"email":"test@mail.ru",
"password":"test-password",
"enabled": true,
"roles": [{"role":"ROLE_ADMIN"},{"role":"ROLE_USER"}]
}' \
--user test@mail.ru:test-password
```

##### delete Profile

```bash
curl -s -X DELETE http://localhost:8080/api/user/v1/profiles --user test@mail.ru:test-password
```

##### Votes

###### get Daily Votes

```bash
curl -s http://localhost:8080/api/user/v1/votes?day=2022-05-15 --user user@yandex.ru:password
```

###### vote

Success

```bash
curl -s -X 'POST' \
http://localhost:8080/api/user/v1/votes \
-H 'Content-Type: application/json' \
-d '{
"day":"2022-05-15",
"restaurantId": 2,
"votedAt":"10:00"
}' \
--user guest@mail.ru:guest
```

Too late

```bash
curl -s -X 'POST' \
http://localhost:8080/api/user/v1/votes \
-H 'Content-Type: application/json' \
-d '{
"day":"2022-05-15",
"restaurantId": 2,
"votedAt":"11:00"
}' \
--user guest@mail.ru:guest
```

---

#### Admin

##### Users

###### get All Users

```bash
curl -s http://localhost:8080/api/admin/v1/users --user admin@gmail.com:admin
```

###### get Users 1

```bash
curl -s http://localhost:8080/api/admin/v1/users/1 --user admin@gmail.com:admin
```

##### validate with Error

```bash
curl -s -X POST -d '{}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/v1/users --user admin@gmail.com:admin
```

```bash
curl -s -X POST -d '{"email":"guest@mail.ru"}' -H 'Content-Type: application/json' http://localhost:8080/api/admin/v1/users --user admin@gmail.com:admin
```

##### create User

```bash
curl -s -X 'POST' \
http://localhost:8080/api/admin/v1/users \
-H 'Content-Type: application/json' \
-d '{
"name":"New User",
"email":"new@gmail.ru",
"password":"new-password",
"enabled": true,
"roles": [{"role":"ROLE_ADMIN"},{"role":"ROLE_USER"}]
}' \
--user admin@gmail.com:admin
```

##### update User

```bash
curl -s -X 'PUT' \
http://localhost:8080/api/admin/v1/users/5 \
-H 'Content-Type: application/json' \
-d '{
"id": 5,
"name":"updatedUser",
"email":"updated@gmail.ru",
"password":"updated-password",
"enabled": true,
"roles": [{"role":"ROLE_ADMIN"},{"role":"ROLE_USER"}]
}' \
--user admin@gmail.com:admin
```

##### delete User

```bash
curl -s -X DELETE http://localhost:8080/api/admin/v1/users/5 --user admin@gmail.com:admin
```

##### Dishes

##### get All Dishes

```bash
curl -s http://localhost:8080/api/admin/v1/dishes --user admin@gmail.com:admin
```

###### get Dish 1

```bash
curl -s http://localhost:8080/api/admin/v1/dishes/1 --user admin@gmail.com:admin
```

##### create Dish

```bash
curl -s -X 'POST' \
http://localhost:8080/api/admin/v1/dishes \
-H 'Content-Type: application/json' \
-d '{
"name":"newDish"
}' \
--user admin@gmail.com:admin
```

##### update Dish

```bash
curl -s -X 'PUT' \
http://localhost:8080/api/admin/v1/dishes/2 \
-H 'Content-Type: application/json' \
-d '{
"id": 2,
"name":"updatedDish"
}' \
--user admin@gmail.com:admin
```

##### delete Dish

```bash
curl -s -X DELETE http://localhost:8080/api/admin/v1/dishes/3 --user admin@gmail.com:admin
```

##### Menu

##### get All Menu items

```bash
curl -s http://localhost:8080/api/admin/v1/menu --user admin@gmail.com:admin
```

###### get Menu item 1

```bash
curl -s http://localhost:8080/api/admin/v1/menu/1 --user admin@gmail.com:admin
```

##### create Menu item

```bash
curl -s -X 'POST' \
http://localhost:8080/api/admin/v1/menu \
-H 'Content-Type: application/json' \
-d '{
"day":"2022-05-17",
"price": 150000,
"dishId": 2,
"restaurantId": 3
}' \
--user admin@gmail.com:admin
```

##### update Menu item

```bash
curl -s -X 'PUT' \
http://localhost:8080/api/admin/v1/menu/2 \
-H 'Content-Type: application/json' \
-d '{
"id": 2,
"day":"2022-05-17",
"price": 154000,
"dishId": 1,
"restaurantId": 3
}' \
--user admin@gmail.com:admin
```

##### delete Menu item

```bash
curl -s -X DELETE http://localhost:8080/api/admin/v1/menu/3 --user admin@gmail.com:admin
```

##### Restaurants

##### get All Restaurants

```bash
curl -s http://localhost:8080/api/admin/v1/restaurants --user admin@gmail.com:admin
```

###### get Restaurant 1

```bash
curl -s http://localhost:8080/api/admin/v1/restaurants/1 --user admin@gmail.com:admin
```

##### create Restaurant

```bash
curl -s -X 'POST' \
http://localhost:8080/api/admin/v1/restaurants \
-H 'Content-Type: application/json' \
-d '{
"name":"newRestaurant"
}' \
--user admin@gmail.com:admin
```

##### update Restaurant

```bash
curl -s -X 'PUT' \
http://localhost:8080/api/admin/v1/restaurants/2 \
-H 'Content-Type: application/json' \
-d '{
"id": 2,
"name":"updatedRestaurant"
}' \
--user admin@gmail.com:admin
```

##### delete Restaurant

```bash
curl -s -X DELETE http://localhost:8080/api/admin/v1/restaurants/3 --user admin@gmail.com:admin
```
