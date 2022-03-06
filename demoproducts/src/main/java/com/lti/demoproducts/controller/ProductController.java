package com.lti.demoproducts.controller;



import org.springframework.web.bind.annotation.DeleteMapping;

//@GetMapping is a composed annotation that acts as a 
//shortcut for @RequestMapping(method = RequestMethod.GET).

//RequestMapping can be used at class level:

//Postman - u need to download desktop agent 
//run that setup 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RestController
@RequestMapping("/dmart" )  
public class ProductController 
{
	@GetMapping("/hello")
	public String hello()
	{
		
		return "hello";
	}
		
	private ProductDAO dao= new ProductDAO();
	
	
	//http://localhost:8080/dmart/products
	
	@GetMapping(path="/products", produces="application/xml")// produces="application/json"
	public Product[] findAllProducts()	{		
		return dao.getAll();		
	}
	
	//same can be achived using @Produces(MediaType.APPLICATION_JSON)
	
	
	//Content negotiation if you change it to produces="application/xml"
	//	if you get No converter for [class [Lcom.lti.demoproducts.controller.Product;] with preset Content-Type 'null']
	//add dependency com.fasterxml.jackson.dataformat
	/*
	 * <dependency> <groupId>com.fasterxml.jackson.dataformat</groupId>
	 * <artifactId>jackson-dataformat-xml</artifactId> <version>2.12.3</version>
	 * </dependency>
	 */
	
	
	//http://localhost:8080/dmart/product/101	
	//@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	
	@GetMapping(path="/products/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Product getProductDetails(@PathVariable(value="id") int prdId)
	{
		return dao.findById(prdId);
	}
	//http://localhost:8086/demoproducts/dmart/newproduct 
	@PostMapping("/newproduct" )
	public void  createProduct(@RequestBody Product p) {
		dao.consumeJSONRequest(p);
	}	
	//- select POST method from postman 
		/*{
		"name":"Pen",
		"description":"Reynold",
		"price":200
		}*/
	
	//http://localhost:8086/demoproducts/dmart/updateCost/101 and send new product details 
	
	@PutMapping("updateCost/{prdId}")
	 public Product updateProduct(@PathVariable int prdId, @RequestBody Product p)
	 {
	 return dao.updateProduct(prdId,p);
	 }
	
	//http://localhost:8086/demoproducts/dmart/deleteProduct/101
	@DeleteMapping("deleteProduct/{prdId}")
	public String deleteProduct(@PathVariable int prdId)
	{
		return dao.deleteProduct(prdId);
	}
	
	
}




/*
 * //http://localhost:8086/demoproducts/hello
	@GetMapping("/hello")
	//@RequestMapping("/hello")
	public String hello()
	{
		return "Hello Welcome to First Rest Demo";
	}
	
	private ProductDAO dao = new ProductDAO();
					
	//http://localhost:8086/demoproducts/products
	
	//@GetMapping("/products")	
	@RequestMapping(path="/products", produces = "application/json")
	@ResponseBody 
	public Product[] findAll() {
		return dao.getAll();
	}	
	
	//   http://localhost:8086/demoproducts/products/by?id=101
	@RequestMapping("/product/{id}")
	public Product getEmpDetails(@PathVariable(value = "id") int id) {
		return dao.findById(id);
	}
		*/


/*
1. @Controller. This annotation is used to make a class as a web controller, 
				which can handle client requests and send a response back to the client. ...			
2. @RequestMapping. ...			
3. @RequestParam. ...			
4. @PathVariable. ...			
5. @RequestBody. ...			
6. @ResponseBody. ...			
7. @RestController. ...			
8. @SprinbBootApplication.			
*/