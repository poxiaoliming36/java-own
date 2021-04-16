package com.lq.tank;

import java.awt.*;

/**
 * @Description
 * @Date 2021/4/14 6:34
 * @Author lq
 */
public class Explode {
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private TankFrame tankFrame;
    // 爆炸的坐标
    private int x, y;

    private int step = 0;

    public Explode(int x, int y,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(()->{ new Audio("audio/explode.wav").play();}).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            step = 0;
            tankFrame.getExplodeList().remove(this);
        }
    }
}
