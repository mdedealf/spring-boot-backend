package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.repository;

import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
