class Branch extends Obstacle
{
  int speedX = 0;
  int counter = 0;
  PImage branchLeft, branchRight;
  boolean faceRight = false;
  
  Branch(int x, int y)
  {
    super(x, y);
    branchLeft = loadImage("branchLeft.png");
    branchRight = loadImage("branchRight.png");
  }
  
  void update()
  {
    render();
    move();
  }
  
  void render()
  {
    super.render();
    if (x <= 10 && x >= -300)
    {
      image(branchRight, x, y);
      faceRight = true;
    }
    else if (x >= 190 && x < 500)
    {
      image(branchLeft, x, y);
    }
  
  }
  
  void move()
  {
    super.move();
    if (faceRight)
    {
      if (x > 0 || x < -300)
      {
        speedX = - speedX;
      }
    }
    else
    {
      if (x > 500 || x < 201)
      {
        speedX = - speedX;
      }
    }
    x = x + speedX;
  }
  
  void randSpeed()
  {
    while (speedX == 0)
    {
      speedX = (int)random(-7,7);
    }
  }
}
