
import java.util.HashMap;
import java.util.Map;

public class Dish {

	public String name;
	public String discription;
	public double price;
	public Map<String, Double> ingredients = new HashMap<>();
	
	public Dish(String name, String discription, double price, Map<String, Double> ingredients) {
		super();
		this.name = name;
		this.discription = discription;
		this.price = price;
		this.ingredients = ingredients;
	}
	
	public double getPrice() {
		return price;
	}
	public Map<String, Double> getIngredients() {
		return ingredients;
	}
	public String getName() {
		return name;
	}
	public String getDiscription() {
		return discription;
	}
	
	
}
