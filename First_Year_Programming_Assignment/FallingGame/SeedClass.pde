
class Seed
{
  int x = player1.x + 20;
  int y = player1.y + 80;
  int speedY = 5;
  
  void update()
  {
    render();
    move();
  }
  
  void render()
  {
    fill(255,255,0);
    rect(x, y, 10, 10);
  }
  
  void move()
  {
    y = y + speedY;
  }
  
  boolean shotBee(Bee testBee)
  {
    return (abs(this.x - testBee.x) < 50 && abs(this.y - testBee.y) < 25);
  }

  boolean missed()
  {
    return(y >= height);
  }
}
