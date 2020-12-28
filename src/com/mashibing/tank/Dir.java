package com.mashibing.tank;
import java.util.Random;

public enum Dir {
    L,U,R,D;

    private static Random r = new Random();

    public static Dir ramdomDir() {
        return  values()[r.nextInt(Dir.values().length)];
    }
}
