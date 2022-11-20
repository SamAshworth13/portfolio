Alien alien1, alien2, alien3;
Defender defender1;
Bullet bullet1;
PImage background;
int bgX=0; //global variable background location
final int PLAY = 0;
final int LOSS = 1;
final int WIN = 2;
int gameMode = PLAY;
String lossMessage = "CRASH!";
String winMessage = "VICTORY!";

void setup() {
  size(800, 400);
  background = loadImage("spaceBackground.jpg");
  background.resize(width, height);
  alien1 = new Alien(400,200);
  alien2 = new Alien(550,60);
  alien3 = new Alien(750, 310);
  defender1 = new Defender(20,200);
}
void draw()
{
  if (gameMode == PLAY)
  {
    if (alien1 == null && alien2 == null && alien3 == null)
    {
      gameMode = WIN;
    }
    
    scrollBackground();
    if (alien1 != null)
    {
      alien1.update();
    }
    if (alien2 != null)
    {
      alien2.update();
    }
    if (alien3 != null)
    {
      alien3.update();
    }
    
    defender1.render();
    //has defender crashed? If so stop the game
    if (defender1.crashed())
    {
      gameMode = LOSS;
    }
    
    if (bullet1 != null)
    {
      bullet1.update();
      if (missed(bullet1))
      {
        bullet1 = null;
      }
      else if (alien1 != null && alien1.isShot())
      {
        alien1 = null;
        bullet1 = null;
      }
      else if (alien2 != null && alien2.isShot())
      {
        alien2 = null;
        bullet1 = null;
      }
      else if (alien3 != null && alien3.isShot())
      {
        alien3 = null;
        bullet1 = null;
      }
    }
    
  }
  else if (gameMode == LOSS)
  {
    textAlign(CENTER);
    textSize(100);
    fill(255,0,0);
    text(lossMessage, 400, 200);
  }
  else
  {
    textAlign(CENTER);
    textSize(100);
    fill(0,255,0);
    text(winMessage, 400, 200);
  }
}

void scrollBackground()
{
  image(background, bgX, 0); //draw background twice adjacent
  image(background, bgX+background.width, 0);
  bgX -=4;
  if (bgX == -background.width)
  {
    bgX=0; //wrap background
  }
}

void keyPressed()
{
  if(key == CODED)
  {
    if (keyCode == UP && ((defender1.y)-10) >= 0)
    {
      defender1.y = defender1.y - defender1.speedY;
    }
    else if (keyCode == DOWN && ((defender1.y)+20) <= height)
    {
      defender1.y = defender1.y + defender1.speedY;
    }
    else if (keyCode == RIGHT && bullet1 == null)
    {
      bullet1 = new Bullet();
    }
  }
}
  
  
boolean missed(Bullet test)
  {
    return(test.x >= width);
  }
