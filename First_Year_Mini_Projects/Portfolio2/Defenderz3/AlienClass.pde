color ALIEN1 = color(0,255,0);
color ALIEN2 = color(50,100,0);

class Alien
{
  int x, y;
  int speedX = -3;
  int speedY;
  int counter = 0;
  
  
  Alien (int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  void update()
  {
    render();
    move();
  }
  
  void render()
  {
    fill(ALIEN1);
    ellipse(x,y,30,30);
    fill(ALIEN2);
    ellipse(x,y,50,15);
  }
  
  void move()
  {
    x = x + speedX;
    
    if ((counter % 10) == 0)
    {
      speedY = (int)random(-5,5);
    }
    
    y = y + speedY;
    
    if (y <= 0)
    {
      y = height - 1;
    }
    else if (y>= height - 1)
    {
      y = 0;
    }
    
    if ((x+25) <= 0)
    {
      x = width + 25;
    }
    
    counter++;
  }
  
  boolean isShot()
  {
    color test;
    for (int i = -15; i < 14; i++)
    {
      test = get(x, y + i);
      if (test == BULLET1)
      {
        return true;
      }
    }
    return false;
  }
  
}
