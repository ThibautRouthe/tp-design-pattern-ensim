package fr.ensim.dp.cache.filter;

import fr.ensim.dp.util.GzipUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CompressFilterCacheTest {

    @Test
    void doAdd() throws IOException {

        byte[] buf = "test".getBytes();

        CompressFilterCache cache = new CompressFilterCache();

        byte[] bufAdd = cache.doAdd("", buf);
        assertArrayEquals(GzipUtil.compress(buf), bufAdd);

        assertArrayEquals(cache.doRetreive("", bufAdd), buf);
    }
    @Test
    void setNext(){
        CompressFilterCache cache = new CompressFilterCache();

        assertEquals(cache,cache.setNext(cache));

    }
}