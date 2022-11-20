class Defender
{
  int x, y;
  int speedY = 8;
  
  Defender(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  void render()
  {
    fill(255,0,0);
    rect(x,y,50,20);
    triangle(x+50,y,x+50,y+20,x+60,y+10);
    fill(0,0,100);
    rect(x,y-10,20,10);
  }
  

  
  boolean crashed()
  {
    color test;
    for (int i = 0; i < 29; i++)
    {
      test = get(x + 65,y + i);
      if (test == ALIEN1 || test == ALIEN2)
      {
        return true;
      }
    }
    return false;
  }
  
}
