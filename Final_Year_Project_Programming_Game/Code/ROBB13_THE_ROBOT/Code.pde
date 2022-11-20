class Code
{
  private String word;
  private int x, y;
  private color c1 = color(0, 0, 0);
  
  Code()
  {
    this.x = 0;
    this.y = 0;
    this.word = "null";
  }
  
  Code(String word, int x, int y)
  {
    this.word = word;
    this.x = x;
    this.y = y;
  }
  
  void update()
  {
    render();
  }
  
  void render()
  {
    stroke(c1);
    fill(c1);
    textAlign(LEFT);
    textSize(25);
    text(word, x, y);
  }
  
  String getWord()
  {
    return this.word;
  }
  
  void setWord(String word)
  {
   this.word = word;
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
  
  void activate()
  {
    this.c1 = color(255, 0, 0);
  }
  
  void deactivate()
  {
    this.c1 = color(0, 0, 0);
  }
}
