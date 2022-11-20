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
  
  void update()
  {
    render();
    updateCommands();
    checkComplete();
  }
  
  void render()
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
  
  void addWall(int x1, int y1, int x2, int y2)
  {
    wallList.add(new Wall(x1, y1, x2, y2));
  }
  
  void addButton (int x, int y)
  {
    buttonList.add(new Button (x, y));
  }
  
  void setCommandList(Command[] givenList)
  {
    this.commandList.clear();
    for (Command c : givenList)
    {
      this.commandList.add(c);
    }
  }
  
  void setOutroText(String outText)
  {
    this.outroText = outText;
  }
  
  void addGoal(int x, int y)
  {
    g1 = new Goal(x, y);
  }
  
  int getLevelNum()
  {
    return this.levelNum;
  }
  
  ArrayList<Wall> getWallList()
  {
    return this.wallList;
  }
  
  ArrayList<Command> getCommandList()
  {
    return this.commandList;
  }
  
  ArrayList<Button> getButtonList()
  {
    return this.buttonList;
  }
  
  Goal getGoal()
  {
    return g1;
  }
  
  void closeDoor()
  {
    g1.closeDoor();
  }
  
  void checkComplete()
  {
    this.complete = g1.checkReached();
  }
  
  void setStartPos(int x, int y)
  {
    this.startPosX = x;
    this.startPosY = y;
  }
  
  void updateCommands()
  {
    for (Command c : commandList)
    {
      c.update();
    }
  }
  
  void checkButtons()
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
  
  int getStartPosX()
  {
    return this.startPosX;
  }
  
  int getStartPosY()
  {
    return this.startPosY;
  }
  
  boolean isComplete()
  {
    return this.complete;
  }
  
  Button getButton(int pos)
  {
    return this.buttonList.get(pos);
  }
}
