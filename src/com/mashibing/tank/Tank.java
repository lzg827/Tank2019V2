package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Tank {
    public static final int SPEED = 1;
    private int x, y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private boolean moving = true;
    private Group group;
    private boolean live = true;

    private int oldX,oldY;
    private  int width,height;


    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        //   this.tf =tf;

        oldX = x;
        oldY = y;

        this.width = ResourceMgr.goodTankU.getWidth();
        this.height = ResourceMgr.goodTankU.getHeight();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }
    //   TankFrame tf;

    public void setLive(boolean live) {
        this.live = live;
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

    public void paint(Graphics g) {
//       g.fillRect(x, y, 50, 50);
//        try {
//            BufferedImage tankL = ImageIO.read(Tank.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
//            g.drawImage(tankL,x,y,null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (!this.isLive()) return;

        oldX = x;
        oldY = y;

        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();
        boundsCheck();
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height> TankFrame.GAME_HEIGHT) {
            this.back();
        }
    }

    private void back() {
        this.x = oldX;
        this.y = oldY;
    }


    private void move() {
        if (!moving) return;

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

        randomDir();

        if (r.nextInt(100) > 90)
            fire();
    }

    private Random r = new Random();

    private void randomDir() {
        //  this.dir = Dir.values()[random.nextInt(Dir.values().length)] ;
        if (r.nextInt(100) > 90)
            this.dir = Dir.ramdomDir();
    }


    private void fire() {
//        new Bullet(x,y,dir,group);
        int bX = x + ResourceMgr.goodTankD.getWidth() / 2 - ResourceMgr.bulletL.getWidth() / 2;
        int bY = y + ResourceMgr.goodTankD.getHeight() / 2 - ResourceMgr.bulletL.getHeight() / 2;
        TankFrame.INSTANCE.add(new Bullet(bX, bY, dir, group));
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x,y));
    }


}
