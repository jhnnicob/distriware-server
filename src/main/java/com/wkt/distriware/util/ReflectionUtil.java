package com.wkt.distriware.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {


	public static List<Field> getAllFields(Class<?> clazz) {
	    return getAllFieldsRec(clazz, new ArrayList<Field>());
	}

	private static List<Field> getAllFieldsRec(Class<?> clazz, List<Field> list) {
	    Class<?> superClazz = clazz.getSuperclass();
	    if(superClazz != null){
	        getAllFieldsRec(superClazz, list);
	    }
	    list.addAll(Arrays.asList(clazz.getDeclaredFields()));
	    return list;
	}
	
}
