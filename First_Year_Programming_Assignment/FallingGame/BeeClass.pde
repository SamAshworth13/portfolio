class Bee extends Obstacle
{
  int speedX;
  int counter = 0;
  int animationCount = 0;
  PImage beeLeft1, beeRight1, beeLeft2, beeRight2, beeLeft3, beeRight3;
  
  Bee (int x, int y)
  {
    super(x, y);
    beeLeft1 = loadImage("beeLeft.png");
    beeLeft2 = loadImage("beeLeft2.png");
    beeLeft3 = loadImage("beeLeft3.png");
    beeRight1 = loadImage("beeRight.png");
    beeRight2 = loadImage("beeRight2.png");
    beeRight3 = loadImage("beeRight3.png");
  }
  
  void update()
  {
    render();
    move();
  }
  
  
  void render()
  {
    super.render();
    if (animationCount >= 0 && animationCount <= 10)
    {
      if (speedX < 0)
      {
        image(beeLeft1, x, y);
      }
      else
      {
        image(beeRight1, x, y);
      }
    }
    else if (animationCount > 10 && animationCount <= 20)
    {
      if (speedX < 0)
      {
        image(beeLeft2, x, y);
      }
      else
      {
        image(beeRight2, x, y);
      }
    }
    else
    {
      if (speedX < 0)
      {
        image(beeLeft3, x, y);
      }
      else
      {
        image(beeRight3, x, y);
      }
      
      if (animationCount + 1 > 30)
      {
        animationCount = 0;
      }
    }
    animationCount++;
  }
  
  void move()
  {
    super.move();
    if ((counter % 10) == 0)
    {
      speedX = (int)random(-5, 5);
    }

    x = x + speedX;

    if (x <= 0)
    {
      x = width - 1;
    } else if (x>= width - 1)
    {
      x = 0;
    }
    
    counter++;
  }
  
}
