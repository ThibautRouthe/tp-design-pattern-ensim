package fr.ensim.dp.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DiskCacheTest {
    private static DiskCache dCache;
    private static String key;
    private static String typeDir;
    private static byte[] image;
    @BeforeAll
    public static void initTest(){
        typeDir= "type1";
        dCache =DiskCache.getInstance(typeDir);
        key = "keyTest";
        image = key.getBytes();
    }
    @BeforeEach
    public void avantTest() {
        dCache.clear();
    }

    @Test
    void testSize() throws IOException {
        dCache.add(key,image);
        long expected = image.length;
        long actual = dCache.size();

        assertEquals(expected,actual);
    }

    @Test
    void testAdd() throws IOException {
        boolean expected = true;
        boolean actual = dCache.add(key,image);

        assertEquals(expected,actual);
    }

    @Test
    void testRetreive() throws IOException {
        dCache.add(key,image);
        byte[] expected = image;
        byte[] actual = dCache.retreive(key);

        assertArrayEquals(expected,actual);
    }

    @Test
    void testClear() throws IOException {
        dCache.add(key,image);
        dCache.clear();

        assertThrows(FileNotFoundException.class, ()-> {
            dCache.retreive(key);
        });
    }
}