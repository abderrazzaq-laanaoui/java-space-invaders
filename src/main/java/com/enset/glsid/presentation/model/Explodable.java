package com.enset.glsid.presentation.model;

import javafx.scene.image.Image;

public interface Explodable {

  Image EXPLOSION_IMG = new Image("file:src/main/resources/images/explosion.png");
  int EXPLOSION_W = 128;
  int EXPLOSION_ROWS = 3;
  int EXPLOSION_COL = 3;
  int EXPLOSION_H = 128;
  int EXPLOSION_STEPS = 15;

  boolean isExploding();
  void setExploding(boolean exploding);
  boolean isDestroyed();
  void setDestroyed(boolean destroyed);

  void explode();



}
