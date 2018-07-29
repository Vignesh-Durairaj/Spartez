package warmup;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MyWarmupTest {

	private Warmup warmup;
	
	private int[] inputArray;
	
	private int maxIndex;

	public MyWarmupTest(int[] inputArray, int maxIndex) {
		super();
		this.inputArray = inputArray;
		this.maxIndex = maxIndex;
	}
	
	@Parameters
	public static Collection<Object[]> paramData() {
		return Arrays.asList(new Object[][] {
			{null, -1},
			{new int[] {}, -1},
			{new int[] {1, 2, 3, 0, -1}, 2},
			{new int[] {1, 2, 2, 1}, 1}, 
			{new int[] {1, 2, 3, 2, 3, 2, 1, 3}, 2}
		});
	}

	@Before
	public void testSetup() throws Exception {
		warmup = new MyWarmup();
	}

	@Test
	public void findMaxFromTaskDescription() throws Exception {
		// Passing the integer array as parameter
		int result = warmup.findMax(inputArray);
		assertEquals(maxIndex, result);
	}
}