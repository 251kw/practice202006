package controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * 選択されたシャウトを削除する処理を行う
 */
@WebServlet("/multiDeleteShoutsResultServlet")
public class MultiDeleteShoutsResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MultiDeleteShoutsResultServlet() {
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
	 * multiDeteleShoutConfirm.jspの確定から呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		DBManager dbm = new DBManager();

		//送られてきたshoutsIdをもとにShoutDTOのリストを取得するSQLを生成,実行しリクエストスコープへ入れる
		String[] shoutsIds = request.getParameterValues("shoutsId");
		String sql = MakeSelectSQL.makeSelectsShouts(shoutsIds);
		ArrayList<ShoutDTO> searchShouts = dbm.getSearchShoutsList(sql);
		request.setAttribute("searchShouts", searchShouts);

		//shoutsIdをもとに削除(論理削除)するSQLを生成、実行
		sql = MakeSelectSQL.makeDeletesShoutsIds(shoutsIds);
		dbm.deleteSQL(sql);

		dispatcher = request.getRequestDispatcher("multiDeleteShoutsResult.jsp");
		dispatcher.forward(request, response);

	}

}
