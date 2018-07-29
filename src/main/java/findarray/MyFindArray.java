package findarray;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyFindArray implements FindArray {

	@Override
	public int findArray(int[] array, int[] subArray) {
		int subArrayIdx = -1;
		
		if (null != array && null != subArray && array.length > 0 && 
				subArray.length > 0 && subArray.length < array.length) {
			
			Integer[] subArr = IntStream.of(subArray)
										.boxed()
										.toArray(Integer[]::new);
			
			for (int i = 0; i < (array.length - subArray.length + 1) ; i++) {
				
				Integer[] tempArray = IntStream	
											.of(Arrays.copyOfRange(array, i, subArray.length + i))
											.boxed()
											.toArray(Integer[]::new);
				
				if (Arrays.equals(tempArray, subArr)) {
					subArrayIdx = i;
				}
			}
		}
		
		return subArrayIdx;
	}
}
