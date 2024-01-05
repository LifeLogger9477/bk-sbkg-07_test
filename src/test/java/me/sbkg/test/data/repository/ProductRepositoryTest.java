package me.sbkg.test.data.repository;

import me.sbkg.test.data.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * author : ms.Lee
 * date   : 2024-01-05
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  @DisplayName ("save test")
  void saveTest() {

    // Given
    Product product = new Product();
    product.setName( "펜" );
    product.setPrice( 1000 );
    product.setStock( 1000 );

    // When
    Product savedProduct = productRepository.save( product );

    // Then
    assertEquals( product.getName(), savedProduct.getName() );
    assertEquals( product.getPrice(), savedProduct.getPrice() );
    assertEquals( product.getStock(), savedProduct.getStock() );
  }
}
