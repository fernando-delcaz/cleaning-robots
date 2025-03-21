# cleaning-robots

## Volkswagen digital:hub technical test

**Fernando del Caz**

---

## Domain

I have tried to void an anemic domain model by adding business rules to the domain objects. A simple example of this could be the forward movement that checks it the displacement is allowed or not. This way I try to enforce the correctness and consistency of the application.    

- Robot 
  - Any of the robots to clean the factory
- Status
  - Represents the position and heading of the robot
- Position
  - Coordinates of the robot
- Instruction 
  - It´s an abstraction of what a robot can do, moving forward or rotating
- Heading
  - Where the robot is pointing to
- ForwardMovement
  - Advance one position towards the heading direction
- Factory
  - It´s responsible of containing the business rules to move robots around
- FactoryFloor
  - Auxiliary class to encapsulate the business rules for the boundaries and holding the status of the application ç

These concepts represent the ubiquitous language of the core domain. I have paid some special attention also to use some natural language, closer to the expert domains, for the oprations. E.g the method canSomethingBeMovedTo(position: Position) in the factory floor or isInsideTheFactory(position: Position) in the factoryclass

## Summary of Technical Decisions

### 1. Using TDD

The exercise requires a testing approach to ensure flawless code execution. There are two main approaches:

- **Test last**
- **Test first**

Using **TDD** is the best option as it generally leads to more concise and robust code. I will use techniques like _"fake it until you make it"_ and _baby steps_. However, TDD alone does not guarantee a good design, so I will also apply principles like **hexagonal architecture** and **DDD concepts**.

#### Tests to be implemented:
- Place a robot outside the grid
- Place a robot within the grid
- Rotate the robot (left/right)
- Move the robot one position (pointing to all the posible directions)
- Ensure robots do not go out of boundaries
- Move the robot multiple tiles
- Handle multiple robots
- Prevent robots from moving over each other

### 2. Using Domain Exceptions

To handle unexpected behaviors:

- Controlled results will be returned for expected errors.
- Exceptions will be thrown for unexpected errors.
- Custom domain exceptions will be used instead of language-built ones to provide better domain-specific feedback.

### 3. Avoiding Primitive Obsession [(Object Calisthenics)](https://keyvanakbary.com/object-calisthenics-mejora-tu-diseno-orientado-a-objetos/)

Instead of using primitives for domain entities like **position** or **heading**, I will create domain objects. This enhances readability, reduces data clumps, and ensures better domain representation.

### 4. Modeling the Factory Floor: Arrays vs. Lists

Using **arrays** is more efficient since the factory floor size is fixed, eliminating the need for boundary checks. Lists, while more flexible, require additional boundary management, which I prefer to avoid.

This finally was discarded for simplicity. Please check the class factory floor and   [DDD Elements](#10-ddd-elements).

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

### 7. Not Implementing Domain Events

To separate concerns between:

- **Factory Floor**: Manages grid boundaries.
- **Robots**: Handle collisions.

A `RobotMovedEvent` domain event could be used. This could be nice from the point of view of DDD but at this point in time I think it would add a lot of overengineering to the project.
The approach I was considering was to mimic that the robots can "see" by registering them in the factory, at the beginning. After that, I would broadcast the event to all the subscribers anytime a robot ends its routine to make them all aware of where to robots are parked and enabling them to "see" them without the need of querying the factory

A more canonical approach should be to emit these events to a collaborative domain such as a metrics system which is completely out of scope

### 8. Collision Handling: Continue Execution

A robot encountering a wall or another robot will **not halt execution**. Instead, it will attempt to continue moving, maximizing cleaning coverage.

### 9. Hexagonal + Onion Architecture

Using **Hexagonal Architecture** simplifies adapting the application to different entry points (e.g., API, file input). **Onion Architecture** ensures better decoupling and testability.
Hexagonal Architecture (Ports & Adapters) focuses on isolating the core business logic by using ports (interfaces) to interact with external systems (APIs, databases, UI). This makes it easy to swap adapters (CLI, REST, file input) without modifying the core.
The CommandLineInterface is an example of an adapter. 
Onion Architecture takes a more layered approach, enforcing strict dependencies from the outer layers (infrastructure) towards the core. The business logic is at the center, and everything else depends on it, ensuring maximum decoupling and testability.

### 10. DDD Elements
Bounded Context - There is a single domain which is the only bounded context we have
Value Objects - The Robot is an example of a value object
Aggregate - The Factory is similar to an aggregate, not 100% canonical
Services - FactoryCleaningService is an example of a service
Layered architecture - Explained in the previous decision

### 11. Not Implementing Repositories

At this stage, the Factory Floor contains a map with all the robot positions in order to know where they are and prepare for collisions. This is basically what represents my persistance layer and the status of the application. A good idea would be to isolate this into a repository that would be injected by it´s interface to the components needing to know where other robots are. 
Being this a would idea, at this point I think that I have go far enough and adding this layer would require a lot of changes that would make the code potentially less readable or make me rewrite quite a lot of components of the application. 
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
   ```

   ```sh   
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
---

