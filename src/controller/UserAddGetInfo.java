package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.CheckChar;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class UserAddGetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddGetInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		// ID被りチェック用
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		RequestDispatcher newdispatcher = null;
		String checkmessage1 = null;
		String checkmessage2 = null;
		String newmessage = null;
		String regex_AlphaNum = "^[A-Za-z0-9]+$" ;

		// 送信情報の取得
		String newloginId = request.getParameter("newloginId");
		String newpassword = request.getParameter("newpassword");
		String newuserName = request.getParameter("newuserName");
		String newicon = request.getParameter("newicon");
		String newprofile = request.getParameter("newprofile");

		HttpSession session = request.getSession();
		session.setAttribute("newloginId",newloginId);
		session.setAttribute("newpassword",newpassword);
		session.setAttribute("newuserName",newuserName);
		session.setAttribute("newicon",newicon);
		session.setAttribute("newprofile",newprofile);

		// 文字数チェック
		if(newloginId.length()<=4 && newpassword.length()<=7) {
			checkmessage1 = "IDは５文字以上で入力してください";
			checkmessage2 = "パスワードは８文字以上で入力してください";
			request.setAttribute("alert2", checkmessage1);
			request.setAttribute("alert3", checkmessage2);

			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}else if(newloginId.length()<=4) {
			checkmessage1 = "IDは５文字以上で入力してください";
			request.setAttribute("alert2", checkmessage1);

			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}else if(newpassword.length()<=7) {
			checkmessage2 = "パスワードは８文字以上で入力してください";
			request.setAttribute("alert3", checkmessage2);

			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);

			// ID被りをチェック
			String sql2 = "SELECT * FROM users WHERE LOGINID=?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1,newloginId);
			rset2 = pstmt2.executeQuery();

			if(rset2.next()) {
				String message1 = "入力されたログインIDは既に使用されています";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert1", message1);
				newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
				newdispatcher.forward(request,response);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt2.close();
			}catch(SQLException e) {}
		}


		if(CheckChar.checkLogic(regex_AlphaNum, newloginId)==false && CheckChar.checkLogic(regex_AlphaNum, newpassword)==false) {
			checkmessage1 = "ログインIDは半角英数字で記入してください";
			checkmessage2 = "パスワードは半角英数字で記入してください";
			request.setAttribute("alert2", checkmessage1);
			request.setAttribute("alert3", checkmessage2);

			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}else if(CheckChar.checkLogic(regex_AlphaNum, newloginId)==false) {
			checkmessage1 = "ログインIDは半角英数字で記入してください";
			request.setAttribute("alert2", checkmessage1);
			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}else if (CheckChar.checkLogic(regex_AlphaNum, newpassword)==false) {
			checkmessage2 = "パスワードは半角英数字で記入してください";
			request.setAttribute("alert3", checkmessage2);
			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}

		if(newloginId.equals("") || newpassword.equals("") || newuserName.equals("") || newicon.equals("") || newprofile.equals("")){
			// ログインID かパスワードどちらか、もしくは双方未入力なら
			newmessage = "全ての入力欄を埋めてください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", newmessage);

			// register.jsp に処理を転送
			newdispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			newdispatcher.forward(request,response);
		}else {
			// 全入力欄が埋まっている場合


			newdispatcher = request.getRequestDispatcher("UserAddConfirm.jsp");
			newdispatcher.forward(request, response);
		}

	}

}
