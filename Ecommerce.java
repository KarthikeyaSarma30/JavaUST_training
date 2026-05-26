import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.*;
import java.util.List;
import java.util.Optional;

public class Ecommerce {
	int productId;
	String name;
	int price;
	boolean inStock;
	double  discountPercentage;
	
    public Ecommerce(int productId, String name, int price, boolean inStock, double discountPercentage) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.inStock = inStock;
		this.discountPercentage = discountPercentage;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	@Override
	public String toString() {
	    return "Ecommerce [id=" + productId + ", name=" + name + ", price=" + price + 
	           ", status=" + inStock + ", discountPercentage=" + discountPercentage + "]";
	}
	public static void main(String args[])
	{
		List<Ecommerce>  pros= Arrays.asList(
				new Ecommerce(111,"Iphone",50000,true,10.5),
				new Ecommerce(222,"ear phones",3000,true,5.0),
				new Ecommerce(333,"bat",13000,true,14.0),
				new Ecommerce(444,"ball",4000,true,12.0));
		
		//remove out of stock products
		List<Ecommerce> res1= pros.stream()
				.filter(e->e.isInStock())
				.collect(Collectors.toList());
		res1.forEach(System.out::println);
		
		//apply discounts in products
		List<Ecommerce> res2 = pros.stream()
				.map(e->{
					double da=e.getPrice()*(e.discountPercentage*0.01);
					int dp=e.getPrice() - (int)da;
					e.setPrice(dp);
					return e;
				})
				.collect(Collectors.toList());
		res2.forEach(System.out::println);
		
		//calculate cart ammount
		
		int Cartamount=pros.stream()
				.filter(e->e.isInStock())
				.mapToInt(Ecommerce::getPrice)
				.sum();
		System.out.print(Cartamount);
		
		//expensive product
		
		Optional<Ecommerce> res3= pros.stream()
				.max(Comparator.comparing(e->e.getPrice()));
					
		if(res3.isPresent())
		{
			System.out.print(res3.get());
		}
		else {
			System.out.print("No product found!");
		}
		//sort
		List<Ecommerce> sortedByPriceLow = pros.stream()
		        .sorted(Comparator.comparingInt(Ecommerce::getPrice))
		        .collect(Collectors.toList());

		System.out.println("--- Products Sorted by Price (Low to High) ---");
		sortedByPriceLow.forEach(System.out::println);
		
		//optional nullable
		
		System.out.println("--- Product Descriptions (Handling Nulls) ---");
		pros.stream().forEach(p -> {
		    // Wrap the description in an Optional container
		    String safeDescription = Optional.ofNullable(p.getName())
		                                     .orElse("No description available.");
		    
		    System.out.println(p.getName() + ": " + safeDescription);
		});
	}
	
}
