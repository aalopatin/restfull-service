package ru.watchlist.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SpecificationBuilder<T> {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationBuilder(String search) {
        params = new ArrayList<>();
        Pattern pattern = Pattern.compile("([\\\\.\\w]+?)(:|<|>)([\\\\.\\w]+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while(matcher.find()) {
            with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
    }

    public SpecificationBuilder with(String key, String operation, String value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(DomainSpecification<T>::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
