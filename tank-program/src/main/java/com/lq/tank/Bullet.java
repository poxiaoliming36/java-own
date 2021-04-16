package com.lq.tank;

import com.lq.tank.enums.Dir;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2021/4/12 23:09
 * @Author lq
 */
public class Bullet {
    private int x, y;
    private static final int speed = 10;
    private Dir dir;
    private Boolean isLiving = true;
    private TankFrame tankFrame;
    public static final int WIDTH = ResourceMgr.bulletL.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletL.getHeight();
    // 用于区别是我方的子弹还是地方的子弹
    private Group group;

    private Rectangle rectangle;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.rectangle = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
    }

    public void paint(Graphics g) {
        if (!isLiving) {
            tankFrame.getBullets().remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            default:
                break;
        }
        move();
        this.rectangle.setLocation(this.x,this.y);
    }

    private void move() {

        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case UP:
                y -= speed;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            isLiving = false;
        }
    }

    /**
     * 做碰撞检测
     *
     * @param t
     */
    public void collideWith(Tank t) {
        if (t.getGroup()==this.group){
            return;
        }
        // 看两个方块是否相交
        if (this.rectangle.intersects(t.getRectangle())) {
            // 创建一个爆炸放到list集合中
            this.tankFrame.getExplodeList().add(new Explode(this.x+Tank.WIDTH/2-Explode.WIDTH/2,this.y+Tank.HEIGHT/2-Explode.HEIGHT/2,this.tankFrame));
            this.die();
            t.die();
        }
    }

    private void die() {
        this.isLiving =false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


}
