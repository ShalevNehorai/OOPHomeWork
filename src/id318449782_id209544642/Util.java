package id318449782_id209544642;

import java.util.Arrays;

public class Util {

	public static int getLastEmptyInArray(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public static Object[] doubleArrLength(Object[] arr) {
		return Arrays.copyOf(arr, arr.length * 2);
	}

	public static Object[] addCellInArray(Object[] arr, Object obj, int spot) {
		int lastLocation = getLastEmptyInArray(arr);
		if (lastLocation < 0) {
			lastLocation = arr.length;
			arr = doubleArrLength(arr);
		}
		if (spot >= lastLocation || spot < 0) {
			arr[lastLocation] = obj;
		} else {
			for (int i = lastLocation; i >= spot + 1; i--) {
				arr[i] = arr[i - 1];
			}
			arr[spot] = obj;
		}

		return arr;
	}

	public static Object[] addToLast(Object[] arr, Object obj) {
		return addCellInArray(arr, obj, arr.length);
	}

	public static int indexOf(Object[] arr, Object obj) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == null) {
				return -1;
			}
			if (arr[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}
}
