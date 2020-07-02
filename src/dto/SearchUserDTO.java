package dto;

public class SearchUserDTO {
	private String loginId;
	private String userName;;
	private String[] icon;
	private String profile;

	public SearchUserDTO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public SearchUserDTO(String loginId, String userName, String[] icon, String profile) {
		this.loginId = loginId;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getuserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getIcon() {
		return icon;
	}

	public void setIcon(String[] icon) {
		this.icon = icon;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
