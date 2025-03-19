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


