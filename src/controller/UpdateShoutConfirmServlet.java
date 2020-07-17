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
 * シャウト更新の確認処理
 */
@WebServlet("/updateShoutConfirmServlet")
public class UpdateShoutConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateShoutConfirmServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * updateShoutInput.jspの叫び直すボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		//shoutsIdと叫び直したwaitingを受け取る
		String shoutsId = request.getParameter("shoutsId");
		String shout = request.getParameter("shout");

		//shoutsIdをもとにShoutDTOを取り出すSQL文を生成し、実行
		String sql = MakeSelectSQL.makeSelectsShouts(shoutsId);
		ShoutDTO searchShout = dbm.getShout(sql);
		//叫び直したデータに置き換える
		searchShout.setWriting(shout);

		request.setAttribute("searchShout", searchShout);

		dispatcher = request.getRequestDispatcher("updateShoutConfirm.jsp");
		dispatcher.forward(request, response);

	}

}