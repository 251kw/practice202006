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
 * Servlet implementation class SerachInputServlet
 */
@WebServlet("/search")
public class SerachInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerachInputServlet() {
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
		RequestDispatcher dispatcher;
		DBManager dbm = new DBManager();

		//並び替えで指定された、もしくはhiddenで受け取ったsortを入れる
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");
		String car = request.getParameter("icon-car");
		String clip = request.getParameter("icon-paperclip");
		String radio = request.getParameter("icon-radio");

		//SQL文の作成メソッド
		String sql = MakeSelectSQL.makeSelect(loginId, userName, profile, car, clip, radio);
		//作成したSQLを渡しserchUserで受け取る
		ArrayList<UserDTO> searchUser = dbm.getSearchUserList(sql);

		HttpSession session = request.getSession();
		session.setAttribute("searchUser",searchUser );

		dispatcher = request.getRequestDispatcher("SearchResult.jsp");
		dispatcher.forward(request, response);
	}

}
