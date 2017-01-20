package jp.ac.uryukyu.ie.e165730;

public class Board {
    private int[][] board = new int[9][9];

    public Board(){
        emptyBoard();
    }
    public void emptyBoard(){
        int i, j;
        for(i=0; i<9; i++){
            for(j=0; j<9; j++){
                board[i][j] = 0;
            }
        }
    }

    /*
    boardの値から，それに対応する石で現在のボード状況を出力する。
    0："+"
    1："●"
    2："○"
     */
    public void printBoard(){
        int i, j;
        for(i=0; i<=9; i++){
            if(i==0){
                System.out.print("  ");
                for(j=1; j<=9; j++)
                    System.out.print(j+" ");
            }
            else{
                System.out.print(i+" ");
                for(j=1; j<=9; j++){
                    switch(board[i-1][j-1]){
                        case 0:
                            System.out.print("+ ");
                            break;
                        case 1:
                            System.out.print("● ");
                            break;
                        case 2:
                            System.out.print("○ ");
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //座標の場所に石を置く
    public void setBoard(int stone, int x, int y){
        x -= 1;
        y -= 1;
        board[y][x] = stone;
    }

    public int[][] getBoard() {
        return board;
    }
}
