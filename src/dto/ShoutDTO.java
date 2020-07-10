package dto;

/**
 * Shoutユーザ情報を保持するクラス
 */
public class ShoutDTO {
	private String loginId;		//ログインID
	private String userName;	//ユーザ名
	private String icon;	//ユーザアイコン
	private String date;	//書き込み日時
	private String writing;	//書き込み内容

	public ShoutDTO() {

	}

	public ShoutDTO(String loginId, String userName, String icon, String date, String writing) {
		this.loginId = loginId;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}

	//ゲッターセッターの生成

	public String getUserName() {
		return userName;
	}

	public String getIcon() {
		return icon;
	}

	public String getDate() {
		return date;
	}

	public String getWriting() {
		return writing;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


}
