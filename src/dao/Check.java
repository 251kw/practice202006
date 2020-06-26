package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
	public boolean checkLogic(String target) {
	    boolean result = false;
	    String eisu = "^[A-Za-z0-9]+$";

	    Pattern p = Pattern.compile(eisu); // 正規表現パターンの読み込み
	    Matcher m = p.matcher(target); // パターンと検査対象文字列の照合
	    result = m.matches(); // 結果をtrueかfalseで取得
	    return result;
	  }
}
