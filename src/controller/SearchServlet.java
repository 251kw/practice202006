package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.ErrorDTO;
import dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/searchSrv")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
	@SuppressWarnings("static-access")

	//search.jspの「検索」ボタンから呼び出される。ユーザ検索のサーブレット
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

		//ログインID、パスワードの入力制限チェックの変数
		RequestDispatcher dispatcher = null;
		String errId = null;	//ログインIDの入力制限
		String errPass = null;	//パスワードの入力制限
		//下記、errorDTOが使いたいので変数だけ定義する。処理は何もしない。
		String errMsId = null;
		String errMsPass = null;
		String errMsUname = null;
		String errMsIcon = null;
		String errMsProf = null;
		String errDepMs = null;

		//ifに入ったらbooがfalseになる
		boolean boo = true;

/* ログインID、パスワードの入力制限のエラーチェック(あとで)(それかcssで入力制限つける)------------------------------------

		errId = CheckUtil.charCheck(loginId,"ログインID");
errPass = CheckUtil.charCheck(password,"パスワード");

*/

//エラーがなければユーザ検索してsearchComp.jspへ、あれば入力情報を保持したまま再度search.jsp画面へ遷移---------------------

		//エラーがなければtrue、ひとつでもエラーがあればfalseで返ってくる(あとで)
		//boo = CheckUtil.inputCheck(errId,errPass);

		if(boo == true) {

			//ユーザ情報の検索
			DBManager dbm = new DBManager();
			UserDTO user = dbm.getSearchUser(loginId, password, userName, errMsIcon, profile);

			//ArrayList使ってみる


			request.setAttribute("user", user);

			//reqestスコープにひとつずつセット
		/*	いったんコメントアウト
		 * request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);
		*/
			//insertConf.jspに処理を転送
			dispatcher = request.getRequestDispatcher("searchComp.jsp");
			dispatcher.forward(request, response);

		}else{
			//errorDTOをreqestへセットしてinsertに渡す
			ErrorDTO errorDTO = new ErrorDTO(errMsId,errMsPass,errMsUname,errMsIcon,errMsProf,errDepMs,errId,errPass);
			request.setAttribute("errorDTO", errorDTO);

			//reqestスコープにひとつずつセット
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);

			//insert.jspに処理を転送
			dispatcher = request.getRequestDispatcher("search.jsp");
			dispatcher.forward(request, response);
		}
	}
}