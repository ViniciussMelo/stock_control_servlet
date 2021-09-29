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
			String action = req.getParameter("action");
			if (action != null && action.equalsIgnoreCase("saveProduct")) {
				productService.editProduct(req);
			} else {
				productService.insertProduct(req);
			}
			
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
			} else if (action.equalsIgnoreCase("editProduct")) {
				createEditPage(req, resp);
			}

		} catch (Exception ex) {
			System.out.println("Product get error: " + ex.getMessage());
		}
	}
	
	private void createEditPage(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String barcode = req.getParameter("barcode");
			PrintWriter out = resp.getWriter();
			
			Product prodcut = productService.getProductByBarcode(req, barcode);
			
			out.print("<html>");
				out.print("<head>");
					out.print("<title>Stock control</title>");
				out.print("</head>");
				out.print("<style> 	#main {\r\n"
						+ "		position: absolute;\r\n"
						+ "		left: 15%;\r\n"
						+ "		height: 95%;\r\n"
						+ "		padding: 0px 20px 0 20px;\r\n"
						+ "		width: 82%;\r\n"
						+ "		background-color: #DCDCDC;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Add padding to containers */\r\n"
						+ "	.container {\r\n"
						+ "	  padding: 16px 100px 16px 16px;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Full-width input fields */\r\n"
						+ "	input[type=text], input[type=number], input[type=password] {\r\n"
						+ "	  width: 100%;\r\n"
						+ "	  padding: 15px;\r\n"
						+ "	  margin: 5px 0 22px 0;\r\n"
						+ "	  display: inline-block;\r\n"
						+ "	  border: none;\r\n"
						+ "	  background: #f1f1f1;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	input[type=text]:focus, input[type=number]:focus, input[type=password]:focus {\r\n"
						+ "	  background-color: white;\r\n"
						+ "	  outline: none;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	#inputReadonly {\r\n"
						+ "	  width: 100%;\r\n"
						+ "	  padding: 15px;\r\n"
						+ "	  margin: 5px 0 22px 0;\r\n"
						+ "	  display: inline-block;\r\n"
						+ "	  border: none;\r\n"
						+ "	  background: gray;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Overwrite default styles of hr */\r\n"
						+ "	hr {\r\n"
						+ "	  border: 1px solid #f1f1f1;\r\n"
						+ "	  margin-bottom: 25px;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Set a style for the submit button */\r\n"
						+ "	.registerbtn {\r\n"
						+ "	  background-color: #04AA6D;\r\n"
						+ "	  color: white;\r\n"
						+ "	  padding: 16px 20px;\r\n"
						+ "	  margin: 8px 0;\r\n"
						+ "	  border: none;\r\n"
						+ "	  cursor: pointer;\r\n"
						+ "	  width: 100%;\r\n"
						+ "	  opacity: 0.9;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	.registerbtn:hover {\r\n"
						+ "	  opacity: 1;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Add a blue text color to links */\r\n"
						+ "	a {\r\n"
						+ "	  color: dodgerblue;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Set a grey background color and center the text of the \"sign in\" section */\r\n"
						+ "	.signin {\r\n"
						+ "	  background-color: #f1f1f1;\r\n"
						+ "	  text-align: center;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	\r\n"
						+ "	.containerCheck {\r\n"
						+ "	  display: block;\r\n"
						+ "	  position: relative;\r\n"
						+ "	  padding-left: 35px;\r\n"
						+ "	  margin-bottom: 12px;\r\n"
						+ "	  cursor: pointer;\r\n"
						+ "	  font-size: 22px;\r\n"
						+ "	  -webkit-user-select: none;\r\n"
						+ "	  -moz-user-select: none;\r\n"
						+ "	  -ms-user-select: none;\r\n"
						+ "	  user-select: none;\r\n"
						+ "	}");
				out.print("</style>");
				out.print("<body>");
					out.print("<div>");
						out.print("<form action=ProductController?action=saveProduct method=POST>");
							out.print("<div class=container>");
								out.print("<h3>Product</h3>");
								out.print("<label><b>Barcode</b></label>");
								out.print("<input type=text placeholder=Enter barcode name=barcode id=inputReadonly readonly=readonly value="+ prodcut.getBarcode() +" required>");
								out.print("<br>");
								out.print("<label><b>Name</b></label>");
								out.print("<input type=text placeholder=Enter name=name id=name value=" + prodcut.getName() +" required>");
								out.print("<br>");
								out.print("<label><b>Price</b></label>");
								out.print("<input type=number placeholder=Enter price name=price id=price value=" + prodcut.getPrice() +" required>");
								out.print("<br>");
								out.print("<button type=submit class=registerbtn>Register</button>");
							out.print("</div>");
						out.print("</form>");
					out.print("</div>");
				out.print("</body>");
			out.print("</html>");
		} catch (Exception ex) {
			System.out.println("createEditPage error: " + ex.getMessage());
		}
	}

}
