package me.sbkg.test.service.impl;

import me.sbkg.test.data.repository.ProductRepository;
import me.sbkg.test.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * author : ms.Lee
 * date   : 2024-01-05
 */
// 의존성을 사용해서 객체 생성 -> Mockito 보다는 느리다.
@ExtendWith (SpringExtension.class)
@Import( ProductServiceImpl.class )
public class ProductServiceTest2 {

  @MockBean
  ProductRepository productRepository;

  @Autowired
  ProductService productService;
}
