package shop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.bean.CustomerBean;
import shop.dao.CustomerDAO;
import shop.dao.DAOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null || action.equals("login")) {
			gotoPage(request,response,"/login.jsp");
		}
	}

	
	
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			CustomerDAO customerdao = new CustomerDAO();
			
			CustomerBean customerbean =  customerdao.loginCheck(name, password);
			
			if(customerbean != null) {
				session.setAttribute("customer", name);
				request.setAttribute("message", "ログインしました");
				gotoPage(request,response,"/index.jsp");	
			}else {
				request.setAttribute("message", "ログインに失敗しました");
				gotoPage(request,response,"/login.jsp");	
			}
		}catch(DAOException e) {
		     e.printStackTrace();
	         request.setAttribute("message", "内部エラーが発生しました。");
	         RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
	         rd.forward(request, response);
		}
		
	}

}
