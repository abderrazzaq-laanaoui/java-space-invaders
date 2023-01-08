package com.enset.glsid.presentation.model;

import static com.enset.glsid.N7Invaders.WIDTH;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Universe extends Character {

  private static final Random RAND = new Random();

  public Universe(GraphicsContext gc,int posX, int posY, int size, int speed) {
        super(gc, posX, posY, size, speed, null);
    }
  private int h, w, r, g, b;
  private double opacity;

  public Universe(GraphicsContext gc) {
    super(gc, 0, 0, 0, 0, null);
    posX = RAND.nextInt(WIDTH);
    posY = 0;
    w = RAND.nextInt(5) + 1;
    h =  RAND.nextInt(5) + 1;
    r = RAND.nextInt(100) + 150;
    g = RAND.nextInt(100) + 150;
    b = RAND.nextInt(100) + 150;
    opacity = RAND.nextFloat();
    if(opacity < 0) opacity *=-1;
    if(opacity > 0.5) opacity = 0.5;
  }

  public void draw() {
    if(opacity > 0.8) opacity-=0.01;
    if(opacity < 0.1) opacity+=0.01;
    getGc().setFill(Color.rgb(r, g, b, opacity));
    getGc().fillOval(posX, posY, w, h);
    posY+=20;
  }

}

