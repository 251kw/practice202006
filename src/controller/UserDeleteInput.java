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

		//情報受け取る
		String user = request.getParameter("user");
		String all = request.getParameter("all");
		//複数削除用
		String[] deleteList =request.getParameterValues("select");
		RequestDispatcher dispatcher = null;
		//ログインしているユーザーの情報をとってくる
		HttpSession session = request.getSession();
		UserDTO loginuser =  (UserDTO)session.getAttribute("user");

		if(all!=null && all.equals("複数削除")) {
			ArrayList<String[]> deleteuser = new ArrayList<String[]>();
			for(int i=0; i<deleteList.length; i++) {
				String[] list =deleteList[i].split(",",0);
				if(list[0].equals(loginuser.getLoginId())){
					String msg = null;
					msg = "＊現在ログインしているユーザーを含むため消すとログアウトします。";
					request.setAttribute("alert", msg);
				}
				deleteuser.add(list);
			}
			request.setAttribute("deleteList",deleteList);
			dispatcher = request.getRequestDispatcher("multDelete.jsp");
		}else {
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
