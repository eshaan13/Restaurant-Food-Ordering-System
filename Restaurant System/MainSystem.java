
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
class MainSystem
{
    ArrayList<Table> tables = new ArrayList<>();
    ArrayList<Dish> dishes = new ArrayList<>();
    ArrayList<Side> sides = new ArrayList<>();
    ArrayList<Drink> drinks = new ArrayList<>();
    ArrayList<Order> activeOrders = new ArrayList<>();
    private ArrayList<Order> sales = new ArrayList<>();
    ArrayList<Purchase> purchases = new ArrayList<>();
    Map<String, Double> grocery = new HashMap<String, Double>();
    
    
    
    public MainSystem(ArrayList<Table> tables, ArrayList<Dish> dishes, ArrayList<Side> sides, ArrayList<Drink> drinks,
			Map<String, Double> grocery) 
    {
		this.tables = tables;
		this.dishes = dishes;
		this.sides = sides;
		this.drinks = drinks;
		this.grocery = grocery;
	}

	public ArrayList<Order> getSalesRecords()
    {
    	return sales;
    }
    
    public ArrayList<Purchase> getPurchases()
    {
    	return purchases;
    }
    
    
    public void makeAnOrder(int tableNo, Map<String, Integer> dishNames, Map<String, Integer> sideNames, Map<String, Integer> drinkNames, String salesPerson)
    {
    	Map<Dish, Integer> dishesOdered = new HashMap<>();
    	Map<Side, Integer> sidesOrdered = new HashMap<>();
    	Map<Drink, Integer> drinksOrdered = new HashMap<>();
    	
    	
    	//	Dishes ordered
    	
    	for(Entry<String, Integer> entry: dishNames.entrySet())
    	{
    		boolean flag = false;
    		for(Dish dish: dishes)
    		{
    			if(entry.getKey().equals(dish.getName()))
    			{	
    				flag = true;
    				dishesOdered.put(dish, entry.getValue());
    			}
    		}
    		if(!flag)	assert(false);// dish doesn't exist
    	}
    	
    	
    	//	Sides ordered
    	
    	for(Entry<String, Integer> entry: sideNames.entrySet())
    	{
    		boolean flag = false;
    		for(Side side: sides)
    		{
    			if(entry.getKey().equals(side.getName()))
    			{
    				flag = true;
    				sidesOrdered.put(side, entry.getValue());
    			}
    		}
    		if(!flag)	assert(false);// side doesn't exist
    	}
    	
    	
    	
    	// Drinks ordered
    	
    	for(Entry<String, Integer> entry: drinkNames.entrySet())
    	{
    		boolean flag = false;
    		for(Drink drink: drinks)
    		{
    			if(entry.getKey().equals(drink.getName()))
    			{
    				flag = true;
    				drinksOrdered.put(drink, entry.getValue());
    			}
    		}
    		if(!flag)	assert(false);// drink doesn't exist
    	}
    	
    	Table table = tables.get(tableNo);
    	
    	
    	// creating an order
    	
    	Order order = new Order(dishesOdered, sidesOrdered, drinksOrdered, salesPerson, new Date());
    	order.setOrderNo(activeOrders.size() + 1);
    	
    	table.setOrder(order);
    	
    	activeOrders.add(order);
    	updateGrocery(order, true);// updating the grocery
    }
    
