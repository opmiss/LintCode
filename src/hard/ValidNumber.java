package hard;

public class ValidNumber {
	/**
     * @param s the string that represents a number
     * @return whether the string is a valid number
     */
	public boolean isNumber(String s) {
		s = s.trim();
		int e = s.indexOf("e");
		if (e > 0) {
			return isRegularNumber(s.substring(0, e))
					&& isPowerNumber(s.substring(e + 1));
		} else
			return isRegularNumber(s);
	}

	public boolean isRegularNumber(String s) {
		if (s.length() < 1)
			return false;
		String p1 = "[+-]?[0-9]+([.][0-9]*)?";
		String p2 = "[+-]?[0-9]*[.][0-9]+";
		return s.matches(p1) || s.matches(p2);
	}

	public boolean isPowerNumber(String s) {
		String p = "[+-]?[0-9]+";
		return s.matches(p);
	}

}
