package dto;

/**
 * 掲示板書き込み
 * ユーザー情報と書き込み情報保持bean
 *
 */

public class ShoutDTO {
	private String userName; //ユーザ名
	private String icon; //ユーザアイコン
	private String date; //書き込み日時
	private String writing; //書き込み内容
	private int d_flg;		//削除フラグ

	public ShoutDTO() {
		//
	}

	/**
	 * private変数に代入
	 *
	 * @param userName ユーザー名
	 * @param icon アイコン
	 * @param date 日付
	 * @param writing 書き込み内容
	 * @param d_flg 削除フラグ
	 */
	public ShoutDTO(String userName, String icon, String date, String writing, int d_flg) {
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
		this.d_flg = d_flg;
	}

	/**
	 * 以下getterとsetter
	 *
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public int getD_flg() {
		return d_flg;
	}

	public void setD_flg(int d_flg) {
		this.d_flg = d_flg;
	}

}