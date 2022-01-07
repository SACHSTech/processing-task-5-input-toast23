import processing.core.PApplet;
import java.util.Random;

public class Sketch extends PApplet {
	

  // global variables
  Random rand = new Random();
  public float width = 1200f;
  public float height = 1000f;
  public float widthSF = width / 400f;
  public float heightSF = height / 400f;
  public float starScale = 0.35f;
  public int sunSize = 55;
  public int moonSize = 40;
  public boolean night = false;


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
    sun();
    house();

  }


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // draw
    if (mousePressed) {
      fill(255);
      if (!night) {
        cloud();
      }
      else if (night) {
        star(mouseX, mouseY, 5 * widthSF, 11 * heightSF, 5);
      }
    }
  }

  // change day time
  public void keyPressed() {
    if (!night) {
      night = true;
      background(0);
      moon();
    }
    else if (night) {
      night = false;
      background(115, 235, 255);
      sun();
      }
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
  
  // draw sun
  public void sun() {
    fill(255, 255, 0);
    ellipse(rand.nextInt((int)width), (rand.nextInt((int)(190f * heightSF))), sunSize * widthSF, sunSize * heightSF);
  }

  // draw moon
  public void moon() {
    fill(255);
    ellipse(rand.nextInt((int)width), (rand.nextInt((int)(190f * heightSF))), moonSize * widthSF, moonSize * heightSF);    
  }

  // draw cloud
  public void cloud() {

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