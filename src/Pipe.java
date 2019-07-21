import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.RecursiveAction;

public class Pipe extends Rectangle {

    public Pipe(){

        int ranY = (int)(Math.random() * 300) + 20;

        setX(600);
        setY(ranY);
        setWidth(50);
        setHeight(500);
        setFill(Color.GREEN);

    }

    public void moveLEFT(){
        double curX = this.getX();
        this.setX(curX - 5);
    }

}
