package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.CheckDB;
import dto.UserDTO;

/**
 * Servlet implementation class TurnDeleteConfirmServlet
 */
@WebServlet("/TurnDeleteConfirm")
public class TurnDeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurnDeleteConfirmServlet() {
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

		RequestDispatcher dispatcher = null;

		HttpSession session = request.getSession();

		// 送信情報の取得
		String dloginId = request.getParameter("dloginId");
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		// 値の保持用
		request.setAttribute("dloginId", dloginId);
		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		UserDTO user1 = new UserDTO();
		UserDTO user2 = new UserDTO();

		String logoutmessage = null;

		user1 = (UserDTO)session.getAttribute("user");
		user2 = CheckDB.SearchUser(dloginId);

		request.setAttribute("user", user2);

		if(user1.getLoginId().equals(user2.getLoginId())) {
			logoutmessage = "※登録情報の削除後はログアウトする必要があります";
			request.setAttribute("logoutalert",logoutmessage);
		}

		dispatcher = request.getRequestDispatcher("DeleteConfirm.jsp");
		dispatcher.forward(request,response);
	}

}
