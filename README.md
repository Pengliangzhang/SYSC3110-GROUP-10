# SYSC3110-GROUP-10
Authors:
  * Pengliang Zhang 101014341
  * Bohua Cao 100971104
  * Kevin Li 101042813
  * Xinrui Li 101018938
  
  
Deliverables:
  * UML diagram: A class diagram, sequence diagram 
  * Documentation: This file introduces the process of creating this project and describes how to play
  * 22 Java Classes

Change Log:

  Pengliang:

      Milestone 3:
      1. Created Advanced Peashooter and advanced zombie class
      2. Edited plantAPlant and zombieAction methon in Game class, which allow users to plant advanced peashooter and release advanced zombie
      3. Added game menu and added redo/undo menu item into the game menu
      4. Changed all class to implements Serialiable
      5. Merged all files together
      6. Distrubuted works to team members
      
      Milestone 4:
      1. Changed code in Game.java which added Save and Load method to achieved save/load features.
      2. Changed button layout in GUIGame.java which added new menu item and deleted button which won't be used in current version
      3. Added an timer to implements real time feature.
      4. Changed all class to implements Serialiable
      5. Organized works and merged all updates together.
      6. Check working processes and make sure everyone update works on time.


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

  1. Unable to implements to implement redo/undo features
  2. Have to write new method to achieve redo/undo fetures
  3. Unable to save the Game object directly into the arraylist because all objects are the same, have to convert it to different object

  Milestone 4:
  1. Unable to save object into a file except use serilization
  2. Implementing level selector should read from xml file
  3. Real time feature is difficult because of the time period to release zombie 


Road Map:

  1. Beck works on the save, load fecture, real time, and Origanize works. 
  2. Oliver will change the method in Game class to achieve the level selection feature.
  3. Bohua testing all class
  4. Kevin document all class and check work process is as expected


Work distribution: 

  * Pengliang Zhang: Achieved save/load, real time fecture
  * Xinrui Li      : Modify the game class to achieve the level selection features, and makek change for the GUI as well.
  * Bohua Cao      : Working with the test, test all class using JUnit test
  * Kevin Li       : Working with documentations, UML, check the process of the project and make sure everyone finish all works on time
                   
 
