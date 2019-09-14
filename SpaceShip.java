
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;



public class SpaceShip extends Sprite {

    private int dx;
    private int dy;
    private final int mapX = 1400;
    private final int mapY = 1400;
    private final int SPACE_SHIP_SPEED = 10;
    private List<Missile> missiles;
    public Boolean specialAttack = false;
    public Boolean instinctSurvival = false;
    public Boolean superAttack = false;
    public int life = 3;
    public int stack = 0;

    public SpaceShip(int x, int y){
        super(x, y);
        initSpaceShip();
    }

    public void initSpaceShip(){
        missiles = new ArrayList<>();
        loadImage("img/sprite.png");
        getImageDimensions();
    }

    public void move(){
            x += dx;
            y += dy;
    }

    public List<Missile> getMissiles(){
        return missiles;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            fire();
        }
        if(key == KeyEvent.VK_LEFT){
            dx = -1 * SPACE_SHIP_SPEED;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 1 * SPACE_SHIP_SPEED;
        }

        if(key == KeyEvent.VK_UP){
            dy = -1 * SPACE_SHIP_SPEED;
        }

        if(key == KeyEvent.VK_DOWN){
            dy = 1 * SPACE_SHIP_SPEED;
        }
        if(key == 86 && superAttack || key == 86 && stack > 0){
            if(stack > 0){
                stack--;
            }
            else{
                superAttack = false;
            }
            Kamehamada();
        }
    }

    public void fire(){
        if(specialAttack){
            for(int i = 0; i < 20; i++){
                missiles.add(new Missile((x / 2) + i * 40, y - 100 / 2, "img/laser.png"));
            }
            specialAttack = false;
        }
        else{
            missiles.add(new Missile(x + 15, y - 100 / 2, "img/laser.png" ));

        }
        if(instinctSurvival){
            for(int i = 0; i < 20; i++){
                missiles.add(new Missile((x / 2) + i * 40, y - 100 / 2, "img/laser.png"));
            }
        }
   }

   public void Kamehamada(){
        missiles.add(new Missile(x +15, y - 100 / 2, "img/kiblast.png"));
   }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            if(dx < 0){
                dx = 0;
            }
        }

        if(key == KeyEvent.VK_RIGHT){
            if(dx > 0){
                dx = 0;
            }
        }

        if(key == KeyEvent.VK_UP){
            if(dy < 0){
                dy = 0;
            }
        }

        if(key == KeyEvent.VK_DOWN){
            if(dy > 0){
                dy = 0;
            }
        }
    }

    public void removeLife(){
        if(life > 0){
            life -= 1;
        }
    }

    public int getLife(){
        return life;
    }

    public void setSpecialAttack(Boolean bool){
        this.specialAttack = bool;
    }

    public Boolean getSpecialAttack(){
        return specialAttack;
    }

    public void setInstinctSurvival(Boolean bool){
        this.instinctSurvival = bool;
    }

    public void bonusLife(){
        this.life += 1;
    }

    public void setSuperAttack(Boolean bool){
        if(this.superAttack){
            stack += 1;
        }else{
            this.superAttack = bool;
        }
    }

    public void setDefaultStats(){
        this.superAttack = false;
        this.instinctSurvival = false;
        this.superAttack = false;
        this.stack = 0;
    }

    public void setLife(int life){
        this.life = life;
    }
}