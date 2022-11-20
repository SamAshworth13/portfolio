class Command
{
  private String name;
  private int x, y;
  private int greyValue = 0;
  
  Command(String name, int x, int y)
  {
    this.name = name;
    this.x = x;
    this.y = y;
  }
  
  void update()
  {
    render();
  }
  
  void render()
  {
    stroke(0);
    fill(255);
    rect(x, y, 100, 30);
    fill(greyValue);
    textAlign(CENTER);
    textSize(20);
    text(name, x + 50, y + 25);
  }
  
  String getName()
  {
    return this.name;
  }
  
  void setName(String name)
  {
   this.name = name;
  }
  
  int getX()
  {
    return this.x;
  }
  
  void setX(int x)
  {
   this.x = x;
  }
  
  int getY()
  {
    return this.y;
  }
  
  void setY(int y)
  {
   this.y = y;
  }
  
  void setGrey()
  {
    this.greyValue = 127;
  }
  
  void setBlack()
  {
    this.greyValue = 0;
  }
}
