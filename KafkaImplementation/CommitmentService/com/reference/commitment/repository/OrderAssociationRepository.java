package com.reference.commitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.reference.commitment.entity.OrderAssociationEntity;

public interface OrderAssociationRepository extends JpaRepository<OrderAssociationEntity, Integer> {

}
