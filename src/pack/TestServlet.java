package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "Hello Servlet World!!";
		PrintWriter out = response.getWriter();

		out.println("<html lang='ja'>"); // 属性値は ' ' で囲む
		out.println("<head>");
		out.println("<title>Test Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>"+ message +"</h3>");
		out.println("</body>");
		out.println("</html>");
	}
}
