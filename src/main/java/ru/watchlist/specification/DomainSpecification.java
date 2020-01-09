package ru.watchlist.specification;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DomainSpecification<Parameter> implements Specification<Parameter> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Parameter> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<String> chain = criteria.getChainKey();
        Path path = root.get(chain.get(0));
        for(int i = 1; i < chain.size(); i++) {
            path = path.get(chain.get(i));
        }

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(path, criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(path, criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else if (path.getJavaType() == boolean.class) {
                boolean value = Boolean.parseBoolean(criteria.getValue());
                return  value ? builder.isTrue(path) : builder.isFalse(path);
            }
            else {
                return builder.equal(path, criteria.getValue());
            }
        }
        return null;
    }
}