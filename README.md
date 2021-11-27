# GIF по курсам валют (Exchange rates)
Spring-Boot, Feign, JUnit

### Описание
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
- если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
- если ниже - отсюда https://giphy.com/search/broke
Ссылки
- REST API курсов валют - https://docs.openexchangerates.org/
- REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
Must Have
- Сервис на Spring Boot 2 + Java / Kotlin
- Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
- Для взаимодействия с внешними сервисами используется Feign
- Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
- На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
- Для сборки должен использоваться Gradle
- Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
Nice to Have
- Сборка и запуск Docker контейнера с этим сервисом

### Запуск
1. Клонировать: `git clone git@github.com:gronblack/exchange-rates.git`  
Или скачать на [странице](https://github.com/gronblack/exchange-rates) (кнопка Code)
2. В папке проекта выполнить `./gradlew bootrun`
3. Запускать [curl](#curl) в другом окне консоли или использовать UI-client (`http://localhost:8080`)

### Curl
Получить GIF для курса RUB:  
`curl -s http://localhost:8080/api/?symbols=RUB`  

Получить GIF для курса CAD:  
`curl -s http://localhost:8080/api/?symbols=CAD`