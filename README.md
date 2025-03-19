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
