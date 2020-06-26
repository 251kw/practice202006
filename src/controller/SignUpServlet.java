package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Check;
import dao.DBManager;
import dto.UserDTO;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		String botton = request.getParameter("btn");
		String message = null;

		if (botton.equals("確認画面へ")) {
			//送信情報の取得
			String loginId = request.getParameter("loginId");
			String pass = request.getParameter("pass");
			String userName = request.getParameter("userName");
			String icon = request.getParameter("icon");
			String profile = request.getParameter("profile");

			UserDTO newuser = new UserDTO(loginId, pass, userName, icon, profile);
			HttpSession session = request.getSession();
			session.setAttribute("newuser", newuser);

			if (loginId.equals("") || pass.equals("")) {
				//ログインIDとパスワードどちらか、もしくは双方未入力なら
				message = "ログインIDとパスワードは必須入力です";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//index.jspに処理を転送
				dispatcher = request.getRequestDispatcher("signup.jsp");
				dispatcher.forward(request, response);
			}

			Check check = new Check();
			boolean rsult = check.checkLogic(loginId);

			if(rsult==false) {
				message = "ログインIDに使用できるのは半角英数字のみです。";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//index.jspに処理を転送
				dispatcher = request.getRequestDispatcher("signup.jsp");
				dispatcher.forward(request, response);
			}


			if (dbm.SameCheckId(loginId)) {

				dispatcher = request.getRequestDispatcher("signup_check.jsp");
			} else {
				message = "このログインIDは既に使われています。";
				request.setAttribute("alert", message);
				message = "";

				dispatcher = request.getRequestDispatcher("signup.jsp");
			}

		} else if (botton.equals("登録する")) {
			HttpSession session = request.getSession();
			UserDTO newuser = (UserDTO) session.getAttribute("newuser");

			dbm.SignUp(newuser);
			dispatcher = request.getRequestDispatcher("signup_comp.jsp");
		}

		dispatcher.forward(request, response);

	}

}
