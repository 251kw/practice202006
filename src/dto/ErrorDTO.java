package dto;

//エラー情報を保持するクラス
public class ErrorDTO {
	public String errMsId;
	public String errMsPass;
	public String errMsUname;
	public String errMsIcon;
	public String errMsProf;
	public String errDepMs;
	public String errId;
	public String errPass;

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

	//ゲッター
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
