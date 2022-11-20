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

  void update()
  {
    render();
  }

  void render()
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

  int[] getTopLeft()
  {
    this.x = cX - (wid / 2);
    this.y = cY - (hei / 2);
    int[] topLeft = {this.x, this.y};
    return topLeft;
  }

  int[] getCentre()
  {  
    int[] centre = {cX, cY};
    return centre;
  }

  int getCX()
  {
    return this.cX;
  }

  int getCY()
  {
    return this.cY;
  }

  void hide()
  {
    this.visible = false;
  }

  void show()
  {
    this.visible = true;
  }

  void moveX(int speed)
  {
    cX += speed;
  }

  void moveY(int speed)
  {
    cY += speed;
  }

  void setStartPos(int cX, int cY)
  {
    this.cX = cX;
    this.cY = cY;
  }

  void startMoving()
  {
    this.moving = true;
  }

  void stopMoving()
  {
    this.moving = false;
  }
  
  void setGreen()
  {
    this.robColour = GREEN;
  }
  
  void setRed()
  {
    this.robColour = RED;
  }
  
  void setBlue()
  {
    this.robColour = BLUE;
  }
  
  int getColour()
  {
    return this.robColour;
  }
  
  boolean getMoving()
  {
    return this.moving;
  }
}
