package dto;

//cookie情報を保持するクラス
//TODO 使ってないので削除予定
@Deprecated
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

	public String getLogId() {
		return logId;
	}

	public String getPass() {
		return pass;
	}

	public String getuName() {
		return uName;
	}

	public String getIcon() {
		return Icon;
	}

	public String getProf() {
		return prof;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

}
