package com.cenmw.util;

import java.util.Arrays;

class Pair {
	public int max = 0;
	public int min = 0;

}

public class NumberUtil {
	private static void max_min(int a[], int s, int e, Pair pair) {
		Pair lPair = new Pair();
		Pair rPair = new Pair();
		if (e == s + 1 || e == s) {
			if (a[s] >= a[e]) {
				pair.max = a[s];
				pair.min = a[e];
			} else {
				pair.max = a[e];
				pair.min = a[s];
			}
			return;
		}
		int mid = (s + e) / 2;
		max_min(a, mid + 1, e, lPair);
		max_min(a, s, mid, rPair);
		pair.max = lPair.max > rPair.max ? lPair.max : rPair.max;
		pair.min = lPair.min < rPair.min ? lPair.min : rPair.min;
	}
	public  static  int[] getMinAndMaxNumber(int array[]){
		int arg[]=new int[2];
		Pair pair = new Pair();
		max_min(array, 0, array.length - 1, pair);
		arg[0]=pair.min;
		arg[1]=pair.max;
		return arg;
	}
}
