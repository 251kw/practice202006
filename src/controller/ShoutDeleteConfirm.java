package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * top.jspの叫びの削除ボタンから
 * s_delete_confirm.jspに送る
 */
@WebServlet("/sdc")
public class ShoutDeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutDeleteConfirm() {
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


		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("s_delete_confirm.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
