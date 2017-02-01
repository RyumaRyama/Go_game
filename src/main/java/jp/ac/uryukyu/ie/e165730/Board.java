package jp.ac.uryukyu.ie.e165730;
import java.util.*;

public class Board {
    private int[][] board = new int[11][11];
    private int[][] search = new int[11][11];

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
    //固定変数
    private final int EMPTY = 0;
    private final int BLACK = 1;
    private final int WHITE = 2;
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
                        case EMPTY:
                            System.out.print("  ");
                            break;
                        case BLACK:
                            System.out.print("● ");
                            break;
                        case WHITE:
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
    public int[][] getSearch() {
        return search;
    }


    //グループ作成時の確認済み判定配列の初期化
    public void searchClean(){
        int i, j;
        for(i=0; i<11; i++){
            for(j=0; j<11; j++){
                search[i][j] = 0;
            }
        }
    }

    //グループ作成時の確認済み判定配列の管理
    public void searchStone(int x, int y){
        search[y][x] = 1;
    }

    //呼吸点を数える
    public int breathCount(Group group, Player enemy, int x, int y){
        searchStone(x, y);
        int count = 0;

        //それぞれの点を確認
        pointBreath(group, x, y-1, enemy);
        pointBreath(group, x, y+1, enemy);
        pointBreath(group, x-1, y, enemy);
        pointBreath(group, x+1, y, enemy);


        if(group.getBreath() == 0){
            count += deleteStone(group, enemy);
        }
        return count;
    }


    //座標の呼吸点をカウント
    public void pointBreath(Group group, int x, int y, Player enemy){
        if(search[y][x] == 0){
            if(board[y][x] == EMPTY){
                group.breathPlus();
            }
            else if(board[y][x] == enemy.getStone()){
                breathCount(group, enemy, x, y);
            }
        }
    }


    //石を消す
    public int deleteStone(Group group, Player enemy){
        int i, delete_x, delete_y, count;
        count = 0;
        List<Integer> list;
        for(i=0; i<group.getGroupList().size(); i++){
            list = group.getGroup(i);
            delete_x = list.get(0);
            delete_y = list.get(1);
            board[delete_y][delete_x] = EMPTY;
            enemy.getGroupNum().put(list, null);
            count += 1;
        }
        return count;
    }
}
