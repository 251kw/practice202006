package dto;

/**
 * エラー情報を保持するクラス
 */
public class ErrorDTO {
	public String errMsId;		//ログインID未入力エラー
	public String errMsPass;	//パスワード未入力エラー
	public String errMsUname;	//氏名未入力エラー
	public String errMsIcon;	//アイコン未入力エラー
	public String errMsProf;	//プロフィール未入力エラー
	public String errDepMs;		//ログインID重複エラー
	public String errId;		//ログインID入力制限エラー
	public String errPass;		//パスワード入力制限エラー

	public ErrorDTO() {
	}

	public ErrorDTO(String errMsId, String errMsPass, String errMsUname, String errMsIcon, String errMsProf, String errDepMs, String errId, String errPass) {
		this.errMsId = errMsId;
		this.errMsPass = errMsPass;
		this.errMsUname = errMsUname;
		this.errMsIcon = errMsIcon;
		this.errMsProf = errMsProf;
		this.errDepMs = errDepMs;
		this.errId = errId;
		this.errPass = errPass;

	}

	//セッターとゲッター
		public String getErrMsId() {
			return errMsId;
		}

		public String getErrMsPass() {
			return errMsPass;
		}

		public String getErrMsUname() {
			return errMsUname;
		}

		public String getErrMsIcon() {
			return errMsIcon;
		}

		public String getErrMsProf() {
			return errMsProf;
		}

		public String getErrDepMs() {
			return errDepMs;
		}

		public String getErrId() {
			return errId;
		}

		public String getErrPass() {
			return errPass;
		}

		public String setErrPass() {
			return errPass;
		}


}
