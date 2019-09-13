
public class Bonus extends Asteroid{
    private int dx;
    private int dy;
    private final int MOVE_SPEED = 4;

    Bonus(int x){
        super(x, 0, 1);
        initBonus();
    }

    public void initBonus(){
        loadImage("img/risita.png");
    }

    public void move(){
        y += MOVE_SPEED;
    }

    @Override
    public boolean isBonus(){
        return true;
    }
}