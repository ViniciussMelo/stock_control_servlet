package pacote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.MovementService;
import service.ProductService;

@WebServlet(name="movementController", urlPatterns = {"/MovementController"})
public class MovementController extends HttpServlet {
	private final String BASE_URL = "http://localhost:8080/stock_control_servlet/";
	private final String GET_ALL = BASE_URL + "GetAllController";
	private String INSERT_MOVEMENT = "/WEB-INF/view/movement/insertMovement.jsp";
	
	private MovementService movementService;
	private ProductService productService;

	
	public MovementController() {
		movementService = new MovementService();
		productService = new ProductService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			
			if(action == null) {
				movementService.insertMovement(req);
			}
			
			resp.sendRedirect(GET_ALL);
		} catch (Exception ex) {
			System.out.println("Movement post error: " + ex.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String action = req.getParameter("action");
	        
	        if (action.equalsIgnoreCase("insertMovement")) {				
	        	createMovementPage(req, resp);
	        }
			
		} catch (Exception ex) {
			System.out.println("Movement get error: " + ex.getMessage());
		}
	}
	
	private void createMovementPage(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = resp.getWriter();
			List<Product> products = productService.getAllProducts(req);

			out.print("<html>");
				out.print("<head>");
					out.print("<title>Stock control</title>");
				out.print("</head>");
				out.print("<style> "
						+ "	#main {\r\n"
						+ "		height: 100%;\r\n"
						+ "		padding: 0px 20px 0 20px;\r\n"
						+ "		width: 100%;\r\n"
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
						+ "	select {\r\n"
						+ "	  width: 2000px;\r\n"
						+ "	  height: 45px;\r\n"
						+ "	  max-width: 100%;\r\n"
						+ "	\r\n"
						+ "	  /* Reset Select */\r\n"
						+ "	  appearance: none;\r\n"
						+ "	  outline: 0;\r\n"
						+ "	  border: 0;\r\n"
						+ "	  box-shadow: none;\r\n"
						+ "	  /* Personalize */\r\n"
						+ "	  padding: 0 1em;\r\n"
						+ "	  color: gray;\r\n"
						+ "	  background: #f1f1f1;\r\n"
						+ "	  background-image: none;\r\n"
						+ "	  cursor: pointer;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Remove IE arrow */\r\n"
						+ "	select::-ms-expand {\r\n"
						+ "	  display: none;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Custom Select wrapper */\r\n"
						+ "	.select {\r\n"
						+ "	  position: relative;\r\n"
						+ "	  display: flex;\r\n"
						+ "	  width: 20em;\r\n"
						+ "	  height: 3em;\r\n"
						+ "	  border-radius: .25em;\r\n"
						+ "	  overflow: hidden;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Arrow */\r\n"
						+ "	.select::after {\r\n"
						+ "	  content: '\\25BC';\r\n"
						+ "	  position: absolute;\r\n"
						+ "	  top: 0;\r\n"
						+ "	  right: 0;\r\n"
						+ "	  padding: 1em;\r\n"
						+ "	  background-color: #34495e;\r\n"
						+ "	  transition: .25s all ease;\r\n"
						+ "	  pointer-events: none;\r\n"
						+ "	  height: 45px;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	/* Transition */\r\n"
						+ "	.select:hover::after {\r\n"
						+ "	  color: #f39c12;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	option{ \r\n"
						+ "       font-size: 20px;\r\n"
						+ "     }");
				out.print("</style>");
				out.print("<body>");
					out.print("<div id=main>");
						out.print("<form action=MovementController method=POST>");
							out.print("<div class=container>");
								out.print("<h3>Movement</h3>");
								out.print("<label><b>Product</b></label>");
								out.print("<br>");
								out.print("<select name=productName>");
									for(Product prod : products) {
										out.print("<option value="+ prod.getName() +">"+ prod.getName() +"</option>");
									}
								out.print("</select>");
								out.print("<br>");
								out.print("<br>");
								out.print("<label><b>Type</b></label>");
								out.print("<br>");
								out.print("<select name=type>");
								out.print("<option value=entry>Entry</option>");
								out.print("<option value=exit>Exit</option>");
								out.print("</select>");
								out.print("<br>");
								out.print("<br>");
								out.print("<label><b>Quantity</b></label>");
								out.print("<input type=number placeholder=Enter quantity name=quantity id=quantity required>");
								out.print("<br>");
								out.print("<button type=submit class=registerbtn>Register</button>");
							out.print("</div>");
						out.print("</form>");
					out.print("</div>");
				out.print("</body>");
			out.print("</html>");
		}
		catch (Exception ex) {
			System.out.println("createMovementPage error: " + ex.getMessage());
		}
	}
}
