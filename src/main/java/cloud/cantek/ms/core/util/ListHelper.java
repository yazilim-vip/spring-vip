package cloud.cantek.ms.core.util;

import java.util.Collections;
import java.util.List;

public class ListHelper {

	public static <T> List<T> getSafeList(List<T> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}
	
}
