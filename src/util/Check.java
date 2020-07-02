package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import dto.UserDTO;

public class Check {
	public static boolean checkLogic(String target) {
	    boolean result = false;
	    String eisu = "^[A-Za-z0-9]+$";

	    Pattern p = Pattern.compile(eisu); // 正規表現パターンの読み込み
	    Matcher m = p.matcher(target); // パターンと文字列を比較
	    result = m.matches(); // 結果をtrueかfalseで取得

	    if(target==null || target=="") {
	    	result=true;
	    }

	    return result;
	  }

	public static String checkIcons(String icontype, String[] icons) {

		if(icons==null) {
			return "";
		}

		for(String i: icons) {
			if(i.equals(icontype)) {
				return "checked";
			}
		}
		return "";
	}

}
