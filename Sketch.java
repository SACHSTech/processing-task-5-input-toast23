import processing.core.PApplet;
import java.util.Random;

public class Sketch extends PApplet {
	

  // global variables
  Random rand = new Random();
  public float width = 1200f;
  public float height = 1200f;
  public float widthSF = width / 400f;
  public float heightSF = height / 400f;
  public float circleX = 50f;
  public float circleY = 50f;
  public int sunSize = 55;
  public int moonSize = 40;
  public int cloudSize = 30;
  public boolean night = false;
  public boolean upPressed = false;
  public boolean downPressed = false;
  public boolean leftPressed = false;
  public boolean rightPressed = false;


  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size((int)width, (int)height);
  }


  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(115, 235, 255);
    ground();
    house();
  }


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // draw
    if (upPressed) {
      circleY-=2;
    }
    if (downPressed) {
      circleY+=2;
    }
    if (leftPressed) {
      circleX-=2;
    }
    if (rightPressed) {
      circleX+=2;
    }
    if (!night) {
      fill(255, 255, 0); 
      ellipse(circleX, circleY, sunSize, sunSize);
    }
    else if (night) {
      fill(255);
      ellipse(circleX, circleY, moonSize, moonSize);
    }
    if (mousePressed) {
      fill(255);
      if (!night) {
        cloud();
      }
      else if (night) {
        star(mouseX, mouseY, 5 * widthSF, 11 * heightSF, 5);
      }
    }
    if (keyPressed) {
      if (!night) {
        fill(255, 255, 0);
        ellipse(rand.nextInt((int)width), (rand.nextInt((int)(190f * heightSF))), sunSize, sunSize);
      }
      else if (night) {
        fill(255);
        ellipse(rand.nextInt((int)width), (rand.nextInt((int)(190f * heightSF))), moonSize, moonSize);
      }
    }
  }
  

  // draw only one cloud/moon
  public void mouseClicked() {
    if(!night) {
      fill(255, 0, 255); 
      cloud();
    }
    else if (night) {
      fill(255, 255, 0);
      star(mouseX, mouseY, 5 * widthSF, 11 * heightSF, 5);
    }
  }


  // continously move sun/moon when keys pressed
  public void keyPressed() {
    if (keyCode == UP) {
      upPressed = true;
    }
    else if (keyCode == DOWN) {
      downPressed = true;
    }
    else if (keyCode == LEFT) {
      leftPressed = true;
    }
    else if (keyCode == RIGHT) {
      rightPressed = true;
    }
  }


  // functions for keys released
  public void keyReleased() {
    // change time of day 
    if (key == ' ') {
      if (!night) {
        night = true;
        nightTime();
      }
      else if (night) {
        night = false;
        dayTime();
      }
    }
    if (keyCode == UP) {
      upPressed = false;
    }
    else if (keyCode == DOWN) {
      downPressed = false;
    }
    else if (keyCode == LEFT) {
      leftPressed = false;
    }
    else if (keyCode == RIGHT) {
      rightPressed = false;
    }
  }


  // day scene
  public void dayTime() {
    background(115, 235, 255);
    ground();
    house();
  }
   

  // night scene
  public void nightTime() {
    background(0);
    ground();
    house();
  }


  // draw ground
  public void ground() {
    fill(0, 255, 0); 
    rect(0, 300 * heightSF, width, height - 300);
  }


  // draw house
  public void house() {
      fill(255, 205, 180);
      rect(170f * widthSF, 255 * heightSF, 70 * widthSF, 45 * heightSF);
      fill(165, 42, 42);
      triangle(170 * widthSF, 255 * heightSF, 205 * widthSF, 230 * heightSF, 240 * widthSF, 255 * heightSF);
      fill(0, 0, 255);
      rect(179 * widthSF, 267 * heightSF, 11 * widthSF, 12 * heightSF);
      rect(220 * widthSF, 267 * heightSF, 11 * widthSF, 12 * heightSF);
      fill(165, 42, 42);
      rect(200 * widthSF, 277 * heightSF, 10 * widthSF, 20 * heightSF);
  }


  // draw cloud
  public void cloud() {
    beginShape();
    ellipse(mouseX-15, mouseY+10, cloudSize, cloudSize);
    ellipse(mouseX+55, mouseY+10, cloudSize, cloudSize);
    ellipse(mouseX+8, mouseY-10, cloudSize, cloudSize);
    ellipse(mouseX+33, mouseY-10, cloudSize, cloudSize);
    ellipse(mouseX+20, mouseY-15, cloudSize, cloudSize);


    for(int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
      ellipse(mouseX + (j*20), mouseY + (i*23), cloudSize, cloudSize);
      }
    } 
    endShape(CLOSE);
  }


  // draw star
  public void star(float x, float y, float radius1, float radius2, int npoints) {
    float angle = TWO_PI / npoints;
    float halfAngle = angle/2;
    beginShape();
    for (float a = 0; a < TWO_PI; a += angle) {
      float sx = x + cos(a) * radius2;
      float sy = y + sin(a) * radius2;
      vertex(sx, sy);
      sx = x + cos(a+halfAngle) * radius1;
      sy = y + sin(a+halfAngle) * radius1;
      vertex(sx, sy);
    }
    endShape(CLOSE);
  }


}