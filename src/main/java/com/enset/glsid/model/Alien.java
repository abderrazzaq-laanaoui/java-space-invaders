package com.enset.glsid.model;

import com.enset.glsid.N7Invaders;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Alien extends Character implements Explodable{
    private static final String IMAGE_PATH = "file:src/main/resources/alien";
    private static final String IMAGE_EXT = ".png";

    boolean exploding, destroyed;
    int explosionStep;
    public Alien(GraphicsContext gc, int posX, int posY, int size, int score, int imgNumber) {
        super(gc,posX, posY, size, (score/5)+2, new Image(IMAGE_PATH+imgNumber+IMAGE_EXT));
        exploding = false;
        destroyed = false;
        explosionStep =0;
    }


    public void update() {
        //super.update();
        if(!exploding && !destroyed) posY += speed;
        if(posY > N7Invaders.HEIGHT) destroyed = true;
        // posX should be changed so that the alien moves to the rocket

    }


    @Override
    public boolean isExploding() {
        return exploding;
    }

    @Override
    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
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

    // TODO : aliens should shot the rocket also
    Shot shoot() {
        return new Shot(getGc(),posX + size / 2 - Shot.size / 2, posY + size, Shot.size,  1);
    }
}
