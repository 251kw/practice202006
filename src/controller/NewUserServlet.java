package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import checker.MakeNewUser;
import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/newuser")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		request.setAttribute("loginID","");
		request.setAttribute("icon","icon-car");
		request.setAttribute("userName","");
		request.setAttribute("profile","よろしくお願いします");
		// newUser.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("newUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginID = request.getParameter("loginID");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		RequestDispatcher dispatcher;

		UserDTO udto;
		udto = new UserDTO(loginID, password1, userName,  icon,  profile);
		//ログインユーザー情報、書き込み内容リストを取得
		HttpSession session = request.getSession();
		session.setAttribute("user", udto);

		String checker = MakeNewUser.checkinfo(loginID,password1,password2,userName);
		if (checker == null) {
			if(dbm == null){
				dbm = new DBManager();
			}

			//ログインＩＤの使用の可否を確かめる
			if(dbm.checkID(loginID)) {
				/*UserDTO udto;
				udto = new UserDTO(loginID, password1, userName,  icon,  profile);
				//ログインユーザー情報、書き込み内容リストを取得
				HttpSession session = request.getSession();
				session.setAttribute("user", udto);*/
				dispatcher = request.getRequestDispatcher("confirmation.jsp");
			}else {

				String message = null;
				message = "そのログインＩＤは既に使用されています";
				request.setAttribute("loginID",loginID);
				request.setAttribute("icon", icon);
				request.setAttribute("userName", userName);
				request.setAttribute("profile", profile);

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// top.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("newUser.jsp");
			}
		}else {
			String message = null;
			message = checker;
			request.setAttribute("loginID",loginID);
			request.setAttribute("icon", icon);
			request.setAttribute("userName", userName);
			request.setAttribute("profile", profile);

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// top.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("newUser.jsp");

		}

		dispatcher.forward(request, response);
	}

}
