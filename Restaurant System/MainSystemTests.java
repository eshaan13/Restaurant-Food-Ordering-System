
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MainSystemTests {
	
	public ArrayList<Table> tables = new ArrayList<>();
	public ArrayList<Dish> dishes = new ArrayList<>();
	public ArrayList<Side> sides = new ArrayList<>();
	public ArrayList<Drink> drinks = new ArrayList<>();
	public HashMap<String, Double> grocery = new HashMap<>();
	MainSystem ms;
	
	
	public void setUp()
	{
		
		//	TABLES
		
		Table table1 = new Table(1);
		Table table2 = new Table(2);
		Table table3 = new Table(3);
		Table table4 = new Table(4);
		Table table5 = new Table(5);
		
		tables.add(table1);
		tables.add(table2);
		tables.add(table3);
		tables.add(table4);
		tables.add(table5);
		
		//	INGREDIENTS
		
		Map<String, Double> ingredients1 = new HashMap<>();
		ingredients1.put("Cottage cheese", 0.5);
		ingredients1.put("Cashew", 0.1);
		ingredients1.put("Tomatoes", 0.2);
		ingredients1.put("Ghee", 0.1);
		ingredients1.put("Onion", 0.2);
		
		Map<String, Double> ingredients2 = new HashMap<>();
		ingredients2.put("Makhani Grains", 0.1);
		ingredients2.put("Tomatoes", 0.2);
		ingredients2.put("Onion", 0.2);
		ingredients2.put("Ghee", 0.1);
		
		Map<String, Double> ingredients3 = new HashMap<>();
		ingredients3.put("Dough", 0.1);
		
		
		//	DISHES
		
		Dish dish1 = new Dish("Shahi Panner", "Cottage cheese dipped in gently prepared cashew curry.", 500, ingredients1);
		Dish dish2 = new Dish("Dal Makhani", "Curry cooked for more than 24 hrs in a traditional Indian style.", 450, ingredients2);
		
		dishes.add(dish1);
		dishes.add(dish2);
		
		//	SIDES
		
		Side side1 = new Side("Garlic naan", "Garlic bread cooked in tandoor.", 100, ingredients3);
		Side side2 = new Side("Tandori roti", "Plain bread cooked in tandoor.", 80, ingredients3);
		Side side3 = new Side("Roti", "Plain bread cooked on a pan", 60, ingredients3);
		
		sides.add(side1);
		sides.add(side2);
		sides.add(side3);
		
		//	DRINKS
		
		Drink drink1 = new Drink("Virgin Mojito", "Lemonade prepared with crushed mint and ice", 250);
		Drink drink2 = new Drink("Lemonade", "Lemon water with crushed ice and sweet cyrup", 200);
		Drink drink3 = new Drink("Water", "Mineral water", 50);
		
		drinks.add(drink1);
		drinks.add(drink2);
		drinks.add(drink3);
		
		//	GROCERY
		
		grocery.put("Cottage cheese", 2.0);
		grocery.put("Cashew", 1.0);
		grocery.put("Tomatoes", 3.0);
		grocery.put("Ghee", 5.0);
		grocery.put("Onion", 3.0);
		grocery.put("Makhani Grains", 1.0);
		grocery.put("Dough", 5.0);
		
		ms = new MainSystem(tables, dishes, sides, drinks, grocery);
	}
	
//	@Test
//	void testAddPurchase() 
//	{
//		System.out.println("Inside testAddPurchase()");
//		
//		setUp();
//		Map<String, Double> items1 = new HashMap<>();
//		Map<String, Double> items2 = new HashMap<>();
//		items1.put("Potatoes", 3.0);
//		items2.put("Tomatoes", 3.0);
//		ms.addPurchase(items1, 2000.0);
//		ms.addPurchase(items2, 1800.0);
//		ArrayList<Purchase> actualPurchases = ms.getPurchases();
//		assertEquals(2, actualPurchases.size());
//	}
//	
//	@Test
//	void testMakeAnOrder()
//	{
//		System.out.println("Inside testMakeAnOrder()");
//
//		setUp();
//		
//		// making a purchase
//	
//	Map<String, Double> items1 = new HashMap<>();
//	Map<String, Double> items2 = new HashMap<>();
//	
//	items1.put("Potatoes", 3.0);
//	items2.put("Tomatoes", 3.0);
//	
//	ms.addPurchase(items1, 2000.0);
//	ms.addPurchase(items2, 1800.0);
//	
//	assertEquals(ms.purchases.size(), 2);
//	
//	}
	
	@Test
	void testUpdateDishOrSidesInOrder()
	{
		//System.out.println("Inside testUpdateDishInOrder()");
		
		setUp();
		
		// making a purchase
		
		Map<String, Double> items1 = new HashMap<>();
		Map<String, Double> items2 = new HashMap<>();
		
		items1.put("Potatoes", 3.0);
		items2.put("Tomatoes", 3.0);
		
		ms.addPurchase(items1, 2000.0);
		ms.addPurchase(items2, 1800.0);
		
		assertEquals(ms.purchases.size(), 2);
		
		// Making an order
		
		Map<String, Integer> dishNames = new HashMap<>();
		Map<String, Integer> sideNames = new HashMap<>();
		Map<String, Integer> drinkNames = new HashMap<>();
		
		dishNames.put("Shahi Panner", 1);
		dishNames.put("Dal Makhani", 1);
		
		sideNames.put("Garlic naan", 1);
		sideNames.put("Roti", 1);
		
		
		drinkNames.put("Lemonade", 1);
		
		String salesPerson = "eshaan";
		
		ms.makeAnOrder(3, dishNames, sideNames, drinkNames, salesPerson);
		
		// Removing dishes and slides
		
		Map<Dish, Integer> dishesToBeRemoved = new HashMap<>();
		Map<Side, Integer> sidesToBeRemoved = new HashMap<>();
		
		
		dishesToBeRemoved.put(ms.dishes.get(1), 1);
		sidesToBeRemoved.put(ms.sides.get(2), 1);
		
		assertEquals(ms.tables.get(3).getOrder().getDishes().size(), 2);
		ms.updateDishInOrder(3,dishesToBeRemoved , false);
		assertEquals(ms.tables.get(3).getOrder().getDishes().size(), 1);
		
		assertEquals(ms.tables.get(3).getOrder().getSides().size(), 2);
		ms.updateSideInOrder(3, sidesToBeRemoved, false);
		assertEquals(ms.tables.get(3).getOrder().getSides().size(), 1);
		
		
		// Adding dishes and slides
		
		Map<Side, Integer> sidesToAdd = new HashMap<>();
		Map<Dish, Integer> dishesToAdd = new HashMap<>();
	
		dishesToAdd.put(dishes.get(1), 2);
		sidesToAdd.put(sides.get(1), 2);
		
		assertEquals(ms.tables.get(3).getOrder().getSides().size(), 1);
		ms.updateSideInOrder(3, sidesToAdd, true);
		assertEquals(ms.tables.get(3).getOrder().getSides().size(), 2);
	
		assertEquals(ms.tables.get(3).getOrder().getDishes().size(), 1);
		ms.updateDishInOrder(3, dishesToAdd, true);
		assertEquals(ms.tables.get(3).getOrder().getDishes().size(), 2);
		
		// Making the payment
		
		assertEquals(1860.0, ms.payment(3));
		
		// Updating the sales records
		
		ms.updateSalesRecords(3);
		assertEquals(1, ms.getSalesRecords().size());
		
		// Checking profitAndLoss
		
		assertEquals(-1940, ms.profitOrLoss(1, 2020));
	}
	
//	@Test
//	void testProfitOrLoss()
//	{
//		System.out.println("inisde testProfitOrLoss()");
//		
//		setUp();
//		
//		// making a purchase
//		Map<String, Double> items1 = new HashMap<>();
//		Map<String, Double> items2 = new HashMap<>();
//		items1.put("Potatoes", 3.0);
//		items2.put("Tomatoes", 3.0);
//		ms.addPurchase(items1, 2000.0);
//		ms.addPurchase(items2, 1800.0);
//		
//		// making an order
//		ArrayList<String> dishNames = new ArrayList<>();
//		dishNames.add("Shahi Panner");
//		ArrayList<String> sideNames = new ArrayList<>();
//		sideNames.add("Garlic naan");
//		ArrayList<String> drinkNames = new ArrayList<>();
//		drinkNames.add("Lemonade");
//		String salesPerson = "eshaan";
//		ms.makeAnOrder(3, dishNames, sideNames, drinkNames, salesPerson);
//		
//		// updating sales records
//		ms.updateSalesRecords(3);
//	
//		assertEquals(-3000.0, ms.profitOrLoss(1, 2020));
//	}

}
