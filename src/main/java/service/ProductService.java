package service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import model.Product;

public class ProductService {
	private final String PRODUCT = "PRODUCT";
	
	public void insertProduct(HttpServletRequest req) {
		String barcode = req.getParameter("barcode");
		String name = req.getParameter("name");
		Double price = Double.parseDouble(req.getParameter("price"));
		
		Product product = new Product(barcode, name, price);
		addProductInSession(req, product);
	}
	
	private void addProductInSession(HttpServletRequest req, Product product) {
		ArrayList<Product> products = getSessionProducts(req);
		boolean prodAlreadyExists = productAlreayExists(req, product);
		
		if (prodAlreadyExists) {
			products = updateProductList(req, product);
		} else {
			products.add(product);
		}
		
		setProductsSession(req, products);
	}
	
	public void setProductsSession(HttpServletRequest req, ArrayList<Product> products) {
		req.getSession().setAttribute(PRODUCT, products);
	}
	
	private ArrayList<Product> getSessionProducts(HttpServletRequest req) {
		ArrayList<Product> prod = (ArrayList<Product>) req.getSession().getAttribute(PRODUCT);
		
		if (prod == null) prod = new ArrayList<Product>();
		
		return prod;
	}
	
	public List<Product> getAllProducts(HttpServletRequest req) {
		List<Product> listProducts = getSessionProducts(req);
		
		return listProducts;
	}
	
	public void editProduct(HttpServletRequest req) {
		Product prod = getProductByBarcode(req, req.getParameter("barcode"));
		
		prod.setName(req.getParameter("name"));
		prod.setPrice(Double.parseDouble(req.getParameter("price")));
		
		addProductInSession(req, prod);
	}
	
	public void deleteProduct(HttpServletRequest req) {
		ArrayList<Product> products = getSessionProducts(req);
		products.removeIf(p -> p.getBarcode().equalsIgnoreCase(req.getParameter("barcode")));
		
		setProductsSession(req, products);
	}
	
	private boolean productAlreayExists(HttpServletRequest req, Product product) {
		ArrayList<Product> prods = getSessionProducts(req);
		
		for(Product prod : prods) {
		    if(prod != null && prod.getBarcode().equalsIgnoreCase(product.getBarcode())) {
		        return true;
		    }
		}
		
		return false;
	}
	
	private ArrayList<Product> updateProductList(HttpServletRequest req, Product product) {
		ArrayList<Product> prods = getSessionProducts(req);
		
		for(Product prod : prods) {
		    if(prod != null && prod.getBarcode().equalsIgnoreCase(product.getBarcode())) {
		        prod.setBarcode(product.getBarcode());
		        prod.setName(product.getName());
		        prod.setPrice(product.getPrice());
		    }
		}
		
		return prods;
	}
	
	public Product getProductByBarcode(HttpServletRequest req, String barcode) {		
		ArrayList<Product> prods = getSessionProducts(req);
		
		for(Product prod : prods) {
		    if(prod != null && prod.getBarcode().equalsIgnoreCase(barcode)) {
		        return prod;
		    }
		}
		
		return null;
	}
}
