package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.ShoutDTO;
import util.MakeSelectSQL;

/**
 * Servlet implementation class UpdateShoutConfirmServlet
 */
@WebServlet("/updateShoutConfirmServlet")
public class UpdateShoutConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoutConfirmServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");

		String shoutsId = request.getParameter("shoutsId");
		String shout = request.getParameter("shout");

		DBManager dbm = new DBManager();
		String sql = MakeSelectSQL.makeSelectsShouts(shoutsId);
		ShoutDTO searchShout = dbm.getShout(sql);
		request.setAttribute("searchShout", searchShout);

		dispatcher = request.getRequestDispatcher("");
		dispatcher.forward(request, response);

	}

}
