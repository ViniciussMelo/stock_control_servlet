package model;

public class Movement {
	private int Id;
	private String ProductName;
	private String Type;
	private int Quantity;
	
	public Movement(int id, String productName, String type, int quantity) {
		this.Id = id;
		this.ProductName = productName;
		this.Type = type;
		this.Quantity = quantity;		
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String barcode) {
		ProductName = barcode;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}
