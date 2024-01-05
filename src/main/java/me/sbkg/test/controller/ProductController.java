package me.sbkg.test.controller;

import me.sbkg.test.data.dto.ChangeProductNameDTO;
import me.sbkg.test.data.dto.ProductDTO;
import me.sbkg.test.data.dto.ProductResponseDTO;
import me.sbkg.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
@RestController
@RequestMapping (value = "/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {

    this.productService = productService;
  }

  @GetMapping ()
  public ResponseEntity<ProductResponseDTO> getProduct(Long number) {

    ProductResponseDTO productResponseDTO = productService.getProduct( number );

    return ResponseEntity
        .status( HttpStatus.OK )
        .body( productResponseDTO );
  }

  @PostMapping ()
  public ResponseEntity<ProductResponseDTO> createProduct(
      @RequestBody ProductDTO productDTO
  ) {

    ProductResponseDTO productResponseDTO =
        productService.saveProduct( productDTO );

    return ResponseEntity
        .status( HttpStatus.OK )
        .body( productResponseDTO );
  }

  @PutMapping()
  public ResponseEntity<ProductResponseDTO> changeProductName(
      @RequestBody ChangeProductNameDTO changeProductNameDTO
  ) {

    ProductResponseDTO productResponseDTO =
        productService.changeProductName(
            changeProductNameDTO.getNumber(),
            changeProductNameDTO.getName()
        );

    return ResponseEntity
        .status( HttpStatus.OK )
        .body( productResponseDTO );
  }

  @DeleteMapping ()
  public ResponseEntity<String> deleteProduct(Long number) {

    productService.deleteProduct( number );

    return ResponseEntity
        .status( HttpStatus.OK )
        .body( number + " 번이 정상적으로 삭제되었습니다." );
  }
}
