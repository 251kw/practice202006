package dto;

/**
 * 検索時に使用するbeanのクラス
 * データ保持
 *
 * SeachUserDTO
 *
 * @author y.sato
 *
 */

public class SearchUserDTO {
	private String loginId;
	private String userName;
	private String[] icon; //アイコンは配列で
	private String profile;

	public SearchUserDTO() {

	}

	/**
	 * private変数に代入
	 *
	 * @param loginId ログインId
	 * @param userName ユーザー名
	 * @param icon[] アイコン（複数）
	 * @param profile プロフィール
	 *
	 */
	public SearchUserDTO(String loginId, String userName, String[] icon, String profile) {
		this.loginId = loginId;
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
