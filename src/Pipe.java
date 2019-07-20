import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pipe extends Rectangle {

    public Pipe(){
        setX(600);
        setY((int)(Math.random() * 300) + 20);
        setWidth(50);
        setHeight(500);
        setFill(Color.GREEN);
    }

    public void moveLEFT(){
        double curX = this.getX();
        this.setX(curX - 5);
    }

}
