package com.starter.api.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }

    public ProductResponse findById(Long id) {
        return productRepository.findById(id).map(ProductResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public ProductResponse create(CreateProductRequest request, Long userId) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .createdBy(userId)
                .build();
        return ProductResponse.from(productRepository.save(product));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
