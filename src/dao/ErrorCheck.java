package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorCheck {
	public boolean halfCheck(String str) {
		boolean result = false;
		String eng = "^[A-za-z0-9]+$";


		Pattern p = Pattern.compile(eng);
		Matcher m = p.matcher(str);
		result = m.matches();
		return result;

	}

}
