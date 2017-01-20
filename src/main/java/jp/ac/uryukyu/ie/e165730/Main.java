package jp.ac.uryukyu.ie.e165730;
import java.io.*;

public class Main {
    public static void main(String[] args){
        //碁盤オブジェクト
        Board go = new Board();

        //0で黒石，1で白石のプレイヤー
        Player black = new Player(1);
        Player white = new Player(2);

        System.out.println("---------GO--------");
        System.out.println("x,yに0を入力するとパス");
        System.out.println();
        while(true){
            go = black.thinking(go);
            if(black.getPass() && white.getPass())
                break;
            go = white.thinking(go);
            if(black.getPass() && white.getPass())
                break;
        }
    }
}
