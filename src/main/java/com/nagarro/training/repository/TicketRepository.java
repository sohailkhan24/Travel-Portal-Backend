package com.nagarro.training.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.model.Ticket;
import com.nagarro.training.model.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
	Page<Ticket> findByStatusCode(int statusCode, Pageable pageable);

	Page<Ticket> findByUser(User user, Pageable pageable);

	Page<Ticket> findByPriorityAndStatusCode(String priority, int statusCode, Pageable pageable);

	Page<Ticket> findByProjectNameAndStatusCode(String projectName, int statusCode, Pageable pageable);

	Page<Ticket> findByUserUserNameAndStatusCode(String userName, int statusCode, Pageable pageable);

	Page<Ticket> findByUserBuisnessUnitAndStatusCode(String buisnessUnit, int statusCode, Pageable pageable);

	Page<Ticket> findByTravelCityAndStatusCode(String travelCity, int statusCode, Pageable pageable);
}
