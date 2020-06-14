package vip.yazilim.libs.springcore.util;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class ListHelper {

    /**
     * To get a not-null safe list
     * 
     * @param list input list
     * @param <T> type of list items
     * @return not-null list
     */
    public static <T> List<T> getSafeList(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

}
