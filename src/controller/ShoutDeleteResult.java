package controller;

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
 * 実際にデリート文を実行する
 * s_delete_confirm.jspから送られる
 * s_delete_result.jspに送る
 */
@WebServlet("/sdr")
public class ShoutDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutDeleteResult() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//情報取得
		String shoutsId = request.getParameter("shoutsId");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String date = request.getParameter("date");
		String writing = request.getParameter("writing");
		int s_id = Integer.parseInt(shoutsId);

		//情報送信
		request.setAttribute("shoutsId", s_id);
		request.setAttribute("icon", icon);
		request.setAttribute("userName", userName);
		request.setAttribute("date", date);
		request.setAttribute("writing", writing);

		DBManager db = new DBManager();

		//削除実行
		db.deleteOneShout(s_id);

		//shoutsリスト更新
		ArrayList<ShoutDTO> list = db.getShoutList();
		HttpSession session = request.getSession();
		session.setAttribute("shouts", list);


		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("s_delete_result.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
