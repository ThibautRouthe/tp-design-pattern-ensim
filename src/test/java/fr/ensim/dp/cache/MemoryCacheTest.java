package fr.ensim.dp.cache;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MemoryCacheTest {
	private static MemoryCache mCache;
	private static String key;
	private static byte[] image;

	@BeforeAll
	public static void initTest(){
		mCache=MemoryCache.getInstance();
		key = "keyTest";
		image = key.getBytes();
	}

	@BeforeEach
	public void avantTest() {
		mCache.clear();
	}
	@Test
	public void testSize() throws IOException {
		mCache.add(key,image);
		long expected = key.length()*2+image.length;
		long actual = mCache.size();

		assertEquals(expected,actual);
	}

	@Test
	public void testAll() throws IOException {
		mCache.add(key, image);
		long expectedSize = key.length()*2+image.length;
		long actualSize = mCache.size();
		byte[] expectedRetreive = image;
		byte[] actualRetreive = mCache.retreive(key);
		mCache.clear();
		byte[] expectedClear = null;
		byte[] actualClear = mCache.retreive(key);

		assertEquals(expectedSize,actualSize);
		assertEquals(expectedRetreive,actualRetreive);
		assertEquals(expectedClear,actualClear);
	}
	@Test
	public void testRetreive() throws IOException {
		mCache.add(key, image);
		byte[] expected = image;
		byte[] actual = mCache.retreive(key);

		assertEquals(expected,actual);
	}

	@Test
	public void testClear() throws IOException {
		mCache.add(key, image);
		mCache.clear();
		byte[] expected = null;
		byte[] actual = mCache.retreive(key);

		assertEquals(expected,actual);
	}

}
