package com.user.repository;

import com.user.entity.UserBo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserBo, Long> {

}
