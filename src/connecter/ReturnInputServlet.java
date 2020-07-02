package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnInput
 */
@WebServlet("/ReturnInput")
public class ReturnInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnInputServlet() {
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
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String newloginId = request.getParameter("newloginId");
		String newpassword = request.getParameter("newpassword");
		String newuserName = request.getParameter("newuserName");
		String newicon = request.getParameter("newicon");
		String newprofile = request.getParameter("newprofile");

		request.setAttribute("newloginId", newloginId);
		request.setAttribute("newpassword", newpassword);
		request.setAttribute("newuserName", newuserName);
		request.setAttribute("newicon", newicon);
		request.setAttribute("newprofile", newprofile);

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
		dispatcher.forward(request,response);
	}

}
