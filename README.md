# cleaning-robots
Volkswagen digital:hub technical test

Fernando del Caz

Summary of technical decisions

1.- Using TDD.

The exercise tells you to use any kind of testing approach to ensure that the code works flawlessly so not adding any kind of tests is not an option.
There are mainly 2 approaches that I have considered.
- Test last
- Test first
To me, using a TDD approach is the best option as it usually leads to a more concise and robust code. I will use well known techniques like fake it until you make it and baby steps.
There are also caveats when using this approach as not leading to a good design by itself so I will also keep in mind the advices shown like using hexagonal architecture and some DDD concepts

    Some of the tests I will need to create are the following ones:
        * Place a robot outside the grid
        * Place a robot within the grid
        * Rotate the robot up/down, left/right      
        * Send the robot just one position up/down, left/right  
        * Ensure that the robot is not going out of boundaries (vertically and horizontally)
        * Send the robot several tiles away from its initial position
        * Same movements with more than one robot
        * Represent that a robot cannot move over another one

2.- Using domain exceptions

To model unexpected or wrong system behaviours my thought process is as following:
      I will return controlled results in case that something wrong, that I can expect happens
      I will just throw an exception in case that something wrong, that it should not normally happen, occurs
            In this case, I think that it´s better to use custom domain exceptions rather than the ones provided by the language because those are part of our domain and they can provide more accurate and better information

3.- Object Calisthenics or avoiding primitive obssesion

For some domain entities such as the position or the heading I could use primitives instead of domain objects. The code would still work properly and maybe going for this abstractions could be seen as a premature abstraction. I decide to go this way because they better represent the domain of the context and I can get better control such as avoiding data clumps as e.g. using the coordinates components on isolatino 

4.- Modelling the factory floor using arrays vs lists

I have taken the decision of using arrays as the floor size does not change and that way I don´t need to control some of the scenarios myself.

Using lists, in contrast, would be a bit less efficient and I would need to handle out of boundaries assignments myself. This way I may also lose some powerful, out of the box operations, which is a tradeoff that I accept for this exercise

5.- Using enums for the robot instructions to avoid propagating wrong inputs deep in the code

The application contract is using strings. This means that a variety of inputs should be controlled in order to avoid errors. I could make the robot handle this errors but I think it´s better to narrow this behaviour and postpone the decision of choosing a better system component to do this

6.- Ubiquitous language

In order to ensure that anybody involved the project, including potential expert domains, understand the same concept for the same word I think it´s important to clarify some core concepts of our domain

Robot - Any of the cleaner robots
Factory - Is the center we want to clean
Factory Floor - It´s the area to clean, represented by a matrix
Position - Coordinates over the floor factory which. A robot may be in a position or it can be emtpy
Heading - Is where the robot is pointing to. It can be North, West, South or East
Status - Is the report representing where a robot is within the factory and where is it pointing to
Instructions - Is the set of possible movements that the robot need to follow. The can be rotation (L, R) or front movements

7.- Implementing event sourcing
I want to split the responsibilities between the factory floor (just to handle it a robot is moving within boundaries) and the robots (to handle collisions). I will use event sourcing for this creating a domain event named "RobotMovedEvent"

8.- Continue with the operation when hitting a wall or another robot
I will just consider that a robot hits a wall or another robot during a movement but this does not stop it to try to continue as it can potentially clean a bigger area than if I make it halt or throw an exception and stop the whole program