package com.weverse.sb.product.entity;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.List;

import com.weverse.sb.artist.entity.Artist;

import jakarta.persistence.CascadeType;
=======

import com.weverse.sb.artist.entity.Artist;

>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
=======
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "category_id")
=======
    @JoinColumn(name = "category_id", nullable = false)
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
    private ProductCategory category;

    @Column(name = "product_name", length = 200, nullable = false)
    private String productName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", precision = 15, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "stock_qty")
    private Integer stockQty = 0;
<<<<<<< HEAD
    
    @Column(name = "group_id")
    private Long groupId;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;
=======
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
}
