package bean;

public class SearchUserBean {

	private String loginId;
	private String userName;
	private String profile;
	private String car;
	private String clip;
	private String radio;

	public SearchUserBean(String loginId, String userName, String profile, String car, String clip, String radio) {
		this.loginId = loginId;
		this.userName = userName;
		this.profile = profile;
		this.car = car;
		this.clip = clip;
		this.radio = radio;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getClip() {
		return clip;
	}
	public void setClip(String clip) {
		this.clip = clip;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}


}
