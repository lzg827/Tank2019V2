package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank {
    private int x, y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = false;
    private Group group;

 //   TankFrame tf;

    public static final int SPEED = 2;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
     //   this.tf =tf;
    }

    public void paint(Graphics g) {
//       g.fillRect(x, y, 50, 50);
//        try {
//            BufferedImage tankL = ImageIO.read(Tank.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
//            g.drawImage(tankL,x,y,null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if(this.group == Group.GOOD)
            switch(dir){
                case L:
                    g.drawImage(ResourceMgr.goodTankL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.goodTankU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD,x,y,null);
                    break;
            };

        if(this.group == Group.BAD)
            switch(dir){
                case L:
                    g.drawImage(ResourceMgr.badTankL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.badTankU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.badTankR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.badTankD,x,y,null);
                    break;
            }

        move();
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }

        setMainDir();

    }

    private void setMainDir() {
        if (!bL && !bU && !bR && !bD)
            moving = false;
        else {
            moving = true;
            if (bL && !bU && !bR && !bD)
                dir = Dir.L;
            if (!bL && bU && !bR && !bD)
                dir = Dir.U;
            if (!bL && !bU && bR && !bD)
                dir = Dir.R;
            if (!bL && !bU && !bR && bD)
                dir = Dir.D;
        }

    }


    private void move() {
        if (!moving) return;
        ;

        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setMainDir();
    }

    private void fire() {
//        new Bullet(x,y,dir,group);
        TankFrame.INSTANCE.add(new Bullet(x,y,dir,group));
    }


}
