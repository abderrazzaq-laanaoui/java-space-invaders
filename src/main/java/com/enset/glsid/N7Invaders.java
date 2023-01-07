package com.enset.glsid;

import com.enset.glsid.presentation.model.Alien;
import com.enset.glsid.presentation.model.Rocket;
import com.enset.glsid.presentation.model.Shot;
import com.enset.glsid.presentation.model.Universe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class N7Invaders extends Application {

  private static final Random RAND = new Random();
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  private static final int PLAYER_SIZE = 60;
  static final int PLAYER_IMG_INDEX = 1;
  private static final int ALIENS_IMG_SIZE = 1;


  final int MAX_ALIENS = 5,  MAX_SHOTS = MAX_ALIENS * 2;
  boolean gameOver = false;
  private GraphicsContext gc;


  private static Rocket player;
  List<Shot> shots;
  List<Universe> univ;
  ArrayList<Alien> aliens;

  private int score;

  public static Rocket getPlayer() {
    return player;
  }


  @Override
  public void start(Stage stage) throws Exception {
    Canvas canvas = new Canvas(WIDTH, HEIGHT);
    canvas.setFocusTraversable(true);
    gc = canvas.getGraphicsContext2D();
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
    canvas.setCursor(Cursor.DISAPPEAR);

   /* canvas.setOnMouseMoved(e -> mouseX = e.getX());
    canvas.setOnMouseClicked(e -> {
      if(shots.size() < MAX_SHOTS) shots.add(player.shoot());
      if(gameOver) {
        gameOver = false;
        setup();
      }
    });
*/
    //replace mouse event by keyboard event, space for shooting, left and right arrows for moving, and escape for pause and resume
    canvas.setOnKeyPressed(e -> {
      switch(e.getCode()) {
        case SPACE:
          if(shots.size() < MAX_SHOTS) shots.add(player.shoot());
          if(gameOver) {
            gameOver = false;
            setup();
          }
          break;
        case LEFT:
          player.moveLeft();
          break;
        case RIGHT:
          player.moveRight();
          break;
        case ESCAPE:
          if(timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.pause();
          }
          else timeline.play();
          break;

      }
    });

    setup();
    stage.setScene(new Scene(new StackPane(canvas)));
    stage.setTitle("Space Invaders");
    stage.show();

  }

  // set up the game
  private void setup() {
    univ = new ArrayList<>();
    shots = new ArrayList<>();
    aliens = new ArrayList<>();
    player = new Rocket(gc,WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG_INDEX);
    score = 0;
    IntStream.range(0, MAX_ALIENS).mapToObj(i -> this.newAlien()).forEach(aliens::add);
  }

  //run Graphics
  private void run(GraphicsContext gc) {
    gc.setFill(Color.grayRgb(20));
    gc.fillRect(0, 0, WIDTH, HEIGHT);
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setFont(Font.font(20));
    gc.setFill(Color.WHITE);
    gc.fillText("Score: " + score, 60,  20);


    if(gameOver) {
      gc.setFont(Font.font(35));
      gc.setFill(Color.YELLOW);
      gc.fillText("Game Over \n Your Score is: " + score + " \n Click to play again", WIDTH / 2.0, HEIGHT /2.5);
      //	return;
    }
    univ.forEach(Universe::draw);

    player.update();
    player.draw();

    aliens.stream().peek(Alien::update).peek(Alien::draw).forEach(e -> {
      if(player.colide(e) && player.isExploding()) {
        player.explode();
      }
    });

    for (int i = shots.size() - 1; i >=0 ; i--) {
      Shot shot = shots.get(i);
      if(shot.posY < 0 || shot.isToRemove())  {
        shots.remove(i);
        continue;
      }
      shot.update();
      shot.draw(score);
      for (Alien alien : aliens) {
        if(shot.colide(alien) && alien.isExploding()) {
          score++;
          alien.explode();
          shot.setToRemove(true);
        }
      }
    }

    for (int i = aliens.size() - 1; i >= 0; i--){
      if(aliens.get(i).isDestroyed())  {
        aliens.set(i, newAlien());
      }
    }

    gameOver = player.isDestroyed();
    if(RAND.nextInt(10) > 2) {
      univ.add(new Universe(gc));
    }

    univ.removeIf(u -> u.getPosY() > HEIGHT);

}
  Alien newAlien() {
    return new Alien(gc,50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, score,RAND.nextInt(ALIENS_IMG_SIZE)+1);
  }


  public static void main(String[] args) {
    launch();
  }
}