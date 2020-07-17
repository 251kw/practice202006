package connecter;

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
import dto.ShoutDTO;

/**
 * 掲示板に移動するために経由するサーブレット
 */
@WebServlet("/TurnBoardTop")
public class TurnBoardTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnBoardTopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String shoutselect = request.getParameter("shoutselect");
		String checkall = request.getParameter("checkall");

		if(checkall != null ) {
			// 掲示板の全選択ボタンの値保持用
			request.setAttribute("checkall", checkall);
		}

		if(shoutselect == null ) {
			// チェックボックスの保持を一旦リセット
			String[] select = null;
			session.setAttribute("select", select);
		}

		// sessionに新たな書き込みリストを保持
		DBManager dbm = new DBManager();
		ArrayList<ShoutDTO> shouts = new ArrayList<ShoutDTO>();
		shouts = dbm.getShoutList();
		session.setAttribute("shouts", shouts);

		// 掲示板に移動
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("boardTop.jsp");
		dispatcher.forward(request,response);
	}

}