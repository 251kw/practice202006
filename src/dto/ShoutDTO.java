package dto;

public class ShoutDTO {
	private String loginId;
	private String userName;
	private String icon;
	private String date;
	private String writing;

	public ShoutDTO() {

	}
	public ShoutDTO(String loginId, String userName, String icon, String date, String writing) {
		this.loginId = loginId;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}
	public String getloginId() {
		return loginId;
	}
	public void setloginId(String userId) {
		this.loginId = userId;
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
	public void setDate(String date) {
		this.date = date;
	}
	public String getWriting() {
		return writing;
	}
	public void setWriting(String writing) {
		this.writing = writing;
	}



}
