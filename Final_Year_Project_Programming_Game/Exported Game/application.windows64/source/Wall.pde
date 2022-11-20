class Wall
{
  private int x1, y1, x2, y2;
  
  Wall(int x1, int y1, int x2, int y2)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  void update()
  {
    render();
  }
  
  void render()
  {
    strokeWeight(8);
    stroke(0);
    fill(0);
    line(x1, y1, x2, y2);
    strokeWeight(1);
  }
  
  int[] getPoints()
  {
    int[] points = {x1, y1, x2, y2};
    return points;
  }
}
