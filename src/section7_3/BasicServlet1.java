package section7_3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BasicServlet1
 */
@WebServlet("/bs1")
public class BasicServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicServlet1() {
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
		// 送信情報の取得
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");
		String gender = request.getParameter("gender");
		// チェックボックスは配列で取得
		String [] hobby = request.getParameterValues("hobby");
		String blood = request.getParameter("blood");
		String remarks = request.getParameter("remarks");

		// HTML 出力準備
		PrintWriter out = response.getWriter();

		out.println("<html lang='ja'>");
		out.println("<head>");
		out.println("<title>Test Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Name : " + userName + "<br>");
		out.println("Password : " + pass + "<br>");
		out.println("Gender : " + gender + "<br>");
		out.println("Blood Type : " + blood + "<br>");
		out.println("Remarks : " + remarks + "<br>");
		out.println("Hobby : ");

		// 配列のすべての要素にアクセス
		if(hobby != null){
			for(String s : hobby){
				out.println(s + ":");
			}
		}

		out.println("</body>");
		out.println("</html>");
	}
}
