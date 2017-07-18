package com.mkyong.service;

import com.mkyong.Repository.RepositoryUser;
import com.mkyong.domain.User;
import com.mkyong.service.iService.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mtoader on 7/17/2017.
 */
@Service
public class ServiceUserImpl implements ServiceUser {
    private final RepositoryUser repositoryUser;

    @Autowired
    public ServiceUserImpl(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public User findByName(String name) {
        return repositoryUser.findByName(name);
    }
}
