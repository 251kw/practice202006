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
import dto.UserDTO;

/**
 * Servlet implementation class UserMultdeleteConfirm
 */
@WebServlet("/umc")
public class UserMultdeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserMultdeleteConfirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String[] user =  request.getParameterValues("users");

		DBManager db = new DBManager();
		RequestDispatcher dispatcher = null;
		boolean result = false;


		String str = "WHERE loginId IN (";
		for(int i=0; i<user.length; i++) {
			str = str+"'"+user[i]+"',";
		}
		str = str.substring(0, (str.length() - 1));
		str = str+")";
		ArrayList<UserDTO> list = db.searchUser(str);
		request.setAttribute("users", list);


		for(String us : user) {
			//デリート文実行
			String str_d = "'"+us+"'";
			result =db.deleteUser(str_d);
			if(result==false) {
				break;
			}
		}

		//成功したかどうか
		if(result==true) {
			dispatcher = request.getRequestDispatcher("multDelete_result.jsp");
		}else {
			//失敗したときもう一度jspに情報を送る
			String msg = null;
			msg = "＊データベースで問題が発生するため削除できません。";
			request.setAttribute("alert", msg);
			dispatcher = request.getRequestDispatcher("multDelete.jsp");
		}

		dispatcher.forward(request, response);

	}

}
