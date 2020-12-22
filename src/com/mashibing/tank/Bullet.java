package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = 1;
    public boolean live = true;
    private int x, y;
    private Dir dir;
    private Group group;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {

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

        };



        boundsCheck();
    }

    public void collidesWithTank(Tank tank) {
        if (!tank.isLive())return;

        Rectangle rect = new Rectangle(x, y, ResourceMgr.badTankU.getWidth(), ResourceMgr.badTankU.getHeight());
        Rectangle recTank = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());

        if(rect.intersects(recTank)){
            this.die();
            tank.die();
        }

    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    public  void  die() {
        this.setLive(false);
    }

}
