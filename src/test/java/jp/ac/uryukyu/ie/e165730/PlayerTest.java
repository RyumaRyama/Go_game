package jp.ac.uryukyu.ie.e165730;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void thinking() throws Exception {
        int stone, testX, testY;
        stone = 1;
        testX = 5;
        testY = 5;
        Board go = new Board();
        Player black = new Player(stone);
        black.put(go, testX, testY);
        assertEquals(stone, go.getBoard()[testY][testX]);
    }
}