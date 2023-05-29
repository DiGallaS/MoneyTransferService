# # Курсовой проект "Сервис перевода денег"

## Описание проекта

Разработано приложение - REST-сервис. Сервис предоставляет интерфейс для перевода денег с одной карты на другую по заранее описанной [спецификации](MoneyTransferServiceSpecification.yaml). Заранее подготовленное веб-приложение (FRONT) подключается к разработанному сервису без доработок и использует его функционал для перевода денег.

## Описание интеграции с FRONT
FRONT доступен по адресу https://github.com/serp-ya/card-transfer, можно выкачать репозиторий и запустить nodejs приложение локально
(в описании репозитория фронта добавлена информация как запустить).

## Сборка, команда запуска, порты

#### Сборка

Для этого в терминале в корне проекта выполните команду: `./gradlew clean build`

#### Запуск
Для этого в терминале в корне проекта выполните команду: `docker-compose up`

