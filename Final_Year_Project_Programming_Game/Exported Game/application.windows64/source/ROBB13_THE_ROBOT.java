import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ROBB13_THE_ROBOT extends PApplet {

//global variables

//the current level number
int lv = 1;

//constants
final int MENU = 0;
final int PLAY = 1;
final int INFO = 2;
final int WIN = 3;
final int INTRO = 4;
final int SELECT = 5;
final int WAITFORKEY = 6;

final int GREEN = 0;
final int RED = 1;
final int BLUE = 2;

final String inst = "The aim of the game is to guide a robot to the exit on each level.\nTo do this, you must arrange and run a set of commands, to tell the robot what to do.\n\nAs you progress through the levels, you will gain access to more and more commands.";
final String backButton = "BACK TO MENU";
final String introText1 = "Set up a list of commands using the buttons at the top-right of the screen.\n\nThen press the PLAY button to run them.\n\nThe aim is to get the ROBOT to the DOOR.\n\nYou can use the DELETE button to remove the command at the end of your list\nand the RESET button to clear the full list.";
final String introText2 = "In this level, the DOOR is LOCKED so the ROBOT can't go straight through it.\n\nTo UNLOCK the DOOR, the ROBOT must first step on the BUTTON.";
final String introText3 = "This level features multiple BUTTONS.\n\nTo UNLOCK the DOOR, ALL BUTTONS must be pressed.";
final String introText4 = "This level features a BUTTON of a different COLOUR.\n\nTo be able to press this BUTTON, the ROBOT must be the SAME COLOUR as it, while pressing it.\n\nTo change the ROBOT'S COLOUR, use the GO GREEN, GO RED or GO BLUE commands.\n\nNOTE: You CANNOT enter two COLOUR change commands in a row";
final String introText5 = "Remember when you beat Level 3 and you were asked to think about what\ncould have made things more efficient?\n\nWell, this level features the solution!\n\nSome levels, like this one, need the ROBOT to move very far.\n\nHowever, you only have 13 lines for commands.\n\nTo solve this problem, LOOPS can be used to repeat certain sets of commands.\n\nAll commands you place between LOOP X2 and LOOP END will be carried out TWICE.";
final String introText6 = "This level features a BUTTON that RANDOMLY changes COLOUR.\n\nThrough brute force you could just pick a COLOUR and hope the BUTTON changes to it at the right time.\n\nHowever, the IDEAL SOLUTION is to use IF and ELSE\nto check what COLOUR the BUTTON is as the ROBOT approaches and change its COLOUR accordingly.\n\n You can use IF BUTTON IS RED to check if the BUTTON is RED.\nThe following lines (Until ELSE or END IF is reached) will then only run if that condition is TRUE.\n\nThe lines of code after ELSE will only run if the IF statement was FALSE.\n\n END IF can be used to show the END of the ELSE block or the END of the IF block if there is no ELSE.\n\nNOTE: The BUTTON COMMAND only targets a BUTTON that the ROBOT is touching.";
final String introText7 = "This level introduces LOOP X5, which works the same as Loop X2, but LOOPS 5 times instead of 2.\n\nTo beat this level you should combine what you have learned about LOOPS and IF statements.";
final String introText8 = "So far, when using LOOPS, you've only had to deal with CONSTANTS (2 and 5).\n\nCONSTANTS are values which NEVER CHANGE while the code is running.\n\nThis level, introduces a VARIABLE.\n\nA VARIABLE is a value that CAN CHANGE while the code runs.\n\nTo beat this level you will need to use the '? =' command to set the value of the VARIABLE\nand the 'LOOP X?' command to LOOP the code that many times";
final String introText9 = "This is the final level and is the most difficult.\n\nTo complete this level, you will need to have LOOPS within LOOPS.\n\nThese are known as NESTED LOOPS.\n\nAs a hint as to how NESTED LOOPS work:\n\nIf you have an OUTER LOOP X2 and an INNER LOOP X5,\nthe INNER LOOP will actually run a total of 2X5 times (10 times).\n\n GOOD LUCK!";

final String outroText1 = "Well done!\n\nNow complete the next few levels to help you get used to how your code will run.";
final String outroTextGen = "Congratulations!\n\nNow for something a bit trickier...";
final String outroText3 = "Well done!\n\nYou may have noticed you had to repeat the same few commands over and over to beat that level.\n\nMaybe think about what kind of command might have made it more efficient if you'd had access to it...";
final String outroText4 = "Nice one!\n\nYou're starting to get how buttons work, but there's still more to learn...";
final String outroText5 = "Great work!\n\nNow you know you need to press all buttons to beat a level,\nbut there are still more button-related secrets to uncover...";
final String outroText6 = "Fantastic!\n\nYou now know you can change colour to activate a button of the same colour.\n\nThat's all you need to know about buttons...for now.";
final String outroText8 = "Cool!\n\nYou've just used a loop for the first time!\n\nLoops are useful in programming when the same set of commands needs to be run multiple times.\n\nThis helps save time writing code, makes it easier to read,\nand often makes it more efficient for the computer to run!";
final String outroText9 = "Nice One!\n\nAs you've probably just figured out, not all code needs to be within a loop if you use one.\n\nIf you have code that doesn't need to be repeated,\njust enter it before the start of the loop, or after the end!";
final String outroText10 = "Amazing!\n\nBy beating that level, you should hopefully\nhave realised that you can have multiple separate loops in your code!";
final String outroText11 = "Wow!\n\nThat was a tough one.\n\nYou've managed to combine your knowledge about loops\nwith your knowledge about buttons without any instructions!\n\nProblem solving is a major part of being a good programmer!";
final String outroText12 = "Great work!\n\nHopefully you used IF and ELSE rather than brute force.\n\nIf not, then you'll probably find the next few levels a lot harder!";
final String outroText14 = "Fantastic!\n\nYou've mastered the use of If Statements!\n\nWhen programming, you'll find that these are fundamental and will likely be in every project you do!\n\nIt's always far more efficient to only run necessary code and skip what doesn't apply,\nwhich is why If Statements are great!";
final String outroText15 = "Marvellous!\n\nNow you have the ability to be more precise with the number of loops the code runs through!\n\nYou now only have one more thing to master!\n\nYou'll find out more in the next level!";
final String outroText16 = "Congratulations! You did it!\n\nYou beat the final level!\n\nThroughout this game you should have learned about the basics of writing code,\nselection through if statements, iteration through loops,\nnested loops, nesting selection within iteration and more!\n\nYou're now well on your way to becoming a competent programmer! Well done!";

int mode = MENU;

//booleans to keep track of whether the next command should be on the same line or not
boolean firstWord = true;
boolean secondWord = false;
boolean thirdWord = false;

//boolean to check if the robot's colour has just been changed
boolean colourChanged = false;

//boolean to check if an If command has been entered
boolean ifUsed = false;
int ifCount = 0;

//boolean to check if most recent if statement was passed
boolean checkIf = true;

//a list of all Levels
ArrayList<Level> allLevels = new ArrayList<Level>();

//position of circle
int robPosX = 250;
int robPosY = 250;

//create the robot character
Robot r1;

//position of next location of circle
int newRobX = 250;
int newRobY = 250;

//dimensions of buttons and gaps between them
int buttonWidth = 100;
int buttonHeight = 30;
int buttonHorGap = buttonWidth + 10;
int buttonVerGap = buttonHeight + 10;

//maximum number of lines of code
int lineLimit = 13;
//current number of lines of code
int numLines = 0;

int currentUserVar = 0;

//dimensions of lines of code and gaps between them
int lineGap = 50;
int wordGap = 100;
int linePosX = 1010;
int linePosY = 350;
IntList wordsList = new IntList();
IntList colourChangedList = new IntList();

//speed at which the character moves
int speed = 5;

//boolean to check if code is running
boolean runningCode = false;

//the currently running line of code (-1 when not running)
int curLine = -1;

//boolean to decide whether code should be rendered
boolean renderCode = false;

//ArrayList of lines of code
ArrayList<Code> codeList = new ArrayList<Code>();
ArrayList<Code> activeCodeList = new ArrayList<Code>();


//Commands to be shown in buttons
Command up = new Command("Up", 1010, 10);
Command down = new Command("Down", 1010 + buttonHorGap, 10);
Command l = new Command("Left", 1010 + (2 * buttonHorGap), 10);
Command r = new Command("Right", 1010 + (3 * buttonHorGap), 10);
Command loop2 = new Command("Loop x2", 1010 + (4 * buttonHorGap), 10);
Command loopEnd = new Command("Loop End", 1010 + (4 * buttonHorGap), 10 + buttonVerGap);
Command goGreen = new Command("Go Green", 1010, 10 + buttonVerGap);
Command goRed = new Command("Go Red", 1010 + buttonHorGap, 10 + buttonVerGap);
Command goBlue = new Command("Go Blue", 1010 + (2 * buttonHorGap), 10 + buttonVerGap);
Command ifCom = new Command("If", 1010 + (3 * buttonHorGap), 10 + buttonVerGap);
Command elseCom = new Command("Else", 1010 + (3 * buttonHorGap), 10 + (2 * buttonVerGap));
Command endIfCom = new Command("End If", 1010 + (3 * buttonHorGap), 10 + (3 * buttonVerGap));
Command buttonCom = new Command("Button", 1010 + (4 * buttonHorGap), 10 + (2* buttonVerGap));
Command isGreen = new Command("Is Green", 1010, 10 + (2 * buttonVerGap));
Command isRed= new Command("Is Red", 1010 + buttonHorGap, 10 + (2 * buttonVerGap));
Command isBlue = new Command("Is Blue", 1010 + (2 * buttonHorGap), 10 + (2 * buttonVerGap));
Command loop5 = new Command("Loop x5", 1010 + (4 * buttonHorGap), 10 + (3 * buttonVerGap));
Command userVar = new Command ("? = ", 1010, 10 + (3 * buttonVerGap));
Command varLoop = new Command ("Loop x?", 1010 + buttonHorGap, 10 + (3 * buttonVerGap));

Command[] commandList1 = {up, down, l, r};
Command[] commandList2 = {up, down, l, r, goGreen, goRed, goBlue};
Command[] commandList3 = {up, down, l, r, goGreen, goRed, goBlue, loop2, loopEnd};
Command[] commandList4 = {up, down, l, r, goGreen, goRed, goBlue, ifCom, elseCom, endIfCom, buttonCom, isGreen, isRed, isBlue};
Command[] commandList5 = {up, down, l, r, goGreen, goRed, goBlue, ifCom, elseCom, endIfCom, buttonCom, isGreen, isRed, isBlue, loop2, loopEnd, loop5};
Command[] commandList6 = {up, down, l, r, goGreen, goRed, goBlue, ifCom, elseCom, endIfCom, buttonCom, isGreen, isRed, isBlue, loop2, loopEnd, loop5, userVar, varLoop};

Command[] firstWordCommands = {up, down, l, r, goGreen, goRed, goBlue, ifCom, elseCom, endIfCom,loop2, loopEnd, loop5, userVar, varLoop};
Command[] secondWordCommands = {buttonCom};
Command[] thirdWordCommands = {isGreen, isRed, isBlue};
Command[] colourChangeCommands = {goGreen, goRed, goBlue};




public void setup() 
{
  //create game window with grey background
  
  background(240);
  
  for (Command c : secondWordCommands)
  {
     c.setGrey();
  }

  for (Command c : thirdWordCommands)
  {
    c.setGrey();
  }
  
  endIfCom.setGrey();
  
  //set up levels
  if (allLevels.size() == 0)
  {
    levelsSetup();
  }
    
  //create new robot
  r1 = new Robot();

  if (mode == PLAY)
  {
    //create area for code
    setupCodeArea();

    //set up checkerboard background
    checkerboard();



    //set robot start point
    if (r1.getCX() == -1)
    {
      for (Level l : allLevels)
      {
        if (l.getLevelNum() == lv)
        {
          r1.setStartPos(l.getStartPosX(), l.getStartPosY());
          newRobX = r1.getCX();
          newRobY = r1.getCY();
        }
      }
    }

    //draw robot character
    r1.update();

    //update robPosX and robPosY
    robPosX = r1.getCX();
    robPosY = r1.getCY();

    //update code if necessary
    if (renderCode)
    {
      for (Code c : codeList)
      {
        c.update();
      }
    }
  } 
  else if (mode == MENU)
  {
    textAlign(CENTER);
    textSize(50);
    fill(0);
    text("PLAY", 780, 300);
    text("HOW TO PLAY", 780, 400);
    text("LEVEL SELECT", 780, 500);
    textSize(14);
    text("Click on an option.", 780, 100);
  }
  else if (mode == INFO)
  {
    textAlign(CENTER);
    textSize(50);
    fill(0);
    text("HOW TO PLAY", 780, 100);
    textAlign(LEFT);
    textSize(30);
    text(inst, 100, 200);
    text(backButton, 50, 50);
    float backWidth = textWidth(backButton);
    noFill();
    rect(45, 20, backWidth + 10, 40);
  }
}

public void draw()
{
  background(240);
  if (mode == PLAY || mode == WIN || mode == INTRO || mode == WAITFORKEY)
  {
    setupCodeArea();
    checkerboard();
    
    //if code should be shown, show it
    if (renderCode)
    {
      for (Code c : codeList)
      {
        c.update();
      }
    }
    
    //reset robot if necessary
    if (r1.getCX() == -1)
    {
      r1.setGreen();
      for (Level l : allLevels)
      {
        if (l.getLevelNum() == lv)
        {
          r1.setStartPos(l.getStartPosX(), l.getStartPosY());
          robPosX = r1.getCX();
          robPosY = r1.getCY();
          newRobX = robPosX;
          newRobY = robPosY;
        }
      }
    }
    
    //draw robot character
    r1.update();
    

    //update robPosX and robPosY
    robPosX = r1.getCX();
    robPosY = r1.getCY();

    checkCode();

    updateCurLevel();
    if (checkWin())
    {
      mode = WIN;
    }
    if (mode == INTRO)
    {
      stroke(0);
      fill(240);
      rect(260, 167, 1040, 667);
      fill(0);
      textAlign(CENTER);
      textSize(50);
      text("Instructions", 780, 220);
      textSize(20);
      if (lv == 1)
      {
        text(introText1, 780, 270);
      }
      else if (lv == 4)
      {
        text(introText2, 780, 270);
      }
      else if (lv == 5)
      {
        text(introText3, 780, 270);
      }
      else if (lv == 6)
      {
        text(introText4, 780, 270);
      }
      else if (lv == 8)
      {
        text(introText5, 780, 270);
      }
      else if (lv == 12)
      {
        text(introText6, 780, 270);
      }
      else if (lv == 14)
      {
        text(introText7, 780, 270);
      }
      else if (lv == 15)
      {
        text(introText8, 780, 270);
      }
      else if (lv == 16)
      {
        text(introText9, 780, 270);
      }
      fill(240);
      rect(1120, 754, 150, 50);
      fill(0);
      textSize(30);
      text("Start", 1195, 789);
    }
    else if (mode == WAITFORKEY)
    {
      stroke(0);
      fill(240);
      rect(260, 167, 1040, 667);
      fill(0);
      textAlign(CENTER);
      textSize(50);
      text("PRESS A NUMBER ON YOUR KEYBOARD", 780, 500);
      fill(240);
      rect(1120, 754, 150, 50);
      fill(0);
      textSize(30);
      text("Cancel", 1195, 789);
    }
  }
  else if (mode == MENU)
  {
    textAlign(CENTER);
    textSize(50);
    fill(0);
    text("PLAY", 780, 300);
    text("HOW TO PLAY", 780, 400);
    text("LEVEL SELECT", 780, 500);
    textSize(14);
    text("Click on an option.", 780, 200);
    textSize(100);
    text("ROBB13 THE ROBOT", 780, 100);
  }
  else if (mode == INFO)
  {
    textAlign(CENTER);
    textSize(50);
    fill(0);
    text("HOW TO PLAY", 780, 100);
    textAlign(LEFT);
    textSize(30);
    text(inst, 100, 200);
    text(backButton, 50, 50);
    float backWidth = textWidth(backButton);
    noFill();
    rect(45, 20, backWidth + 10, 40);
  }
  else if (mode == SELECT)
  {
    textAlign(CENTER);
    textSize(50);
    noFill();
    int lvlCount = 0;
    for (int row = 125; row < 925; row+= 200)
    {
      for (int col = 75; col < 1560 ; col+= 400)
      {
        lvlCount++;
        rect(col, row, 100, 100);
        text(lvlCount, col + 50, row + 60);
      }
    }
    textSize(30);
    textAlign(LEFT);
    text(backButton, 50, 50);
    float backWidth = textWidth(backButton);
    noFill();
    rect(45, 20, backWidth + 10, 40);
  }
}


public void checkerboard()
{
  int row = 1;

  //create checkerboard style grid
  fill(125, 189, 158);
  for (int i = 0; i < 1000; i += 100)
  {
    if (row % 2 == 1)
    {
      for (int j = 0; j < 1000; j += 200)
      {
        rect(j, i, 100, 100);
      }
    } else
    {
      for (int j = 100; j < 1000; j += 200)
      {
        rect(j, i, 100, 100);
      }
    }
    row++;
  }
}

public void setupCodeArea()
{
    fill(255);
    stroke(125, 189, 158);
    rect (1001, 0, 558, 999);
    line(1001, 300, 1559, 300);
    //create run, delete, reset and main menu buttons
    stroke(0);
    rect(1498, 310, 50, 50);
    rect(1498, 370, 50, 50);
    rect(1498, 430, 50, 50);
    fill(0);
    textSize(40);
    text("R", 1525, 470);
    stroke(15, 245, 15);
    fill(15, 245, 15);
    triangle(1502, 314, 1502, 356, 1544, 335);
    stroke(255, 0, 0);
    line(1500, 372, 1546, 418);
    line(1546, 372, 1500, 418);
    stroke(0);
    
    textAlign(LEFT);
    textSize(20);
    stroke(0);
    fill(0);
    text("Main Menu", 1443, 275);
    noFill();
    rect(1440, 250, 110, 30);
}


public void mousePressed()
{
  if (mode == PLAY)
  {
    //check if code is currently running
    if (!runningCode)
    {
      //if play button is clicked, run code
      if (mouseX >= 1498 && mouseX <= 1548 && mouseY >= 310 && mouseY <= 360)
      {
        runningCode = true;
        for (Code c : codeList)
        {
          activeCodeList.add(c);
        }
      }
      //if delete button is clicked, delete last line of code
      else if (mouseX >= 1498 && mouseX <= 1548 && mouseY >= 370 && mouseY <= 420)
      {
        if (codeList.size() > 0)
        {
          codeList.remove(codeList.size() - 1);
          if (wordsList.get(wordsList.size() - 1) == 1)
          {
            firstWord = true;
            secondWord = false;
            thirdWord = false;
            linePosY -= lineGap;
            
            for (Command c : firstWordCommands)
            {
              c.setBlack();
            }
            
            for (Command c : secondWordCommands)
            {
              c.setGrey();
            }
            
            for (Command c : thirdWordCommands)
            {
              c.setGrey();
            }
            
            if (colourChangedList.get(colourChangedList.size() - 1) == 1)
            {
              colourChanged = false;
              for (Command c : colourChangeCommands)
              {
                c.setBlack();
              }
            }
            else
            {
              if (colourChangedList.size() > 1)
              {
                if (colourChangedList.get(colourChangedList.size() - 2) == 1)
                {
                  colourChanged = true;
                  for (Command c : colourChangeCommands)
                  {
                    c.setGrey();
                  }
                }
              }
            }
            
            wordsList.remove(wordsList.size() - 1);
            numLines--;
          }
          else if (wordsList.get(wordsList.size() - 1) == 2)
          {
            firstWord = false;
            secondWord = true;
            thirdWord = false;
            linePosX -= wordGap;
            
            for (Command c : firstWordCommands)
            {
              c.setGrey();
            }
            
            for (Command c : secondWordCommands)
            {
              c.setBlack();
            }
            
            for (Command c : thirdWordCommands)
            {
              c.setGrey();
            }
            
            wordsList.remove(wordsList.size() - 1);
          }
          else if (wordsList.get(wordsList.size() - 1) == 3)
          {
            firstWord = false;
            secondWord = false;
            thirdWord = true;
            linePosY -= lineGap;
            linePosX += (2 * wordGap);
            
            for (Command c : firstWordCommands)
            {
              c.setGrey();
            }
            
            for (Command c : secondWordCommands)
            {
              c.setGrey();
            }
            
            for (Command c : thirdWordCommands)
            {
              c.setBlack();
            }
            
            wordsList.remove(wordsList.size() - 1);
          }
          else if (wordsList.get(wordsList.size() - 1) == 4)
          {
            //Special case for "If"
            firstWord = true;
            secondWord = false;
            thirdWord = false;
            linePosX -= wordGap;
            
            for (Command c : firstWordCommands)
            {
              c.setBlack();
            }
            
            for (Command c : secondWordCommands)
            {
              c.setGrey();
            }
            
            for (Command c : thirdWordCommands)
            {
              c.setGrey();
            }
            
            wordsList.remove(wordsList.size() - 1);
            numLines--;
            ifCount--;
            
            if (ifCount % 2 == 0)
            {
              ifUsed = false;
              endIfCom.setGrey();
            }
          }
          else if (wordsList.get(wordsList.size() - 1) == 5)
          {
            //Special case for "End If"
            firstWord = true;
            secondWord = false;
            thirdWord = false;
            linePosY -= lineGap;
            
            for (Command c : firstWordCommands)
            {
              c.setBlack();
            }
            
            for (Command c : secondWordCommands)
            {
              c.setGrey();
            }
            
            for (Command c : thirdWordCommands)
            {
              c.setGrey();
            }
            
            if (colourChangedList.get(colourChangedList.size() - 1) == 1)
            {
              colourChanged = false;
              for (Command c : colourChangeCommands)
              {
                c.setBlack();
              }
            }
            else
            {
              if (colourChangedList.get(colourChangedList.size() - 2) == 1)
              {
                colourChanged = true;
                for (Command c : colourChangeCommands)
                {
                  c.setGrey();
                }
              }
            }
            
            wordsList.remove(wordsList.size() - 1);
            numLines--;
            ifCount++;
            
            ifUsed = true;
            endIfCom.setBlack();
          }
          
        }
      }
      //if reset button is clicked reset level
      else if (mouseX >= 1498 && mouseX <= 1548 && mouseY >= 430 && mouseY <= 480)
      {
        resetLevel();
      }
      //if main menu button clicked, change mode to MENU
      else if (mouseX >= 1440 && mouseX <= 1550 && mouseY >= 250 && mouseY <= 280)
      {
        mode = MENU;
      }
      //check if line limit has been reached
      else if (numLines < lineLimit)
      {
        //if Up button is clicked, add line of code with Up command
        if (mouseX >= 1010 && mouseX <= 1110 && mouseY >=10 && mouseY <= 40 && firstWord)
        {
          codeList.add(new Code("Up", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        //if Down button is clicked, add line of code with Down command
        else if (mouseX >= 1010 + buttonHorGap && mouseX <= 1110 + buttonHorGap && mouseY >=10 && mouseY <= 40 && firstWord)
        {
          codeList.add(new Code("Down", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        //if Left button is clicked, add line of code with Left command
        else if (mouseX >= 1010 + (2 * buttonHorGap) && mouseX <= 1110 + (2 * buttonHorGap) && mouseY >=10 && mouseY <= 40 && firstWord)
        {
          codeList.add(new Code("Left", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        //if Right button is clicked, add line of code with Right command
        else if (mouseX >= 1010 + (3 * buttonHorGap) && mouseX <= 1110 + (3 * buttonHorGap) && mouseY >=10 && mouseY <= 40 && firstWord)
        {
          codeList.add(new Code("Right", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 && mouseX <= 1110 && mouseY >= 10 + buttonVerGap && mouseY <= 40 + buttonVerGap && firstWord && lv >= 6 && !colourChanged)
        {
          codeList.add(new Code("Go Green", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(1);
          numLines++;
          colourChanged = true;
          
          for (Command c : colourChangeCommands)
          {
            c.setGrey();
          }
        }
        else if (mouseX >= 1010 + buttonHorGap && mouseX <= 1110 + buttonHorGap && mouseY >= 10 + buttonVerGap && mouseY <= 40 + buttonVerGap && firstWord && lv >= 6 && !colourChanged)
        {
          codeList.add(new Code("Go Red", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(1);
          numLines++;
          colourChanged = true;
          for (Command c : colourChangeCommands)
          {
            c.setGrey();
          }
        }
        else if (mouseX >= 1010 + (2 * buttonHorGap) && mouseX <= 1110 + (2 * buttonHorGap) && mouseY >= 10 + buttonVerGap && mouseY <= 40 + buttonVerGap && firstWord && lv >= 6 && !colourChanged)
        {
          codeList.add(new Code("Go Blue", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(1);
          numLines++;
          colourChanged = true;
          for (Command c : colourChangeCommands)
          {
            c.setGrey();
          }
        }
        else if (mouseX >= 1010 + (3 * buttonHorGap) && mouseX <= 1110 + (3 * buttonHorGap) && mouseY >= 10 + buttonVerGap && mouseY <= 40 + buttonVerGap && firstWord && lv >= 12)
        {
          codeList.add(new Code("If", linePosX, linePosY));
          renderCode = true;
          firstWord = false;
          secondWord = true;
          
          ifUsed = true;
          ifCount++;
          
          for (Command c : secondWordCommands)
          {
            c.setBlack();
          }
          
          for (Command c : firstWordCommands)
          {
            c.setGrey();
          }
          
          linePosX += wordGap;
          wordsList.append(4);
          colourChangedList.append(0);
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
          
        }
        else if(mouseX >= 1010 + (4 * buttonHorGap) && mouseX <= 1110 + (4 * buttonHorGap) && mouseY >= 10 && mouseY <= 40 && firstWord && ((lv >= 8 && lv < 12) || lv >= 14))
        {
          codeList.add(new Code("Loop x2", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if(mouseX >= 1010 + (4 * buttonHorGap) && mouseX <= 1110 + (4 * buttonHorGap) && mouseY >= 10 + (3 * buttonVerGap) && mouseY <= 40 + (3 * buttonVerGap) && firstWord && ((lv >= 8 && lv < 12) || lv >= 14))
        {
          codeList.add(new Code("Loop x5", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 + (4 * buttonHorGap) && mouseX <= 1110 + (4 * buttonHorGap) && mouseY >= 10 + buttonVerGap && mouseY <= 40 + buttonVerGap && firstWord && ((lv >= 8 && lv < 12) || lv >= 14))
        {
          codeList.add(new Code("Loop End", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 && mouseX <= 1110 && mouseY >= 10 + (2 * buttonVerGap) && mouseY <= 40 + (2 * buttonVerGap) && thirdWord && lv >= 12)
        {
          codeList.add(new Code("Is Green", linePosX, linePosY));
          renderCode = true;
          thirdWord = false;
          firstWord = true;
          
          for (Command c : firstWordCommands)
          {
            c.setBlack();
          }
          
          for (Command c : thirdWordCommands)
          {
            c.setGrey();
          }
          
          linePosX -= (2 * wordGap);
          linePosY += lineGap;
          wordsList.append(3);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 + buttonHorGap && mouseX <= 1110 + buttonHorGap && mouseY >= 10 + (2 * buttonVerGap) && mouseY <= 40 + (2 * buttonVerGap) && thirdWord && lv >= 12)
        {
          codeList.add(new Code("Is Red", linePosX, linePosY));
          renderCode = true;
          thirdWord = false;
          firstWord = true;
          
          for (Command c : firstWordCommands)
          {
            c.setBlack();
          }
          
          for (Command c : thirdWordCommands)
          {
            c.setGrey();
          }
          
          linePosX -= (2 * wordGap);
          linePosY += lineGap;
          wordsList.append(3);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 + (2 * buttonHorGap) && mouseX <= 1110 + (2 * buttonHorGap) && mouseY >= 10 + (2 * buttonVerGap) && mouseY <= 40 + (2 * buttonVerGap) && thirdWord && lv >= 12)
        {
          codeList.add(new Code("Is Blue", linePosX, linePosY));
          renderCode = true;
          thirdWord = false;
          firstWord = true;
          
          for (Command c : firstWordCommands)
          {
            c.setBlack();
          }
          
          for (Command c : thirdWordCommands)
          {
            c.setGrey();
          }
          
          linePosX -= (2 * wordGap);
          linePosY += lineGap;
          wordsList.append(3);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 + (3 * buttonHorGap) && mouseX <= 1110 + (3 * buttonHorGap) && mouseY >= 10 + (2 * buttonVerGap) && mouseY <= 40 + (2 * buttonVerGap) && firstWord && lv >= 12)
        {
          codeList.add(new Code("Else", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 + (4 * buttonHorGap) && mouseX <= 1110 + (4 * buttonHorGap) && mouseY >= 10 + (2 * buttonVerGap) && mouseY <= 40 + (2 * buttonVerGap) && secondWord && lv >= 12)
        {
          codeList.add(new Code("Button", linePosX, linePosY));
          renderCode = true;
          secondWord = false;
          thirdWord = true;
          
          for (Command c : thirdWordCommands)
          {
            c.setBlack();
          }
          
          for (Command c : secondWordCommands)
          {
            c.setGrey();
          }
          
          linePosX += wordGap;
          wordsList.append(2);
          colourChangedList.append(0);
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
        else if (mouseX >= 1010 + (3 * buttonHorGap) && mouseX <= 1110 + (3 * buttonHorGap) && mouseY >= 10 + (3 * buttonVerGap) && mouseY <= 40 + (3 * buttonVerGap) && firstWord && lv >= 12 && ifUsed)
        {
          codeList.add(new Code("End If", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(5);
          colourChangedList.append(0);
          numLines++;
          
          ifUsed = false;
          ifCount --;
          
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
          
          endIfCom.setGrey();
        }
        else if (mouseX >= 1010 && mouseX <= 1110 && mouseY >= 10 + (3 * buttonVerGap) && mouseY <= 40 + (3 * buttonVerGap) && firstWord && lv >= 15)
        {
          mode = WAITFORKEY;
        }
        else if (mouseX >= 1010 + buttonHorGap && mouseX <= 1110 + buttonHorGap && mouseY >= 10 + (3 * buttonVerGap) && mouseY <= 40 + (3 * buttonVerGap) && firstWord && lv >= 15)
        {
          codeList.add(new Code("Loop x?", linePosX, linePosY));
          renderCode = true;
          linePosY += lineGap;
          wordsList.append(1);
          colourChangedList.append(0);
          numLines++;
          if (colourChanged)
          {
            colourChanged = false;
            for (Command c : colourChangeCommands)
            {
                  c.setBlack();
            }
          }
        }
      }
    }
  }
  else if (mode == MENU)
  {
    if (mouseX > 710 && mouseX < 850 && mouseY > 250 && mouseY < 300)
    {
      mode = INTRO;
    } 
    else if (mouseX > 515 && mouseX < 1045 && mouseY > 350 && mouseY < 400)
    {
      mode = INFO;
    }
    else if (mouseX > 515 && mouseX < 1045 && mouseY > 450 && mouseY < 500)
    {
      mode = SELECT;
    }
  }
  else if (mode == INFO)
  {
    textSize(30);
    float backWidth = textWidth(backButton);
    if (mouseX > 45 && mouseX < backWidth + 55 && mouseY > 20 && mouseY < 60)
    {
      mode = MENU;
    }
  }
  else if (mode == SELECT)
  {
    /*
    for (int row = 125; row < 925; row+= 200)
    {
      for (int col = 75; col < 1560 ; col+= 400)
      {
    */
    textSize(30);
    float backWidth = textWidth(backButton);
    if (mouseX > 45 && mouseX < backWidth + 55 && mouseY > 20 && mouseY < 60)
    {
      mode = MENU;
    }
    else if (mouseX > 75 && mouseX < 175 && mouseY > 125 && mouseY < 225)
    {
      lv = 1;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 475 && mouseX < 575 && mouseY > 125 && mouseY < 225)
    {
      lv = 2;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 875 && mouseX < 975 && mouseY > 125 && mouseY < 225)
    {
      lv = 3;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 1275 && mouseX < 1375 && mouseY > 125 && mouseY < 225)
    {
      lv = 4;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 75 && mouseX < 175 && mouseY > 325 && mouseY < 425)
    {
      lv = 5;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 475 && mouseX < 575 && mouseY > 325 && mouseY < 425)
    {
      lv = 6;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 875 && mouseX < 975 && mouseY > 325 && mouseY < 425)
    {
      lv = 7;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 1275 && mouseX < 1375 && mouseY > 325 && mouseY < 425)
    {
      lv = 8;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 75 && mouseX < 175 && mouseY > 525 && mouseY < 625)
    {
      lv = 9;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 475 && mouseX < 575 && mouseY > 525 && mouseY < 625)
    {
      lv = 10;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 875 && mouseX < 975 && mouseY > 525 && mouseY < 625)
    {
      lv = 11;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 1275 && mouseX < 1375 && mouseY > 525 && mouseY < 625)
    {
      lv = 12;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 75 && mouseX < 175 && mouseY > 725 && mouseY < 825)
    {
      lv = 13;
      resetLevel();
      mode = PLAY;
    }
    else if (mouseX > 475 && mouseX < 575 && mouseY > 725 && mouseY < 825)
    {
      lv = 14;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 875 && mouseX < 975 && mouseY > 725 && mouseY < 825)
    {
      lv = 15;
      resetLevel();
      mode = INTRO;
    }
    else if (mouseX > 1275 && mouseX < 1375 && mouseY > 725 && mouseY < 825)
    {
      lv = 16;
      resetLevel();
      mode = INTRO;
    }
  }
  else if (mode == WIN)
  {
    if (mouseX > 1120 && mouseX < 1270 && mouseY > 754 && mouseY < 804 && lv !=16)
    {
      lv++;
      resetLevel();
      if (lv == 4 || lv == 5 || lv == 6 || lv == 8 || lv == 12 || lv == 14 || lv == 15 || lv == 16)
      {
        mode = INTRO;
      }
      else
      {
        mode = PLAY;
      }
    }
    else if(mouseX > 1120 && mouseX < 1270 && mouseY > 754 && mouseY < 804)
    {
      mode = MENU;
    }
  }
  else if (mode == INTRO)
  {
    if (mouseX > 1120 && mouseX < 1270 && mouseY > 754 && mouseY < 804)
    {
      resetLevel();
      mode = PLAY;
    }
  }
  else if (mode == WAITFORKEY)
  {
    if (mouseX > 1120 && mouseX < 1270 && mouseY > 754 && mouseY < 804)
    {
      mode = PLAY;
    }
  }
}

public void keyPressed()
{
  if (mode == WAITFORKEY)
  {
    fill(0);
    stroke(0);
    textSize(25);
    textAlign(LEFT);
    
    if (key == '0')
    {
      addUserVarCode(0);
    }
    else if (key == '1')
    {
      addUserVarCode(1);
    }
    else if (key == '2')
    {
      addUserVarCode(2);
    }
    else if (key == '3')
    {
      addUserVarCode(3);
    }
    else if (key == '4')
    {
      addUserVarCode(4);
    }
    else if (key == '5')
    {
      addUserVarCode(5);
    }
    else if (key == '6')
    {
      addUserVarCode(6);
    }
    else if (key == '7')
    {
      addUserVarCode(7);
    }
    else if (key == '8')
    {
      addUserVarCode(8);
    }
    else if (key == '9')
    {
      addUserVarCode(9);
    }
    
    mode = PLAY;
  }
}

public void addUserVarCode(int num)
{
  codeList.add(new Code("? = " + num, linePosX, linePosY));
  renderCode = true;
  linePosY += lineGap;
  wordsList.append(1);
  colourChangedList.append(0);
  numLines++;
          
  if (colourChanged)
  {
    colourChanged = false;
    for (Command c : colourChangeCommands)
    {
      c.setBlack();
    }
  }
}

public void checkCode()
{
  //if circle isn't in the correct position, move it
  if (robPosX != newRobX)
  {
    r1.startMoving();
    if (newRobX - robPosX < 0)
    {
      r1.moveX(-speed);
      
    } else
    {
      r1.moveX(speed);
      
    }
  } 
  else if (robPosY != newRobY)
  {
    r1.startMoving();
    if (newRobY - robPosY < 0)
    {
      r1.moveY(-speed);
      
    } else
    {
      r1.moveY(speed);
      
    }
  }
  //if code is supposed to be running, run it
  else if (runningCode)
  {
    if (activeCodeList.size() > 0)
    {
      //increment current line
      curLine++;
      //activate code at current line
      if (curLine < codeList.size())
      {
        codeList.get(curLine).activate();
      }
      //decativate previous line of code if applicable
      if (curLine > 0 && curLine < codeList.size())
      {
        codeList.get(curLine - 1).deactivate();
      }
      //run line of code1)
      runCode(activeCodeList.get(0));
      //remove line of code from running list
      activeCodeList.remove(0);
    }
    //if no code is left to run, stop it from continuing and return robot to start
    else
    {
      runningCode = false;
      checkIf = true;
      for (Level l : allLevels)
      {
        if (l.getLevelNum() == lv)
        {
          if(!checkWin())
          {
            r1.setStartPos(-1, -1);
            if (l.getButtonList().size() > 0)
            {
              for (Button b : l.getButtonList())
              {
                b.reset();
              }
              l.closeDoor();
            }
          }
        }
      }
    }
  } 
  else
  {
    //ensure that all code is deactivated
    for (Code c : codeList)
    {
      c.deactivate();
    }
    //reset current line
    curLine = -1;
    r1.stopMoving();
  }
}

public void runCode(Code c)
{
  ArrayList<Wall> wallList = getCurWalls();
  boolean allowMove = true;
  int[] points;

  if (wallList != null)
  {
    if (c.getWord() == "Up" && newRobY - 100 > 0 && checkIf)
    {
      //loop through walls
      for (Wall w : wallList)
      {
        points = w.getPoints();
        //check if x-values of wall are different, if so wall is horizontal
        if (points[0] != points[2])
        {
          //check if the character currently lies between the two x-values
          if (max(points[0], points[2]) >= robPosX && min(points[0], points[2]) <= robPosX)
          {
            //check if wall is above character
            if (robPosY > points[1])
            {
              //check if moving would cross the wall
              if (newRobY - 100 < points[1])
              {
                //if so disallow move
                allowMove = false;
              }
            }
          }
        }
      }
      if (allowMove)
      {
        
        newRobY -= 100;
      }
    } 
    else if (c.getWord() == "Down" && newRobY + 100 < 1000 && checkIf)
    {
      //loop through walls
      for (Wall w : wallList)
      {
        points = w.getPoints();
        //check if x-values of wall are different, if so wall is horizontal
        if (points[0] != points[2])
        {
          //check if the character currently lies between the two x-values
          if (max(points[0], points[2]) >= robPosX && min(points[0], points[2]) <= robPosX)
          {
            //check if wall is below character
            if (robPosY < points[1])
            {
              //check if moving would cross the wall
              if (newRobY + 100 > points[1])
              {
                //if so disallow move
                allowMove = false;
              }
            }
          }
        }
      }
      if (allowMove)
      {
        
        newRobY += 100;
      }
    } 
    else if (c.getWord() == "Left" && newRobX - 100 > 0 && checkIf)
    {
      //loop through walls
      for (Wall w : wallList)
      {
        points = w.getPoints();
        //check if x-values of wall are the same, if so wall is vertical
        if (points[0] == points[2])
        {
          //check if the character currently lies between the two y-values
          if (max(points[1], points[3]) >= robPosY && min(points[1], points[3]) <= robPosY)
          {
            //check if wall is left of character
            if (robPosX > points[0])
            {
              //check if moving would cross the wall
              if (newRobX - 100 < points[0])
              {
                //if so disallow move
                allowMove = false;
              }
            }
          }
        }
      }
      if (allowMove)
      {
        
        newRobX -= 100;
      }
    } 
    else if (c.getWord() == "Right" && newRobX + 100 < 1000 && checkIf)
    {
      //loop through walls
      for (Wall w : wallList)
      {
        points = w.getPoints();
        //check if x-values of wall are the same, if so wall is vertical
        if (points[0] == points[2])
        {
          //check if the character currently lies between the two y-values
          if (max(points[1], points[3]) >= robPosY && min(points[1], points[3]) <= robPosY)
          {
            //check if wall is right of character
            if (robPosX < points[0])
            {
              //check if moving would cross the wall
              if (newRobX + 100 > points[0])
              {
                //if so disallow move
                allowMove = false;
              }
            }
          }
        }
      }
      if (allowMove)
      {
        
        newRobX += 100;
      }
    }
    else if (c.getWord() == "Go Green" && checkIf)
    {
      r1.setGreen();
    }
    else if (c.getWord() == "Go Red" && checkIf)
    {
      r1.setRed();
    }
    else if (c.getWord() == "Go Blue" && checkIf)
    {
      r1.setBlue();
    }
    else if (c.getWord() == "Loop x2" && checkIf)
    {
      loopCode(2);
    }
    else if (c.getWord() == "Loop x5" && checkIf)
    {
      loopCode(5);
    }
    else if (c.getWord() == "Loop x?" && checkIf)
    {
      loopCode(currentUserVar);
    }
    else if (c.getWord().equals("? = 0")  && checkIf)
    {
      currentUserVar = 0;
    }
    else if (c.getWord().equals("? = 1")  && checkIf)
    {
      currentUserVar = 1;
    }
    else if (c.getWord().equals("? = 2")  && checkIf)
    {
      currentUserVar = 2;
    }
    else if (c.getWord().equals("? = 3")  && checkIf)
    {
      currentUserVar = 3;
    }
    else if (c.getWord().equals("? = 4")  && checkIf)
    {
      currentUserVar = 4;
    }
    else if (c.getWord().equals("? = 5")  && checkIf)
    {
      currentUserVar = 5;
    }
    else if (c.getWord().equals("? = 6")  && checkIf)
    {
      currentUserVar = 6;
    }
    else if (c.getWord().equals("? = 7")  && checkIf)
    {
      currentUserVar = 7;
    }
    else if (c.getWord().equals("? = 8")  && checkIf)
    {
      currentUserVar = 8;
    }
    else if (c.getWord().equals("? = 9")  && checkIf)
    {
      currentUserVar = 9;
    }
    else if(c.getWord() == "If" && checkIf)
    {
      
      if (activeCodeList.size() >= 2)
      {
        Code nextLine = activeCodeList.get(1);
        if (nextLine.getWord() == "Button")
        {
           if (activeCodeList.size() >= 3)
           {
              Code thirdLine = activeCodeList.get(2);
              ArrayList<Button> curButtonList = new ArrayList<Button>();
              
              for (Level l : allLevels)
              {
                if (l.getLevelNum() == lv)
                {
                  curButtonList = l.getButtonList();
                }
              }
              
              checkIf = false;
              
              for (Button b : curButtonList)
              {
                if (b.checkCollided())
                {
                  if (thirdLine.getWord() == "Is Green")
                  {
                    if (b.getColour() == GREEN)
                    {
                      checkIf = true;
                    }
                    else
                    {
                      checkIf = false;
                    }
                  }
                  else if(thirdLine.getWord() == "Is Red")
                  {
                    if (b.getColour() == RED)
                    {
                      checkIf = true;
                    }
                    else
                    {
                      checkIf = false;
                    }
                  }
                  else if(thirdLine.getWord() == "Is Blue")
                  {
                    if (b.getColour() == BLUE)
                    {
                      checkIf = true;
                    }
                    else
                    {
                      checkIf = false;
                    }
                  }
                }
              }
           }
        }
      }
      
    }
    else if (c.getWord() == "Else" && !checkIf)
    {
      checkIf = true;
    }
    else if (c.getWord() == "Else" && checkIf)
    {
      checkIf = false;
    }
    else if (c.getWord() == "End If" && !checkIf)
    {
      checkIf = true;
    }
  } 
  else
  {
    if (c.getWord() == "Up" && newRobY - 100 > 0 && checkIf)
    {
      
      newRobY -= 100;
    } else if (c.getWord() == "Down" && newRobY + 100 < 1000 && checkIf)
    {
      
      newRobY += 100;
    } else if (c.getWord() == "Left" && newRobX - 100 > 0 && checkIf)
    {
      
      newRobX -= 100;
    } else if (c.getWord() == "Right" && newRobX + 100 < 1000 && checkIf)
    {
      
      newRobX += 100;
    }
  }
}

public void loopCode(int numLoops)
{
  ArrayList<Code> newCodeList1 = new ArrayList<Code>();
  ArrayList<Code> newCodeList2 = new ArrayList<Code>();
  ArrayList<Code> newCodeList3 = new ArrayList<Code>();
  boolean foundEnd = false;
  int endPos = -1;
  int innerLoops = 0;
  
  //add the loop command to the first list
  newCodeList1.add(activeCodeList.get(0));
  
  //add everything between the loop command and the loop end command to the second list
  for (int i = 1; i < activeCodeList.size(); i++)
  {
    //if a new loop starts, increase innerLoops
    if ((activeCodeList.get(i).getWord() == "Loop x2" || activeCodeList.get(i).getWord() == "Loop x5" || activeCodeList.get(i).getWord() == "Loop x?") && !foundEnd)
    {
      innerLoops++;
    }
    
    //if there are no active inner loops, the end has not been found and "Loop End" is found, set the end point
    if (activeCodeList.get(i).getWord() == "Loop End" && !foundEnd && innerLoops == 0)
    {
      foundEnd = true;
      endPos = i;
    }
    //if a "Loop End" is found and the end has not been found but an inner loop is active, decrease innerLoops
    else if (activeCodeList.get(i).getWord() == "Loop End" && !foundEnd)
    {
      innerLoops--;
    }
    
    if (!foundEnd)
    {
      newCodeList2.add(activeCodeList.get(i));
    }
  }
  
  //add everything after the loop end to the third list
  if (endPos < activeCodeList.size() - 1 && foundEnd)
  {
    for (int i = endPos + 1; i < activeCodeList.size(); i++)
    {
      newCodeList3.add(activeCodeList.get(i));
    }
  }
  
  //append the second list to the first the correct number of times
   for (int i = 0; i < numLoops; i++)
   {
      for (Code c : newCodeList2)
      {
        newCodeList1.add(c);
      }
   }
   
   //append the third list to the first
   for (Code c: newCodeList3)
   {
     newCodeList1.add(c);
   }
  
  activeCodeList = newCodeList1;
}


public void levelsSetup()
{
  //set up level 1
  Level l1 = new Level(1);
  l1.addWall(200, 200, 500, 200);
  l1.addWall(200, 200, 200, 500);
  l1.addWall(500, 200, 500, 500);
  l1.addWall(200, 500, 500, 500);
  l1.addGoal(450, 450);
  l1.setStartPos(250, 250);
  l1.setCommandList(commandList1);
  l1.setOutroText(outroText1);
  allLevels.add(l1);
  
  //set up level 2
  Level l2 = new Level(2);
  l2.addWall(200, 200, 400, 200);
  l2.addWall(200, 200, 200, 700);
  l2.addWall(300, 200, 300, 600);
  l2.addWall(400, 200, 400, 700);
  l2.addWall(200, 700, 400, 700);
  l2.addGoal(350, 250);
  l2.setStartPos(250, 250);
  l2.setCommandList(commandList1);
  l2.setOutroText(outroTextGen);
  allLevels.add(l2);
  
  //set up level 3
  Level l3 = new Level(3);
  l3.addWall(200, 200, 800, 200);
  l3.addWall(200, 200, 200, 400);
  l3.addWall(800, 200, 800, 400);
  l3.addWall(200, 400, 800, 400);
  l3.addWall(300, 200, 300, 300);
  l3.addWall(400, 400, 400, 300);
  l3.addWall(500, 200, 500, 300);
  l3.addWall(600, 400, 600, 300);
  l3.addWall(700, 200, 700, 300);
  l3.addGoal(750, 250);
  l3.setStartPos(250, 250);
  l3.setCommandList(commandList1);
  l3.setOutroText(outroText3);
  allLevels.add(l3);
  
  //One button introduced
  Level l4 = new Level(4);
  l4.addWall(200, 200, 800, 200);
  l4.addWall(200, 200, 200, 300);
  l4.addWall(800, 200, 800, 300);
  l4.addWall(200, 300, 300, 300);
  l4.addWall(400, 300, 800, 300);
  l4.addWall(300, 300, 300, 400);
  l4.addWall(400, 300, 400, 400);
  l4.addWall(300, 400, 400, 400);
  l4.addButton(750, 250);
  l4.addGoal(350, 350);
  l4.setStartPos(250, 250);
  l4.setCommandList(commandList1);
  l4.setOutroText(outroText4);
  allLevels.add(l4);
  
  //Multiple buttons introduced
  Level l5 = new Level(5);
  l5.addWall(400, 200, 500, 200);
  l5.addWall(400, 200, 400, 300);
  l5.addWall(500, 200, 500, 300);
  l5.addWall(500, 300, 600, 300);
  l5.addWall(600, 300, 600, 500);
  l5.addWall(500, 400, 600, 400);
  l5.addWall(400, 500, 600, 500);
  l5.addWall(400, 400, 400, 500);
  l5.addWall(300, 400, 400, 400);
  l5.addWall(300, 300, 300, 400);
  l5.addWall(300, 300, 400, 300);
  l5.addButton(450, 250);
  l5.addButton(350, 350);
  l5.addButton(550, 350);
  l5.addGoal(550, 450);
  l5.setStartPos(450, 450);
  l5.setCommandList(commandList1);
  l5.setOutroText(outroText5);
  allLevels.add(l5);
  
  //Coloured Buttons introduced
  Level l6 = new Level(6);
  l6.addWall(200, 200, 200, 300);
  l6.addWall(200, 200, 700, 200);
  l6.addWall(700, 200, 700, 300);
  l6.addWall(200, 300, 700, 300);
  l6.addButton(650, 250);
  l6.getButton(0).setRed();
  l6.addGoal(250, 250);
  l6.setStartPos(450, 250);
  l6.setCommandList(commandList2);
  l6.setOutroText(outroText6);
  allLevels.add(l6);
  
  Level l7 = new Level(7);
  l7.addWall(200, 200, 500, 200);
  l7.addWall(200, 200, 200, 500);
  l7.addWall(500, 200, 500, 500);
  l7.addWall(200, 500, 500, 500);
  l7.addWall(200, 300, 400, 300);
  l7.addWall(400, 300, 400, 400);
  l7.addWall(400, 400, 300, 400);
  l7.addButton(450, 250);
  l7.addButton(450, 450);
  l7.addButton(250, 450);
  l7.getButton(1).setRed();
  l7.getButton(2).setBlue();
  l7.addGoal(350, 350);
  l7.setStartPos(250, 250);
  l7.setCommandList(commandList2);
  l7.setOutroText(outroTextGen);
  allLevels.add(l7);
  
  //Loops intoduced
  Level l8 = new Level(8);
  l8.addWall(200, 200, 200, 300);
  l8.addWall(200, 200, 700, 200);
  l8.addWall(200, 300, 300, 300);
  l8.addWall(300, 300, 300, 700);
  l8.addWall(300, 700, 700, 700);
  l8.addWall(700, 200, 700, 700);
  l8.addWall(400, 200, 400, 600);
  l8.addWall(600, 200, 600, 600);
  l8.addWall(500, 700, 500, 300);
  l8.addGoal(650, 250);
  l8.setStartPos(250, 250);
  l8.setCommandList(commandList3);
  l8.setOutroText(outroText8);
  allLevels.add(l8);
  
  Level l9 = new Level(9);
  l9.addWall(400, 100, 600, 100);
  l9.addWall(400, 100, 400, 800);
  l9.addWall(600, 100, 600, 800);
  l9.addWall(400, 800, 600, 800);
  l9.addWall(500, 200, 500, 700);
  l9.addWall(400, 700, 500, 700);
  l9.addGoal(450, 650);
  l9.setStartPos(450, 750);
  l9.setCommandList(commandList3);
  l9.setOutroText(outroText9);
  allLevels.add(l9);
  
  Level l10 = new Level(10);
  l10.addWall(100, 100, 800, 100);
  l10.addWall(800, 100, 800, 800);
  l10.addWall(100, 100, 100, 200);
  l10.addWall(100, 200, 700, 200);
  l10.addWall(700, 200, 700, 700);
  l10.addWall(800, 800, 400, 800);
  l10.addWall(400, 800, 400, 700);
  l10.addWall(400, 700, 700, 700);
  l10.addGoal(450, 750);
  l10.setStartPos(150, 150);
  l10.setCommandList(commandList3);
  l10.setOutroText(outroText10);
  allLevels.add(l10);
  
  //Loops with buttons
  Level l11 = new Level(11);
  l11.addWall(100, 100, 300, 100);
  l11.addWall(100, 100, 100, 200);
  l11.addWall(100, 200, 200, 200);
  l11.addWall(300, 100, 300, 300);
  l11.addWall(200, 200, 200, 400);
  l11.addWall(200, 400, 300, 400);
  l11.addWall(300, 300, 500, 300);
  l11.addWall(400, 300, 400, 500);
  l11.addWall(300, 400, 300, 600);
  l11.addWall(300, 600, 500, 600);
  l11.addWall(500, 600, 500, 400);
  l11.addWall(500, 400, 600, 400);
  l11.addWall(600, 400, 600, 100);
  l11.addWall(500, 300, 500, 100);
  l11.addWall(500, 100, 600, 100);
  l11.addButton(250, 350);
  l11.addButton(350, 550);
  l11.addButton(450, 350);
  l11.getButton(0).setBlue();
  l11.getButton(2).setRed();
  l11.addGoal(550, 150);
  l11.setStartPos(150, 150);
  l11.setCommandList(commandList3);
  l11.setOutroText(outroText11);
  allLevels.add(l11);
  
  //If statements introduced
  Level l12 = new Level(12);
  l12.addWall(200, 100, 900, 100);
  l12.addWall(200, 100, 200, 300);
  l12.addWall(200, 300, 700, 300);
  l12.addWall(700, 300, 700, 400);
  l12.addWall(700, 400, 900, 400);
  l12.addWall(900, 100, 900, 400);
  l12.addButton(750, 250);
  l12.getButton(0).setCycling();
  l12.addGoal(750, 350);
  l12.setStartPos(250, 250);
  l12.setCommandList(commandList4);
  l12.setOutroText(outroText12);
  allLevels.add(l12);
  
  //Multiple if statements
  Level l13 = new Level(13);
  l13.addWall(200, 200, 600, 200);
  l13.addWall(200, 200, 200, 500);
  l13.addWall(200, 500, 600, 500);
  l13.addWall(600, 200, 600, 500);
  l13.addButton(350, 350);
  l13.addButton(450, 350);
  l13.getButton(0).setCycling();
  l13.getButton(1).setCycling();
  l13.addGoal(550, 350);
  l13.setStartPos(250, 350);
  l13.setCommandList(commandList4);
  l13.setOutroText(outroTextGen);
  allLevels.add(l13);
  
  //loops with ifs
  Level l14 = new Level(14);
  l14.addWall(200, 200, 900, 200);
  l14.addWall(200, 200, 200, 500);
  l14.addWall(200, 500, 900, 500);
  l14.addWall(900, 200, 900, 500);
  l14.addButton(350, 350);
  l14.addButton(450, 350);
  l14.addButton(550, 350);
  l14.addButton(650, 350);
  l14.addButton(750, 350);
  l14.getButton(0).setCycling();
  l14.getButton(1).setCycling();
  l14.getButton(2).setCycling();
  l14.getButton(3).setCycling();
  l14.getButton(4).setCycling();
  l14.addGoal(850, 350);
  l14.setStartPos(250, 350);
  l14.setCommandList(commandList5);
  l14.setOutroText(outroText14);
  allLevels.add(l14);
  
  //user variable introduced
  Level l15 = new Level(15);
  l15.addWall(100, 0, 100, 200);
  l15.addWall(100, 200, 200, 200);
  l15.addWall(200, 200, 200, 400);
  l15.addWall(200, 400, 300, 400);
  l15.addWall(300, 400, 300, 600);
  l15.addWall(300, 600, 400, 600);
  l15.addWall(400, 0, 400, 800);
  l15.addWall(500, 800, 500, 0);
  l15.addWall(0, 300, 100, 300);
  l15.addWall(100, 300, 100, 500);
  l15.addWall(100, 500, 200, 500);
  l15.addWall(200, 500, 200, 700);
  l15.addWall(200, 700, 300, 700);
  l15.addWall(300, 700, 300, 900);
  l15.addWall(300, 900, 600, 900);
  l15.addWall(600, 900, 600, 800);
  l15.addWall(600, 800, 500, 800);
  l15.addButton(450, 50);
  l15.addGoal(450, 850);
  l15.setStartPos(50, 50);
  l15.setCommandList(commandList6);
  l15.setOutroText(outroText15);
  allLevels.add(l15);
  
  //final level - nested loops
  Level l16 = new Level(16);
  l16.addWall(0, 200, 1000, 200);
  l16.addWall(0, 800, 1000, 800);
  l16.addWall(100, 200, 100, 700);
  l16.addWall(300, 200, 300, 700);
  l16.addWall(500, 200, 500, 700);
  l16.addWall(700, 200, 700, 700);
  l16.addWall(900, 200, 900, 700);
  l16.addWall(200, 800, 200, 300);
  l16.addWall(400, 800, 400, 300);
  l16.addWall(600, 800, 600, 300);
  l16.addWall(800, 800, 800, 300);
  //green buttons
  l16.addButton(50, 350);
  l16.addButton(50, 750);
  l16.addButton(250, 350);
  l16.addButton(250, 650);
  l16.addButton(450, 250);
  l16.addButton(450, 450);
  l16.addButton(450, 750);
  l16.addButton(650, 250);
  l16.addButton(650, 550);
  l16.addButton(850, 450);
  l16.addButton(850, 750);
  //red buttons
  l16.addButton(150, 750);
  l16.addButton(150, 250);
  l16.addButton(250, 750);
  l16.addButton(350, 750);
  l16.addButton(350, 450);
  l16.addButton(550, 650);
  l16.addButton(550, 350);
  l16.addButton(550, 250);
  l16.addButton(650, 750);
  l16.addButton(750, 550);
  l16.addButton(950, 750);
  l16.addButton(950, 350);
  for (int i = 11; i < 23; i++)
  {
    l16.getButton(i).setRed();
  }
  //blue buttons
  l16.addButton(250, 250);
  l16.addButton(350, 250);
  l16.addButton(750, 250);
  l16.addButton(850, 250);
  for (int i = 23; i < 27; i++)
  {
    l16.getButton(i).setBlue();
  }
  
  l16.addGoal (950, 250);
  l16.setStartPos(50, 250);
  l16.setCommandList(commandList6);
  l16.setOutroText(outroText16);
  allLevels.add(l16);
  
  
}

public void updateCurLevel()
{
  for (Level l : allLevels)
  {
    if (l.getLevelNum() == lv)
    {
      l.getGoal().checkCollision(r1);
      for (Button b : l.getButtonList())
      {
        b.checkCollision(r1);
      }
      l.update();
    }
  }
}

public ArrayList<Wall> getCurWalls()
{
  for (Level l : allLevels)
  {
    if (l.getLevelNum() == lv)
    {
      return l.getWallList();
    }
  }
  return null;
}

public boolean checkWin()
{
  for (Level l : allLevels)
  {
    if (l.getLevelNum() == lv)
    {
      return l.isComplete();
    }
  }
  return false;
}

public void resetLevel()
{
  ifCount = 0;
  checkIf = true;
  wordsList.clear();
  colourChangedList.clear();
  numLines = 0;
  allLevels.clear();
  codeList.clear();
  linePosX = 1010;
  linePosY = 350;
  levelsSetup();
  r1.setStartPos(-1, -1);
  r1.setGreen();
  currentUserVar = 0;
  
  firstWord = true;
  secondWord = false;
  thirdWord = false;
  colourChanged = false;
  ifUsed = false;
  
  for (Command c : firstWordCommands)
  {
    c.setBlack();
  }
  
  for (Command c : secondWordCommands)
  {
    c.setGrey();
  }
          
  for (Command c : thirdWordCommands)
  {
    c.setGrey();
  }
  
  endIfCom.setGrey();
  
}
class Button
{
  //the points of the centre of the goal
  private int cX, cY;
  private int wid = 50;
  private int hei = 50;
  private boolean visible = true;
  private boolean reached = false;
  private boolean collided = false;
  private PImage redButton;
  private PImage blueButton;
  private PImage greenButton;
  private boolean cycling = false;
  private int GREEN = 0;
  private int RED = 1;
  private int BLUE = 2;
  private int colour = GREEN;
  private int cycleTimer = 0;
  private float randomColour = 0;
  
  Button(int cX, int cY)
  {
    this.cX = cX;
    this.cY = cY;
    redButton = loadImage("redButton.png");
    blueButton = loadImage("blueButton.png");
    greenButton = loadImage("greenButton.png");
  }
  
  public void update()
  {
    render();
  }
  
  public void render()
  {
    if (visible)
    {
      int[] topLeft = getTopLeft();
      if (colour == RED)
      {
        image(redButton, topLeft[0], topLeft[1]);
      }
      else if (colour == BLUE)
      {
        image(blueButton, topLeft[0], topLeft[1]);
      }
      else if (colour == GREEN)
      {
        image(greenButton, topLeft[0], topLeft[1]);
      }
      if (cycling)
      {
        cycleTimer++;
        if (cycleTimer >= 45)
        {
          cycleTimer = 0;
          randomColour = random(0.5f, 3.5f);
          if (randomColour < 1.5f)
          {
            colour = GREEN;
          }
          else if (randomColour >= 1.5f && randomColour < 2.5f)
          {
            colour = RED;
          }
          else
          {
            colour = BLUE;
          }
        }
      }
    }
  }
  
  public int[] getTopLeft()
  {
    int x = cX - (wid / 2);
    int y = cY - (hei / 2);
    int[] topLeft = {x, y};
    return topLeft;
  }
  
  public int[] getCentre()
  {  
    int[] centre = {cX, cY};
    return centre;
  }
  
  public void hide()
  {
    this.visible = false;
  }
  
  public void show()
  {
    this.visible = true;
  }
  
  public void checkCollision(Robot r)
  {
    int[] centre = r.getCentre();
    int[] topLeft = this.getTopLeft();
    //check if centre of robot has same y position and is between the left and right sides
    if (centre[0] >= topLeft[0] && centre[1] == cY && centre[0] <= topLeft[0] + wid)
    {
      collided = true;
      if (colourMatch(r))
      {
        this.hide();
        reached = true;
        collided = false;
      }
    }
    //check if centre of robot has same x position and is between the top and bottom sides
    else if (centre[1] >= topLeft[1] && centre[0] == cX && centre[1] <= topLeft[1] + hei)
    {
      collided = true;
      if (colourMatch(r))
      {
        this.hide();
        reached = true;
        collided = false;
      }
    }
  }
  
  public boolean checkVisibility()
  {
    return this.visible;
  }
  
  public boolean checkReached()
  {
    return this.reached;
  }
  
  public void reset()
  {
    this.reached = false;
    this.show();
  }
  
  private boolean colourMatch(Robot r)
  {
    if (r.getColour() == this.colour)
    {
      return true;
    }
    return false;
  }
  
  public void setGreen()
  {
    this.colour = GREEN;
  }
  
  public void setRed()
  {
    this.colour = RED;
  }
  
  public void setBlue()
  {
    this.colour = BLUE;
  }
  
  public int getColour()
  {
    return this.colour;
  }
  
  public boolean checkCollided()
  {
    return this.collided;
  }
  
  public void setCycling()
  {
    this.cycling = true;
  }
}
class Code
{
  private String word;
  private int x, y;
  private int c1 = color(0, 0, 0);
  
  Code()
  {
    this.x = 0;
    this.y = 0;
    this.word = "null";
  }
  
  Code(String word, int x, int y)
  {
    this.word = word;
    this.x = x;
    this.y = y;
  }
  
  public void update()
  {
    render();
  }
  
  public void render()
  {
    stroke(c1);
    fill(c1);
    textAlign(LEFT);
    textSize(25);
    text(word, x, y);
  }
  
  public String getWord()
  {
    return this.word;
  }
  
  public void setWord(String word)
  {
   this.word = word;
  }
  
  public int getX()
  {
    return this.x;
  }
  
  public void setX(int x)
  {
   this.x = x;
  }
  
  public int getY()
  {
    return this.y;
  }
  
  public void setY(int y)
  {
   this.y = y;
  }
  
  public void activate()
  {
    this.c1 = color(255, 0, 0);
  }
  
  public void deactivate()
  {
    this.c1 = color(0, 0, 0);
  }
}
class Command
{
  private String name;
  private int x, y;
  private int greyValue = 0;
  
  Command(String name, int x, int y)
  {
    this.name = name;
    this.x = x;
    this.y = y;
  }
  
  public void update()
  {
    render();
  }
  
  public void render()
  {
    stroke(0);
    fill(255);
    rect(x, y, 100, 30);
    fill(greyValue);
    textAlign(CENTER);
    textSize(20);
    text(name, x + 50, y + 25);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
   this.name = name;
  }
  
  public int getX()
  {
    return this.x;
  }
  
  public void setX(int x)
  {
   this.x = x;
  }
  
  public int getY()
  {
    return this.y;
  }
  
  public void setY(int y)
  {
   this.y = y;
  }
  
  public void setGrey()
  {
    this.greyValue = 127;
  }
  
  public void setBlack()
  {
    this.greyValue = 0;
  }
}
class Goal
{
  //the points of the centre of the goal
  private int cX, cY;
  private int wid = 50;
  private int hei = 50;
  private boolean visible = true;
  private boolean reached = false;
  private PImage doorClosed;
  private PImage doorLocked;
  private boolean open = true;
  
  Goal(int cX, int cY)
  {
    this.cX = cX;
    this.cY = cY;
    doorClosed = loadImage("doorClosed.png");
    doorLocked = loadImage("doorLocked.png");
  }
  
  public void update()
  {
    render();
  }
  
  public void render()
  {
    if (visible)
    {
      int[] topLeft = getTopLeft();
      if (open)
      {
        image(doorClosed, topLeft[0], topLeft[1]);
      }
      else
      {
        image(doorLocked, topLeft[0], topLeft[1]);
      }
    }
  }
  
  public int[] getTopLeft()
  {
    int x = cX - (wid / 2);
    int y = cY - (hei / 2);
    int[] topLeft = {x, y};
    return topLeft;
  }
  
  public int[] getCentre()
  {  
    int[] centre = {cX, cY};
    return centre;
  }
  
  public void hide()
  {
    this.visible = false;
  }
  
  public void show()
  {
    this.visible = true;
  }
  
  public void checkCollision(Robot r)
  {
    int[] centre = r.getCentre();
    int[] topLeft = this.getTopLeft();
    //check if centre of robot has same y position and is between the left and right sides
    if (centre[0] >= topLeft[0] && centre[1] == cY && centre[0] <= topLeft[0] + wid)
    {
      if (open)
      {
        this.hide();
        reached = true;
      }
    }
    //check if centre of robot has same x position and is between the top and bottom sides
    else if (centre[1] >= topLeft[1] && centre[0] == cX && centre[1] <= topLeft[1] + hei)
    {
      if (open)
      {
        this.hide();
        reached = true;
      }
    }
  }
  
  public boolean checkVisibility()
  {
    return this.visible;
  }
  
  public boolean checkReached()
  {
    return this.reached;
  }
  
  public void closeDoor()
  {
    this.open = false;
  }
  
  public void openDoor()
  {
    this.open = true;
  }
}
class Level
{
  private int levelNum;
  private ArrayList<Wall> wallList = new ArrayList<Wall>();
  private ArrayList<Command> commandList = new ArrayList<Command>();
  private ArrayList<Button> buttonList = new ArrayList<Button>();
  private Goal g1;
  private int startPosX, startPosY;
  private boolean complete = false;
  private String outroText = "";
  
  //used for forcing the game to wait a short while before pop up appears
  private int winCounter = 0;
  
  Level(int levelNum)
  {
    this.levelNum = levelNum;
  }
  
  public void update()
  {
    render();
    updateCommands();
    checkComplete();
  }
  
  public void render()
  {
      if (buttonList.size() > 0)
      {
        checkButtons();
      }
      for (Wall w : wallList)
      {
        w.update();
      }
      for (Button b : buttonList)
      {
        b.update();
      }
      g1.update();
    if (complete && winCounter < 90)
    {
      winCounter++;
    }
    else if (complete)
    {
      stroke(0);
      fill(240);
      rect(260, 167, 1040, 667);
      fill(0);
      textAlign(CENTER);
      textSize(50);
      text("Level Complete", 780, 220);
      textSize(20);
      text(outroText, 780, 270);
      textSize(30);
      fill(240);
      rect(1120, 754, 150, 50);
      fill(0);
      if (levelNum != 16)
      {
        text("Next", 1195, 789);
      }
      else
      {
        text("Menu", 1195, 789);
      }
    }
  }
  
  public void addWall(int x1, int y1, int x2, int y2)
  {
    wallList.add(new Wall(x1, y1, x2, y2));
  }
  
  public void addButton (int x, int y)
  {
    buttonList.add(new Button (x, y));
  }
  
  public void setCommandList(Command[] givenList)
  {
    this.commandList.clear();
    for (Command c : givenList)
    {
      this.commandList.add(c);
    }
  }
  
  public void setOutroText(String outText)
  {
    this.outroText = outText;
  }
  
  public void addGoal(int x, int y)
  {
    g1 = new Goal(x, y);
  }
  
  public int getLevelNum()
  {
    return this.levelNum;
  }
  
  public ArrayList<Wall> getWallList()
  {
    return this.wallList;
  }
  
  public ArrayList<Command> getCommandList()
  {
    return this.commandList;
  }
  
  public ArrayList<Button> getButtonList()
  {
    return this.buttonList;
  }
  
  public Goal getGoal()
  {
    return g1;
  }
  
  public void closeDoor()
  {
    g1.closeDoor();
  }
  
  public void checkComplete()
  {
    this.complete = g1.checkReached();
  }
  
  public void setStartPos(int x, int y)
  {
    this.startPosX = x;
    this.startPosY = y;
  }
  
  public void updateCommands()
  {
    for (Command c : commandList)
    {
      c.update();
    }
  }
  
  public void checkButtons()
  {
    boolean buttonsReached = true;
    for (Button b : buttonList)
    {
      if (! b.checkReached())
      {
        buttonsReached = false;
      }
    }
    
    if (buttonsReached == true)
    {
      g1.openDoor();
    }
    else
    {
      g1.closeDoor();
    }
  }
  
  public int getStartPosX()
  {
    return this.startPosX;
  }
  
  public int getStartPosY()
  {
    return this.startPosY;
  }
  
  public boolean isComplete()
  {
    return this.complete;
  }
  
  public Button getButton(int pos)
  {
    return this.buttonList.get(pos);
  }
}
class Robot
{
  //the starting point of the character
  private int x, y;
  private int cX, cY;
  private int wid = 64;
  private int hei = 56;
  private boolean visible = true;
  private boolean moving = false;
  private PImage rob1, rob2, rob3, rob4, redRob1, redRob2, redRob3, redRob4, blueRob1, blueRob2, blueRob3, blueRob4;
  private int counter = 0;
  private int countDir = 1;
  private int robColour;
  private int GREEN = 0;
  private int RED = 1;
  private int BLUE = 2;

  Robot()
  {
    this.cX = -1;
    this.cY = -1;
    this.visible = true;
    this.robColour = GREEN;
    rob1 = loadImage("robotSpriteWalk1.png");
    rob2 = loadImage("robotSpriteWalk2.png");
    rob3 = loadImage("robotSpriteWalk3.png");
    rob4 = loadImage("robotSpriteWalk4.png");
    redRob1 = loadImage("redRobotSpriteWalk1.png");
    redRob2 = loadImage("redRobotSpriteWalk2.png");
    redRob3 = loadImage("redRobotSpriteWalk3.png");
    redRob4 = loadImage("redRobotSpriteWalk4.png");
    blueRob1 = loadImage("blueRobotSpriteWalk1.png");
    blueRob2 = loadImage("blueRobotSpriteWalk2.png");
    blueRob3 = loadImage("blueRobotSpriteWalk3.png");
    blueRob4 = loadImage("blueRobotSpriteWalk4.png");
  }

  public void update()
  {
    render();
  }

  public void render()
  {
    if (visible)
    {
      if (robColour == GREEN)
      {
        if (moving)
        {
          if (counter >= 0 && counter <= 10) 
          {
            image(rob1, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 10 && counter <= 20) 
          {
            image(rob2, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 20 && counter <= 30)
          {
            image(rob3, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 30 && counter <= 35)
          {
            image(rob4, cX - (wid / 2), cY - (hei / 2));
          } 
          else 
          {
            countDir = -countDir;
            if (countDir < 0)
            {
              image(rob4, cX - (wid / 2), cY - (hei / 2));
            } 
            else
            {
              image(rob1, cX - (wid / 2), cY - (hei / 2));
            }
          }
          counter = counter + countDir;
        } 
        else
        {
          image(rob1, cX - (wid / 2), cY - (hei / 2));
        }
      }
      else if (robColour == RED)
      {
        if (moving)
        {
          if (counter >= 0 && counter <= 10) 
          {
            image(redRob1, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 10 && counter <= 20) 
          {
            image(redRob2, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 20 && counter <= 30)
          {
            image(redRob3, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 30 && counter <= 35)
          {
            image(redRob4, cX - (wid / 2), cY - (hei / 2));
          } 
          else 
          {
            countDir = -countDir;
            if (countDir < 0)
            {
              image(redRob4, cX - (wid / 2), cY - (hei / 2));
            } 
            else
            {
              image(redRob1, cX - (wid / 2), cY - (hei / 2));
            }
          }
          counter = counter + countDir;
        } 
        else
        {
          image(redRob1, cX - (wid / 2), cY - (hei / 2));
        }
      }
      else
      {
        if (moving)
        {
          if (counter >= 0 && counter <= 10) 
          {
            image(blueRob1, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 10 && counter <= 20) 
          {
            image(blueRob2, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 20 && counter <= 30)
          {
            image(blueRob3, cX - (wid / 2), cY - (hei / 2));
          } 
          else if (counter > 30 && counter <= 35)
          {
            image(blueRob4, cX - (wid / 2), cY - (hei / 2));
          } 
          else 
          {
            countDir = -countDir;
            if (countDir < 0)
            {
              image(blueRob4, cX - (wid / 2), cY - (hei / 2));
            } 
            else
            {
              image(blueRob1, cX - (wid / 2), cY - (hei / 2));
            }
          }
          counter = counter + countDir;
        } 
        else
        {
          image(blueRob1, cX - (wid / 2), cY - (hei / 2));
        }
      }
    }
  }

  public int[] getTopLeft()
  {
    this.x = cX - (wid / 2);
    this.y = cY - (hei / 2);
    int[] topLeft = {this.x, this.y};
    return topLeft;
  }

  public int[] getCentre()
  {  
    int[] centre = {cX, cY};
    return centre;
  }

  public int getCX()
  {
    return this.cX;
  }

  public int getCY()
  {
    return this.cY;
  }

  public void hide()
  {
    this.visible = false;
  }

  public void show()
  {
    this.visible = true;
  }

  public void moveX(int speed)
  {
    cX += speed;
  }

  public void moveY(int speed)
  {
    cY += speed;
  }

  public void setStartPos(int cX, int cY)
  {
    this.cX = cX;
    this.cY = cY;
  }

  public void startMoving()
  {
    this.moving = true;
  }

  public void stopMoving()
  {
    this.moving = false;
  }
  
  public void setGreen()
  {
    this.robColour = GREEN;
  }
  
  public void setRed()
  {
    this.robColour = RED;
  }
  
  public void setBlue()
  {
    this.robColour = BLUE;
  }
  
  public int getColour()
  {
    return this.robColour;
  }
  
  public boolean getMoving()
  {
    return this.moving;
  }
}
class Wall
{
  private int x1, y1, x2, y2;
  
  Wall(int x1, int y1, int x2, int y2)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public void update()
  {
    render();
  }
  
  public void render()
  {
    strokeWeight(8);
    stroke(0);
    fill(0);
    line(x1, y1, x2, y2);
    strokeWeight(1);
  }
  
  public int[] getPoints()
  {
    int[] points = {x1, y1, x2, y2};
    return points;
  }
}
  public void settings() {  size(1560, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "ROBB13_THE_ROBOT" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
