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
import dto.UserDTO;


/**
 * @author t.kurihara
 * 基本的にsqlを回すだけのサーブレット
 * delete_result.jsp、delete_confirm.jsp、update_input.jsp、update_result.jsp1から送られてくる
 * search_result.jspに返す
 */
@WebServlet("/udr")
public class UserDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserDeleteResult() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		DBManager db = new DBManager();
		RequestDispatcher dispatcher = null;

		//現在ログインしているユーザーの処理に必要
		String loginId = request.getParameter("d_loginId");			//削除用
		String[] loginIds = request.getParameterValues("d_loginIds");	//複数削除用
		HttpSession session = request.getSession();
		UserDTO user =  (UserDTO)session.getAttribute("user");			//ログインしているユーザー


		//複数削除
		if(loginIds != null) {
			for(int i=0; i<loginIds.length; i++) {
				if(loginIds[i].equals(user.getLoginId())){
					loginId = loginIds[i];	//index.jspにとぶようにする
				}
			}
		}

		//削除かつ現在ログインしているユーザーの場合最初の画面に飛ぶ
		if(loginId != null && loginId.equals(user.getLoginId())){
			dispatcher = request.getRequestDispatcher("index.jsp");
		}else {
			//もう一度sqlをまわす
			String str = (String)session.getAttribute("str");
			ArrayList<UserDTO> list = db.searchUser(str);
			request.setAttribute("users", list);
			//削除済みユーザー
			list = db.getDeleteUser();
			request.setAttribute("d_users", list);

			dispatcher = request.getRequestDispatcher("search_result.jsp");
		}

		dispatcher.forward(request, response);
	}

}
