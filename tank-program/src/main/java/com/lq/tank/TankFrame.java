package com.lq.tank;

import com.lq.tank.enums.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

/**
 * @Description
 * @Date 2021/4/11 22:10
 * @Author lq
 */
public class TankFrame extends Frame {
    boolean bR = false,bL = false,bD = false,bU = false;
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    private Tank tank = new Tank(400,500,Dir.DOWN,this,Group.GOOD);

    //private Bullet bullet = new Bullet(200,200,Dir.DOWN,this,Group.GOOD);

    private List<Bullet> bullets = new ArrayList<>(6);

    private Image offScreenImage = null;
    // 敌军坦克
    private List<Tank> tanks = new ArrayList<>(5);
    // 一个爆炸
//    private Explode explode = new Explode(100,100);
    //创建一个爆炸的集合
    List<Explode> explodeList = new ArrayList<>(5);


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        // 不能改变大小
        setResizable(false);
        //设置可见
        setVisible(true);
        // 设置标题
        setTitle("tank war");
        //添加按键的监听
        this.addKeyListener(new MykeyListener());
        // 设置可以关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 系统退出，关闭窗口
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 这个方法会在frame窗口打开自动调用
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bullets.size(), 10, 60);
        g.drawString("tanks:" + tanks.size(), 10, 80);
        g.drawString("explodes:" + explodeList.size(), 10, 100);
        g.setColor(c);
        tank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        // 画出敌军tank
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        // 遍历子弹做碰撞检测
        for (Bullet b : bullets) {
            for (Tank t : tanks) {
                b.collideWith(t);
            }
        }
        for (Explode explode : explodeList) {
            explode.paint(g);
        }
        //explode.paint(g);
    /*    bullets.forEach((t)->{
            t.paint(g);
        });*/
//        for (Bullet bullet1 : bullets) {
//            bullet1.paint(g);
//        }
        //x += 10;

    }


    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        bullets = bullets;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    class MykeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //  x += 100;
            // 调用repaint（）方法就会自动调用paint方法进行重画
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    setMainTankDir();
                    break;
                default:
                    break;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_CONTROL:
                    new Thread(()->{new Audio("audio/tank_fire.wav").play();}).start();
                    tank.fire();
                    break;
                default:
                    break;
            }
            repaint();
        }

        /**
         * 设置主坦克的放向
         */
        private void setMainTankDir() {
            //save the old dir1
            Dir dir = tank.getDir();
            if (!bL && !bU && !bR && !bD) {
                tank.setMoving(false);
            }else {
                if (bL){
                    tank.setDir(Dir.LEFT);
                }
                if (bR){
                    tank.setDir(Dir.RIGHT);
                }
                if (bU){
                    tank.setDir(Dir.UP);
                }
                if (bD){
                    tank.setDir(Dir.DOWN);
                }
                tank.setMoving(true);
            }
        }
        }

    public List<Explode> getExplodeList() {
        return explodeList;
    }

    public void setExplodeList(List<Explode> explodeList) {
        this.explodeList = explodeList;
    }
}
