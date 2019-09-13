
import java.util.List;

public class Asteroid extends Sprite{
    private  int MOVE_SPEED = 5;
    protected final int BOARD_HEIGHT = 1050;
    public int life;
    private List<RicardoMissile> missile;


    public Asteroid(int x, int y, int life){
        super(x, 0);
        this.life = life;
        this.width = 100;
        this.height = 100;
        initAsteroid();
    }

    private void initAsteroid(){
        loadImage("img/asteroid.png");
    }


    public void removeLife(int damage){
        if(life > 0){
            life -= damage;
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

    public boolean isBoss(){
        return false;
    }

    public boolean isPacman(){
        return false;
    }

    public boolean isRicardo(){
        return false;
    }

    public boolean isBonus(){
        return false;
    }

    public List<RicardoMissile> getMissiles(){
        return missile;
    }


    public void fire(){
    }

    public void move(){
        y += MOVE_SPEED;
        if(y > BOARD_HEIGHT || x > 1400){
            visible = false;
        }
    }
}