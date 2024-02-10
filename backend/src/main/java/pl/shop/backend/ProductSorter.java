package pl.shop.backend;

import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@Getter
public enum ProductSorter {
    BY_NAME_A_Z(Sort.by(new Order(Direction.ASC, "name", Sort.NullHandling.NULLS_LAST))),
    BY_NAME_Z_A(Sort.by(new Order(Direction.DESC, "name", Sort.NullHandling.NULLS_LAST))),
    BY_PRICE_ASC(Sort.by(new Order(Direction.ASC, "price", Sort.NullHandling.NULLS_LAST))),
    BY_PRICE_DESC(Sort.by(new Order(Direction.DESC, "price", Sort.NullHandling.NULLS_LAST))),
    DISCOUNT_FIRST(Sort.by(new Order(Direction.ASC, "discountPrice", Sort.NullHandling.NULLS_LAST))),
    DISCOUNT_LAST(Sort.by(new Order(Direction.ASC, "discountPrice", Sort.NullHandling.NULLS_FIRST)));

    private final Sort sorter;

    ProductSorter(Sort sort) {
        this.sorter = sort;
    }
}
