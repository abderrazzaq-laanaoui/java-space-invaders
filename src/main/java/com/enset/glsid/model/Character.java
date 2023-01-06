package com.enset.glsid.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;

public abstract class Character {
  @Getter
  public int posX;
  @Getter
  public int posY;
  @Getter
  int size;
  @Getter
  int speed;
  @Getter
  Image img;
  @Getter
  private GraphicsContext gc;


  public Character(GraphicsContext gc ,int posX, int posY, int size, int speed, Image img) {
    this.posX = posX;
    this.posY = posY;
    this.size = size;
    this.speed = speed;
    this.img = img;
    this.gc = gc;
  }

  public boolean colide(Character other) {
    int d = distance(this.posX + size / 2, this.posY + size /2,
        other.posX + other.size / 2, other.posY + other.size / 2);
    return d < other.size / 2 + this.size / 2 ;
  }
  int distance(int x1, int y1, int x2, int y2) {
    return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
  }
}
