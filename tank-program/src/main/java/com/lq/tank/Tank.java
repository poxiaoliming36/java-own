package com.lq.tank;

import com.lq.tank.enums.Dir;

import java.awt.*;
import java.util.Random;

/**
 * @Description
 * @Date 2021/4/12 22:25
 * @Author lq
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.UP;
    private Boolean moving = true;
    private static final int speed = 5;
    private TankFrame tankFrame = null;
    // tank是否活着
    private Boolean isLiving = true;

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    // 区分是地方坦克还是我方坦克
    private Group group;

    private Random random = new Random();

    private Rectangle rectangle;

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public Tank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.rectangle = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
    }

    public void paint(Graphics g) {
        // 如果tank不活着就设置为死
        if (!isLiving) {
            tankFrame.getTanks().remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            default:
                break;
        }
        move();
        this.rectangle.setLocation(this.x,this.y);
    }


    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    private void move() {
        if (!moving) {
            return;
        }

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
        // 随机开火
        if (random.nextInt(100) > 95) {
            fire();
        }
        // 随机移动反向
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        //边界检测
        boundCheck();

    }

    private void boundCheck() {
        if (this.x < 2) {
            x = 2;
        } else if (this.y < 32) {
            y=32;
        }else if (this.x>TankFrame.GAME_WIDTH-2-Tank.WIDTH){
            this.x=TankFrame.GAME_HEIGHT-2;
        }else if (this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT){
            this.y = TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
        }
    }

    /**
     * 随机移动方向
     */
    private void randomDir() {
        Dir[] values = Dir.values();
        this.dir = values[random.nextInt(4)];

    }


    public Dir getDir() {
        return dir;
    }


    public void fire() {
        // 将子弹从坦克中心打出来
        int bx = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.getBullets().add(new Bullet(bx, by, this.dir, tankFrame, this.group));
    }

    public Boolean getMoving() {

        return moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean getLiving() {
        return isLiving;
    }

    public void setLiving(Boolean living) {
        isLiving = living;
    }

    public void die() {
        this.isLiving = false;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
