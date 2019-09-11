
import java.util.Random;

public class Pacman extends Asteroid{
    public int randomPos;
    public int MOVE_SPEED = 10;

    Pacman(int x, int y, int randomPos){
        super(x, y, 1);
        this.y = y;
        this.width = 200;
        this.height = 200;
        this.randomPos = randomPos;
        initPacman();
    }

    public void initPacman(){
        loadImage("./img/pacman.png");
    }

    @Override
    public boolean isBoss(){
        return false;
    }

    @Override
    public boolean isPacman(){
        return true;
    }

    public void move(){


        switch(randomPos){
            case 0:
                x += MOVE_SPEED;
                y += MOVE_SPEED;
                if(y > BOARD_HEIGHT){
                    visible = false;
                }
                break;
            case 1:
                x -= MOVE_SPEED;
                y += MOVE_SPEED;
                if(y > BOARD_HEIGHT){
                    visible = false;
                }
                break;
            case 2:
                x += MOVE_SPEED;
                y -= MOVE_SPEED;
                if(y < -200){
                    visible = false;
                }
                break;
            case 3:
                x -= MOVE_SPEED;
                y -= MOVE_SPEED;
                if(y < -200){
                    visible = false;
                }
                break;
            case 4:
                x += MOVE_SPEED;
                if(x > 1200){
                    visible = false;
                }
                break;
            case 5:
                x -= MOVE_SPEED;
                if(x < - 200){
                    visible = false;
                }
                break;
            case 6:
                y += MOVE_SPEED;
                if(y > BOARD_HEIGHT){
                    visible = false;
                }
                break;
        }
    }
}