package jp.ac.uryukyu.ie.e165730;
import java.util.*;

public class Group {
    //まとまっている石の座標とその色
    List<ArrayList<Integer>> group = new ArrayList<ArrayList<Integer>>();
    int stone;

    public Group(int stone){
        this.stone = stone;
    }

    //集まりとして石の座標を追加
    public void addStone(int x, int y){
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        group.add(coordinate);
    }

    public ArrayList getGroup(int num){
        return group.get(num);
    }

    public void printGroup(int num){
        System.out.println(group.get(num));
    }
}
