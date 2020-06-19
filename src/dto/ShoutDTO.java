package dto;

public class ShoutDTO {
	private String userName;
	private String icon;
	private String data;
	private String writing;

	public ShoutDTO() {

	}

	public ShoutDTO(String userName, String icon, String data,String writing) {
		this.userName = userName;
		this.icon = icon;
		this.data = data;
		this.writing = writing;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}


}