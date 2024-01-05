package me.sbkg.test.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private String name;
  private int price;
  private int stock;
}
