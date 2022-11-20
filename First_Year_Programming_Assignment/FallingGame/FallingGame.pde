
Bee bee1, bee2, bee3;
Branch branch1, branch2, branch3;
Player player1;
Seed seed1;
PImage background;
int bgY = 0; //global variable background location
final int PLAY = 0;
final int LOSS = 1;
final int WIN = 2;
final int MENU = 3;
final int CONTROLS = 4;
int health;
float score;
int gameMode = MENU;
final String lossMessage = "CRASH!";
final String lossMessage2 = "Press the up arrow to go back to the Main Menu.";
final String winMessage = "VICTORY!";
final String winMessage2 = "Press the up arrow to go back to the Main Menu.";
final String playButton = "PLAY";
final String controlsButton = "HOW TO PLAY";
final String controlText = "Use the left and right arrow keys to move. \n Avoid the branches and bees. \n Press the down arrow to shoot seeds to get rid of bees \n and score points.\n Only 3 seeds can be in play at any time. \n The game ends when your health reaches 0. \n \n Score 5000 points or more to win!";
final String backButton = "Back";
final String healthText = "Health: ";
final String scoreText = "Score: ";
Bee[] beeArray = new Bee[3];
Branch[] branchArray = new Branch[3];
Seed[] seedArray = new Seed[3];
boolean crashed;

void setup() 
{
  size(500, 800);
  background = loadImage("cloudBackground.jpg");
  background.resize(width, height);
  

  branch1 = new Branch(-90, 10 + height);
  branch2 = new Branch(320, 270 + height);
  branch3 = new Branch(-10, 540 + height);
  branchArray[0] = branch1;
  branchArray[1] = branch2;
  branchArray[2] = branch3;

  //give each branch a random speed
  branch1.randSpeed();
  branch2.randSpeed();
  branch3.randSpeed();

  player1 = new Player(225, 50);
  
  bee1 = new Bee(100, player1.y + 90 + height);
  bee2 = new Bee(380, player1.y + 350 + height);
  bee3 = new Bee(490, player1.y + 620 + height);
  beeArray[0] = bee1;
  beeArray[1] = bee2;
  beeArray[2] = bee3;
  
  health = 10;
  score = 0;
  crashed = false;
  for (int i = 0; i < seedArray.length; i++)
  {
    seedArray[i] = null;
  }
}

void draw()
{
  if (gameMode == PLAY)
  {

    scrollBackground();
    
    updateObstacles();


    player1.render(crashed);

    checkCrash();
    textAlign(LEFT);
    fill(0, 190, 0);
    text(healthText + health, 0, 790);
    textAlign(RIGHT);
    text(scoreText + round(score), 500, 20);

    checkKeys();
    checkShots();
    score += 0.1;
  } 
  else if (gameMode == LOSS)
  {
    textAlign(CENTER);
    textSize(100);
    fill(255, 0, 0);
    text(lossMessage, 250, 400);
    textSize(16);
    text(lossMessage2, 250, 430);
    checkKeys();
  } 
  else if (gameMode == WIN)
  {
    textAlign(CENTER);
    textSize(100);
    fill(0, 255, 0);
    text(winMessage, 250, 400);
    textSize(16);
    text(winMessage2, 250, 430);
    checkKeys();
  } 
  else if (gameMode == MENU)
  {
    background(131, 242, 235);
    textAlign(CENTER);
    textSize(50);
    fill(0, 190, 0);
    text(playButton, 250, 300);
    text(controlsButton, 250, 400);
    textSize(14);
    text("Click on an option.", 250, 100);
  } 
  else if (gameMode == CONTROLS)
  {
    background(131, 242, 235);
    textAlign(CENTER);
    textSize(16);
    fill(0, 190, 0);
    text(controlText, 250, 200);
    textSize(50);
    textAlign(LEFT);
    text(backButton, 10, 50);
  }
}

void scrollBackground()
{
  image(background, 0, bgY); //draw background twice adjacent
  image(background, 0, bgY + background.height);
  bgY -= 4;
  if (bgY == -background.height)
  {
    bgY = 0; //wrap background
  }
}


