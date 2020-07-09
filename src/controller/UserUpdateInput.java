package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;

/**
 * 更新内容入力画面から送られてきた情報をチェックし確認画面に送る
 * update_input.jspから送られてくる
 * update_confirm.jspに返す
 */
@WebServlet("/uui")
public class UserUpdateInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserUpdateInput() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//変更後の値
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//変更前の値
		String oldId = request.getParameter("oldId");
		String oldword = request.getParameter("oldword");
		String oldName = request.getParameter("oldName");
		String oldicon = request.getParameter("oldicon");
		String oldpro = request.getParameter("oldpro");

		String[] duser = {oldId,oldword,oldName,oldicon,oldpro};
		request.setAttribute("duser", duser);

		RequestDispatcher dispatcher = null;
		String message = null;

		if (password.equals("")) {
			//未入力
			message = "*パスワードは必須です";

			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			//jspに処理を転送
			dispatcher = request.getRequestDispatcher("update_input.jsp");
			dispatcher.forward(request, response);
		} else {
			UserDTO user = null;
			user = new UserDTO();
			user.setLoginId(oldId);
			user.setPassword(password);
			user.setUserName(userName);
			user.setIcon(icon);
			user.setProfile(profile);

			request.setAttribute("user", user);

			//jspに処理を転送
			dispatcher = request.getRequestDispatcher("update_confirm.jsp");
			dispatcher.forward(request, response);
		}

	}

}
