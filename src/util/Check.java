package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 入力チェッククラス
 *
 * checkLogic
 * checkIcons
 *
 * @author y.sato
 *
 */

public class Check {
	/**
	 * 引数の文字列が半角英数字のみであるか
	 * 判定するメソッド
	 * 半角英数字以外があれば偽を返す
	 *
	 * @param target 入力された文字列
	 * @return result 真偽値
	 *
	 */
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

	/**
	 * check項目と選択した項目を比較して
	 * 選択状態を返すメソッド
	 *
	 * @param item htmlで設定してある項目の種類
	 * @param select 選択した項目が格納された配列
	 * @return 一致で"checked" 不一致で""
	 *
	 */
	public static String checkBox(String item, String[] select) {

		if(select==null) {				//初回
			return "";
		}

		for(String i: select) {			//配列に入っていたらチェック
			if(i.equals(item)) {
				return "checked";
			}
		}

		return "";						//入っていなかったので何も書かない
	}

}
