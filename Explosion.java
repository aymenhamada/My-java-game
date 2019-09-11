
public class Explosion extends Sprite{

    public Explosion(int x, int y){
        super(x, y);
        initExplosion();
    }

    public void initExplosion(){
        loadImage("img/explosion.gif");
        getImageDimensions();
    }
}