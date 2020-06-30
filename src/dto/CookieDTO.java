package dto;

//cookie情報を保持するクラス
public class CookieDTO {
	private String logId;
	private String pass;
	private String uName;
	private String Icon;
	private String prof;

	public CookieDTO() {

	}

	public CookieDTO(String logId, String pass, String uName, String Icon, String prof) {
		this.logId = logId;
		this.pass = pass;
		this.uName = uName;
		this.Icon = Icon;
		this.prof = prof;

	}

	//ゲッターセッター
	public String getLoginId() {
		return logId;
	}

	public String getPassword() {
		return pass;
	}

	public void setLoginId(String logId) {
		this.logId = logId;
	}

	public void setPassword(String pass) {
		this.pass = pass;
	}

	public String getUserName() {
		return uName;
	}

	public String getProf() {
		return prof;
	}

	public void setUserName(String uName) {
		this.uName = uName;
	}

	public void setProfile(String prof) {
		this.prof = prof;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}





}
