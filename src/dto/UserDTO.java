package dto;

// ユーザー情報を保持するクラス
public class UserDTO {
	private String loginId;	// ログインID
	private String password;	// パスワード
	private String userName;	// ユーザー名
	private String icon;	// ユーザーアイコン
	private String profile;	// プロフィール
	private String d_flg;	// deleteflag

	// getter and setter
	public UserDTO() {

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

	public String getD_flg() {
		return d_flg;
	}

	public void setD_flg(String d_flg) {
		this.d_flg = d_flg;
	}



	// ユーザー情報１人分の情報を保持するクラス
	public UserDTO(String loginId, String password, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}
}
