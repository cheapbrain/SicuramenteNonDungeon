package MobsManagers;

public class Drop {
	public int id, rate, quantity;
	
	public Drop(int id, int rate, int quantity){
		this.id=id;
		this.rate=rate;
		this.quantity=quantity;
	}
	
	public Drop(int id, int rate){
		this(id, rate, 1);
	}
	
	public Drop(int id){
		this(id, 100, 1);
	}
}
