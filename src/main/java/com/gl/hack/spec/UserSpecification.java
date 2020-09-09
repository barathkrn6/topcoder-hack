package com.gl.hack.spec;

import com.gl.hack.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    private SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder criteriaBuilder) {
        if (criteria != null) {
            System.out.println("--------------------- nulllllll" + criteria.getKey());
            System.out.println("--------------------- nulllllll" + criteria.getValue());
        }
        return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
    }
}
