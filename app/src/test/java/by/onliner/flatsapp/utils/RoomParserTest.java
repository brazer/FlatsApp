package by.onliner.flatsapp.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 05.11.2016.
 */
public class RoomParserTest {
    @Test
    public void getRooms() throws Exception {
        String result = RoomParser.getRooms("6_room");
        Assert.assertEquals("6-комнатная", result);
        result = RoomParser.getRooms("12_rooms");
        Assert.assertEquals("12-комнатная", result);
        result = RoomParser.getRooms("room");
        Assert.assertEquals("Комната", result);
        result = RoomParser.getRooms("2_rooms");
        Assert.assertEquals("Двухкомнатная", result);
    }

}