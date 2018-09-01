package com.mail.repository;

import com.mail.entity.MailBo;
import org.springframework.data.repository.CrudRepository;

public interface MailRepository extends CrudRepository<MailBo, Long> {
}
