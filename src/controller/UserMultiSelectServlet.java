package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import util.CheckUtil;

/**
 * 複数選択されたユーザを取得するサーブレット
 */
@WebServlet("/userMultiSelSrv")
public class UserMultiSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserMultiSelectServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 *userDelete.jspの「削除する」ボタンから呼び出される
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止,エンコード方式の指定
        response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		String[] checkedLogId = request.getParameterValues("checkedLogId");

		//検索結果を入れるArrayList
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		//チェックをつけたユーザを検索、ユーザ情報をArrayListに追加
		if(checkedLogId != null) {
			for(String logId : checkedLogId) {
				//チェックをつけたユーザをリストに追加
				list.addAll(CheckUtil.checkedUser(logId));
			}

			//ユーザリストをリクエストスコープへセット
			request.setAttribute("checkedUserlist", list);

			//userDelete.jspに処理を転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("userDelete.jsp");
			dispatcher.forward(request, response);
		}else {
			//何もチェックつけなかった場合は前画面に戻りエラー文表示
			String errCheckBoxMsg = CheckUtil.errCheckbox();
			//エラー文をリクエストスコープへセット
			request.setAttribute("errCheckBoxMsg", errCheckBoxMsg);

			//./searchSrvに処理を転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("./searchSrv");
			dispatcher.forward(request, response);
		}


	}
}
