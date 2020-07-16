package dto;

//ユーザ情報を保持するクラス
public class UserDTO {
	private String loginId;		//ログインID
	private String password;	//パスワード
	private String userName;	//氏名
	private String icon;		//アイコン
	private String profile;		//プロフィール
	private int dFlg;

	public UserDTO() {
	}

	public UserDTO(String loginId, String password, String userName, String icon, String profile, int dFlg) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
		this.dFlg = dFlg;
	}

	//ゲッターセッター
	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getProfile() {
		return profile;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getDFlg() {
		return dFlg;
	}

	public void setDFlg(int dFlg) {
		this.dFlg = dFlg;
	}




}
