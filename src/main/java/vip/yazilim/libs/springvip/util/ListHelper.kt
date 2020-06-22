package vip.yazilim.libs.springvip.util

/**
 * To get a not-null safe list
 *
 * @param list input list
 * @param <T> type of list items
 * @return not-null list
</T> */
fun <T> getSafeList(list: List<T?>?): List<T?> {
    return list ?: emptyList()
}