boolean crashedBranch(int x, int y)
{
  float test;
  //Tests for collision with top
  for (int i = 0; i < 49; i++)
  {
    for (int j = 0; j < 299; j++)
    {
      for (int k = 0; k < branchArray.length; k++)
      {
        test = dist(x + i, y + 74, branchArray[k].x + j, branchArray[k].y);
        if (test < 1.1)
        {
          return true;
        }
      }
    }
  }

  //Tests for collision with sides
  for (int i = 74; i >= 0; i--)
  {
    for (int j = 0; j < 9; j++)
    {
      for (int k = 0; k < branchArray.length; k++)
      {
        test = dist(x, y + i, branchArray[k].x + 299, branchArray[k].y + j);
        if (test < 3)
        {
          return true;
        }
      }
    }
  }

  for (int i = 74; i >= 0; i--)
  {
    for (int j = 0; j < 9; j++)
    {
      for (int k = 0; k < branchArray.length; k++)
      {
        test = dist(x + 49, y + i, branchArray[k].x, branchArray[k].y + j);
        if (test < 3)
        {
          return true;
        }
      }
    }
  }
  return false;
}


void mousePressed()
{
  if (gameMode == MENU)
  {
    if (mouseX > 180 && mouseX < 320 && mouseY > 250 && mouseY < 300)
    {
      //Set font info for healthText
      textAlign(LEFT);
      textSize(25);
      gameMode = PLAY;
    } 
    else if (mouseX > 85 && mouseX < 415 && mouseY > 350 && mouseY < 400)
    {
      gameMode = CONTROLS;
    }
  } 
  else if (gameMode == CONTROLS && mouseX >= 0 && mouseX <= 120 && mouseY < 50)
  {
    gameMode = MENU;
  }
}

void keyPressed()
{
  // Not a part of checkKeys() because, unlike the movement keys, there is no necessity for the shoot key to be held down
  if (key == CODED)
  {
    if (gameMode == PLAY && keyCode == DOWN)
    {
      for (int i = 0; i < seedArray.length; i++)
      {
        if (seedArray[i] == null)
        {
          seedArray[i] = new Seed();
          i = seedArray.length;
        }
      }
    }
  }
}
void checkCrash()
{
  for (int i = 0; i < beeArray.length; i++)
  {
    if (beeArray[i] != null)
    {
      if (player1.crashedBee(beeArray[i]))
      {
        crashed = true;
        if (score - 100 < 0)
        {
          score = 0;
        } 
        else
        {
          score -= 100;
        }
        health--;
        /* The extra 50 added to the height counteracts an issue that caused 
        the distance between the bees and branches to not remain consistent */
        beeArray[i].y += height + 50;
        if (health < 1)
        {
          if (score < 5000)
          {
            gameMode = LOSS;
          }
          else
          {
            gameMode = WIN;
          }
        }
      }
    }
  }

  if (crashedBranch(player1.x, player1.y))
  {
    crashed = true;
    if (score - 100 < 0)
    {
      score = 0;
    } 
    else
    {
      score -= 100;
    }
    health--;
    if (health < 1)
    {
      if (score < 5000)
      {
        gameMode = LOSS;
      }
      else
      {
        gameMode = WIN;
      }
      
    }
  }
}
void checkKeys()
{
  //used instead of void keyPressed() to stop there from being a delay when holding a key down
  if (keyPressed)
  {
    if (key == CODED)
    {
      if (gameMode == PLAY)
      {
        if (keyCode == LEFT && ((player1.x)-player1.speedX) >= 0)
        {
          player1.x = player1.x - player1.speedX;
        } 
        else if (keyCode == RIGHT && ((player1.x)+49 + player1.speedX) <= width)
        {
          player1.x = player1.x + player1.speedX;
        }
      } 
      else if (keyCode == UP && (gameMode == WIN || gameMode == LOSS))
      {
        setup();
        gameMode = MENU;
      }
    }
  }
}

void checkShots()
{
  for (int i = 0; i < seedArray.length; i++)
  {
    if (seedArray[i] != null)
    {
      seedArray[i].update();
      if (seedArray[i].missed())
      {
        seedArray[i] = null;
      } 
      else
      {
        for (int j = 0; j < beeArray.length; j++)
        {
          if (beeArray[j] != null && seedArray[i] != null)
          {
            if (seedArray[i].shotBee(beeArray[j]))
            {
              /* The extra 50 added to the height counteracts an issue that caused 
              the distance between the bees and branches to not remain consistent */
              beeArray[j].y += height + 50;
              seedArray[i] = null;
              score += 500;
            }
          }
        }
      }
    }
  }
}

void updateObstacles()
{
  for (int i = 0; i < beeArray.length; i++)
    {
      /*All checks to see if bees are null were originally used because bees would become null when shot.
      This no longer happens, but the checks remain to prevent any unforseen major errors */
      if (beeArray[i] != null)
      {
        beeArray[i].update();
      }
    }


    for (int i = 0; i < branchArray.length; i++)
    {
      branchArray[i].update();
    }
}
