package com.mkyong.service.iService;

import com.mkyong.domain.User;

/**
 * Created by mtoader on 7/17/2017.
 */
public interface ServiceUser {
    User findByName(String name);
}
