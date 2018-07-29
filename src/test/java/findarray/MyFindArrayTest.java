package findarray;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MyFindArrayTest {

	private FindArray findArray;
	
	private int[] array;
	
	private int[] subArray;
	
	private int lastIndex;

	public MyFindArrayTest(int[] array, int[] subArray, int lastIndex) {
		super();
		this.array = array;
		this.subArray = subArray;
		this.lastIndex = lastIndex;
	}

	@Parameters
	public static Collection<Object[]> paramData() {
		return Arrays.asList(new Object[][] {
			{new int[] {4, 9, 3, 7, 8}, new int[] {3, 7}, 2},
			{new int[] {1, 2, 7, 4, 7, 0, 7, 4, 7, 9, 7, 4, 7}, new int[] {7, 4, 7}, 10},
			{new int[] {4, 9, 3, 7, 8}, new int[] {1, 5, 3, 9, 0}, -1},
			{new int[] {}, new int[] {1, 4, 6}, -1},
			{new int[] {1, 4, 6}, new int[] {}, -1}, 
			{new int[] {1, 4, 6}, null, -1},
			{null, new int[] {1, 4, 6}, -1},
			{new int[] {1, 4, 3}, new int[] {0, 5, 1, 4, 3, 7}, -1}
		});
	}
	
	@Before
	public void testSetup() throws Exception {
		findArray = new MyFindArray();
	}

	@Test
	public void findArrayFromTaskDescription() throws Exception {
		int result = findArray.findArray(array, subArray);
		assertEquals(lastIndex, result);
	}
}