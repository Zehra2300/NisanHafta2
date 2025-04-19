package librarymanagementsystem1.Controller;
import librarymanagementsystem1.Entity.Product;
import librarymanagementsystem1.Repository.BooksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BooksRepository booksRepository;
    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    @GetMapping
    public List<Product> getBooks() {
        return booksRepository.findAll();
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Product> getBookById(@PathVariable int id) {
        Optional<Product> book=booksRepository.findById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return booksRepository.save(product);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        return booksRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());
                    return ResponseEntity.ok(booksRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        return booksRepository.findById(id)
                .map(product -> {
                    booksRepository.delete(product);
                    return ResponseEntity.noContent().build();})
                .orElse(ResponseEntity.notFound().build());
    }
}
