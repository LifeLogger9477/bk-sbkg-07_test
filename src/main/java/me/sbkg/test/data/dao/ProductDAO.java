package me.sbkg.test.data.dao;

import me.sbkg.test.data.entity.Product;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
public interface ProductDAO {

  Product insertProduct(Product product);

  Product selectProduct(Long number);

  Product updateProductName(Long number, String name);

  void deleteProduct(Long number);
}
