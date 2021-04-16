package com.lq.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Date 2021/4/13 21:17
 * @Author lq
 */
public class T {
    public static void main(String[] args) throws IOException {
//        InputStream resourceAsStream = ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png");
//        System.out.println(resourceAsStream);

        File file = new File("G:\\persondir\\java\\tank-program\\src\\main\\resources\\images\\GoodTank1.png");
        BufferedImage read = ImageIO.read(file);

    }
}
