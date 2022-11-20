class Obstacle
{
  int x, y;
  int speedY = -3;


  Obstacle (int x, int y)
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
    
  }

  void move()
  {
    y = y + speedY;

    if ((y+25) <= 0)
    {
      y = height + 25;
    }
  }
}
