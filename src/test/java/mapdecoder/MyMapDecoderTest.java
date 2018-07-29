package mapdecoder;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class MyMapDecoderTest {

	private MyMapDecoder mapDecoder;

	@Before
	public void testSetup() throws Exception {
		mapDecoder = new MyMapDecoder();
	}

	@Test
	public void decodeMap() throws Exception {
		final String input = "one=1&two=2";
		Map<String, String> result = mapDecoder.decode(input);

		Map<String, String> expected = new HashMap<>();
		expected.put("one", "1");
		expected.put("two", "2");

		assertEquals(expected, result);
	}

	@Test
	public void decodeMapForEmptyKey() throws Exception {
		final String input = "=1&two=2";
		Map<String, String> result = mapDecoder.decode(input);

		Map<String, String> expected = new HashMap<>();
		expected.put("", "1");
		expected.put("two", "2");

		assertEquals(expected, result);
	}
	
	@Test
	public void decodeMapForEmptyValue() throws Exception {
		final String input = "one=1&two=";
		Map<String, String> result = mapDecoder.decode(input);

		Map<String, String> expected = new HashMap<>();
		expected.put("one", "1");
		expected.put("two", "");

		assertEquals(expected, result);
	}
	
	@Test
	public void decodeMapForEmptyString() throws Exception {
		final String input = "";
		Map<String, String> result = mapDecoder.decode(input);

		Map<String, String> expected = new HashMap<>();
		assertEquals(expected, result);
	}
	
	@Test
	public void decodeMapForNullInput() throws Exception {
		final String input = null;
		Map<String, String> result = mapDecoder.decode(input);

		assertEquals(null, result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void decodeMapForInvalidFormat() throws Exception {
		final String input = "key=value&another.key:another.value";
		Map<String, String> result = mapDecoder.decode(input);
		
		assertNotNull(result);
		fail("It should not be decoded as a map since the second key-value item is of invalid format");
	}

}