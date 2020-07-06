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

import dao.DBManager;
import dto.UserDTO;
import util.MakeSelectSQL;

/**
 * Servlet implementation class MultiDeleteUSerComfirmServlet
 */
@WebServlet("/multiDeleteUSerComfirmServlet")
public class MultiDeleteUSerComfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MultiDeleteUSerComfirmServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は indexInput.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexInput.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * SearchResultの選択削除からの呼び出し
	 * 受け取ったloginIdをUserDTOのリストに詰める
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		String[] loginIds = request.getParameterValues("checked");

		if(loginIds != null) {
			String sql = MakeSelectSQL.makeSelects(loginIds);
			ArrayList<UserDTO> deleteUser = dbm.getSearchUserList(sql);
			request.setAttribute("deleteUser",deleteUser);
			for (String log : loginIds) {
				if(log.equals(loginUser.getLoginId())) {
					String message = "現在ログイン中のユーザーが含まれるため削除を実行すると、自動的にログアウトします";
					request.setAttribute("alert", message);
				}
			}
			dispatcher = request.getRequestDispatcher("multiDeleteUserConfirm.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("/returnSearchResult");
		}

		dispatcher.forward(request, response);
	}

}
