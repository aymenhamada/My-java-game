
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class SpaceShip extends Sprite {

    private int dx;
    private int dy;
    private final int mapX = 1400;
    private final int mapY = 1400;
    private final int SPACE_SHIP_SPEED = 10;
    private List<Missile> missiles;
    public Boolean specialAttack = false;
    public int life = 3;

    public SpaceShip(int x, int y){
        super(x, y);
        initSpaceShip();
    }

    public void initSpaceShip(){
        missiles = new ArrayList<>();
        loadImage("./img/sprite.png");
        getImageDimensions();
    }

    public void move(){
        if(x > 0 && dx < 0 || x < 1300 && dx > 0){
            x += dx;
        }
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
    }

    public void fire(){
        if(specialAttack && life > 1){
            for(int i = 0; i < 20; i++){
                missiles.add(new Missile((x / 2) + i * 40, y - 100 / 2));
            }
            specialAttack = false;
        }
        else if(specialAttack && life == 1){
            for(int i = 0; i < 20; i++){
                missiles.add(new Missile((x / 2) + i * 40, y - 100 / 2));
            }
        }
        else{
            missiles.add(new Missile(x + 25, y - 100 / 2 ));
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }

        if(key == KeyEvent.VK_UP){
            dy = 0;
        }

        if(key == KeyEvent.VK_DOWN){
            dy = 0;
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
}