package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DataManager;
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインからの呼び出し
		//送信情報の取得
		String loginId = request.getParameter("loginID");
		String password = request.getParameter("password");

		RequestDispatcher dispatcher = null;
		String message = null;

		if(loginId.equals("") || password.equals("")) {
			//ＩＤとパスどちらも未入力なら
			message = "ログインIDとパスワードは入力必須です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			//index.jspに処理を転送
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}else {
			//ログイン認証を行い、ユーザー情報を取得
			DataManager dbm = new DataManager();
			UserDTO user = dbm.getloginUser(loginId, password);

			if(user != null) {
				//ユーザー情報を取得できたら、書き込み内容リストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();
				HttpSession session = request.getSession();

				//ログインユーザー情報、書き込み内容リストを取得
				session.setAttribute("user", user);
				session.setAttribute("shouts", list);

				//処理の転送先をtop.jspに指定
				dispatcher = request.getRequestDispatcher("top.jsp");
			}else {
				//ユーザー情報を取得できない場合
				//エラーメッセージをリクエストオブジェクトに保存
				message = "ログインIDまたはパスワードが違います";
				request.setAttribute("alert", message);

				//処理の転送先をindex.jspに指定
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			//処理を転送
			dispatcher.forward(request, response);
		}
	}

}
