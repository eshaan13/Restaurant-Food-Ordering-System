
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Purchase {
	
	public Map<String, Double> items = new HashMap<String, Double>();
	double price;
	Date date;
	
	public Purchase(Map<String, Double> items1, double price, Date date) {
		this.items = items1;
		this.price = price;
		this.date = date;
	}
	
	public Map<String, Double> getItem() {
		return items;
	}

	public double getPrice() {
		return price;
	}

	public Date getDate() {
		return date;
	}

}
