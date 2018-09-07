package com.reference.commitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.reference.commitment.dto.CommitmentDTO;
import com.reference.commitment.dto.OrderAssociationDTO;
import com.reference.commitment.entity.CommitmentEntity;
import com.reference.commitment.entity.QCommitmentEntity;
import com.reference.commitment.entity.OrderAssociationEntity;
import com.reference.commitment.repository.CommitmentRepository;
import com.reference.commitment.repository.OrderAssociationRepository;
import com.reference.order.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommitmentService {

	public OrderAssociationRepository orderAssociationRepository;
	public CommitmentRepository commitmentRepository;

	public CommitmentService(OrderAssociationRepository orderAssociationRepository,
			CommitmentRepository commitmentRepository) {
		this.orderAssociationRepository = orderAssociationRepository;
		this.commitmentRepository = commitmentRepository;
	}

	public OrderAssociationDTO associatecommitmentOrder(OrderDTO orderDTO) {
		OrderAssociationEntity entity = new OrderAssociationEntity();
		OrderAssociationDTO orderassociationDTO = new OrderAssociationDTO();
		@SuppressWarnings("unchecked")
		final List<CommitmentEntity> commitmentIterable = (List<CommitmentEntity>) getCommitments(orderDTO);
		System.out.println("from Iterable" + commitmentIterable);
		if (commitmentIterable != null && commitmentIterable.size() != 0) {
			CommitmentEntity commitment = commitmentIterable.get(0);
			entity.setCommitmentId(commitment.getCommitmentId());
			entity.setOrderAssocaitonId(2);
			entity.setOrderId(orderDTO.getOrderId());
			entity.setPickupDate(orderDTO.getPickupDate());
			orderAssociationRepository.save(entity);
			orderassociationDTO.setCommitmentId(entity.getCommitmentId());
			orderassociationDTO.setOrderAssocaitonId(entity.getOrderAssocaitonId());
			orderassociationDTO.setOrderId(entity.getOrderId());
			orderassociationDTO.setPickupDate(entity.getPickupDate());
		}
		
		return orderassociationDTO;
	}

	public Iterable<CommitmentEntity> getCommitments(final OrderDTO orderDTO) {
		final QCommitmentEntity qCommitment = QCommitmentEntity.commitmentEntity;
		final BooleanBuilder booleanBuilder = new BooleanBuilder();
		Optional.ofNullable(orderDTO.getCorporateAccountId()).ifPresent(
				nationalAccountID -> booleanBuilder.and(qCommitment.corporateAccountId.eq(nationalAccountID)));

		Optional.ofNullable(orderDTO.getOriginArea()).ifPresent(
				originMarketingAreaID -> booleanBuilder.and(qCommitment.originArea.eq(originMarketingAreaID)));

		Optional.ofNullable(orderDTO.getServiceOffering()).ifPresent(
				financeBusinessUnitCode -> booleanBuilder.and(qCommitment.serviceOffering.eq(financeBusinessUnitCode)));

		Optional.ofNullable(orderDTO.getBusinessUnit())
				.ifPresent(serviceOfferingCode -> booleanBuilder.and(qCommitment.businessUnit.eq(serviceOfferingCode)));

		return commitmentRepository.findAll(booleanBuilder.getValue());
	}
}
