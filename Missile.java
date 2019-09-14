
public class Missile extends Sprite{
    private  int BOARD_HEIGHT;
    private final int MISSILE_SPEED = 15;
    public Boolean isSuperAttack = false;


    public Missile(int x, int y, String pathname){
        super(x, y);
        if(pathname == "img/kiblast.png"){
            isSuperAttack = true;
        }
        initMissile(pathname);
        this.BOARD_HEIGHT = 0 - this.width;
    }

    private void initMissile(String pathname){
        loadImage(pathname);
        getImageDimensions();
    }

    public boolean isSuperAttack(){
        return isSuperAttack;
    }

    public void move(){
        y -= MISSILE_SPEED;

        if(y < BOARD_HEIGHT){
            visible = false;
        }
    }
}