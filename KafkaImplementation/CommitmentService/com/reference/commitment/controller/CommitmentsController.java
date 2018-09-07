package com.reference.commitment.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reference.commitment.dto.OrderAssociationDTO;
import com.reference.commitment.entity.CommitmentEntity;
import com.reference.commitment.entity.OrderAssociationEntity;
import com.reference.commitment.repository.CommitmentRepository;
import com.reference.commitment.service.CommitmentService;
import com.reference.order.dto.OrderDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class CommitmentsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommitmentsController.class);

	private final CommitmentRepository commitmentRepository;
	private final CommitmentService commitmentService;
	
	
	public CommitmentsController(CommitmentRepository commitmentRepository,CommitmentService commitmentService) {
		this.commitmentRepository = commitmentRepository;
		this.commitmentService = commitmentService;
	}
	
	// Basic inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@GetMapping(value = "/test")
	public ResponseEntity<String> testMpping() {
		return Optional.ofNullable("Success").map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@GetMapping(value = "/test1")
	public ResponseEntity<String> testMapping() {
		return Optional.ofNullable("Success").map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@RequestMapping(value = "/createCommitment", method = RequestMethod.POST)
	public CommitmentEntity addCommitment(@RequestBody CommitmentEntity entity) {
		// LOG.info("Saving user.");
		return commitmentRepository.save(entity);
	}
	
	@RequestMapping(value = "/getCommitment/all", method = RequestMethod.GET)
	public List<CommitmentEntity> getAllCommitment() {
		LOGGER.info("get all commitment");
		 return commitmentRepository.findAll();
	}

	@RequestMapping(value = "/getCommitment/{commitmentId}", method = RequestMethod.GET)
	public Optional<CommitmentEntity> getCommitment(@PathVariable Integer commitmentId) {
		LOGGER.info("get commitment", commitmentId);

		 return commitmentRepository.findById(commitmentId);
	}

	@RequestMapping(value = "/updateCommitment/{commitmentId}", method = RequestMethod.PUT)
	public CommitmentEntity updateCommitment(@RequestBody CommitmentEntity entity) {
		LOGGER.info("updated", entity);

		return commitmentRepository.save(entity);
	}

	@RequestMapping(value = "/deleteCommitment/{commitmentId}", method = RequestMethod.GET)
	public void deleteCommitment(@PathVariable Integer commitmentId) {
		LOGGER.info("deleted", commitmentId);
		commitmentRepository.deleteById(commitmentId);
	}
	
	@PostMapping(value = "/associateOrder")
	public ResponseEntity<OrderAssociationDTO> associatecommitmentOrder(
			@RequestBody final OrderDTO orderDTO) {
		OrderAssociationDTO orderAssociationDto = commitmentService.associatecommitmentOrder(orderDTO);
		return Optional.ofNullable(orderAssociationDto).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

}
