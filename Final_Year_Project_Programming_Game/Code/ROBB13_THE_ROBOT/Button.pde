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
  
  void update()
  {
    render();
  }
  
  void render()
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
          randomColour = random(0.5, 3.5);
          if (randomColour < 1.5)
          {
            colour = GREEN;
          }
          else if (randomColour >= 1.5 && randomColour < 2.5)
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
  
  boolean checkVisibility()
  {
    return this.visible;
  }
  
  boolean checkReached()
  {
    return this.reached;
  }
  
  void reset()
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
  
  void setGreen()
  {
    this.colour = GREEN;
  }
  
  void setRed()
  {
    this.colour = RED;
  }
  
  void setBlue()
  {
    this.colour = BLUE;
  }
  
  int getColour()
  {
    return this.colour;
  }
  
  boolean checkCollided()
  {
    return this.collided;
  }
  
  void setCycling()
  {
    this.cycling = true;
  }
}
