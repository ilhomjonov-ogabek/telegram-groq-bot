package org.example.demo.repo;

import java.util.List;
import java.util.Optional;
import org.example.demo.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Products, Long> {

  List<Products> findByUserId(Long userId);

  @Query(value = "SELECT * FROM products WHERE user_id = ?1 AND active = ?2", nativeQuery = true)
  List<Products> findByUserAndActive(Long userId, Boolean active);

  List<Products> findByCategoryId(Long categoryId);

  Optional<Products> findById(Long id);
}
