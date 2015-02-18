package ca.daverooney.workshop.microtesting;

import java.util.List;

public class ProductReport {

	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("      Dave's Pharamceuticals Product Report");
		System.out.println("==================================================");

		Product product = new Product();
		
		List<Product> allProducts = product.getAllProducts();
		
		for(Product listProduct : allProducts) {
			System.out.print(listProduct.getId());
			System.out.print(": ");
			System.out.println(listProduct.getName());			
		}
		
		System.out.println("==================================================");
		System.out.println("Total Products: " + allProducts.size());
		System.out.println("");

		List<Product> inStockProducts = product.getInStockProducts();
		
		for(Product listProduct : inStockProducts) {
			System.out.print(listProduct.getId());
			System.out.print(": ");
			System.out.println(listProduct.getName());			
		}
		
		System.out.println("==================================================");
		System.out.println("In Stock Products: " + inStockProducts.size());
	}
}
