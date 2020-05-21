package com.cenmw.util;

public class ProductUtil {
	public static String createclasshref(int onerid, int tworid, int threerid,
			int fourrid, int fiverid, int sixrid, int sevenrid, int index,
			int id) {
		String href = "";
		if (onerid > 0) {
			href += onerid + "-";
		} else {
			href += 0 + "-";
		}
		if (tworid > 0) {
			href += tworid + "-";
		} else {
			href += 0 + "-";
		}
		if (threerid > 0) {
			href += threerid + "-";
		} else {
			href += 0 + "-";
		}
		if (fourrid > 0) {
			href += fourrid + "-";
		} else {
			href += 0 + "-";
		}
		if (fiverid > 0) {
			href += fiverid + "-";
		} else {
			href += 0 + "-";
		}
		if (sixrid > 0) {
			href += sixrid + "-";
		} else {
			href += 0 + "-";
		}
		if (sevenrid > 0) {
			href += sevenrid;
		} else {
			href += 0;
		}
		String h = "";
		String[] hrefarr = href.split("-");
		for (int i = 0; i < hrefarr.length; i++) {
			if (i == index) {
				hrefarr[i] = "" + id;
			}
			h += "-" + hrefarr[i];
		}
		h = h.substring(1);
		return h;
	}

	public static boolean checkclickcss(int onerid, int tworid, int threerid,
			int fourrid, int fiverid, int sixrid, int sevenrid, int index,
			int id) {
		if (index == 0) {
			if (onerid > 0 && onerid == id) {
				return true;
			}
			if (onerid == 0 && id == 0) {
				return true;
			}
		}
		if (index == 1) {
			if (tworid > 0 && tworid == id) {
				return true;
			}
			if (tworid == 0 && id == 0) {
				return true;
			}
		}
		if (index == 2) {
			if (threerid > 0 && threerid == id) {
				return true;
			}
			if (threerid == 0 && id == 0) {
				return true;
			}
		}
		if (index == 3) {
			if (fourrid > 0 && fourrid == id) {
				return true;
			}
			if (fourrid == 0 && id == 0) {
				return true;
			}
		}

		if (index == 4) {
			if (fiverid > 0 && fiverid == id) {
				return true;
			}
			if (fiverid == 0 && id == 0) {
				return true;
			}
		}
		if (index == 5) {
			if (sixrid > 0 && sixrid == id) {
				return true;
			}
			if (sixrid == 0 && id == 0) {
				return true;
			}
		}
		if (index == 6) {
			if (sevenrid > 0 && sevenrid == id) {
				return true;
			}
			if (sevenrid == 0 && id == 0) {
				return true;
			}
		}
		return false;
	}
}
