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
	    return result;
	  }

//	public static String iconCheck(String icontype) {
//		String result="";
//		UserDTO dto;
//		String icon = dto.getIcon();
//
//		if(icon.equals("")&&icontype.equals("icon-user")) {
//			result="selected";
//		} else if(icon.equals(icontype)) {		//デフォルト
//			result = "selected";
//		}
//
//		return result;
//
//	}

}
