package com.loan.service;

import com.loan.model.ClientContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientContextService extends JpaRepository<ClientContext, Long> {

    void  deleteAll();
}
