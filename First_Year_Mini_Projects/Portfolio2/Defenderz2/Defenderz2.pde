Alien alien1, alien2, alien3;
Defender defender1;
PImage background;
int bgX=0; //global variable background location
final int PLAY = 0;
final int FINISH = 1;
int gameMode = PLAY;
String endMessage = "CRASH!";

void setup() {
  size(800, 400);
  background = loadImage("spaceBackground.jpg");
  background.resize(width, height);
  alien1 = new Alien(350,200);
  alien2 = new Alien(500,60);
  alien3 = new Alien(750, 310);
  defender1 = new Defender(20,200);
}
void draw()
{
  if (gameMode == PLAY)
  {
    scrollBackground();
    alien1.update();
    alien2.update();
    alien3.update();
    defender1.render();
    //has defender crashed? If so stop the game
    if (defender1.crashed())
    {
      gameMode = FINISH;
    }
  }
  else
  {
    textAlign(CENTER);
    textSize(100);
    fill(255,0,0);
    text(endMessage, 400, 200);
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
    }
  }
