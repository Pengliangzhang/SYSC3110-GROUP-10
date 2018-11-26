# SYSC3110-GROUP-10
Authors:
  * Pengliang Zhang 101014341
  * Bohua Cao 100971104
  * Kevin Li 101042813
  * Xinrui Li 101018938
  
  
Deliverables:
  * UML diagram: A class diagram, sequence diagram 
  * Documentation: This file introduces the process of creating this project and describes how to play
  * 19 Java Classes

Change Log:

  Pengliang:
  
      1. Created Advanced Peashooter and advanced zombie class
      2. Edited plantAPlant and zombieAction methon in Game class, which allow users to plant advanced peashooter and release advanced zombie
      3. Added game menu and added redo/undo menu item into the game menu
      4. Changed all class to implements Serialiable
      5. Merged all files together
      6. Distrubuted works to team members

  Xinrui:
  
      1. Simplify the code of JButtons in GUIFrame
      2. Create actionPerformed, disableAll, enableAll, and checkWinner methods in GUIFrame
      3. Edit Game, takeTurn, userTurn methods in Game class to fix the GUIFrame
      4. Add newGame method to make the game restartable
      5. Create new field, lists, index, and size, in Game calss to store history and indicate the current state
      6. Create copy, undo, redo method in Game class
      7. Modify taketurn method in Game class to fix undo/redo requirement
      
  Bohua:

  Kevin:
  
     1. Updated UML
     2. Updated documentation for classes (GUIFrame.java, Game.java)
     3. Renamed fields and methods for clarity (renewMap -> refreshMap, disableAll -> disableAllButtons, sun -> sunIndicator)

Known Issues:

  1. Unable to execute Game.java directly, have to change all methods and ready to use by GUI
  2. Have to write new method which return all zombies and plants and can access their position
  3. Do not have use's input from console, all input have to implement by button click
  4. Number of suns have to changed to a small creating rate, too much suns was created.


Road Map:

  1. Pengliang will create a class and wich named the class GUIFrame.java
  2. 


Work distribution: 

  * Pengliang Zhang: Create GUI frame class, which will creat method to add selection panel, mapping panel and menu bar which will creat a method to update map for each turn
  * Xinrui Li      : Working with the logic, Update game.java
  * Bohua Cao      : Working with the test, test all class using JUnit test
  * Kevin Li       : Working with documentations, UML, check the process of the project and make sure everyone finish all works on time
                   
 
