package jp.ac.uryukyu.ie.e165730;
import java.util.*;

public class Group {
    //まとまっている石の座標とその色
    private List<List> group = new ArrayList<List>();
    private int groupCount;
    private int breath;

    public Group(){
        groupCount = -1;
        breath = 0;
    }

    //集まりとして石の座標を追加
    public void addStone(int x, int y){
        List<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        group.add(coordinate);
        groupCount += 1;
    }

    //引数のインデックスにある座標を返す。
    public List getGroup(int num){
        return group.get(num);
    }

    //最後に入った座標を返す。
    public List getGroup(){
        return group.get(groupCount);
    }

    public List getGroupList(){
        return group;
    }

    public void printGroup(int num){
        System.out.println(group.get(num));
    }


    //breathのgetter, setter, plusメソッド
    public void breathPlus(){
        breath += 1;
    }

    public int getBreath(){
        return breath;
    }

    public void setBreath(int breath){
        this.breath = breath;
    }
}
