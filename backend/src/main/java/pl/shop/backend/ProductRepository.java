package pl.shop.backend;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring data JPA repository representation for querying data from MongoDB database
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    @Query("""
            {
                $and: [
                    {availableCount: {$gt: 0}},
                    {category: {$eq: ?0}}
                ]
            }
            """)
    Page<Product> findAvailableProducts(String category, Pageable pageable);

    @Query("{availableCount: {$gt: 0}}")
    Page<Product> findAvailableProducts(Pageable pageable);

    @Query("""
            {
                $and: [
                    {availableCount: {$gt: 0}},
                    {category: {$eq: ?0}}
                ]
            }
            """)
    List<Product> findAvailableProducts(String category);

}
