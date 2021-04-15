Multithreading Network Tic Tac Toe Game

Short Description:

This project is a Tic Tac Toe Game simulation, that allows two clients to play across a network. The game is played by selecting a button on the board which you would like to place your symbol.
Client 1 is listed as player X and Client 2 is listed as Player O. Once this is done, client 2 will refresh their GUI using the refresh button, and then make their subsequent move. This will go on until there is 
a draw or a winner is declared. A winnder is declared when one of the following patterns are displayed:

--- xxx --- x-- -x- --x x-- --x

xxx --- --- x-- -x- --x -x- -x-

--- --- xxx x-- -x- --x --x x--

When a winner or draw occurs, the server and all clients will disconnect. Thus making the game completed.

How to run:

1. Clone the repository
2. Open the cloned repository in the Intellij IDE or any other IDE
3. Click on Run -> Edit Configurations -> Main -> Moidify Options -> Allow Multiple Instances
4. Follow the necessary requirements for javaFX implementation; such as importing necessary libraries and project structures.
5. Expand: GroupProject -> src -> sample
6. Right Click on Server -> select "Run 'Server.main()'"
7. Rick Click on Main -> select "Run 'Main'" (This will run Client #1)
8. Right Click on Main -> Select "Run 'Main'" (This will run Client #2)

The code was built using javaFX, with UI improvements using CSS.

The Two Client Interfaces and Running Server can be seen below:

![unknown](https://user-images.githubusercontent.com/37226310/114810353-e7c54180-9d79-11eb-8cc0-0eac0be06b02.png)


