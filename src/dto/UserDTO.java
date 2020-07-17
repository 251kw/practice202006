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
	private int d_flg;		//削除フラグ

	public UserDTO() {

	}

	/**
	 * private変数に代入
	 *
	 * @param loginId ログインId
	 * @param password パスワード
	 * @param userName ユーザー名
	 * @param icon アイコン
	 * @param profile プロフィール
	 * @param d_flg 削除フラグ
	 */
	public UserDTO(String loginId, String password, String userName, String icon, String profile, int d_flg) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
		this.d_flg = d_flg;
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

	public int getD_flg() {
		return d_flg;
	}

	public void setD_flg(int d_flg) {
		this.d_flg = d_flg;
	}

}
