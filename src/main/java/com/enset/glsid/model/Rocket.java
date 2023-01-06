package com.enset.glsid.model;

import com.enset.glsid.N7Invaders;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rocket extends Character implements Explodable {


  boolean exploding, destroyed;

  int explosionStep = 0;
  private GraphicsContext gc;

  public Rocket( GraphicsContext gc,int posX, int posY, int size, int imgNumber) {
    super(gc, posX, posY, size, 0, new Image("file:src/main/resources/rocket"+imgNumber+".png"));
    this.exploding = false;
    this.destroyed = false;
    this.gc = gc;
  }
  public Shot shoot() {
    return new Shot(gc,posX + size / 2 - Shot.size / 2, posY - Shot.size, Shot.size,  1);
  }

  public void draw() {
    if(exploding) {
      getGc().drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W, (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1,
          EXPLOSION_W, EXPLOSION_H,
          posX, posY, size, size);
    }
    else {
      getGc().drawImage(img, posX, posY, size, size);
    }
  }

  public void update() {
    if(exploding) explosionStep++;
    destroyed = explosionStep > EXPLOSION_STEPS;
  }

  @Override
  public boolean isExploding() {
    return this.exploding;
  }

  @Override
  public void setExploding(boolean exploding) {
    this.exploding = exploding;
  }

  @Override
  public boolean isDestroyed() {
    return this.destroyed;
  }

  @Override
  public void setDestroyed(boolean destroyed) {
    this.destroyed = destroyed;
  }

  @Override
  public void explode() {
    exploding = true;
    explosionStep = -1;
  }

  public void moveLeft() {
    if(posX > 0) posX -= 10;
  }

  public void moveRight() {
    if(posX < N7Invaders.WIDTH - size) posX += 10;
  }
}
