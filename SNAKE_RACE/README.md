# ARSW Laboratorio 1, 2020-2

# Integrantes: 
- Edwin Yesid Rodriguez Maldonado
- Guillermo Esteban Bernal Bonilla

## Snake Race
### Part I

Control threads using wait/notify.

1. Download the project PrimeFinder. this is a program that calculates prime numbers beetween 0 and M (Control.MAXVALUE),concurrently, distributing the searching of them between n (Control.NTHREADS) independent threads.
2. Modify the application in such way that each t milliseconds of thread execution, all the threads stop and show the number of primes found until that moment. Then, you have to wait until press ENTER in order to resume the threads execution.Use the synchronization mechanisms given by java (wait y notify, notifyAll).

Note that:

- The synchronized statement is used in order to get exclusive access to an object
- The statement A.wait executed in a B thread set it to suspended mode (Independently that objective A is being used as 'lock') To resume, other active thread can resume B calling notify () to the object used as 'lock' ( in this case A)
- The notify() statement, wakes the first thread  up who called wait() on the object
- The notifyAll() instruction, wake  every thread up that are waiting for the object

### Part 2

SnakeRace is an autonomous version, multi-snake of the famous game called 'snake' based on the Joao Andrade´s project-this exercise is a fork thereof

- N snakes works as an autonomous way.
- The collision concept does not exists among them. The only way that they die is by crashing against a wall
- There are mice distributed along the game. As in the classic game, each time a snake eats  a mouse, it grows
- There are some points (red arrows) that teleport the snakes
- The rays causes that the snake increase its speed

IMAGEN

### Part 3

1. Analyse the code in order to understand how the threads are being used to create an autonomous behavior in the N snakes

2. Accordingly, and using the game logic, identify and write clearly (ANSWERS.txt file)

    2,1. Possible race conditions

    2,2. An incorrect or inappropriate use of collections, considering its concurrent handling(For this increase the game speed and execute it multiples times until an error has been raised).

    2,3. Unnecessary use of active waits

3. Identify critical regions associated with race conditions, and do something in order to eliminate them.Note that you have to synchronize strictly needed. In the answers document suggest the solution proposed for each item of the point 2. As the same way note that you don´t have to add more race conditions

4. As you can see, the game is incomplete. Write code in order to implement functionallities through buttons in the GUI to start / Pause / Resume the game: start the game if it has not started, Pause the game if it is on, Resume the game if it is suspended. Keep in mind:

    4,1. When the game has been paused, in some point of the screen you have to show 
    - the longest snake
    - The worst snake:(the first snake  dead)

    Remember that the pause of the snakes are not instantanious, and you have to      guarantee that all the information showed is consistent
