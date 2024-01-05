package me.sbkg.test.data.dao.impl;

import me.sbkg.test.data.dao.ProductDAO;
import me.sbkg.test.data.entity.Product;
import me.sbkg.test.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
@Component
public class ProductDAOImpl implements ProductDAO {

  private final ProductRepository productRepository;

  @Autowired
  public ProductDAOImpl(ProductRepository productRepository) {

    this.productRepository = productRepository;
  }

  @Override
  public Product insertProduct(Product product) {

    Product savedProduct = productRepository.save( product );

    return savedProduct;
  }

  @Override
  public Product selectProduct(Long number) {

    // exception 을 준비하였다면 당연히 orElseThrow로 처리하는 것이 바람직
    Product selectedProduct =
        productRepository
            .findById( number )
            .orElse(null);

    return selectedProduct;
  }

  @Override
  public Product updateProductName(Long number, String name) {

    Optional<Product> selectedProduct = productRepository.findById( number );

    Product updatedProduct = null;
    if (selectedProduct.isPresent()) {

      Product product = selectedProduct.get();

      product.setName( name );
      product.setUpdatedAt( LocalDateTime.now() );

      updatedProduct = productRepository.save( product );
    }
    else {

      throw new NoSuchElementException("해당 product가 없음");
    }

    return updatedProduct;
  }

  @Override
  public void deleteProduct(Long number) {

    Optional<Product> selectedProduct = productRepository.findById( number );

    if (selectedProduct.isPresent()) {

      Product product = selectedProduct.get();

      productRepository.delete( product );
    }
    else {

      throw new NoSuchElementException("해당 product가 없음");
    }
  }
}
