import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {

    //used for gravity accel
    double speed = 0;
    //used for left/right mov
    double mvmSpeed = 5;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean jump = false;

    boolean alive = true;

    int score = 0;

    public Sprite(){
        setX(50);
        setY(50);
        setWidth(25);
        setHeight(25);
    }

    public void moveUP(double y){
        double curY = this.getY();
        this.setY(curY - y);
    }
    public void moveDOWN(double y){
        double curY = this.getY();
        this.setY(curY + y);
    }
    public void moveLEFT(double x){
        double curX = this.getX();
        this.setX(curX - x);
    }
    public void moveRIGHT(double x){
        double curX = this.getX();
        this.setX(curX + x);
    }


    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double x) {
        speed = x;
    }

    public void move(){
        //if (up) moveUP(2);
        //if (down) moveDOWN(2);
        if (left) moveLEFT(mvmSpeed);
        if (right) moveRIGHT(mvmSpeed);
        if (jump) jump();

    }

    public void jump() {
        for (int i = 0; i <= 5; i++) {
            this.setY(this.getY() - i);
        }

    }

}
