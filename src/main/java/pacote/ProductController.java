package pacote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;

@WebServlet(name="productController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String BASE_URL = "http://localhost:8080/stock_control_servlet/";
	private final String GET_ALL = BASE_URL + "GetAllController";
	private String INSERT_PRODUCT = "/WEB-INF/view/product/insertProduct.jsp";
	
	private ProductService productService;
	

	public ProductController() {
		productService = new ProductService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			productService.insertProduct(req);
			resp.sendRedirect(GET_ALL);
		} catch (Exception ex) {
			System.out.println("Product post error: " + ex.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String forward = "";
	        String action = req.getParameter("action");
	        
	        if (action.equalsIgnoreCase("insertProduct")) {
				forward = INSERT_PRODUCT;
				
				RequestDispatcher view = req.getRequestDispatcher(forward);
				view.forward(req, resp);
			} else {
				PrintWriter out = resp.getWriter();
				
				out.print("<html>");
					
				out.print("</html>");
			}

		} catch (Exception ex) {
			System.out.println("Product get error: " + ex.getMessage());
		}
	}

}
