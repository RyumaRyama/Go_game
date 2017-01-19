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

    public void printBoard(){
        int i, j;
        for(i=0; i<9; i++){
            for(j=0; j<9; j++){
                switch(board[i][j]){
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
            System.out.println();
        }
    }

    public void setBorad(int stone, int x, int y){
        board[y][x] = stone;
    }
}
