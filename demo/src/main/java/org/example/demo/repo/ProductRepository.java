package org.example.demo.repo;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.example.demo.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Products, Long> {

  List<Products> findByUserId(Long userId);

  @Query(value = "SELECT * FROM products WHERE user_id = ?1 AND active = ?2", nativeQuery = true)
  List<Products> findByUserAndActive(Long userId, Boolean active);


  @Query(value = "SELECT * FROM products WHERE category_id = ?1 AND active = ?2", nativeQuery = true)
  List<Products> findByCategoryIdAndActive(Long categoryId, boolean active);

  Optional<Products> findById(Long id);

  List<Products> findByUserIdAndActive(@NonNull Long id, boolean b);
}
