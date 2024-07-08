package com.devstack.ecom.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "product_image")
public class ProductImage {
    @Id
    @Column(name="property_id")
    private String propertyId;

    @Lob
    @Column(name="directory", nullable = false)
    private byte[] directory;

    @Lob
    @Column(name="resource_url", nullable = false)
    private byte[] resourceUrl;

    @Lob
    @Column(name="hash", nullable = false)
    private byte[] hash;

    @Lob
    @Column(name="file_name", nullable = false)
    private byte[] fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",
            referencedColumnName = "property_id")
    private Product product;
}
