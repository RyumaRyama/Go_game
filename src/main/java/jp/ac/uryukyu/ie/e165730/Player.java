package jp.ac.uryukyu.ie.e165730;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Player {
    private int take, stone, count;   //石の色，石の集まりができるたびにカウント
    private boolean pass, noPut;    //パス，石を置いたかの判定
    private List<Group> groupList = new ArrayList<Group>();
    private Map<List, Integer> groupNum = new HashMap<List, Integer>();

    public Player(int stone){
        this.stone = stone;
        pass = false;
        count = 0;
    }

    //groupNumのgetter
    public Map getGroupNum(){
        return groupNum;
    }

    //石を置く
    public void put(Board go, int x, int y){
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
    }

    //石を置く座標の入力
    public void thinking(Board go){
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
            put(go, x, y);
        }while(noPut);
        if(!pass){
            lookStone(go, x, y);
        }
    }

    //石を集まりを作成
    public void makeGroup(int x, int y){
        //集まりとしてのインスタンス生成
        Group group = new Group();
        group.addStone(x, y);
        groupList.add(group);

        //どの集まりに所属しているか
        groupNum.put(groupList.get(count).getGroup(), count);
        count += 1;
    }

    //座標の石を確認
    public void lookStone(Board go, int x, int y){
        //go.searchStone(x, y);
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        //上下左右の石を確認
        addGroup(go, x, y, 0, -1);
        addGroup(go, x, y, 0, 1);
        addGroup(go, x, y, -1, 0);
        addGroup(go, x, y, 1, 0);
        if(groupNum.get(coordinate) == null){
            makeGroup(x, y);
        }
    }

    //石を集まりに追加
    //x_x, y_yは元の座標の上下左右に+1, -1する変数
    public void addGroup(Board go, int x, int y, int x_x, int y_y){
        int stone = go.getBoard()[y+y_y][x+x_x];
        List<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        if(stone == this.stone){
            List<Integer> move = new ArrayList<Integer>();
            move.add(x + x_x);
            move.add(y + y_y);
            //グループに所属していない時
            if(groupNum.get(coordinate) == null){
                groupList.get(groupNum.get(move)).addStone(x, y);
                groupNum.put(coordinate, groupNum.get(move));
            }
            /*
                所属しているグループがあるならそこにもう片方のグループを追加していく
                一つにまとめて，片方は使わず放置
            */
            else{
                int size, i, temp_x, temp_y, num;
                size = groupList.get(groupNum.get(move)).getGroupList().size();
                for(i=0; i<size; i++){
                    List<Integer> list;
                    list =  groupList.get(groupNum.get(move)).getGroup(i);
                    temp_x = list.get(0);
                    temp_y = list.get(1);
                    groupList.get(groupNum.get(coordinate)).addStone(temp_x, temp_y);
                    num = groupNum.get(coordinate);
                    groupNum.put(list, num);
                }
            }
        }
    }

    //passのsetter,getter
    public void setPass(boolean pass){
        this.pass = pass;
    }
    public boolean getPass(){
        return pass;
    }
    public int getStone(){
        return stone;
    }
    public int getTake(){
        return take;
    }

    //石をとる
    public void takeStone(Board go, Player enemy){
        go.searchClean();
        int i, j;
        for(i=0; i<11; i++){
            for(j=0; j<11; j++){
                //的の石を見つけたらそこから呼吸点を計算
                if (go.getSearch()[i][j] == 0) {
                    if(go.getBoard()[i][j] == enemy.getStone()){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(j);
                        list.add(i);
                        enemy.groupList.get(enemy.groupNum.get(list)).setBreath(0);
                        take += go.breathCount(enemy.groupList.get(enemy.groupNum.get(list)), enemy, j, i);
                    }
                }
            }
        }
    }
}
