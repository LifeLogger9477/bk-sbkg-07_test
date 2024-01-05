package me.sbkg.test.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * author : ms.Lee
 * date   : 2024-01-04
 */
@Entity
@Table (name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "name")
public class Product {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long number;

  @Column (nullable = false)
  private String name;

  @Column (nullable = false)
  private Integer price;

  @Column (nullable = false)
  private Integer stock;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
