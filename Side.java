
import java.util.HashMap;
import java.util.Map;

public class Side {

	public String name;
	public String discription;
	public double price;
	public Map<String, Double> ingredients = new HashMap<>();
	
	public Side(String name, String discription, double price, Map<String, Double> ingredients) {
		this.name = name;
		this.discription = discription;
		this.price = price;
		this.ingredients = ingredients;
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
	public Map<String, Double> getIngredients() {
		return ingredients;
	}
}
