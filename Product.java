package com.capg.springinti.entity;

public class Product {
    private Long id;
    private String name;
    private double price;

    // constructor
    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

}
//kavya
 public Product() {
        super();
    }


///praharshini

@Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price +"}";}
       package com.capg.spring_assignment.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capg.spring_assignment.entity.Chocolates;
import com.capg.spring_assignment.service.IChocolatesService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200/Chocolates")
@Validated
@RestController
@RequestMapping("api/chocolates")
public class ChocolateRestController {
	 @Autowired
	    IChocolatesService service;

	 	@PostMapping("/insertChocolate")
	    public ResponseEntity<Chocolates> addChocolate(@Valid @RequestBody Chocolates choco) {
	        Chocolates saved = service.addChocolate(choco);
	        return new ResponseEntity<>(saved, HttpStatus.CREATED);
	    }
	 	
	 	 @PutMapping("/update/{name}")
		    public ResponseEntity<Chocolates> updateChocolate(@PathVariable String name,
		                                                      @RequestParam double price,
		                                                      @RequestParam int quantity) {
		        Chocolates updated = service.updateChocolate(name, price, quantity);
		        return ResponseEntity.ok(updated);
		    }
	 	 
	 	 @DeleteMapping("/delete-expired")
		    public String deleteExpiredChocolates() {
		        return service.deleteChocolates();
		    }

	 	 @GetMapping("/sort")
		    public List<Chocolates>getBySortedPrice() {
		        return service.getBySortedPrice();
		    }
	 	 
	 	 @GetMapping("/getall")
		    public List<Chocolates> getAll() {
		        return service.getAll();
		    }

		    @GetMapping("/getbyname/{name}")
		    public List<Chocolates> getByName(@PathVariable String name) {
		        return service.getByName(name);
		    }
		
		    @GetMapping("/getbyprice/{price}")
		    public List<Chocolates> getByPrice(@PathVariable double price) {
		        return service.getByPrice(price);
		    }
		
		    @GetMapping("/getbyquantity/{quant}")
		    public List<Chocolates> getByQuantity(@PathVariable int quant) {
		        return service.getByQuantity(quant);
		    }
		    
		    @GetMapping("/expiring-soon")
		    public List<Chocolates> getExpiringChocolates() {
		        return service.getChocolatesExpiringSoon();
		    }
		
		    @GetMapping("/greaterprice/{price}")
		    public List<Chocolates> findByPriceGreaterThan(@PathVariable double price) {
		        return service.findByPriceGreaterThan(price);
		    }
		    @DeleteMapping("/deletequatitynegative")
		    public ResponseEntity<String> deleteNegativeQuantity() {
		        String message = service.deleteNegativeQuantity();
		        return ResponseEntity.ok(message);
		    }


	   

}
 



//sanks

package com.capg.studentcrudapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capg.studentcrudapi.Entity.Address;
import com.capg.studentcrudapi.Entity.Student;
import com.capg.studentcrudapi.Repository.Studentrepository;

@SpringBootApplication
public class Studentprojectapplication {

    public static void main(String[] args) {
        SpringApplication.run(Studentprojectapplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(Studentrepository studentrepository) {
        return args -> {
            Address address = new Address();
            address.setStreet("123 Main St");
            address.setCity("Hyderabad");
            address.setZipcode("500001");

            Student student = new Student();
            student.setName("Sankeerthi");
            student.setAge(24);
            student.setEmail("sankeerthi@example.com");
            student.setAddress(address);

            studentrepository.save(student);
            studentrepository.save(student);

        };
    }
}

//var

//hellow this is my code
public String hello(){
    return "Hello world";
}


//pranav


