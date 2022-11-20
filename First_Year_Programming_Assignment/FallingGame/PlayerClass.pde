class Player
{
  int x, y;
  int speedX = 8;
  PImage bird1, bird2, bird3, bird4, birdCrash1, birdCrash2, birdCrash3;
  int counter = 0;
  int countDir = 1;
  int crashCount = 0;
  
  Player(int x, int y)
  {
    this.x = x;
    this.y = y;
    bird1 = loadImage("bird1.png");
    bird2 = loadImage("bird2.png");
    bird3 = loadImage("bird3.png");
    bird4 = loadImage("bird4.png");
    birdCrash1 = loadImage("birdCrash1.png");
    birdCrash2 = loadImage("birdCrash2.png");
    birdCrash3 = loadImage("birdCrash3.png");
  }
  
  void render(boolean hasCrashed)
  {
    if (!hasCrashed)
    {
      if (counter >= 0 && counter <= 10){
          image(bird1, x, y);
        }
        else if (counter > 10 && counter <= 20){
          image(bird2, x, y);
        }
        else if (counter > 20 && counter <= 30)
        {
          image(bird3, x, y);
        }
        else if (counter > 30 && counter <= 35)
        {
          image(bird4, x, y);
        }
        else 
        {
          countDir = -countDir;
          if (countDir < 0)
          {
            image(bird4, x, y);
          }
          else
          {
            image(bird1, x, y);
          }
        }
        counter = counter + countDir;   
     }
      else
      {
         if (counter >= 0 && counter <= 10)
         {
          image(birdCrash1, x, y);
         }
        else if (counter > 10 && counter <= 20)
        {
          image(birdCrash2, x, y);
        }
        else if (counter > 20 && counter <= 30)
        {
          image(birdCrash3, x, y);
        }
        else
        {
          image(birdCrash3, x, y);
          counter = 0;
        }
        counter++;
        crashCount++;
        if (crashCount >= 60)
        {
          crashed = false;
          crashCount = 0;
        }
      }

  }
  
  boolean crashedBee(Bee testBee)
  {
    return (abs(this.x - testBee.x) < 50 && abs(this.y - testBee.y) < 75);
  }
  
}
