Bouncer bouncer1, bouncer2, bouncer3;
void setup()
{
  size(500,500);
  bouncer1 = new Bouncer(10, 10, 6, 2);
  bouncer2 = new Bouncer(80, 80, 2, 7);
  bouncer3 = new Bouncer(423, 375, -2, 5);
}

void draw()
{
  background(245);
  bouncer1.collisionCheck(bouncer2);
  bouncer1.collisionCheck(bouncer3);
  bouncer2.collisionCheck(bouncer3);
  bouncer1.update();
  bouncer2.update();
  bouncer3.update();

  
}

  
