package pacote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movement;
import model.Product;
import service.MovementService;
import service.ProductService;

@WebServlet(name="getAllController", urlPatterns = {"/GetAllController"})
public class GetAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String BASE_URL = "http://localhost:8080/stock_control_servlet/";
	
	private ProductService productService;
	private MovementService movementService;
	

	public GetAllController() {
		productService = new ProductService();
		movementService = new MovementService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {			
			List<Product> products = productService.getAllProducts(req);
			List<Movement> movements = movementService.getAllMovements(req);
			
			PrintWriter out = resp.getWriter();			
			out.print("<html>");
				out.print("<head>");
					out.print("<title>Stock control</title>");
				out.print("</head>");
				out.print("<style>"
						+ "	table {\r\n"
						+ "	  font-family: Arial, Helvetica, sans-serif;\r\n"
						+ "	  width: 100%;\r\n"
						+ "	  border-spacing: 0px;\r\n"
						+ "	  border-collapse: separate;\r\n"
						+ "	  border: 1px solid black;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	td, th {\r\n"
						+ "	  border: 1px solid #ddd;\r\n"
						+ "	  padding: 8px;\r\n"
						+ "	  width:0.1%;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	tr:nth-child(even){background-color: #f2f2f2;}\r\n"
						+ "	\r\n"
						+ "	tbody tr:hover {background-color: #ddd;}\r\n"
						+ "	\r\n"
						+ "	th {\r\n"
						+ "	  padding-top: 12px;\r\n"
						+ "	  padding-bottom: 12px;\r\n"
						+ "	  text-align: left;\r\n"
						+ "	  background-color: #04AA6D;\r\n"
						+ "	  color: white;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	.button {\r\n"
						+ "	  background-color: #4CAF50; /* Green */\r\n"
						+ "	  border: none;\r\n"
						+ "	  color: white;\r\n"
						+ "	  padding: 15px 32px;\r\n"
						+ "	  text-align: center;\r\n"
						+ "	  text-decoration: none;\r\n"
						+ "	  display: inline-block;\r\n"
						+ "	  font-size: 16px;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	.buttonActionEdit {\r\n"
						+ "	  background-color: #4CAF50; /* Green */\r\n"
						+ "	  border: none;\r\n"
						+ "	  color: white;\r\n"
						+ "	  text-align: center;\r\n"
						+ "	  text-decoration: none;\r\n"
						+ "	  display: inline-block;\r\n"
						+ "	  font-size: 12px;\r\n"
						+ "	  padding: 10px 20px;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	.buttonActionDelete {\r\n"
						+ "	  background-color: red;\r\n"
						+ "	  border: none;\r\n"
						+ "	  color: white;\r\n"
						+ "	  text-align: center;\r\n"
						+ "	  text-decoration: none;\r\n"
						+ "	  display: inline-block;\r\n"
						+ "	  font-size: 12px;\r\n"
						+ "	  padding: 10px 15px;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	tfoot {\r\n"
						+ "	  background-color: #04AA6D;\r\n"
						+ "	}\r\n"
						+ "	\r\n"
						+ "	tfoot:hover {\r\n"
						+ "		background-color: #04AA6D\r\n"
						+ "	}"
						+ "</style>");
				out.print("<body>");
					out.print("<h3>Products</h3>");
					out.print("<a href=" + BASE_URL + "ProductController?action=insertProduct class=button>Add Product</a>");
					out.print("<table id=products border=1>");
						out.print("<thead>");
							out.print("<tr>");
								out.print("<th>Row</th>");
								out.print("<th>Barcode</th>");
								out.print("<th>Name</th>");
								out.print("<th>Price</th>");
								out.print("<th>Actions</th>");
							out.print("</tr>");
						out.print("</thead>");
						out.print("<tbody>");
						int i = 1;
						for (Product prod : products) {
							out.print("<tr>");
								out.print("<td>" + i++ + "</td>");
								out.print("<td>" + prod.getBarcode() + "</td>");
								out.print("<td>" + prod.getName() + "</td>");
								out.print("<td>" + prod.getPrice() + "</td>");
								out.print("<td>");
									out.print("<a href=" + BASE_URL +"ProductController?action=editProduct&barcode=" 
											+ prod.getBarcode() 
											+ " class=buttonActionEdit>Edit</a> ");
									out.print(" <a href=" + BASE_URL + "ProductController?action=deleteProduct&barcode=" 
											+ prod.getBarcode() 
											+ " class=buttonActionDelete>Remove</a>");
								out.print("</td>");
							out.print("</tr>");
						}
						out.print("</tbody>");
						out.print("<tfoot>");
							out.print("<tr>");
								out.print("<th scope=rowgroup colspan=4>Records</th> ");
								out.print("<td>"+ products.size() + "</td>");
							out.print("</tr>");
						out.print("</tfoot>");
					out.print("</table>");
					out.print("<br>");
					out.print("<br>");
					out.print("<h3>Movements</h3>");
					out.print("<table id=movements border=1>");
						out.print("<thead>");
							out.print("<tr>");
								out.print("<th>Row</th>");
								out.print("<th>Product name</th>");
								out.print("<th>Quantity</th>");
								out.print("<th>Type</th>");
								out.print("<th>Actions</th>");
							out.print("</tr>");
						out.print("</thead>");
						out.print("<tbody>");
							int j = 1;
							for (Movement mov : movements) {
								out.print("<tr>");
									out.print("<td>" + j++ + "</td>");
									out.print("<td>" + mov.getProductName() + "</td>");
									out.print("<td>" + mov.getQuantity() + "</td>");
									out.print("<td>" + mov.getType() + "</td>");
									out.print("<td>");
										out.print("<a href=" + BASE_URL +"MovementController?action=editMovement&id=" 
												+ mov.getId() 
												+ " class=buttonActionEdit>Edit</a> ");
										out.print(" <a href=" + BASE_URL + "MovementController?action=deleteMovement&id=" 
												+ mov.getId() 
												+ " class=buttonActionDelete>Remove</a>");
									out.print("</td>");
								out.print("</tr>");
							}
						out.print("</tbody>");
						out.print("<tfoot>");
							out.print("<tr>");
								out.print("<th scope=rowgroup colspan=4>Records</th> ");
								out.print("<td>"+ movements.size() + "</td>");
							out.print("</tr>");
						out.print("</tfoot>");
					out.print("</table");
					out.print("<br>");
					out.print("<a href=" + BASE_URL + "MovementController?action=insertMovement class=button>Add Movement</a>");
				out.print("</body>");
			out.print("</html>");
		} catch (Exception ex) {
			System.out.println("Get all error: " + ex.getMessage());
		}
	}
}
