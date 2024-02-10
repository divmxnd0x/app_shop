package pl.shop.backend;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ProductListItemDTO>> productsList(@RequestParam int pageNo,
                                                                 @RequestParam(required = false) String category,
                                                                 @RequestParam(required = false) String sort) {
        Optional<ProductSorter> productSorter = Optional.ofNullable(sort).map(ProductSorter::valueOf);

        Page<ProductListItemDTO> page = productService.getProducts(pageNo, Optional.ofNullable(category), productSorter)
                .map(ProductListItemDTO::toDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/product")
    public ResponseEntity<?> product(@RequestParam String id) {
        return productService.getProductById(new ObjectId(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
