package util;

import java.util.regex.Pattern;

public class CheckAddUserInfo {

	/*
	 * 登録情報にエラーがないか調べるメソッド
	 * エラーがなければnullを返す
	 * エラーがあれば内容に応じたString型のメッセージを返す
	 * エラー文は
	 *  未記入 > IDの誤り = PASSの誤り > passの不一致
	 * の順番で優先される(IDとPASSは両方が間違っている場合はs同時に警告する)
	 */

	public static String checkinfo(String loginID, String password1, String password2, String userName) {
		String massage = null;
		String log ="";
		String pass ="";
		String passequ = "";

		//空文字を検討すると入賞する
		if (loginID.equals("") || password1.equals("") || password2.equals("") || userName.equals("")) {
			massage = "記入していない項目があります";
			return massage;
		}

		if (loginID.length() < 4) {
			if (Pattern.matches("^[0-9a-zA-Z]+$", loginID)) {
				log = "ログインID";
			}
		}
		if (password1.length() < 4) {
			if (Pattern.matches("^[0-9a-zA-Z]+$", password1)) {
				pass = "パスワード";
			}
		}

		if (!password1.equals(password2)) {
			passequ = "確認パスワードが一致しませんでした";
		}

		if(!log.equals("") || !pass.equals("")) {
			massage = (log + pass + "が不正です");
			if(!log.equals("") && !pass.equals("")) {
				massage = (log +"と"+ pass + "が不正です");
			}
		}else if(!passequ.equals("")){
			massage = passequ;
		}



		return massage;
	}
}
