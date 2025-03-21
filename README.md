# cleaning-robots

## Volkswagen digital:hub technical test

**Fernando del Caz**

---

## Summary of Technical Decisions

### 1. Using TDD

The exercise requires a testing approach to ensure flawless code execution. There are two main approaches:

- **Test last**
- **Test first**

Using **TDD** is the best option as it generally leads to more concise and robust code. I will use techniques like _"fake it until you make it"_ and _baby steps_. However, TDD alone does not guarantee a good design, so I will also apply principles like **hexagonal architecture** and **DDD concepts**.

#### Tests to be implemented:
- Place a robot outside the grid
- Place a robot within the grid
- Rotate the robot (left/right, up/down)
- Move the robot one position (left/right, up/down)
- Ensure robots do not go out of boundaries
- Move the robot multiple tiles
- Handle multiple robots
- Prevent robots from moving over each other

### 2. Using Domain Exceptions

To handle unexpected behaviors:

- Controlled results will be returned for expected errors.
- Exceptions will be thrown for unexpected errors.
- Custom domain exceptions will be used instead of language-built ones to provide better domain-specific feedback.

### 3. Avoiding Primitive Obsession (Object Calisthenics)

Instead of using primitives for domain entities like **position** or **heading**, I will create domain objects. This enhances readability, reduces data clumps, and ensures better domain representation.

### 4. Modeling the Factory Floor: Arrays vs. Lists

Using **arrays** is more efficient since the factory floor size is fixed, eliminating the need for boundary checks. Lists, while more flexible, require additional boundary management, which I prefer to avoid.

### 5. Using Enums for Robot Instructions

Since the application contract relies on strings, I will map instructions to **enums** to prevent invalid inputs from propagating through the code.

### 6. Ubiquitous Language

To maintain a shared understanding among all stakeholders, key domain terms are defined:

- **Robot** - A cleaning unit.
- **Factory** - The facility being cleaned.
- **Factory Floor** - The grid representation of the cleaning area.
- **Position** - Coordinates on the floor grid.
- **Heading** - The robot's direction (North, South, East, West).
- **Status** - A report on a robot’s position and heading.
- **Instructions** - Movement commands (L, R, F).

### 7. Implementing Event Sourcing

To separate concerns between:

- **Factory Floor**: Manages grid boundaries.
- **Robots**: Handle collisions.

A `RobotMovedEvent` domain event will be used.

### 8. Collision Handling: Continue Execution

A robot encountering a wall or another robot will **not halt execution**. Instead, it will attempt to continue moving, maximizing cleaning coverage.

### 9. Hexagonal + Onion Architecture

Using **Hexagonal Architecture** simplifies adapting the application to different entry points (e.g., API, file input). **Onion Architecture** ensures better decoupling and testability.

---

## Setup and Execution Instructions

### 1. Install Java JDK 15

#### **Windows**
1. Download JDK 15: [Oracle JDK 15](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html)
2. Install following the wizard.
3. Configure `JAVA_HOME`:
      - Open **System Properties** (`Win + R`, type `sysdm.cpl`, press Enter).
      - Navigate to **Advanced > Environment Variables**.
      - Under **System Variables**, click **New**:
            - **Variable name**: `JAVA_HOME`
            - **Variable value**: `C:\Program Files\Java\jdk-15` (or actual path).
      - Edit **Path** variable:
            - Click **New** and add `%JAVA_HOME%\bin`.
      - Save changes.
4. Verify installation:
   ```sh
   java -version
   ```
   
    Expected outuput
    ```
    java version "15.0.2" 2021-01-19
    Java(TM) SE Runtime Environment (build 15.0.2+7-27)
    Java HotSpot(TM) 64-Bit Server VM (build 15.0.2+7-27, mixed mode)
   ```
#### **Linux/macOS**
1. Install OpenJDK 15:
   ```sh
   sudo apt install openjdk-15-jdk  # Debian/Ubuntu
   brew install openjdk@15          # macOS (Homebrew)
   ```
2. Verify installation:
   ```sh
   java -version
   ```

### 2. Install Kotlin and Gradle

1. Install Kotlin:
   ```sh
   sdk install kotlin
   ```
   or manually from [Kotlin](https://kotlinlang.org/)

2. Install Gradle:
   ```sh
   sdk install gradle
   ```
   or manually from [Gradle](https://gradle.org/install/)

3. Verify installation:
   ```sh
   kotlin -version
   gradle -version
   ```

### 3. Clone the Repository
```sh
git clone https://github.com/your-username/cleaning-robots.git
cd cleaning-robots
```

### 4. Build and Run the Application
```sh
gradlew build
gradlew run
```

### 5. Run Tests
```sh
gradlew test
```

### 6. Linting and Code Style
```sh
gradle ktlintCheck
```

---

