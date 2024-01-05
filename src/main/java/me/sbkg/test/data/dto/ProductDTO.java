package me.sbkg.test.data.dto;

import lombok.*;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

  private String name;
  private int price;
  private int stock;
}
