# StatDataExplorer

StatDataExplorer is an Android application for exploring employee and company data retrieved from a remote API. The app displays lists of employees, companies and organizational "stars" using Retrofit and standard Android UI components.

## Prerequisites

- **Android Studio** (compatible with AGP 8.1+)
- **JDK** 17 or later

## Building with Gradle

This project uses the Gradle wrapper. To compile the debug APK and run tests, execute:

```bash
./gradlew assembleDebug
```

For a full build including unit tests you can run:

```bash
./gradlew build
```

## App Overview

The entry point of the application is `MainActivity` which provides navigation cards to the main features:

- A list of employees that supports searching and filtering by organizational star.
- A list of companies with detail screens for each company.
- A star (department) list that links to the employees belonging to each star.

Network requests are handled with Retrofit via the `ApiUtils` and `RestApi` classes. The API base URL is configured in `ApiClient`/`RetrofitClient`.

