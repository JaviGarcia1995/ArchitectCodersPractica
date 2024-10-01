# Harry Potter API Clean Architecture Project
This project showcases a modular Android application that follows Clean Architecture principles using Kotlin and Jetpack Compose. The app fetches data from the Harry Potter API (https://hp-api.onrender.com/) and displays the wizards in a list, offering detailed views of each character. The architecture is designed for scalability, maintainability, and ease of testing by following a clear separation of concerns across different modules and layers.

## Key Technologies
- Modularized Clean Architecture: Organizes the code into distinct layers and independent modules, enhancing scalability, testability, and maintainability.
- Jetpack Compose: A declarative UI framework that simplifies the creation of native Android user interfaces.
- Retrofit: A robust HTTP client that communicates with the Harry Potter API to fetch character data.
- Room: A local database powered by SQLite, used to cache wizards' data for offline access.
- Hilt: Dependency injection framework that improves scalability and simplifies the injection of dependencies across different modules.


## Architecture Overview
The app is divided into five key modules: app, domain, framework, features, and test. Each module plays a specific role in the overall architecture.

### app Module
- Responsibility: The main application module that contains global configurations and the entry point of the app.
- Components:
  - MainActivity: The main activity that initializes navigation and sets up global components like the theme and configurations.
  - Navigation: Handles the navigation graph and screen transitions across the different features of the app.

### domain Module
- Responsibility: This layer contains the core business logic and application rules. It is completely independent of external frameworks and does not rely on Android-specific implementations.
- Components:
  - Use Cases: Encapsulate the app's business logic, such as fetching wizards from the repository or filtering them by house.
  - Entities: Core data models that represent the main business objects (e.g., Wizard, House).
  - Repositories (Interfaces): Define the contract for data operations, implemented in the data layer (located in the framework module).

### framework Module
- Responsibility: Manages interactions with external libraries and Android components, including API calls, local storage, and other framework-specific tasks.
- Components:
  - Retrofit: Used for making HTTP requests to the Harry Potter API to fetch character data.
  - Room: Handles local data storage by caching the fetched wizard data for offline access.
  - Data Sources: Implements the repository interfaces defined in the domain layer, combining data from remote (API) and local (database) sources.

### features Module
- Responsibility: Each feature of the app is encapsulated within its own module, allowing for better modularity and reusability. This ensures that features are developed and maintained independently from one another.
- Components:
  - Wizard List Feature: Handles the logic and UI for displaying the list of wizards, using Jetpack Compose to build the list UI.
  - Wizard Detail Feature: Manages the detail view for each wizard, showing more specific information about the selected character.

Each feature module interacts with the domain layer to fetch the required data and updates the UI based on the ViewModel state.

### test Module
- Responsibility: Contains testing configurations and utilities, ensuring correct functionality of the app.
- Components:
  - Custom Coroutine Setup: Custom configuration of coroutines for unit testing, ensuring proper coroutine management during tests.
  - Fake Data Sources: Mock data sources that simulate real data sources, allowing for isolation of business logic during tests.
  - Helpers: Utility functions and classes that simplify and streamline the testing process across all layers.

### build-logic Module
- Responsibility: This module includes configuration and management of plugins and Gradle logic for each module, ensuring consistent build setups across the project.
- Components:
  - Custom Gradle Plugins: Centralize configuration for tasks such as dependency management, Kotlin options, and code quality checks for all modules.

## Key Technologies
- Modularized Clean Architecture: Divides the codebase into multiple feature-based and layer-specific modules, ensuring separation of concerns and promoting scalability.
- Jetpack Compose: Declarative UI framework for building modern Android interfaces, which helps in maintaining a reactive UI.
- Retrofit: HTTP client used for fetching data from the Harry Potter API.
- Room: Local database solution for persisting data and caching API results for offline use.
- Hilt: Provides dependency injection across the app to improve modularity and testability.
- Gradle Build Logic: Modularized build logic ensures consistent and scalable plugin configuration for all modules.
- Tests: Includes unit and integration tests using Mockito

## Example Use Case
The app demonstrates a flow where:
 1. Data is fetched from the Harry Potter API using Retrofit.
 2. It is stored locally using Room for offline access.
 3. Wizards are displayed in a list using a LazyColumn in Jetpack Compose.
 4. A detail view displays additional information about the selected wizard.

## Conclusion
This project demonstrates how to apply Clean Architecture in a real-world scenario, organizing responsibilities into modular layers to ensure scalability, maintainability, and ease of testing.
