package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ShoutDTO;
import util.CheckDB;

/**
 * Servlet implementation class ShoutEditRegistServlet
 */
@WebServlet("/ShoutEditRegist")
public class ShoutEditRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutEditRegistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		String eshoutsId = request.getParameter("eshoutsId");
		String eswriting = request.getParameter("eswriting");

		ShoutDTO reshout = new ShoutDTO();
		CheckDB.EditShouts(eshoutsId, eswriting);
		reshout = CheckDB.SearchShouts(eshoutsId);

		request.setAttribute("reshout", reshout);

		dispatcher = request.getRequestDispatcher("editShoutResult.jsp");
		dispatcher.forward(request,response);
	}

}
