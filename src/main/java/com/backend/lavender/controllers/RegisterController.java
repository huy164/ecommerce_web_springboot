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
import java.util.regex.Pattern;

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

    @PostMapping("/register")
    ResponseEntity<ResponseObject> Register(@RequestBody RegisterDto registerDto) {

        Customer newCustomer = new Customer();
        CustomerAccount newCusAcc = new CustomerAccount();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        //validate
        //**REGEX */
        Pattern pattern = Pattern.compile("@gmail.com",Pattern.CASE_INSENSITIVE);
        if(!registerDto.getEmail().matches("^[-a-zA-Z0-9._]+")&& !pattern.matcher(registerDto.getEmail()).find()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                new ResponseObject("failed","invalid email format", registerDto));
        }

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
        String encodedPassword = encoder.encode(registerDto.getPassword());
        newCusAcc.setPassword(encodedPassword);
        newCusAcc.setCustomer(savedCustomer);


        List<CustomerAccount> foundTkkh = customerAccountRepository.findByUsername(newCusAcc.getUsername().trim());
        if (foundTkkh.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "username taken", ""));
        }

        CustomerAccount savedCustomerAccount = customerAccountRepository.save(newCusAcc);

        String htmlemail = "Bạn đã đăng ký tài khoản trên Lavender";

        sendMailService.sendSimpleMessage(newCustomer.getEmail(), "xac nhan tai khoan", htmlemail);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert customer successfully",
                        savedCustomer.toString().concat(savedCustomerAccount.toString())));

    }

}
