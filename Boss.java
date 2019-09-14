
import java.util.Random;
public class Boss extends Asteroid{
    public int MOVE_SPEED = 2;
    public Random rand = new Random();

    Boss(int x){
        super(x, 0, 15);
        this.width = 300;
        this.height = 300;
        initBoss();
    }

    public void initBoss(){
        loadImage("img/theboss.png");
    }

    @Override
    public boolean isBoss(){
        return true;
    }

    @Override
    public boolean isPacman(){
        return false;
    }

    public void move(){
        y += MOVE_SPEED;
        if(y > BOARD_HEIGHT){
            visible = false;
        }
    }
}