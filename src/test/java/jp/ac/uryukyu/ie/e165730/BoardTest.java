package jp.ac.uryukyu.ie.e165730;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;

public class BoardTest {
    @Test
    public void deleteStone() throws Exception {
        int stone, testX, testY;
        stone = 1;
        testX = 5;
        testY = 5;
        Board go = new Board();
        int[][] testGo = go.getBoard();
        Player black = new Player(stone);
        black.put(go, testX, testY);
        List<Integer> list = new ArrayList<Integer>();
        list.add(testX);
        list.add(testY);

        List<Group> groups =  black.getGroupList();
        Map<List, Integer> groupNums = black.getGroupNum();
        int groupNum = groupNums.get(list);
        Group group = groups.get(groupNum);
        go.deleteStone(group, black);
    }
}