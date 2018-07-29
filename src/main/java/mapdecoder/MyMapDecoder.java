package mapdecoder;

import java.util.HashMap;
import java.util.Map;

public class MyMapDecoder implements MapDecoder {

	@Override
	public Map<String, String> decode(String s) {
		
		Map<String, String> decodeMap = null;
		if (s != null) {
			decodeMap = new HashMap<>(0);
			if (!s.isEmpty()) {
				String[] keyValueArr = s.split("&");
				
				for (String keyValuePair : keyValueArr) {
					if (!keyValuePair.contains("=")) {
						throw new IllegalArgumentException("Key Value pair is of invalid format");
					}
					
					String[] mapString = keyValuePair.split("=");
					decodeMap.put(mapString[0], mapString.length > 1 ? mapString[1] : "");
				}
			}
		}
		
		return decodeMap;
	}
}
