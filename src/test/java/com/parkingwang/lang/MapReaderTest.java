package com.parkingwang.lang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.parkingwang.lang.kit.MapReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class MapReaderTest {

    @Test
    public void testObject(){
        JSONObject map = JSON.parseObject("{'message': 'YES', 'errors':[{'code': 1999}, {'code': 2016}]}");

        MapReader reader = MapReader.of(map);
        Assert.assertEquals("YES", reader.getCasted("message", "-FAILED-"));
        long code = reader.getCasted("errors[0].code", 0);
        Assert.assertEquals(1999, code );
        Assert.assertEquals(JSON.parseObject("{'code': 1999}"), reader.getCasted("errors[0]", new JSONObject(0)));
        Assert.assertEquals(JSON.parseArray("[{'code': 1999}, {'code': 2016}]"), reader.getCasted("errors", new JSONArray(0)));
    }

    @Test
    public void testObject1(){
        JSONObject map = JSON.parseObject("{\"menu\": {\n" +
                "  \"id\": 123,\n" +
                "  \"value\": \"File\",\n" +
                "  \"popup\": {\n" +
                "    \"menuitem\": [\n" +
                "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
                "    ]\n" +
                "  }\n" +
                "}}");

        MapReader reader = MapReader.of(map);
        final int val = reader.getInt("menu.id", 0);
        Assert.assertEquals(123, val);
        Assert.assertEquals("CloseDoc()", reader.getString("menu.popup.menuitem[0].onclick", "-FAILED-"));
    }

    @Test
    public void testObject2(){
        JSONObject map = JSON.parseObject("{\"widget\": {\n" +
                "    \"debug\": \"on\",\n" +
                "    \"window\": {\n" +
                "        \"title\": \"Sample Konfabulator Widget\",\n" +
                "        \"name\": \"main_window\",\n" +
                "        \"width\": 500,\n" +
                "        \"height\": 500\n" +
                "    },\n" +
                "    \"image\": { \n" +
                "        \"src\": \"Images/Sun.png\",\n" +
                "        \"name\": \"sun1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 250,\n" +
                "        \"alignment\": \"center\"\n" +
                "    },\n" +
                "    \"text\": {\n" +
                "        \"data\": \"Click Here\",\n" +
                "        \"size\": 36,\n" +
                "        \"style\": \"bold\",\n" +
                "        \"name\": \"text1\",\n" +
                "        \"hOffset\": 250,\n" +
                "        \"vOffset\": 100,\n" +
                "        \"alignment\": \"center\",\n" +
                "        \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" +
                "    }\n" +
                "}}");

        MapReader reader = MapReader.of(map);
        long width = reader.getLong("widget.window.width", 0);
        Assert.assertEquals(500, width);
        long hO = reader.getLong("widget.text.hOffset", 0);
        Assert.assertEquals(250, hO);
        Assert.assertEquals("center", reader.getString("widget.text.alignment", "-FAILED-"));
        Assert.assertEquals("-FAILED-", reader.getString("haha.hoho", "-FAILED-"));
    }
}
