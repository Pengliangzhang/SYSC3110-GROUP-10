# SYSC3110-GROUP-10
Authors:
  * Pengliang Zhang 101014341
  * Bohua Cao 100971104
  * Kevin Li 101042813
  * Xinrui Li 101018938
  
  
Deliverables:
  * UML diagram: A class diagram, sequence diagram 
  * Documentation: This file introduce our project process and discribe how to play the game
  * nine java classes


Change Log:

  Pengliang:
  
      1. Created a class called GUIFrame
      2. Added mapping panel and selection panel on the frame
      3. Added buttons on the selection panel.
      4. Added mapping buttons on the mapping panel.
      5. Create methods in the game class named getAllZombia and getAllplant
      6. Created methods in the GUIFrame called printZombieMap and renewMap

  Xinrui:
      1. Simplify the code of JButtons in GUIFrame
      2. Create actionPerformed, disableAll, enableAll, and checkWinner methods in GUIFrame
      3. Edit Game, takeTurn, userTurn methods in Game class to fix the GUIFrame
      4. Add newGame method to make the game restartable

Bohua:
      1.test class for every classes
      2.testsuit for all tests
      3.some useful set and get functions 

  Kevin:
  
     1. Updated UML

Known Issues:

  1. Unable to execute Game.java directly, have to change all methods and ready to use by GUI
  2. Have to write new method which return all zombies and plants and can access their position
  3. Do not have use's input from console, all input have to implement by button click
  4. Number of suns have to changed to a small creating rate, too much suns was created.


Road Map:

  1. Pengliang will creat a class and wich named the class GUIFrame.java
  2. 


Work distribution: 

  * Pengliang Zhang: Create GUI frame class, which will creat method to add selection panel, mapping panel and menu bar which will creat a method to update map for each turn
  * Xinrui Li      : Working with the logic, Update game.java
  * Bohua Cao      : Working with the test, test all class using JUnit test
  * Kevin Li       : Working with documentations, UML, check the process of the project and make sure everyone finish all works on time
                   
 
