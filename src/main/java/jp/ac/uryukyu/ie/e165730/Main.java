package jp.ac.uryukyu.ie.e165730;


public class Main {
    public static void main(String[] args){
        Board go = new Board();
        go.emptyBoard();
        go.setBorad(1, 4, 4);
        go.setBorad(2, 3, 4);
        go.printBoard();
    }
}
