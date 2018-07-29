package warmup;

import java.util.stream.IntStream;

public class MyWarmup implements Warmup {

	@Override
	public int findMax(int[] array) {
		int maxIndex = -1;
		if (null != array && array.length > 0) {
			maxIndex = IntStream.range(0, array.length)
								.reduce((i,j) -> array[i] >= array [j] ? i : j)
								.getAsInt();
		}
		return maxIndex;
	}
}
