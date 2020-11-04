package bean;

import java.io.Serializable;

// JavaBeans の要件に沿って定義したクラス
public class User implements Serializable{
	// フィールドは private 修飾子を付ける（JavaBeansの仕様）
	private String loginId;
	private String password;
	private String userName;

	// 引数なしのコンストラクタの定義（JavaBeansの仕様）
	public User() {

	}

	// 引数ありのコンストラクタの定義
	public User(String loginId, String password, String userName) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
	}

	// アクセサの定義（JavaBeansの仕様）
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

	// IDとパスワードを比較し、ユーザ名を表示するメソッドの定義
	public void authentication(String id, String pass){
		if(loginId.equals(id) && password.equals(pass)){
			System.out.println("Welcome " + userName);

		}else{
			System.out.println("Authentication failure ...");
		}
	}
}
