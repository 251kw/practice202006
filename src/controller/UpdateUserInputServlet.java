package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;

/**
 * ユーザー検索結果のの更新ボタンからの呼び出され
 * "updateUserInput.jspに処理を転送する
 */
@WebServlet("/updateUser")
public class UpdateUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateUserInputServlet() {
        super();
    }

	/**
	 * searchResult.jspの更新ボタンからの呼び出し
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー
		request.setCharacterEncoding("UTF-8");

		//セッションがない場合はindexへ
		if(loginUser == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}else {
			String loginId = request.getParameter("loginId");
			String loginIds[] = request.getParameterValues("keep_cks");
			DBManager dbm = new DBManager();
			UserDTO updateUser = dbm.getUser(loginId);

			//必要な情報をリクエストスコープへリクエストスコープへ
			request.setAttribute("originalLoginId",updateUser.getLoginId());
			request.setAttribute("loginID",updateUser.getLoginId());
			request.setAttribute("password",updateUser.getPassword());
			request.setAttribute("icon",updateUser.getIcon());
			request.setAttribute("userName",updateUser.getUserName());
			request.setAttribute("profile",updateUser.getProfile());

			//戻る場合に備えてチェックボックスの保持情報をリクエストスコープへ
			request.setAttribute("checkUsers", loginIds);

			//処理の転送先を.jspに指定
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("updateUserInput.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * アクセスがあった場合はdogetに処理を移転
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
