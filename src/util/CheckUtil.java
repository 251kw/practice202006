package util;

import dao.DBManager;
import dto.UserDTO;

/*
 * 繰り返し処理を集めたUtilクラス
 */
public class CheckUtil {

	//会員登録時、項目未入力時のエラーメッセージ
	public static String loginCheck(String info, String messageInfo) {
		String message = null;

		if(info == "" || info == null) {
			message = messageInfo + "が未入力です";
		}else {
			message = "";
		}
		return message;
	}

	//ログインIDとパスワードの入力制限チェック
	public static String charCheck(String info, String messageInfo) {
		//【^[0-9a-zA-Z]+$】なら、数字、英字の小字、大文字どれを入れてもよい
		String message = null;

		if(info.matches("^[0-9a-zA-Z]+$") == false) {
			message = messageInfo + "は英数字15文字以内で入力してください";
		}else {
			message = "";
		}
		return message;
	}

	//ひとつでもエラーメッセージがあった場合、falseを返す
	public static boolean inCheck(String errMsId, String errMsPass, String errMsUname, String errMsIcon, String errMsProf, String errDepMs, String errId, String errPass) {
		//エラーメッセージがすべて空文字ならtrue
		boolean boo;
		if(errMsId == "" && errMsPass == "" && errMsUname == "" && errMsIcon == "" && errMsProf == "" && errDepMs == "" && errId == "" && errPass == "") {
			boo = true;
		}else {
			boo = false;
		}
		return boo;
	}

	//DBにすでに登録されているloginIdかどうか調べる
	public static String dbCheck(String loginId) {
		//DB内にあるユーザ情報を取得する
		DBManager dbm = new DBManager();
		UserDTO user = dbm.getCheckUser(loginId);

		String message = null;

		//すでに登録されているIDの時はuserはnullではないのでエラーを返す。
		if(user != null && user.getLoginId() != null) {
			message = "そのログインIDはすでに使用されています";
		}else {
			message = "";
		}
		return message;
	}

	//ひとつでもエラーメッセージがあった場合、falseを返す
	public static boolean inputCheck(String errId, String errPass) {
		//エラーメッセージがすべて空文字ならtrue
		boolean boo;
		if(errId == "" && errPass == "") {
			boo = true;
		}else {
			boo = false;
		}
		return boo;
	}

}


