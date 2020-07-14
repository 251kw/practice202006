package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ShoutDTO;

/**
 * Servlet implementation class TurnEditShoutInputServlet
 */
@WebServlet("/TurnEditShoutInput")
public class TurnEditShoutInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnEditShoutInputServlet() {
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
		String esloginId = request.getParameter("esloginId");
		String esuserName = request.getParameter("esuserName");
		String esicon = request.getParameter("esicon");
		String esdate = request.getParameter("esdate");
		String eswriting = request.getParameter("eswriting");

		ShoutDTO shoutinfo = new ShoutDTO();
		shoutinfo.setShoutsId(eshoutsId);
		shoutinfo.setLoginId(esloginId);
		shoutinfo.setUserName(esuserName);
		shoutinfo.setIcon(esicon);
		shoutinfo.setDate(esdate);
		shoutinfo.setWriting(eswriting);

		request.setAttribute("shoutinfo", shoutinfo);

		dispatcher = request.getRequestDispatcher("editShoutInput.jsp");
		dispatcher.forward(request,response);
	}

}
