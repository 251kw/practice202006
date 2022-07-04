package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet2
 */
public class LogoutServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 直接アクセスがあった場合はindex.jspに処理を転送
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
   		dispatcher.forward(request, response);
   	}

   	// top.jspの「ログアウト」ボタンから呼び出される
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// セッションを破棄
   		HttpSession session = request.getSession();

   		// doGetメソッドを呼び出し、index.jspに処理を転送
   		doGet(request, response);
   	}

   }
