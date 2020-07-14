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
 * 叫び確認画面から戻るときの処理
 */
@WebServlet("/updateShoutFailureConfirm")
public class UpdateShoutFailureConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UpdateShoutFailureConfirm() {
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
	 * updateShoutConfirm.jspの戻るボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");

		String shoutsId = request.getParameter("shoutsId");
		String shout = request.getParameter("shout");

		//変更ユーザーを取得
		DBManager dbm = new DBManager();
		String sql = MakeSelectSQL.makeSelectsShouts(shoutsId);
		ShoutDTO searchShout = dbm.getShout(sql);
		//未確定の変更内容を反映
		searchShout.setWriting(shout);
		//リクエストスコープへ入れる
		request.setAttribute("searchShout", searchShout);

		dispatcher = request.getRequestDispatcher("updateShoutInput.jsp");
		dispatcher.forward(request, response);
	}

}
