import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Main extends Application {
    Pipe pipe = new Pipe();
    Sprite player = new Sprite();
    Rectangle floor = new Rectangle();
    Text score = new Text();

    Pipe topPipe = new Pipe();

    @Override
    public void start(Stage stage) {
        Group root = new Group();

        ObservableList list = root.getChildren();

        floor.setFill(Color.RED);
        floor.setX(0);
        floor.setY(300);
        floor.setHeight(10);
        floor.setWidth(800);

        score.setX(500);
        score.setY(15);
        score.setFont(new Font(15));
        score.setText("Score: " + player.score);

        topPipe.setY(0);
        topPipe.setX(pipe.getX());
        topPipe.setWidth(pipe.getWidth());
        topPipe.setHeight(pipe.getY() - 50);

        list.add(player);
        list.add(floor);
        list.add(score);
        list.add(pipe);
        list.add(topPipe);
        Scene scene = new Scene(root, 600, 300);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                  //  case UP: player.up = true; break;
                  //  case DOWN: player.down = true; break;
                    case LEFT:  player.left = true; break;
                    case RIGHT: player.right = true; break;
                    case SPACE: player.jump = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                   // case UP: player.up = false; break;
                   // case DOWN: player.down = false; break;
                    case LEFT:  player.left = false; break;
                    case RIGHT: player.right = false; break;
                    case SPACE: player.jump = false; break;
                }
            }
        });


        final long startNanoTime = System.nanoTime();

        new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                if(checkBounds(player, pipe) || checkBounds(player, topPipe)){
                    player.alive = false;
                }

                if(pipe.getX() <= -50){
                    pipe = new Pipe();
                    player.score++;
                    score.setText("Score: " + player.score);
                    list.add(pipe);
                }

                if(player.alive){
                    player.move();

                    pipe.moveLEFT();
                    topPipe.setX(pipe.getX());
                    topPipe.setHeight(pipe.getY() - 100);

                    gravity(player);
                }else{
                    return;
                }
            }
        }.start();

        stage.setTitle("...");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void gravity(Sprite s){
        double gravity = 1;
        double termSpeed = 5;
        if(s.getSpeed() <= termSpeed){
            s.setSpeed(s.getSpeed() + gravity);
        }
        s.moveDOWN(s.getSpeed());
    }

    public static boolean checkBounds(Sprite s, Rectangle r){
        if(s.getX()+s.getWidth() >= r.getX() && s.getX() <= r.getX() + r.getWidth()){
            if(s.getY() + s.getHeight() >= r.getY() && s.getY() <= r.getY() + r.getHeight()){
                return true;
            }
        }
        return false;
    }
}
