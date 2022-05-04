package com.backend.lavender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.backend.lavender.models.CustomerAccount;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
  List<CustomerAccount> findByUsername(String username);

  @Query(value="SELECT distinct password FROM customer_account  where username=?1",nativeQuery = true)
  String findPassword(String username);
}
