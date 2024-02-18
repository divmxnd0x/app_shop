package pl.shop.backend;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final GridFsTemplate fsTemplate;
    @Autowired
    public ProductService(ProductRepository productRepository, GridFsTemplate fsTemplate) {
        this.productRepository = productRepository;
        this.fsTemplate = fsTemplate;
    }

    public Page<Product> getProducts(int pageNumber, Optional<String> category, Optional<ProductSorter> sorter) {
        PageRequest pageRequest = sorter
                .map(sort -> PageRequest.of(pageNumber, 8).withSort(sort.getSorter()))
                .orElseGet(() -> PageRequest.of(pageNumber, 8));

        return category.map(cat -> productRepository.findAvailableProducts(cat, pageRequest))
                .orElseGet(() -> productRepository.findAvailableProducts(pageRequest));
    }

    public Optional<Product> getProductById(ObjectId objectId) {
        return productRepository.findById(objectId);
    }

    public GridFsResource getImage(ObjectId productId) {
        GridFSFile file = fsTemplate.findOne(Query.query(Criteria.where("product_id").is(productId)));
        if (file == null) {
            return GridFsResource.absent("");
        }
        return fsTemplate.getResource(file);
    }

    public List<Product> findProductsByName(String keyWord, Optional<String> category) {
        return category.map(productRepository::findAvailableProducts)
                .orElseGet(productRepository::findAll)
                .stream()
                .filter(product -> product.getAvailableCount() > 0)
                .filter(product -> product.getName().toLowerCase().contains(keyWord.toLowerCase()))
                .limit(25)
                .toList();
    }

}
