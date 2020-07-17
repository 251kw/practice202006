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
 * 叫びの情報を保持しつつ叫び編集画面に移行する
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

		// 値保持用の処理
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

		// 叫び編集画面に移行
		dispatcher = request.getRequestDispatcher("editShoutInput.jsp");
		dispatcher.forward(request,response);
	}

}
