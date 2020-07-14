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
 * 検索結果画面から押されたボタンによって、各jspに情報を送る
 * search_result.jspから送られてくる
 * 削除ならdelete_confirm.jspに返す
 * 更新ならupdate_input.jspに返す
 */
@WebServlet("/udi")
public class UserDeleteInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserDeleteInput() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//ボタンの情報受け取る
		String user = request.getParameter("user");
		String all = request.getParameter("all");
		String full = request.getParameter("full");
		//複数削除用
		String[] deleteList =request.getParameterValues("select");
		RequestDispatcher dispatcher = null;
		//ログインしているユーザーの情報をとってくる
		HttpSession session = request.getSession();
		UserDTO loginuser =  (UserDTO)session.getAttribute("user");
		//チェックの保持用
		if(deleteList != null) {
			String check = String.join(",", deleteList);
			session.setAttribute("check", check);
		}else {
			String check = "";
			session.setAttribute("check", check);
		}

		//複数削除
		if(all != null && deleteList != null) {
			DBManager db = new DBManager();
			String str = "WHERE loginId IN (";
			for(int i=0; i<deleteList.length; i++) {
				str = str+"'"+deleteList[i]+"',";
				if(deleteList[i].equals(loginuser.getLoginId())){
					String msg = null;
					msg = "＊現在ログインしているユーザーを含むため消すとログアウトします。";
					request.setAttribute("alert", msg);
				}
			}
			str = str.substring(0, (str.length() - 1));
			str = str+")";
			ArrayList<UserDTO> list = db.searchUser(str);
			request.setAttribute("users", list);
			dispatcher = request.getRequestDispatcher("multDelete.jsp");

		//何もチェックせずに複数削除のボタン押したとき
		}else if(all != null && deleteList==null) {
			DBManager db = new DBManager();
			String str = (String)session.getAttribute("str");
			ArrayList<UserDTO> list = db.searchUser(str);
			request.setAttribute("users", list);
			dispatcher = request.getRequestDispatcher("search_result.jsp");

		//全選択
		}else if(full != null) {
			DBManager db = new DBManager();
			ArrayList<String> idList = new ArrayList<String>();
			String str = (String)session.getAttribute("str");
			idList= db.allGetId(str);
			 String check = String.join(",", idList);
			 //全解除の処理
			 if(deleteList != null) {
				 String oldCheck =  String.join(",", deleteList);
				 if(check.equals(oldCheck)) {
					check = "";
				 }
			 }
			session.setAttribute("check", check);
			ArrayList<UserDTO> list = db.searchUser(str);
			request.setAttribute("users", list);
			dispatcher = request.getRequestDispatcher("search_result.jsp");

		//個別の削除、更新
		}else{
			//情報が一つのString文で送られてくるため、配列に変換
			String[] duser = user.split(",", 0);
			request.setAttribute("duser", duser);

			//ログインしているユーザーかチェック
			if(duser[0].equals(loginuser.getLoginId()) && duser[5].equals("削除")){
				String msg = null;
				msg = "＊現在ログインしているユーザーのため消すとログアウトします。";
				request.setAttribute("alert", msg);
			}
			//配列の[5]に削除か更新と入っているため、それで送る先を決める
			if(duser[5].equals("削除")) {
				dispatcher = request.getRequestDispatcher("delete_confirm.jsp");
			}else if(duser[5].equals("更新")) {
				//更新にはsessionを使うため
				session.setAttribute("duser", duser);
				dispatcher = request.getRequestDispatcher("update_input.jsp");
			}
		}

		dispatcher.forward(request, response);
	}

}
