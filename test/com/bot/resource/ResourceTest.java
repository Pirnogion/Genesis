package com.bot.resource;

import com.bot.Resource;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResourceTest extends Assert
{
    @Test
    public void testConstruct() throws Exception
    {
        Resource resource001 = new Resource("FISHY", 3, -5, 0);
    }

    @Test
    public void testConsume() throws Exception
    {
        Resource resource001 = new Resource("FISHY", -100, 10, 0);

        assertEquals( resource001.consume(0), false );
        assertEquals( resource001.consume(-20), false );
        assertEquals( resource001.consume(50), true );
        assertEquals( resource001.consume(300), false );
    }

    @Test
    public void testProduce() throws Exception
    {
        Resource resource001 = new Resource("FISHY", -100, 10, 0);

        assertEquals( resource001.produce(0), false );
        assertEquals( resource001.produce(-20), false );
        assertEquals( resource001.produce(5), true );
        assertEquals( resource001.produce(300), false );
    }

    @Test
    public void testTransfer() throws Exception
    {
        Resource resource001_src = new Resource("FISHY1",-70, 10, 1);
        Resource resource001_dst = new Resource("FISHY2",0, 50, 0);

        assertEquals( resource001_src.transfer(0, resource001_dst), false );
        assertEquals( resource001_src.transfer(-10, resource001_dst), false );
        assertEquals( resource001_src.transfer(7, resource001_dst), true );
        assertEquals( resource001_src.transfer(60, resource001_dst), false );
        assertEquals( resource001_src.transfer(500, resource001_dst), false );
    }

}