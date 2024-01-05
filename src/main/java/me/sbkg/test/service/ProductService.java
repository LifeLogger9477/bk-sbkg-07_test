package me.sbkg.test.service;

import me.sbkg.test.data.dto.ProductDTO;
import me.sbkg.test.data.dto.ProductResponseDTO;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
public interface ProductService {

  ProductResponseDTO getProduct(Long number);

  ProductResponseDTO saveProduct(ProductDTO prodcutDTO);

  ProductResponseDTO changeProductName(Long number, String name);

  void deleteProduct(Long number);
}
