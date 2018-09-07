package com.reference.commitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import com.reference.commitment.entity.CommitmentEntity;

//@Repository
public interface CommitmentRepository extends JpaRepository<CommitmentEntity, Integer>, QuerydslPredicateExecutor<CommitmentEntity> {

}
