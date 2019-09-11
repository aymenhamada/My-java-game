
public class Missile extends Sprite{
    private  int BOARD_HEIGHT;
    private final int MISSILE_SPEED = 15;

    public Missile(int x, int y){
        super(x, y);
        initMissile();
        this.BOARD_HEIGHT = 0 - this.width;
    }

    private void initMissile(){
        loadImage("./img/laser.png");
        getImageDimensions();
    }

    public void move(){
        y -= MISSILE_SPEED;

        if(y < BOARD_HEIGHT){
            visible = false;
        }
    }
}