package me.sbkg.test.service.impl;

import me.sbkg.test.data.dto.ProductDTO;
import me.sbkg.test.data.dto.ProductResponseDTO;
import me.sbkg.test.data.entity.Product;
import me.sbkg.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * author : ms.Lee
 * date   : 2024-01-05
 */
class ProductServiceTest {

  private ProductRepository productRepository =
      Mockito.mock( ProductRepository.class );
  private ProductServiceImpl productService;

  @BeforeEach
  public void setupTest() {

    productService = new ProductServiceImpl( productRepository );
  }

  @Test
  void getProductTest() {

    // Given
    Product givenProduct = new Product();
    givenProduct.setNumber( 123L );
    givenProduct.setName( "펜" );
    givenProduct.setPrice( 1000 );
    givenProduct.setStock( 1234 );

    // When
    Mockito.when( productRepository.findById( 123L ) )
        .thenReturn( Optional.of( givenProduct ) );

    // Then
    ProductResponseDTO productResponseDTO = productService.getProduct( 123L );

    Assertions.assertEquals(
        productResponseDTO.getNumber(), givenProduct.getNumber()
    );
    Assertions.assertEquals(
        productResponseDTO.getName(), givenProduct.getName()
    );
    Assertions.assertEquals(
        productResponseDTO.getPrice(), givenProduct.getPrice()
    );
    Assertions.assertEquals(
        productResponseDTO.getStock(), givenProduct.getStock()
    );

    verify( productRepository ).findById( 123L );
  }

  @Test
  void saveProductTest() {

    // Given
    Mockito.when( productRepository.save( any( Product.class ) ) )
        .then( returnsFirstArg() );

    // When
    ProductResponseDTO productResponseDTO =
        productService.saveProduct(
            new ProductDTO( "펜", 1000, 1234 )
        );

    // Then
    Assertions.assertEquals( productResponseDTO.getName(), "펜" );
    Assertions.assertEquals( productResponseDTO.getPrice(), 1000 );
    Assertions.assertEquals( productResponseDTO.getStock(), 1234 );

    verify( productRepository ).save( any() );
  }
}