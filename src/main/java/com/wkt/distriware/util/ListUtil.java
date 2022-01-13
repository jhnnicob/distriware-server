package com.wkt.distriware.util;

import java.util.Collection;

public class ListUtil {
	
	public static boolean isNullOrEmpty(Collection collection) {
		return (collection == null) || (collection.size() <= 0);
	}
	
}