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
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price +"}";



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


