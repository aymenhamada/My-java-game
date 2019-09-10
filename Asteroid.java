

public class Asteroid extends Sprite{
    private  int MOVE_SPEED = 5;
    private final int BOARD_HEIGHT = 1050;
    public Boolean boss = false;
    public Boolean pacman = false;
    public int life;


    public Asteroid(int x, int y, int life, int width, int height,  String path){
        super(x, y);
        this.life = life;
        this.width = width;
        this.height = height;
        initAsteroid(path);
    }

    public void initAsteroid(String path){
        if(path == "theboss.png"){
            MOVE_SPEED = 2;
            boss = true;
        }
        if(path == "pacman.png"){
            MOVE_SPEED = 15;
            pacman = true;
        }
        loadImage(path);
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

    public boolean isBoss(){
        return boss;
    }

    public void move(){
        if(!pacman){
            y += MOVE_SPEED;
        }
        else{
            x += MOVE_SPEED;
        }
        if(y > BOARD_HEIGHT || x > 1400){
            visible = false;
        }
    }
}