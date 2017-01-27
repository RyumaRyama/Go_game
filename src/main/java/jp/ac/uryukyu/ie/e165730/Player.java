package jp.ac.uryukyu.ie.e165730;
import java.io.*;
import java.util.*;

public class Player {
    private int stone, count = 0;   //石の色，石の集まりができるたびにカウント
    private boolean pass, noPut;    //パス，石を置いたかの判定
    private List<Group> groupList = new ArrayList<Group>();
    private Map<ArrayList, Integer> groupNum = new HashMap<ArrayList, Integer>();

    public Player(int stone){
        this.stone = stone;
        pass = false;
    }

    //石を置く
    public Board put(Board go, int x, int y){
        //x,yが0でパス，石が置かれている時にはもう一度thinking()
        if(x == 0 || y == 0) {
            pass = true;
            System.out.println("パス\n");
        }
        else{
            int point;
            point = go.getBoard()[y][x];
            if(point == 0)
                go.setBoard(stone, x, y);
            else {
                System.out.println("すでに石があります\n");
                noPut = true;
            }
        }
        return go;
    }

    //石を置く座標の入力
    public Board thinking(Board go){
        int x, y;
        System.out.println("Player "+stone);
        String buf;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        //石が置ける，またはパスするまで実行
        do{
            go.printBoard();
            pass = false;
            noPut = false;
            do{
                try{
                    System.out.print("x軸 ＝ ");
                    buf = br.readLine();
                    x = Integer.parseInt(buf);
                }catch(Exception e){
                    x = -1;
                }
            }while(x < 0 || x > 9);

            if(x != 0){
                do{
                    try{
                        System.out.print("y軸 ＝ ");
                        buf = br.readLine();
                        y = Integer.parseInt(buf);
                    }catch(Exception e){
                        y = -1;
                    }
                }while(y < 0 || y > 9);
            }
            else
                y = 0;

            //boardの出力を見やすくするため
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            go = put(go, x, y);
        }while(noPut);

        makeGroup(x, y);
        //groupList.get(count-1).printGroup(count-1);
        //System.out.println(groupNum.get(groupList.get(count-1).getGroup(count-1)));
        return go;
    }

    //石を一つの集まりとして扱う
    public void makeGroup(int x, int y){
        //集まりとしてのインスタンス生成
        Group group = new Group(stone);
        group.addStone(x, y);
        groupList.add(group);

        //どの集まりに所属しているか
        //エラー( ´▽｀)
        //groupNum.put(group.getGroup(count), count);
        //count += 1;
    }

    //passのsetter,getter
    public void setPass(boolean pass){
        this.pass = pass;
    }
    public boolean getPass(){
        return pass;
    }
}
