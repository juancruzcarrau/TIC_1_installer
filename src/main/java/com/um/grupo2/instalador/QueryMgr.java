package com.um.grupo2.instalador;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
public class QueryMgr {

    @Transactional
    public void execute(String queryString, EntityManager entityManager) {

    }
}
