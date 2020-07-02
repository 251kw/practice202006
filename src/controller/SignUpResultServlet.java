package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sur")
public class SignUpResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SignUpResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //直接アクセスがあった場合はindex.jspに処理を転送
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
   		dispatcher.forward(request, response);
   	}

   	//signup_cmp.jspのログアウトボタンから呼び出される
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		//セッションを破棄
   		//HttpSession session = request.getSession();
   		//session.invalidate();

   		//doGetメソッドを呼び出し、index.jspに処理を転送
   		doGet(request, response);
   	}

}
