package me.sbkg.test.data.repository;

import me.sbkg.test.data.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * author : ms.Lee
 * date   : 2024-01-05
 */
@SpringBootTest
public class ProductRepositoryTest2 {

  @Autowired
  ProductRepository productRepository;

  @Test
  @DisplayName ("CRUD Test")
  void basicCRUDTest() {

    /* create */
    // Given
    Product givenProduct = Product.builder()
        .name( "노트" )
        .price( 1000 )
        .stock( 500 )
        .build();

    // When
    Product savedProduct = productRepository.save( givenProduct );

    // Then
    assertThat( savedProduct.getNumber() )
        .isEqualTo( givenProduct.getNumber() );
    assertThat( savedProduct.getName() )
        .isEqualTo( givenProduct.getName() );
    assertThat( savedProduct.getPrice() )
        .isEqualTo( givenProduct.getPrice() );
    assertThat( savedProduct.getStock() )
        .isEqualTo( givenProduct.getStock() );

    /* read */
    // When
    Product selectedProduct =
        productRepository.findById( savedProduct.getNumber() )
            .orElseThrow( RuntimeException::new );

    // Then
    assertThat( selectedProduct.getNumber() )
        .isEqualTo( givenProduct.getNumber() );
    assertThat( selectedProduct.getName() )
        .isEqualTo( givenProduct.getName() );
    assertThat( selectedProduct.getPrice() )
        .isEqualTo( givenProduct.getPrice() );
    assertThat( selectedProduct.getStock() )
        .isEqualTo( givenProduct.getStock() );

    /* update */
    // when
    Product foundProduct = productRepository.findById( selectedProduct.getNumber() )
        .orElseThrow( RuntimeException::new );

    foundProduct.setName( "장난감" );

    Product updatedProduct = productRepository.save( foundProduct );

    // then
    assertEquals( updatedProduct.getName(), "장난감" );

    /* delete */
    // when
    productRepository.delete( updatedProduct );

    // then
    assertFalse(
        productRepository.findById( selectedProduct.getNumber() )
            .isPresent()
    );
  }
}
