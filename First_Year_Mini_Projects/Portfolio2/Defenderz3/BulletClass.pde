color BULLET1 = color(255,255,0);

class Bullet
{
  int x = defender1.x + 70;
  int y = defender1.y;
  int speedX = 5;
  
  void update()
  {
    render();
    move();
  }
  
  void render()
  {
    fill(BULLET1);
    rect(x,y, 10, 5);
  }
  
  void move()
  {
    x = x + speedX;
  }
  
  

  
}
