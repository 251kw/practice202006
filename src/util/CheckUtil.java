package util;

import java.util.ArrayList;

import dao.DBManager;
import dto.UserDTO;

/**
 * チェック系の処理を集めたUtilクラス
 */
public class CheckUtil {

	/**
	 * 会員登録時、項目未入力時のエラーメッセージ
	 * @param info			//入力された項目(例：loginId)
	 * @param messageInfo	//入力された文字列(例："ログインID")
	 * @return	message		//生成したエラーメッセージ
	 */
	public static String loginCheck(String info, String messageInfo) {
		String message = null;

		if(info == "" || info == null) {
			message = messageInfo + "が未入力です";
		}else {
			message = "";
		}
		return message;
	}

	/**
	 * ログインIDとパスワードの入力制限チェック
	 * @param info			//入力された項目(例：loginId)
	 * @param messageInfo	//入力された文字列(例："ログインID")
	 * @return	message		//生成したエラーメッセージ
	 */
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

	/**
	 * エラーがひとつもないかを確認する
	 * @param errMsId		//ログインID未入力エラー
	 * @param errMsPass		//パスワード未入力エラー
	 * @param errMsUname	//氏名未入力エラー
	 * @param errMsIcon		//アイコン未入力エラー
	 * @param errMsProf		//プロフィール未入力エラー
	 * @param errDepMs		//ログインID重複エラー
	 * @param errId			//ログインID入力制限エラー
	 * @param errPass		//パスワード入力制限エラー
	 * @return boo			//エラーメッセージがすべて空文字かどうかを返すboolean型変数
	 */
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

	/**
	 * DBにすでに登録されているloginIdかどうか調べる
	 * @param loginId	//入力されたログインID
	 * @return message	//生成されたエラーメッセージ
	 */
	public static String dbCheck(String loginId) {
		//DB内にあるユーザ情報を取得する
		DBManager dbm = new DBManager();
		UserDTO user = dbm.getCheckUser(loginId);

		String message = null;

		if(loginId.matches("^[0-9a-zA-Z]+$") == false) {
			//入力制限に沿ってないものははじく
			message = "";
			}else if(user == null){
				//重複IDのチェックなので検索結果なし/入力欄からの場合ははじく
				message = "";
			}else if(user.getLoginId().equals(loginId)){
				//すでに登録されているIDの時はuserはnullではないのでエラーを返す。
				message = "そのログインIDはすでに使用されています";
			}else {
				message = "";
			}
			return message;
		}

	/**
	 * エラーがひとつもないかを確認する
	 * @param errId		//ログインID入力制限エラー
	 * @param errPass	//パスワード入力制限エラー
	 * @return boo		//エラーメッセージがすべて空文字かどうかを返すboolean型変数
	 */
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

	/**
	 * 現在ログインしているユーザがDB内にいるか検索する
	 * @param loginId	//現在ログイン中のログインID
	 * @return	user	//現在ログイン中のユーザ情報
	 */
	public static UserDTO nowLoginCheck(String loginId) {
		//DB内にあるユーザ情報を取得する
		DBManager dbm = new DBManager();
		UserDTO user = dbm.getCheckUser(loginId);

		if(user.getdFlg()==1) {
			//削除フラグが立っているユーザはnullにする
			user = null;
		}

		return user;
	}

	/**
	 * 該当ユーザのすべての情報を引き出す
	 * @param loginId	//対象のユーザID
	 * @return list		//対象ユーザ情報をいれたリスト
	 */
	public static ArrayList<UserDTO> checkedUser(String loginId) {
		//DB内にあるユーザ情報を取得する
		DBManager dbm = new DBManager();
		UserDTO user = dbm.getCheckUser(loginId);

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		list.add(user);

		return list;
	}

	/**チェックボックス未入力時のエラー文
	 * @return message //エラー文
	 */
	public static String errCheckbox() {
		String message = null;
			message = "該当ユーザをチェックしてください";
		return message;
	}

	//新規登録
	public static int okDFlg() {
		int okDFlg = 0;
		return okDFlg;
	}

	//ユーザ論理削除
	public static int delDFlg() {
		int delDFlg = 1;
		return delDFlg;
	}

}
