package dto;

public class ShoutDTO {
	private String shoutsId;
	private String userName;
	private String icon;
	private String date;
	private String writing;
	private String loginId;

	public ShoutDTO() {

	}

	public ShoutDTO(String shoutsId, String userName, String icon, String data,String writing,String loginId) {
		this.shoutsId = shoutsId;
		this.userName = userName;
		this.icon = icon;
		this.date = data;
		this.writing = writing;
		this.loginId = loginId;
	}


	public String getShoutsId() {
		return shoutsId;
	}

	public void setShoutsId(String shoutsId) {
		this.shoutsId = shoutsId;
	}

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

	public void setDate(String data) {
		this.date = data;
	}

	public String getWriting() {
		return writing;
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