
public class RicardoMissile extends Sprite{
    private  int BOARD_HEIGHT;
    private final int MISSILE_SPEED = 15;

    RicardoMissile(int x, int y){
        super(x, y);
        initRicardoMissile();
    }

    public void initRicardoMissile(){
        loadImage("img/lasershot.png");
    }

    public void move(){
        y += MISSILE_SPEED;
        if(y > 1400){
            visible = false;
        }
    }
}