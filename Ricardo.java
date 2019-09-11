import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ricardo extends Asteroid{
    private int dx;
    private int dy;
    private List<RicardoMissile> missiles;
    public Random rand = new Random();
    private  int MOVE_SPEED = 5;
    public int life;
    public Boolean ricardoGoRight = false;
    public Boolean ricardoGoLeft = false;


    public Ricardo(int x){
        super(x, 0, 15);
        this.life = 50;
        this.width = 300;
        this.height = 300;
        initRicardo();
    }

    private void initRicardo(){
        missiles = new ArrayList<>();
        loadImage("./img/ricardo.png");
    }


    public List<RicardoMissile> getMissiles(){
        return missiles;
    }

    public void removeLife(){
        if(life > 0){
            life -= 1;
        }
    }

    public int getLife(){
        return life;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    @Override
    public boolean isRicardo(){
        return true;
    }

    public void fire(){
        int random = rand.nextInt(40);

        if(random == 10){
            missiles.add(new RicardoMissile(x + width / 2, y + height));
        }
    }

    public void move(){
        int random = rand.nextInt(20);

        if(random == 0 && x < 1100 || ricardoGoRight && x < 1100){
            ricardoGoRight = true;
            ricardoGoLeft = false;
            x += MOVE_SPEED;
        }
        if(random == 1  && x > 0|| ricardoGoLeft && x > 0){
            ricardoGoLeft = true;
            ricardoGoRight = false;
            x -= MOVE_SPEED;
        }
        if(y > 1100 || x > 1400){
            visible = false;
        }
    }
}