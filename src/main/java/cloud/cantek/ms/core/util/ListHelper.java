package cloud.cantek.ms.core.util;

import java.util.Collections;
import java.util.List;

public class ListHelper {

	/**
	 * To get a not-null safe list
	 * 
	 * @param list input list
	 * @return not-null list
	 */
	public static <T> List<T> getSafeList(List<T> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}

}
