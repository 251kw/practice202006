package dto;

public class UserDTO {
	private String loginId;
	private String password;
	private String userName;
	private String icon;
	private String profile;
	private int d_flg;

	public UserDTO() {

	}

	public UserDTO(String loginId, String password, String userName, String icon, String profile,int d_flg) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
		this.d_flg = d_flg;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getD_flg() {
		return d_flg;
	}

	public void setD_flg(int d_flg) {
		this.d_flg = d_flg;
	}

}