    public void updateGrocery(Order order, boolean flag)
    {
    	Map<Dish, Integer> dishesOrdered = order.getDishes();
    	Map<Side, Integer> sidesOrdered = order.getSides();

    	for(Entry<Dish, Integer> dishOrdered: dishesOrdered.entrySet())// dishesOrdered
    	{
    		Map<String, Double> ingredients = dishOrdered.getKey().getIngredients();
    		
    		for(Entry<String, Double> entry: ingredients.entrySet())// ingredients of dishes ordered
    		{
    			Double initialValue = grocery.get(entry.getKey());
    			Double newValue;
    			if(flag)
    			{
    				newValue = initialValue - (entry.getValue() * dishOrdered.getValue());// order is placed => grocery reduced
    				
    				if(newValue < 0)
    					assert(false);// Insufficient grocery
    			}
    			else newValue = initialValue + (entry.getValue() * dishOrdered.getValue());// order is removed => grocery restored
    			
    			grocery.replace(entry.getKey(), initialValue, newValue);// updating the grocery
    		}
    	}
    	
    	for(Entry<Side, Integer> sideOrdered: sidesOrdered.entrySet())// sidesOrdered
    	{
    		Map<String, Double> ingredients = sideOrdered.getKey().getIngredients();
    		
    		for(Entry<String, Double> entry: ingredients.entrySet())// ingredients of sides ordered
    		{
    			double initialValue = grocery.get(entry.getKey());
    			double newValue;
    			if(flag)// if true then order is getting made and false if the order is taken back 
    			{
    				newValue = initialValue - (entry.getValue() * sideOrdered.getValue());// order is placed => grocery reduced
    				
    				if(newValue < 0)
    					assert(false);// Insufficient grocery
    			}
    			else newValue = initialValue + (entry.getValue() * sideOrdered.getValue());// order is removed => grocery restored
    			
    			grocery.replace(entry.getKey(), initialValue, newValue);// updating the grocery
    		}
    	}
    }
    
    public void updateDishInOrder(int tableNo, Map<Dish, Integer> dishes, boolean option)
    {
    	Table table = tables.get(tableNo);
    	Order order = table.getOrder();
    	if(option)
    		order.addDishes(dishes);
    	else order.removeDishes(dishes);
    
    }
    
    public void updateSideInOrder(int tableNo, Map<Side, Integer> sides, boolean option)
    {
    	Table table = tables.get(tableNo);
    	Order order = table.getOrder();
    	if(option)
    		order.addSides(sides);
    	else order.removeSides(sides);
    }
    
    public double payment(int tableNo)
    {
    	if(tableNo > tables.size())
    		assert(false);// Table doesn't exist
    	Table table = tables.get(tableNo);
    	return table.getOrder().calcPrice();
    }
    
    // making the payment and # calling updateSalesRecords() #
    
    public void updateSalesRecords(int tableNo)
    {
    	// After payment has been made
    	
    	Table table = tables.get(tableNo);
    	activeOrders.remove(table.getOrder());
    	
    	sales.add(table.getOrder());
    }
    
    public void addPurchase(Map<String, Double> items, double price)
    {
    	Purchase purchase = new Purchase(items, price, new Date());
    	purchases.add(purchase);
    	
    	// updating grocery
    	
    	for(Map.Entry<String, Double> entry: items.entrySet())
    	{
    		if(!grocery.containsKey(entry.getKey()))
    		{
    			String s = entry.getKey();
    			double v = entry.getValue();
    			grocery.put(s, v);
    		}
    		else
    		{
    			double initialValue = grocery.get(entry.getKey());
    			double newValue = initialValue + entry.getValue();// adding the quantity bought
    			grocery.replace(entry.getKey(), initialValue, newValue);// updating the grocery
    		}
    	}
    	
    }
    
    public double profitOrLoss(int requestedMonth, int requestedYr)
    {
    	double cp = 0.0; 
    	double sp = 0.0;
    	requestedMonth -= 1;
    	requestedYr -= 1900;
    	
    	for(Purchase purchase: purchases)
    		if(purchase.getDate().getMonth() == requestedMonth && purchase.getDate().getYear() == requestedYr)
    			cp += purchase.getPrice();
    	
    	for(Order saleOrder: sales)
    		if(saleOrder.getDate().getMonth() == requestedMonth && saleOrder.getDate().getYear() == requestedYr)
    			sp += saleOrder.getPrice();
    		
    	return (sp - cp); 
    }
}
    