package pl.shop.backend;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest api cotroller for products stuff
 */
@RestController
@CrossOrigin
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

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> image(@RequestParam String id) {
        return ResponseEntity.ok(productService.getImage(new ObjectId(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductListItemDTO>> search(@RequestParam String keyWord,
                                                           @RequestParam(required = false) String category) {
        List<ProductListItemDTO> list = productService.findProductsByName(keyWord, Optional.ofNullable(category))
                .stream()
                .map(ProductListItemDTO::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }

}
