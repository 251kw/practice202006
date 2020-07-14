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
 * top.jspの全選択、全解除、更新または削除で呼び出し
 * 押されたボタンを判別して処理を行う
 */
@WebServlet("/deleteOrUpdateShoutsConfirmServlet")
public class DeleteOrUpdateShoutsConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteOrUpdateShoutsConfirmServlet() {
    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * topInput.jspの全選択、全解除、更新、削除からの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		String[] shoutsIds = request.getParameterValues("checked");

		String shoutsId = request.getParameter("shoutsId");

		//更新ボタンが押されたか、削除が押されたかを判別する
		if(shoutsId.equals("alldel")) {  //選択削除処理
			//未選択で削除ボタンを押した場合
			if(shoutsIds == null) {
				//警告文の出力準備
				String alert = "シャウトが選択されていません";
				request.setAttribute("alertshout", alert);
				//全選択をonにする
				String ck = "on";
				request.setAttribute("ck", ck);
				//チェックボックスの判定に空の値を入れる
				ArrayList<String> checkShouts = new ArrayList<String>();
				checkShouts.add("");
				request.setAttribute("checkShouts", checkShouts);
				dispatcher = request.getRequestDispatcher("topInput.jsp");
			}else {
				//選択されたシャウトのshoutsIdの配列をもとにSELECT文の生成と実行
				String sql = MakeSelectSQL.makeSelectsShouts(shoutsIds);
				ArrayList<ShoutDTO> searchShouts = dbm.getSearchShoutsList(sql);
				//取得した情報をリクエストスコープに
				request.setAttribute("shoutsIds", shoutsIds);
				request.setAttribute("searchShouts", searchShouts);
				dispatcher = request.getRequestDispatcher("multiDeleteShoutsConfirm.jsp");
			}
		}else if(shoutsId.equals("allchkon")) {  //全選択処理
			//すべてのシャウト情報を取得
			ArrayList<ShoutDTO> searchShouts = dbm.getShoutList();
			ArrayList<String> checkShouts = new ArrayList<String>();
			//shoutsIdだけをリクエストスコープに
			for (ShoutDTO st : searchShouts) {
				checkShouts.add(String.valueOf(st.getShoutsId()));
			}
			//全選択を全解除に変更する処理
			String ck = "off";
			request.setAttribute("ck", ck);

			request.setAttribute("checkShouts", checkShouts);
			dispatcher = request.getRequestDispatcher("topInput.jsp");

		}else if(shoutsId.equals("allchkoff")) {  //全解除処理
			//チェックボックスの判定に空の値を入れる
			ArrayList<String> checkShouts = new ArrayList<String>();
			checkShouts.add("");
			request.setAttribute("checkShouts", checkShouts);

			//全選択をonにする
			String ck = "on";
			request.setAttribute("ck", ck);

			dispatcher = request.getRequestDispatcher("topInput.jsp");
			dispatcher = request.getRequestDispatcher("topInput.jsp");
		}else {  //更新処理
			//選択されたシャウトのshoutsId(単体)をもとにSELECT文の生成と実行
			String sql = MakeSelectSQL.makeSelectsShouts(shoutsId);
			ShoutDTO searchShout = dbm.getShout(sql);
			//取得した情報をリクエストスコープに
			request.setAttribute("shoutsIds", shoutsIds);
			request.setAttribute("searchShout", searchShout);
			dispatcher = request.getRequestDispatcher("updateShoutInput.jsp");

		}

		dispatcher.forward(request, response);
	}

}
