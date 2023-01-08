package com.enset.glsid.presentation.model;

import com.enset.glsid.N7Invaders;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Alien extends Character implements Explodable{
    private static final String IMAGE_PATH = "file:src/main/resources/images/alien";
    private static final String IMAGE_EXT = ".png";

    boolean exploding, destroyed;
    int explosionStep;
    public Alien(GraphicsContext gc, int posX, int posY, int size, int score, int imgNumber) {
        super(gc,posX, posY, size, (score/5)+2, new Image(IMAGE_PATH+imgNumber+IMAGE_EXT));
        resetState();
    }

    private void resetState() {
        exploding = false;
        destroyed = false;
        explosionStep =0;
    }


    public void update() {
        System.out.println(this+" "+posX+","+posY);
        //super.update();
        if(!exploding && !destroyed) {
            posY += speed;
            if(posY > N7Invaders.HEIGHT) {
                destroyed = true;
            }
            // make it move left and right but in a smooth way


        }
        if(posY > N7Invaders.HEIGHT) destroyed = true;



    }


    @Override
    public boolean isExploding() {
        return !exploding;
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

        // after the explosion is done,wait 2s the alien should reset its position to the top of the screen in a random X position and call resetState()
        // to reset its state
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resetState();
            posX = (int) (Math.random() * (N7Invaders.WIDTH - size));
            posY = 0;
        }).start();


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