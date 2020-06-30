package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;
import dto.errorDTO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/inCheck")
public class insertCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public insertCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    //insert.jspの「OK」ボタンから呼び出される。入力チェックのサーブレット
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//文字化け対策
		request.setCharacterEncoding("UTF-8");


		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//入力・重複チェックの変数
		RequestDispatcher dispatcher = null;
		String errMsId = null;
		String errMsPass = null;
		String errMsUname = null;
		String errMsIcon = null;
		String errMsProf = null;
		String errDepMs = null;
		String errId = null;	//ログインIDの入力制限
		String errPass = null;	//パスワードの入力制限

		//ifに入ったらbooがfalseになる
		boolean boo = true;

//Cookie登録-------------------------------------------------------
		//送信情報の取得は上で済んでる。
		//文字エンコード
		loginId = URLEncoder.encode(loginId, "UTF-8");
		password = URLEncoder.encode(password, "UTF-8");
		userName = URLEncoder.encode(userName, "UTF-8");
		icon = URLEncoder.encode(icon, "UTF-8");
		profile = URLEncoder.encode(profile, "UTF-8");

		//クッキーの取得
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;

		//クッキーの存在をチェック
		if(cookies != null) {
			for(Cookie data : cookies) {
				if(loginId.equals(data.getName())) {
					//クッキーの値を更新
					cookie.setValue(loginId);
				}else {
					//新規にクッキーを作成
					cookie = new Cookie("loginId", loginId);
				}
			}
			response.addCookie(cookie);
		}
		if(cookies != null) {
			for(Cookie data : cookies) {
				if("password".equals(data.getName())) {
					//クッキーの値を更新
					cookie.setValue(password);
				}else {
					//新規にクッキーを作成
					cookie = new Cookie("password", password);
				}
			}
			response.addCookie(cookie);
		}
		if(cookies != null) {
			for(Cookie data : cookies) {
				if("userName".equals(data.getName())) {
					//クッキーの値を更新
					cookie.setValue(userName);
				}else {
					//新規にクッキーを作成
					cookie = new Cookie("userName", userName);
				}
			}
		}
		if(cookies != null) {
			for(Cookie data : cookies) {
				if("icon".equals(data.getName())) {
					//クッキーの値を更新
					cookie.setValue(icon);
				}else {
					//新規にクッキーを作成
					cookie = new Cookie("icon", icon);
				}
			}
		}
		if(cookies != null) {
			for(Cookie data : cookies) {
				if("profile".equals(data.getName())) {
					//クッキーの値を更新
					cookie.setValue(profile);
				}else {
					//新規にクッキーを作成
					cookie = new Cookie("profile", profile);
				}
			}
			//cookieに追加
			response.addCookie(cookie);
		}

		//この後の処理に影響が出るのでデコードする
		loginId = URLDecoder.decode(loginId, "UTF-8");
		password = URLDecoder.decode(password, "UTF-8");
		userName = URLDecoder.decode(userName, "UTF-8");
		icon = URLDecoder.decode(icon, "UTF-8");
		profile = URLDecoder.decode(profile, "UTF-8");

//未入力のエラーチェック-------------------------------------------------------
		//loginID　未入力ならエラー文入れる
		if(loginId.equals("")) {
			boo = false;
			errMsId = "ログインIDが未入力です";
		}

		//password　未入力ならエラー文入れる
		if(password.equals("")) {
			boo = false;
			errMsPass = "パスワードが未入力です";
		}

		//userName　未入力ならエラー文入れる
		if (userName.equals("")) {
			boo = false;
			errMsUname = "名前が未入力です";
		}

		//icon　未選択ならエラー文入れる　初期値icon1チェックつけたからこいつは動かん
		if (icon.equals("")) {
			boo = false;
			errMsIcon = "アイコンが未選択です";
		}

		//profile　未入力ならエラー文入れる
		if (profile.equals("")) {
			boo = false;
			errMsProf = "プロフィールが未入力です";
		}

//ログインID入力制限のエラーチェック-------------------------------------------------------
		//【^[0-9a-zA-Z]+$】なら、数字、英字の小字、大文字どれを入れてもよい
		if(loginId.matches("^[0-9a-zA-Z]+$") == false) {
			//【^[0-9a-zA-Z]+$】なら、数字、英字の小字、大文字どれを入れてもよい
			boo = false;
			errId = "ログインIDは英数字15文字以内で入力してください";
		}

//パスワード入力制限のエラーチェック-------------------------------------------------------
		//【^[0-9a-zA-Z]+$】なら、数字、英字の小字、大文字どれを入れてもよい
		if(password.matches("^[0-9a-zA-Z]+$") == false) {
			boo = false;
			errPass = "パスワードは英数字15文字以内で入力してください";
		}

//ID重複のエラーチェック--------------------------------------------------------------
		//DB内にあるユーザ情報を取得する
		DBManager dbm = new DBManager();
		UserDTO user = dbm.getCheckUser(loginId);

		//初めてのユーザIDが入ってきたときはnullがはいってるのでよける
			if(user != null && user.getLoginId() != null) {
			boo = false;
			errDepMs = "そのログインIDはすでに使用されています";
		}

//エラーがなければinsertConf.jspへ、あればinsert.jspへ遷移---------------------

		if(boo == true) {
			//UserDTOをinsertConf.jspで使えるようにsessionセットする
			UserDTO userDTO = new UserDTO(loginId, password, userName, icon, profile);
			HttpSession session = request.getSession();
			session.setAttribute("userDTO", userDTO);

			//insertConf.jspに処理を転送
			dispatcher = request.getRequestDispatcher("insertConf.jsp");
			dispatcher.forward(request, response);

		}else{
			//errorDTOをreqestへセットしてinsertに渡す
			errorDTO errorDTO = new errorDTO(errMsId,errMsPass,errMsUname,errMsIcon,errMsProf,errDepMs,errId,errPass);
			request.setAttribute("errorDTO", errorDTO);

			//insert.jspに処理を転送
			dispatcher = request.getRequestDispatcher("insert.jsp");
			dispatcher.forward(request, response);
		}
	}
}