package com.backend.lavender.controllers;

import com.backend.lavender.models.Customer;
import com.backend.lavender.models.ResponseObject;
import com.backend.lavender.models.CustomerAccount;
import com.backend.lavender.models.modelsDto.RegisterDto;
import com.backend.lavender.repositories.CustomerRepository;
import com.backend.lavender.repositories.CustomerAccountRepository;
import com.backend.lavender.services.SendMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/Register")
public class RegisterController {
    // DI = Dependency Injection
    @Autowired
    private CustomerRepository CustomerRepository;
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private SendMailService sendMailService;

    // @GetMapping("")
    // //this request is: http://localhost:8080/api/v1/Products
    // List<Customer> getAllProducts() {
    // return repository.findAll();
    // }
    // Get detail product
    // @GetMapping("/{id}")
    // //Let's return an object with: data, message, status
    // ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
    // Optional<Customer> foundProduct = repository.findById(id);
    // return foundProduct.isPresent() ?
    // ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("ok", "Query product successfully", foundProduct)
    // //you can replace "ok" with your defined "error code"
    // ):
    // ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    // new ResponseObject("failed", "Cannot find product with id = "+id, "")
    // );
    // }
    // //insert new Product with POST method
    // //Postman : Raw, JSON
    @PostMapping("/register")
    ResponseEntity<ResponseObject> Register(@RequestBody RegisterDto registerDto) {

        Customer newCustomer = new Customer();
        CustomerAccount newCusAcc = new CustomerAccount();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();


        newCustomer.setName(registerDto.getName());
        newCustomer.setEmail(registerDto.getEmail());
        newCustomer.setPhoneNumber(registerDto.getPhoneNumber());
        newCustomer.setDob(registerDto.getDob());

        List<Customer> foundCustomer = CustomerRepository.findByEmail(newCustomer.getEmail().trim());
        if (foundCustomer.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "email taken", ""));
        }

        foundCustomer = CustomerRepository.findByPhoneNumber(newCustomer.getPhoneNumber());
        if (foundCustomer.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "phone number taken", ""));
        }

        Customer savedCustomer = CustomerRepository.save(newCustomer);

        
        newCusAcc.setUsername(registerDto.getUsername());
        String encodedPassword=encoder.encode(registerDto.getPassword());
        newCusAcc.setPassword(encodedPassword);
        newCusAcc.setCustomer(savedCustomer);

        List<CustomerAccount> foundTkkh= customerAccountRepository.findByUsername(newCusAcc.getUsername().trim());
        if (foundTkkh.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "username taken", ""));
        }

        CustomerAccount savedCusAcc=customerAccountRepository.save(newCusAcc);

        String htmlemail = "Bạn đã đăng ký tài khoản trên Lavender";
        
        sendMailService.sendSimpleMessage(newCustomer.getEmail(), "xac nhan tai khoan", htmlemail);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert customer successfully", savedCustomer.toString().concat(savedCusAcc.toString())));
    
        }
    // update, upsert = update if found, otherwise insert
    // @PutMapping("/{id}")
    // ResponseEntity<ResponseObject> updateProduct(@RequestBody Customer
    // newProduct, @PathVariable Long id) {
    // Customer updatedProduct = repository.findById(id)
    // .map(product -> {
    // product.setProductName(newProduct.getProductName());
    // product.setYear(newProduct.getYear());
    // product.setPrice(newProduct.getPrice());
    // return repository.save(product);
    // }).orElseGet(() -> {
    // newProduct.setId(id);
    // return repository.save(newProduct);
    // });
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("ok", "Update Product successfully", updatedProduct)
    // );
    // }
    // //Delete a Product => DELETE method
    // @DeleteMapping("/{id}")
    // ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
    // boolean exists = repository.existsById(id);
    // if(exists) {
    // repository.deleteById(id);
    // return ResponseEntity.status(HttpStatus.OK).body(
    // new ResponseObject("ok", "Delete product successfully", "")
    // );
    // }
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    // new ResponseObject("failed", "Cannot find product to delete", "")
    // );
    // }
}
