 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
public class Order {
	
	int orderNo;
	Map<Dish, Integer> dishes = new HashMap<>();
	Map<Side, Integer> sides = new HashMap<>();
	Map<Drink, Integer> drinks = new HashMap<>();
	double price;
	Date date;
	String salesPerson;

	public Order(Map<Dish, Integer> dishes, Map<Side, Integer> sides, Map<Drink, Integer> drinks, String salesPerson, Date date) {
		this.dishes = dishes;
		this.sides = sides;
		this.drinks = drinks;
		this.salesPerson = salesPerson;
		this.date = date;
	}

	public Map<Dish, Integer> getDishes() {
		return dishes;
	}

	public Map<Side, Integer> getSides() {
		return sides;
	}
	
	public Map<Drink, Integer> getDrinks() {
		return drinks;
	}

	public String getSalesPerson() {
		return salesPerson;
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Date getDate() {
		return date;
	}
	
	public double getPrice()
	{
		return price;
	}

	public void addDishes(Map<Dish, Integer> dishes)
	{
		for(Entry<Dish, Integer> entry: dishes.entrySet())
		{
			if(this.dishes.containsKey(entry.getKey()))
			{
				int initialValue = this.dishes.get(entry.getKey());
				this.dishes.replace(entry.getKey(), initialValue, (initialValue + entry.getValue()));
			}
			else this.dishes.put(entry.getKey(), entry.getValue());
		}
	}
	
	public void removeDishes(Map<Dish, Integer> dishes)
	{
		for(Entry<Dish, Integer> entry: dishes.entrySet())
		{	
			if(!this.dishes.containsKey(entry.getKey()))
				assert(false);// this dish is not ordered
			
			int oldValue = this.dishes.get(entry.getKey());
			int newValue = oldValue - entry.getValue();
			if(newValue < 1)
				this.dishes.remove(entry.getKey());
			else this.dishes.replace(entry.getKey(), oldValue, (oldValue - entry.getValue()));
		}
	}
	
	public void addSides(Map<Side, Integer> sides)
	{
		for(Entry<Side, Integer> entry: sides.entrySet())
		{
			if(this.sides.containsKey(entry.getKey()))
			{
				int oldValue = this.sides.get(entry.getKey());
				this.sides.replace(entry.getKey(), oldValue, (oldValue + entry.getValue()));
			}
			else this.sides.put(entry.getKey(), entry.getValue());
		}
	}
	
	public void removeSides(Map<Side, Integer> sides)
	{
		for(Entry<Side, Integer> entry: sides.entrySet())
		{
			if(!this.sides.containsKey(entry.getKey()))
				assert(false);// this side is not ordered
			
			int oldValue = this.sides.get(entry.getKey());
			int newValue = oldValue - entry.getValue();
			if(newValue < 1)
				this.sides.remove(entry.getKey());
			else this.sides.replace(entry.getKey(), oldValue, newValue);
		}
	}
	
	public double calcPrice()
	{
		for(Entry<Dish, Integer> entry: dishes.entrySet())
			price += entry.getKey().getPrice() * entry.getValue();
		
		for(Entry<Side, Integer> entry: sides.entrySet())
			price += entry.getKey().getPrice() * entry.getValue();
		
		for(Entry<Drink, Integer> entry: drinks.entrySet())
			price += entry.getKey().getPrice() * entry.getValue();
		
		return price;
	}

}
