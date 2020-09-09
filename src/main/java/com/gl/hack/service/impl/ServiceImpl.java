package com.gl.hack.service.impl;

import com.gl.hack.model.User;
import com.gl.hack.repo.UserRepo;
import com.gl.hack.service.Service;
import com.gl.hack.spec.SearchCriteria;
import com.gl.hack.spec.UserSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> searchUsers(String query) {
        String[] splitQuery = query.split(",");
        List<SearchCriteria> queryList = new ArrayList<>();
        for (String sq : splitQuery) {
            String[] keyValue = sq.split(":");
            SearchCriteria sc = new SearchCriteria(keyValue[0], keyValue[1]);
            queryList.add(sc);
        }

        Specification<User> spec = buildSpecQuery(queryList);
        return userRepo.findAll(spec);
    }

    private Specification<User> buildSpecQuery(List<SearchCriteria> queryList) {
        if (queryList.size() == 0) {
            return null;
        }
        SearchCriteria getFirst = queryList.get(0);
        Specification<User> result = new UserSpecification(new SearchCriteria(getFirst.getKey(), getFirst.getValue()));

        List<UserSpecification> specs = new ArrayList<>();
        for (SearchCriteria ql : queryList) {
            UserSpecification tempSpec = new UserSpecification(new SearchCriteria(ql.getKey(), ql.getValue()));
            specs.add(tempSpec);
        }

        for (int i = 1; i < queryList.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
