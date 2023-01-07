package com.enset.glsid.presentation.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

public class Shot  extends Character{

  static final int size = 6;
  @Getter @Setter
  boolean toRemove;

    public Shot(GraphicsContext gc,int posX, int posY, int size, int imgNumber) {
        super(gc, posX, posY, size, 10, new Image("file:src/main/resources/images/shot"+imgNumber+".png"));
    }

  public void update() {
    posY-=speed;
  }
  public void draw(int score) {
    getGc().setFill(Color.RED);
    if (score >=50 && score<=70 || score>=120) {
      getGc().setFill(Color.YELLOWGREEN);
      speed = 50;
      getGc().fillRect(posX-5, posY-10, size+10, size+30);
    } else {
      getGc().fillOval(posX, posY, size, size);
    }
  }

  /*
  public boolean colide(Rocket rocket) {
    int d = distance(this.posX + size / 2, this.posY + size / 2,
         rocket.getPosX()+ rocket.getSize() / 2, rocket.getPosY() + rocket.getSize() / 2);
    return d  < rocket.size / 2 + size / 2;
  }
*/

}
