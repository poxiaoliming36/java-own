package com.lq.tank;

import com.lq.tank.enums.Dir;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2021/4/11 21:55
 * @Author lq
 */
public class FrameMain {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        new Thread(()->{new Audio("audio/war1.wav").loop();}).start();
        // 初始化地方的坦克
        List<Tank> tanks = tankFrame.getTanks();
        for (int i = 0; i < Integer.parseInt(PropertyMgr.getKey("initTankCount")); i++) {
            tanks.add(new Tank(200+i*60,200, Dir.DOWN,tankFrame,Group.BAD));
        }
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
