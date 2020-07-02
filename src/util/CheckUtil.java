package util;

import dao.DBManager;
import dto.UserDTO;

public class CheckUtil {

	public static String loginCheck(String info, String messageInfo) {
		//項目未入力時のエラーメッセージ
		String message = null;

		if(info == "" || info == null) {
			message = messageInfo + "が未入力です";
		}else {
			message = "";
		}
		return message;
	}


	public static String charCheck(String info, String messageInfo) {
		//ログインIDとパスワードの入力制限チェック
		//【^[0-9a-zA-Z]+$】なら、数字、英字の小字、大文字どれを入れてもよい
		String message = null;

		if(info.matches("^[0-9a-zA-Z]+$") == false) {
			message = messageInfo + "は英数字15文字以内で入力してください";
		}else {
			message = "";
		}
		return message;
	}

	public static boolean inCheck(String errMsId, String errMsPass, String errMsUname, String errMsIcon, String errMsProf, String errDepMs, String errId, String errPass) {
		//エラーメッセージがすべて空文字ならtrue
		//ひとつでも空文字でないならどこかでエラーが発生しているのでfalse

		boolean boo;
		if(errMsId == "" && errMsPass == "" && errMsUname == "" && errMsIcon == "" && errMsProf == "" && errDepMs == "" && errId == "" && errPass == "") {
			boo = true;
		}else {
			boo = false;
		}
		return boo;
	}

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

	public static boolean inputCheck(String errId, String errPass) {
		//エラーメッセージがすべて空文字ならtrue
		//ひとつでも空文字でないならどこかでエラーが発生しているのでfalse

		boolean boo;
		if(errId == "" && errPass == "") {
			boo = true;
		}else {
			boo = false;
		}
		return boo;
	}

}





