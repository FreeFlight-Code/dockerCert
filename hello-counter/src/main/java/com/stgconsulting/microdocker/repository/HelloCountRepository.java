package com.stgconsulting.microdocker.repository;

import org.springframework.data.repository.CrudRepository;

import com.stgconsulting.microdocker.domain.HelloCount;

public interface HelloCountRepository extends CrudRepository<HelloCount, String> {

}
