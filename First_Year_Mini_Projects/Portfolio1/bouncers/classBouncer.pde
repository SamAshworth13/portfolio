class Bouncer
{
  int x, y, speedX, speedY;
  PImage upRight, upLeft, downRight, downLeft;
  Bouncer(int x, int y, int speedX, int speedY)
  {
    this.x = x;
    this.y = y;
    this.speedX = speedX;
    this.speedY = speedY;
    upRight = loadImage("upRight.png");
    upLeft = loadImage("upLeft.png");
    downRight = loadImage("downRight.png");
    downLeft = loadImage("downLeft.png");
  }
  
  void render()
  {
    if (speedX >= 0 && speedY >= 0)
    {
      image(downRight, x, y);
    }
    else if (speedX >= 0 && speedY < 0)
    {
      image(upRight, x, y);
    }
    else if (speedX < 0 && speedY >= 0)
    {
      image(downLeft, x, y);
    }
    else if (speedX < 0 && speedY < 0)
    {
      image(upLeft, x, y);
    }
  }
  
  void move()
  {
    x = x + speedX;
    y = y + speedY;
    
    if ((x+50) >= width || x <= 0)
    {
      speedX = -speedX;
    }
    
    if ((y+50) >= height || y <= 0)
    {
      speedY = -speedY;
    }
    
  }
  
  void update()
  {
    render();
    move();
  }
  
  void collisionCheck(Bouncer other)
    {
      if(this.collision(other))
      {
        if (((this.x+50) < width && this.x > 0) && ((this.y+50) < height && this.y > 0))
        {
          this.speedX = -this.speedX;
          this.speedY = -this.speedY;
          other.speedX = -other.speedX;
          other.speedY = -other.speedY;
        }
        else
        {
          other.speedX = -other.speedX;
          other.speedY = -other.speedY;
        }
      }
    }
  
  boolean collision(Bouncer other)
  {
    return (abs(this.x - other.x) < 50 && abs(this.y - other.y) < 50);
  }
}
