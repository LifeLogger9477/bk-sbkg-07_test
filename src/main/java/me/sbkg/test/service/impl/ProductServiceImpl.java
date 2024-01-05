package me.sbkg.test.service.impl;

import me.sbkg.test.data.dto.ProductDTO;
import me.sbkg.test.data.dto.ProductResponseDTO;
import me.sbkg.test.data.entity.Product;
import me.sbkg.test.data.repository.ProductRepository;
import me.sbkg.test.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger =
      LoggerFactory.getLogger( ProductServiceImpl.class );
  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {

    this.productRepository = productRepository;
  }

  @Override
  public ProductResponseDTO getProduct(Long number) {

    logger.info( "[getProduct] input number : {}", number );
    Product product = productRepository.findById( number ).get();

    logger.info(
        "[getProduct] product number : {}, name : {}",
        product.getNumber(),
        product.getName()
    );
    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
    productResponseDTO.setNumber( product.getNumber() );
    productResponseDTO.setName( product.getName() );
    productResponseDTO.setPrice( product.getPrice() );
    productResponseDTO.setStock( product.getStock() );

    return productResponseDTO;
  }

  @Override
  public ProductResponseDTO saveProduct(ProductDTO prodcutDTO) {

    logger.info( "[saveProduct] productDTO : {}", prodcutDTO.toString() );
    Product product = new Product();
    product.setName( prodcutDTO.getName() );
    product.setPrice( prodcutDTO.getPrice() );
    product.setStock( prodcutDTO.getStock() );

    Product savedProduct = productRepository.save( product );
    logger.info( "[saveProduct] saveProduct : []", savedProduct );

    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
    productResponseDTO.setNumber( savedProduct.getNumber() );
    productResponseDTO.setName( savedProduct.getName() );
    productResponseDTO.setPrice( savedProduct.getPrice() );
    productResponseDTO.setStock( savedProduct.getStock() );

    return productResponseDTO;
  }

  @Override
  public ProductResponseDTO changeProductName(Long number, String name) {

    Product foundProduct = productRepository.findById( number ).get();
    foundProduct.setName( name );
    Product changeProduct = productRepository.save( foundProduct );

    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
    productResponseDTO.setNumber( changeProduct.getNumber() );
    productResponseDTO.setName( changeProduct.getName() );
    productResponseDTO.setPrice( changeProduct.getPrice() );
    productResponseDTO.setStock( changeProduct.getStock() );

    return productResponseDTO;
  }

  @Override
  public void deleteProduct(Long number) {

    productRepository.deleteById( number );
  }
}
