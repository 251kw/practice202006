package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CheckBoxCheck;

/**
 * Servlet implementation class UserSearchInputBack
 */
@WebServlet("/usib")
public class UserSearchInputBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchInputBack() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckBoxCheck cbc = new CheckBoxCheck();
		request.setAttribute("cbc", cbc);

		HttpSession session = request.getSession();

		String[] sicon = (String[])session.getAttribute("sicon");
		if(sicon != null) {
			request.setAttribute("sicon", sicon);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
		dispatcher.forward(request, response);
	}

}
