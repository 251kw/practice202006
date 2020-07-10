package controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class MultiDeleteShoutsResultServlet
 */
@WebServlet("/multiDeleteShoutsResultServlet")
public class MultiDeleteShoutsResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MultiDeleteShoutsResultServlet() {
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
		DBManager dbm = new DBManager();

		String[] shoutsIds = request.getParameterValues("shoutsId");
		String sql = MakeSelectSQL.makeSelectsShouts(shoutsIds);
		ArrayList<ShoutDTO> searchShouts = dbm.getSearchShoutsList(sql);
		request.setAttribute("searchShouts", searchShouts);

		sql = MakeSelectSQL.makeDeletesShoutsIds(shoutsIds);
		dbm.deleteSQL(sql);

		dispatcher = request.getRequestDispatcher("multiDeleteShoutsResult.jsp");
		dispatcher.forward(request, response);

	}

}
