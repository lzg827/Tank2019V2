package com.mashibing.tank;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
//        Frame f = new Frame("tank war");
//        f.setLocation(200,100);
//        f.setSize(800,600);
//        f.setVisible(true);
//        TankFrame tf = new TankFrame();
//        tf.setVisible(true);
        TankFrame.INSTANCE.setVisible(true);

        for(;;){
            try{
                TimeUnit.MICROSECONDS.sleep(800);
                //           Thread.sleep(25);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            TankFrame.INSTANCE.repaint();
        }

    }
}
