package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput{

	/**
	 * 半角英数字かどうかをチェック
	 * @param regex 正規表現パターン
	 * @param target チェックしたい文字列
	 * @return
	 */
	// 半角英数チェック
	public static boolean checkLogic(String regex, String target) {
		    boolean result = true;
		    if( target == null || target.isEmpty()) {
		    	return false ;
		    }
		    // 3. 引数に指定した正規表現regexがtargetにマッチするか確認する
		    Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
		    Matcher m1 = p1.matcher(target); // パターンと検査対象文字列の照合
		    result = m1.matches(); // 照合結果をtrueかfalseで取得
		    return result;
		  }


	/**
	 * 入力欄が全て埋まっているかどうかをチェック
	 * @param id
	 * @param pass
	 * @param name
	 * @param icon
	 * @param profile
	 * @return 一つでも未入力の項目があればfalse
	 */
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

	/**
	 * パスワード以外が入力されているかどうかをチェック
	 * @param id
	 * @param name
	 * @param icon
	 * @param profile
	 * @return 上と同じ
	 */
	// パスワード以外入力チェック
	public static boolean excludeBlank2(String id, String name, String icon, String profile){	//TODO 現在未使用のメソッド
		if(id.equals("") && name.equals("") && icon.equals("") && profile.equals("")){
		    boolean result = false;
		    return result;
		}else {
		    boolean result = true;
		    return result;
		}
	}

	/**
	 * ログインID以外が入力されているかどうかをチェック
	 * @param pass
	 * @param name
	 * @param icon
	 * @param profile
	 * @return 上と同じ
	 */
	// パスワード以外入力チェック
	public static boolean excludeBlank3(String pass, String name, String icon, String profile){
		if(pass.equals("") || name.equals("") || icon.equals("") || profile.equals("")){
		    boolean result = false;
		    return result;
		}else {
		    boolean result = true;
		    return result;
		}
	}
}


