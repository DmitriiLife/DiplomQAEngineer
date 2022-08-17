## Работа в процессе

### Процедура запуска тестов
1. Выполнить git clone https://github.com/DmitriiLife/DiplomQAEngineer;
2. Открыть проект в Android Studio;
3. Запустить эмулятор;
4. Перейти в папку проекта fmh-android;
5. Открыть консоль из папки(fmh-android) и ввести команду для нужных тестов;

#### Запуск espresso tests:
1. Запуск class "SplashPage":
* "./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.espresso.test.SplashPage";

2. Запуск class "AuthorizationPage":
* "./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.espresso.test.AuthorizationPage";

3. Запуск class "AppActivityTest":
* "./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.espresso.test.AppActivityTest";

4. Запуск package "espresso.test":
* "./gradlew app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.package=ru.iteco.fmhandroid.ui.espresso.test";


