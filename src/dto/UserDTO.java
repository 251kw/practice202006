package dto;

/**
 * ユーザ情報を保持するクラス(bean)
 *
 * @author y.sato
 *
 */

public class UserDTO {
	private String loginId="";	//ログインid
	private String password="";	//パスワード
	private String userName="";	//ユーザ名
	private String icon="";		//ユーザアイコン
	private String profile="";	//プロフィール

	public UserDTO() {
		//
	}

	/**
	 * private変数に代入
	 *
	 * @param loginId ログインId
	 * @param password パスワード
	 * @param userName ユーザー名
	 * @param icon アイコン
	 * @param profile プロフィール
	 *
	 */
	public UserDTO(String loginId, String password, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}

	/**
	 * 以下getterとsetter
	 *
	 */
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

}
