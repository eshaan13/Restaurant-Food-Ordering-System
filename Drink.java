
public class Drink {
	
	public String name;
	public String discription;
	public double price;
	
	public Drink(String name, String discription, double price) {
		super();
		this.name = name;
		this.discription = discription;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public String getDiscription() {
		return discription;
	}
}
