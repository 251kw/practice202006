package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUserSearch;
import dto.SearchUserDTO;
import dto.UserDTO;
import util.Check;


@WebServlet("/sis")
public class SearchInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SearchInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");

		if(botton.equals("掲示板へ戻る")) {
			//処理の転送先をtop.jspに指定
			dispatcher = request.getRequestDispatcher("top.jsp");

		} else if(botton.equals("検索")) {
				SearchUserDTO user = new SearchUserDTO("", "", null, "");
				request.setAttribute("user", user);
				dispatcher = request.getRequestDispatcher("search_input.jsp");

		} else if(botton.equals("検索する")) {
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				DBUserSearch dbu = new DBUserSearch();
				String message = null;

				//送信情報の取得
				String loginId = request.getParameter("loginId");
				String userName = request.getParameter("userName");
				String icon[] = request.getParameterValues("icon");		//アイコンは配列で
				String profile = request.getParameter("profile");

				SearchUserDTO searchuser = new SearchUserDTO(loginId, userName, icon, profile);	//データ保持

				 if(Check.checkLogic(loginId)==false) {		//入力チェック
					message = "ログインIDに使用できるのは半角英数字のみです。";

					//エラーメッセージをリクエストオブジェクトに保存
					request.setAttribute("alert", message);
					request.setAttribute("user", searchuser);
					message = "";
					dispatcher = request.getRequestDispatcher("search_input.jsp");

				 }	else {

					 ArrayList<UserDTO> users = dbu.userSearch(loginId, userName, profile, icon);	//検索メソッド

					 if(users.size()==0) {	//arraylistサイズ測る
						 //検索結果無し
						 message = "検索結果がありませんでした。";
						 request.setAttribute("alert", message);
						 request.setAttribute("user", searchuser);
						 dispatcher = request.getRequestDispatcher("search_input.jsp");
					 } else {
						 //検索結果表示へ
						 request.setAttribute("users", users);
						 request.setAttribute("user", searchuser);
						 dispatcher = request.getRequestDispatcher("search_result.jsp");
					 }
				 }

		}
		dispatcher.forward(request, response);

	}

}
