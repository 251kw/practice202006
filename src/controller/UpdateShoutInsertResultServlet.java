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
 * Servlet implementation class UpdateShoutResultServlet
 */
@WebServlet("/updateShoutResultServlet")
public class UpdateShoutInsertResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoutInsertResultServlet() {
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
		request.setCharacterEncoding("UTF-8");

		String shoutsId = request.getParameter("shoutsId");
		String shout = request.getParameter("shout");

		//DBのデータを更新
		DBManager dbm = new DBManager();
		dbm.updateWriting(shout, shoutsId);

		//更新されたデータを再取得
		String sql = MakeSelectSQL.makeSelectsShouts(shoutsId);
		ShoutDTO searchShout = dbm.getShout(sql);
		request.setAttribute("searchShout", searchShout);

		dispatcher = request.getRequestDispatcher("updateShoutResult.jsp");
		dispatcher.forward(request, response);
	}

}
