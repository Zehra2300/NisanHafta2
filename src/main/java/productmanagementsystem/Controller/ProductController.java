package productmanagementsystem.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productmanagementsystem.Entity.Product;
import productmanagementsystem.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public List<Product> getProduct() {
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.noContent().build();})
                .orElse(ResponseEntity.notFound().build());
    }
}