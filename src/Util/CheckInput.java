package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput{

	// 半角英数チェック
	public static boolean checkLogic(String regex, String target) {
		    boolean result = true;
		    if( target == null || target.isEmpty() ) return false ;
		    // 3. 引数に指定した正規表現regexがtargetにマッチするか確認する
		    Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
		    Matcher m1 = p1.matcher(target); // パターンと検査対象文字列の照合
		    result = m1.matches(); // 照合結果をtrueかfalseで取得
		    return result;
		  }

	// 全入力チェック
	public static boolean excludeBlank(String id, String pass, String name, String icon, String profile){
		if(id.equals("") || pass.equals("") || name.equals("") || icon.equals("") || profile.equals("")){
		    boolean result = false;
		    return result;
		}else {
		    boolean result = true;
		    return result;
		}
	}

	// パスワード以外入力チェック
	public static boolean excludeBlank2(String id, String name, String icon, String profile){
		if(id.equals("") && name.equals("") && icon.equals("") && profile.equals("")){
		    boolean result = false;
		    return result;
		}else {
		    boolean result = true;
		    return result;
		}
	}
}


