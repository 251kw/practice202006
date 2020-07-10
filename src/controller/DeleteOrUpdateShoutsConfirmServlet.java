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
import dto.UserDTO;
import util.MakeSelectSQL;

/**
 * top.jspの更新または削除で呼び出し
 * 押されたボタンを判別して処理を行う
 */
@WebServlet("/deleteOrUpdateShoutsConfirmServlet")
public class DeleteOrUpdateShoutsConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrUpdateShoutsConfirmServlet() {
    }

	/**
	 * top.jspの更新または削除で呼び出し
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		String[] shoutsIds = request.getParameterValues("checked");

		String shoutsId = request.getParameter("shoutsId");

		//更新ボタンが押されたか、削除が押されたかを判別する(直接アクセスされた場合はindexへ)
		if(loginUser == null) {  //index処理
			dispatcher = request.getRequestDispatcher("index.jsp");

		}else if(shoutsId == null){ //選択削除を押されたが何も選択していない場合
			String[] checkShouts = {""};
			request.setAttribute("checkShouts", checkShouts);
			dispatcher = request.getRequestDispatcher("topInput.jsp");

		}else if(shoutsId.equals("alldel")) {  //選択削除処理
			String sql = MakeSelectSQL.makeSelectsShouts(shoutsIds);
			ArrayList<ShoutDTO> searchShouts = dbm.getSearchShoutsList(sql);
			request.setAttribute("searchShouts", searchShouts);
			dispatcher = request.getRequestDispatcher("multiDeleteShoutsConfirm.jsp");

		}else if(shoutsId.equals("allchkon")) {  //全選択処理
			ArrayList<ShoutDTO> searchShouts = dbm.getShoutList();
			ArrayList<String> checkShouts = new ArrayList<String>();
			for (ShoutDTO st : searchShouts) {
				checkShouts.add(String.valueOf(st.getShoutsId()));
			}
			String ck = "off";
			request.setAttribute("ck", ck);

			request.setAttribute("checkShouts", checkShouts);
			dispatcher = request.getRequestDispatcher("topInput.jsp");

		}else if(shoutsId.equals("allchkoff")) {  //全解除処理

			ArrayList<String> checkShouts = new ArrayList<String>();
			checkShouts.add("");
			request.setAttribute("checkShouts", checkShouts);

			String ck = "on";
			request.setAttribute("ck", ck);

			dispatcher = request.getRequestDispatcher("topInput.jsp");
		}else {  //更新処理
			String sql = MakeSelectSQL.makeSelectsShouts(shoutsId);
			ShoutDTO searchShout = dbm.getShout(sql);
			request.setAttribute("searchShout", searchShout);
			dispatcher = request.getRequestDispatcher("updateShoutInput.jsp");

		}

		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
