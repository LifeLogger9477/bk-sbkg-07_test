package me.sbkg.test.controller;

import com.google.gson.Gson;
import me.sbkg.test.data.dto.ProductDTO;
import me.sbkg.test.data.dto.ProductResponseDTO;
import me.sbkg.test.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * author : ms.Lee
 * date   : 2024-01-05
 */
@WebMvcTest (ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductServiceImpl productService;

  @Test
  @DisplayName ("MockMvc를 통한 Product 데이터 가져오기 테스트")
  void getProductTest() throws Exception {

    // Given
    given( productService.getProduct( 123L ) )
        .willReturn(
            new ProductResponseDTO( 123L, "pen", 5000, 2000 )
        );

    // When
    String productId = "123";

    // Then
    mockMvc.perform( get( "/product?number=" + productId ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.number" ).exists() )
        .andExpect( jsonPath( "$.name" ).exists() )
        .andExpect( jsonPath( "$.price" ).exists() )
        .andExpect( jsonPath( "$.stock" ).exists() )
        .andDo( print() );

    verify( productService ).getProduct( 123L );
  }

  /**
   * 이걸 따라하니까 계속 에러를 발생했다.
   * java.lang.AssertionError: No value at JSON path "$.number"
   * git source를 봐도 저자는 제대로 되었다고 하던데...
   * 결국 아래 사이트를 보고 따라했는데, test가 성공했다.
   * 그런데 아직 이유는 모른다. 2024-01-05
   * https://velog.io/@hoo5886/%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C-java.lang.AssertionError-No-value-at-JSON-path-.title-%EC%97%90%EB%9F%AC
   */
  @Test
  @DisplayName("Product 데이터 생성 테스트")
  void createProductTest() throws Exception {

    // Mock 객체에서 특정 메소드가 실행되는 경우
    // 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어 줌

    // Given
    given( productService.saveProduct(
        //new ProductDTO("pen", 5000, 2000)
        any()
    ) )
        .willReturn( new ProductResponseDTO(
            12315L,
            "pen",
            5000,
            2000
        ) );

    // When
    ProductDTO productDTO = ProductDTO.builder()
        .name( "pen" )
        .price( 5000 )
        .stock( 2000 )
        .build();

    Gson gson = new Gson();
    String content = gson.toJson( productDTO );

    // Then
    mockMvc.perform(
        post( "/product" )
            .content( content )
            .contentType( MediaType.APPLICATION_JSON )
        )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.number" ).value(12315L) )
        .andExpect( jsonPath( "$.name" ).value( "pen" ) )
        .andExpect( jsonPath( "$.price" ).value( 5000 ) )
        .andExpect( jsonPath( "$.stock" ).value( 2000 ) )
        .andDo( print() );

    verify( productService ).saveProduct(
        // new ProductDTO( "pen", 5000, 2000 )
        refEq( productDTO )
    );
  }
}