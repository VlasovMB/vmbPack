package testquickresto.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * vlasovmb created on 20/01/2021
 **/
public class ParsersTest {


    @Test
    public void testParseNotEmpty(){
        Assert.assertTrue(Parsers.parseStrToSetPaths("/1 1 2 //1 ./ c/ c:\\ c: \\c").size() > 0);

    }

    @Test
    public void testParseEmptyString(){
        Assert.assertTrue(Parsers.parseStrToSetPaths(" ").isEmpty());
        Assert.assertTrue(Parsers.parseStrToSetPaths("          ").isEmpty());
    }

}