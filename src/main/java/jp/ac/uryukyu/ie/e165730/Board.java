package jp.ac.uryukyu.ie.e165730;

public class Board {
    private int[][] board = new int[11][11];

    public Board(){
        emptyBoard();
    }
    //boardを初期化
    //3が壁で0がnull
    public void emptyBoard(){
        int i, j;
        for(i=0; i<11; i++){
            j = 0;
            board[i][j] = 3;
            if(i == 0 || i == 10) {
                for (j=1; j<=9; j++) {
                    board[i][j] = 3;
                }
            }
            else{
                for(j=1; j<=9; j++){
                    board[i][j] = 0;
                }
            }
            board[i][j] = 3;
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
                    switch(board[i][j]){
                        case 0:
                            System.out.print("  ");
                            break;
                        case 1:
                            System.out.print("● ");
                            break;
                        case 2:
                            System.out.print("○ ");
                            break;
                    }
                    //System.out.print(board[i][j]+" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //座標の場所に石を置く
    public void setBoard(int stone, int x, int y){
        board[y][x] = stone;
    }

    public int[][] getBoard() {
        return board;
    }
}
