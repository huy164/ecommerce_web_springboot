package com.backend.lavender.controllers;

import java.util.List;

import com.backend.lavender.models.ResponseObject;
import com.backend.lavender.models.CustomerAccount;
import com.backend.lavender.repositories.CustomerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping(path = "/api/v1/login")
public class LoginController {
    
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @PostMapping("/user-login")
    ResponseEntity<ResponseObject> Register(@RequestBody CustomerAccount tkkh) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = "";
        List<CustomerAccount> foundUsername;

        foundUsername = customerAccountRepository.findByUsername(tkkh.getUsername());
        if (foundUsername.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "user account does not exist", ""));
        }

        encodedPassword = customerAccountRepository.findPasswordByUsername(tkkh.getUsername());

        if (encoder.matches(tkkh.getPassword(), encodedPassword) == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "wrong password", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "login successfully", ""));

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
