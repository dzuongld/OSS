import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyModel implements Model {

	ArrayList<Product> productList = new ArrayList<Product>();
	HashMap<String, String> passwords = new HashMap<>();
	HashMap<String, Customer> details = new HashMap<>();
	
	public DummyModel(){
		
		passwords.put("admin", "admin");
		details.put("admin", new Customer("john", "900 dandenong rd", "123", "456"));

		for(int i = 0; i < 3; i++){
			Product birb = new Product("Item #"+i);
			birb.setProperty("price", "Price ($)", 100f);
			productList.add(birb);
		}
	}
	
	public List<Product> getProducts() {
		return productList;
	}

	public boolean login(String username, String password) {
		if(passwords.containsKey(username)){
			return passwords.get(username).equals(password);
		}
		return false;
	}

	public boolean signup(String username, String password) {
		if(passwords.containsKey(username)) return false;
		passwords.put(username, password);
		details.put(username, new Customer(username, "", "", ""));
		return true;
	}

	public Customer getUserInfo(String username) {
		return details.get(username);
	}

	public boolean setUserInfo(String username, Customer info) {
		details.put(username, info);
		return true;
	}

	public float getPrice(Cart cart) {
		float total = 0;
		for(CartItem item : cart.getList()) if(item.product.hasProperty("price")) total += ((float) item.product.getPropertyValue("price")) * item.quantity;
		return total;
	}

	public boolean processOrder(String currentUserID, Cart cart) {
		return true;
	}

}
