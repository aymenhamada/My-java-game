import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;


public class Board extends JPanel implements ActionListener {
    private final int ICRAFT_X = 400;
    private final int ICRAFT_Y = 800;
    private int score = 0;
    private Timer timer;
    private Timer asteroidTimer;
    private SpaceShip spaceShip;
    private final int DELAY = 10;
    private int AsteroidDelay = 300;
    private List<Asteroid> asteroids;
    private List<Explosion> explosions;
    private Random rand = new Random();
    public Boolean popBoss = false;
    public Boolean popPacman = false;

    public Board(){
        initBoard();
    }

    public void initBoard(){
        addKeyListener(new TAdapter());
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);
        asteroids = new ArrayList<>();
        explosions = new ArrayList<>();
        timer = new Timer(DELAY, this);
        timer.start();

        asteroidTimer = new Timer(AsteroidDelay, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(popBoss){
                    asteroids.add(new Asteroid(rand.nextInt(1100), 0, 15, 300, 300, "theboss.png"));
                    popBoss = false;
                }
                if(popPacman){
                    asteroids.add(new Asteroid(-200, spaceShip.getY(), 1, 200, 200, "pacman.png"));
                    popPacman = false;
                }
                asteroids.add(new Asteroid(rand.nextInt(1300), 0, 1, 100, 100, "asteroid.png"));
            }
        });
        asteroidTimer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), 80, 80, this);

        List<Missile> missiles = spaceShip.getMissiles();

        for(Missile missile : missiles){
            g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), 60, 60, this);
        }

        for(Asteroid asteroid: asteroids){
            g2d.drawImage(asteroid.getImage(), asteroid.getX(), asteroid.getY(), asteroid.getWidth(), asteroid.getHeight(), this);
        }
        for(Explosion explosion: explosions){
            g2d.drawImage(explosion.getImage(), explosion.getX(), explosion.getY(), 100, 100, this);
        }

        g2d.setColor(Color.red);
        g2d.drawString("Score :" + score, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        updateMissiles();
        updateSpaceShip();
        updateAsteroid();
        repaint();
    }

    private void updateMissiles(){
        List<Missile> missiles = spaceShip.getMissiles();

        for(int i = 0; i < missiles.size(); i++){
            Missile missile = missiles.get(i);

            if(missile.isVisible()){
                missile.move();
            } else{
                missiles.remove(i);
            }
            for(int w = 0; w < asteroids.size(); w++){
                Asteroid asteroid = asteroids.get(w);

                int obstacleX = asteroid.getX();
                int obstacleY = asteroid.getY();
                int hitBoxX = obstacleX + asteroid.getWidth();
                int hitBoxY = obstacleY + asteroid.getHeight();

                if(missile.getX() < obstacleX && missile.getX() + 60 > obstacleX && missile.getX() + 60 < hitBoxX && missile.getY() < obstacleY && missile.getY() + 60 > obstacleY && missile.getY() + 60 < hitBoxY){
                    asteroid.removeLife();
                    if(asteroid.getLife() == 0){
                        if(asteroid.pacman){
                            score += 5;
                        }
                        asteroids.remove(w);
                        score ++;
                        makeBoss();
                    }
                    missiles.remove(i);
                    explosions.add(new Explosion(asteroid.getX() + (asteroid.getWidth() / 3), asteroid.getY() + (asteroid.getHeight() / 3) ));
                    setTimeout(() -> explosions.remove(0), 500);
                    if(AsteroidDelay - score > 20){
                        asteroidTimer.setDelay(AsteroidDelay - score);
                        asteroidTimer.restart();
                    }
                }
                if(missile.getX() > obstacleX && missile.getX() < hitBoxX && missile.getY() > obstacleY && missile.getY() < hitBoxY){
                    asteroid.removeLife();
                    if(asteroid.getLife() == 0){
                        asteroids.remove(w);
                        score++;
                        makeBoss();
                    }
                    if(i < missiles.size()){
                        missiles.remove(i);
                        explosions.add(new Explosion(asteroid.getX() + (asteroid.getWidth() / 3), asteroid.getY() + (asteroid.getHeight() / 3) ));
                        setTimeout(() -> explosions.remove(0), 500);
                        if(AsteroidDelay - score > 20){
                            asteroidTimer.setDelay(AsteroidDelay - score);
                            asteroidTimer.restart();
                        }
                    }
                }
            }
        }
    }

    private void updateSpaceShip(){
        spaceShip.move();
    }

    private void updateAsteroid(){
        for(int i = 0; i < asteroids.size(); i++){
            Asteroid asteroid = asteroids.get(i);

            if(asteroid.isVisible()){
                asteroid.move();
            }
            else{
                if(asteroid.isBoss()){
                    timer.stop();
                    asteroidTimer.stop();
                }
                asteroids.remove(i);
            }

            int obstacleX = asteroid.getX() - 10;
            int obstacleY = asteroid.getY();
            double hitBoxX = obstacleX + (asteroid.getWidth() * 0.90);
            double hitBoxY = obstacleY + (asteroid.getHeight() * 0.50);
            if(spaceShip.getX() < obstacleX && spaceShip.getX() + 70  > obstacleX && spaceShip.getX() + 70 < hitBoxX && spaceShip.getY() < obstacleY && spaceShip.getY() + 70 > obstacleY && spaceShip.getY() + 70 < hitBoxY){
                timer.stop();
                asteroidTimer.stop();
            }
            if(spaceShip.getX() >= obstacleX && spaceShip.getX() <= hitBoxX && spaceShip.getY() >= obstacleY && spaceShip.getY() <= hitBoxY){
                timer.stop();
                asteroidTimer.stop();
            }
        }
    }

    public void makeBoss(){
        if(score % 10 == 0 && score > 0 ){
            popBoss = true;
        }
        if(score % 25 == 0 && score > 0){
            popPacman = true;
        }
    }
    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e){
            if(score % 10 == 0 && score > 0){
                spaceShip.setSpecialAttack(true);
                score++;
            }
            spaceShip.keyPressed(e);
        }
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}