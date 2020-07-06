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

/**
 * index.jspの「ログイン」ボタンから呼び出される。ログインチェックのサーブレット
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    //index.jspの「ログイン」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		RequestDispatcher dispatcher = null;	//dispatcherを定義
		String message = null;

		if(loginId.equals("") || password.equals("")) {
		//もしログインIDかパスワードのいずれか、もしくはどちらも未入力なら
			message = "ログインIDとパスワードは必須入力です";
			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
				//index.jspに処理を転送
			dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
		}else {
			//ログイン認証を行い、ユーザ情報を取得
			DBManager dbm = new DBManager();
			UserDTO user = dbm.getLoginUser(loginId, password);

			if(user != null) {
				//ユーザ情報を取得できたら、書き込み内容リストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();
				HttpSession session = request.getSession();
				//ログインユーザ情報、書き込み内容リストとしてセッションに保存
				session.setAttribute("user", user);
				session.setAttribute("shouts", list);
				//処理の転送先をtop.jspに指定
				dispatcher = request.getRequestDispatcher("top.jsp");
			}else {
				//ユーザ情報が取得できない場合、エラーメッセージをリクエストオブジェクトに保存
				message = "ログインIDまたはパスワードが違います";
				request.setAttribute("alert", message);
				//処理の転送先をindex.ｊｐｓに指定
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			//処理を転送
			dispatcher.forward(request, response);
			}
		}
	}
