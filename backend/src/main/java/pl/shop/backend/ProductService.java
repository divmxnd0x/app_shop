package pl.shop.backend;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProducts(int pageNumber, Optional<String> category, Optional<ProductSorter> sorter) {
        PageRequest pageRequest = sorter
                .map(sort -> PageRequest.of(pageNumber, 15).withSort(sort.getSorter()))
                .orElseGet(() -> PageRequest.of(pageNumber, 15));

        return category.map(cat -> productRepository.findAvailableProducts(cat, pageRequest))
                .orElseGet(() -> productRepository.findAvailableProducts(pageRequest));
    }

    public Optional<Product> getProductById(ObjectId objectId) {
        return productRepository.findById(objectId);
    }

}
