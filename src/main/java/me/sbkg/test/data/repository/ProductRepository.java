package me.sbkg.test.data.repository;

import me.sbkg.test.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
