
public class Table {
	
	int tableNo;
	Order order;

	public Table(int tableNo)
	{
		this.tableNo = tableNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
