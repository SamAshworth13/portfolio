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
  
  void update()
  {
    render();
  }
  
  void render()
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
  
  int[] getTopLeft()
  {
    int x = cX - (wid / 2);
    int y = cY - (hei / 2);
    int[] topLeft = {x, y};
    return topLeft;
  }
  
  int[] getCentre()
  {  
    int[] centre = {cX, cY};
    return centre;
  }
  
  void hide()
  {
    this.visible = false;
  }
  
  void show()
  {
    this.visible = true;
  }
  
  void checkCollision(Robot r)
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
  
  boolean checkVisibility()
  {
    return this.visible;
  }
  
  boolean checkReached()
  {
    return this.reached;
  }
  
  void closeDoor()
  {
    this.open = false;
  }
  
  void openDoor()
  {
    this.open = true;
  }
}
