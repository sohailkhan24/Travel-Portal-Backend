package com.nagarro.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nagarro.training.dto.RegisterTicketDto;
import com.nagarro.training.dto.TicketDto;
import com.nagarro.training.enums.ExpenseBorneBy;
import com.nagarro.training.enums.Priority;
import com.nagarro.training.enums.RequestType;
import com.nagarro.training.model.Ticket;
import com.nagarro.training.model.User;
import com.nagarro.training.repository.TicketRepository;
import com.nagarro.training.repository.UserRepository;
import com.nagarro.training.security.Jwt;
import com.nagarro.training.utility.TicketConversion;

@Service
public class TicketService {

	@Autowired
	private AuthService authService;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Jwt jwtutil;

//	Validate
	private boolean ifValid(RegisterTicketDto registerTicketDto) {
		String priority = registerTicketDto.getPriority();
		priority = priority.toUpperCase();
		try {
			Priority.valueOf(priority);
			registerTicketDto.setPriority(priority);
		} catch (IllegalArgumentException exception) {
			return false;
		}

		String expenseBorneBy = registerTicketDto.getExpenseBorneBy();
		expenseBorneBy = expenseBorneBy.toUpperCase();
		try {
			ExpenseBorneBy.valueOf(expenseBorneBy);
			registerTicketDto.setExpenseBorneBy(expenseBorneBy);
		} catch (IllegalArgumentException exception) {
			return false;
		}

		String requestType = registerTicketDto.getRequestType();
		requestType = requestType.toUpperCase();
		try {
			RequestType.valueOf(requestType);
			registerTicketDto.setRequestType(requestType);
		} catch (IllegalArgumentException exception) {
			return false;
		}
		return true;
	}

//	Adding new ticket.
	public RegisterTicketDto addTicket(RegisterTicketDto registerTicketDto, String header) {
		User user = authService.getUser(header);
		if (user != null && user.getRole().equals("user") && ifValid(registerTicketDto)) {
			Ticket ticket = new Ticket(registerTicketDto.getRequestType(), registerTicketDto.getPriority(),
					registerTicketDto.getTravelCity(), registerTicketDto.getFromLocationCity(),
					registerTicketDto.getTravelStartDate(), registerTicketDto.getTravelEndDate(),
					registerTicketDto.getPassportNumber(), registerTicketDto.getProjectName(),
					registerTicketDto.getExpenseBorneBy(), registerTicketDto.getTravelApproverName(),
					registerTicketDto.getExpectedDurationOfTravel(), registerTicketDto.getUpperBound(),
					registerTicketDto.getAnyAdditionalDetails(), user);
			ticket.initializeCreatedAt();
			ticket.setStatus("submit");
			ticket.setStatusCode(0);
			ticket = ticketRepository.save(ticket);
			registerTicketDto.setId(ticket.getId());
			return registerTicketDto;
		}
		return null;
	}

//	Get Tickets of User
	public Page<Ticket> getPaginationTicketsUser(Pageable pageable, String header) {
		User user = authService.getUser(header);
		Page<Ticket> ticketsPage = ticketRepository.findByUser(user, pageable);
		for (Ticket ticket : ticketsPage) {
			ticket.setFiles(null);
			ticket.setUser(null);
		}
		return ticketsPage;
	}

//	Get ticket according to id
	public TicketDto getTicket(String id, String header) {
		if (ticketRepository.existsById(id)) {
			String jwt = header.substring(7);
			String username = jwtutil.extractUsername(jwt);
			User user = userRepository.findByUserName(username).get();
			Ticket ticket = ticketRepository.findById(id).get();
			if (user.getRole().equals("admin") || ticket.getUser().getUserName().equals(username)) {
				TicketConversion ticketConversion = new TicketConversion();
				return ticketConversion.toTicketDto(ticket);
			}
			return null;
		}
		return null;
	}

//	Update a ticket user
	public RegisterTicketDto updateTicket(RegisterTicketDto registerTicketDto, String header) {
		String jwt = header.substring(7);
		String username = jwtutil.extractUsername(jwt);
		if (authService.ifAdminByUsername(username)) {
			return null;
		}
		if (ticketRepository.existsById(registerTicketDto.getId())) {
			Ticket ticketInDb = ticketRepository.findById(registerTicketDto.getId()).get();

			if (!ticketInDb.getUser().getUserName().equals(username) || ticketInDb.getStatus().equals("in process")) {
				return null;
			}

			if (!ifValid(registerTicketDto)) {
				return null;
			}
			User user = userRepository.findByUserName(username).get();
			Ticket ticket = new Ticket(registerTicketDto.getId(), registerTicketDto.getRequestType(),
					registerTicketDto.getPriority(), registerTicketDto.getTravelCity(),
					registerTicketDto.getFromLocationCity(), registerTicketDto.getTravelStartDate(),
					registerTicketDto.getTravelEndDate(), registerTicketDto.getPassportNumber(),
					registerTicketDto.getProjectName(), registerTicketDto.getExpenseBorneBy(),
					registerTicketDto.getTravelApproverName(), registerTicketDto.getExpectedDurationOfTravel(),
					registerTicketDto.getUpperBound(), registerTicketDto.getAnyAdditionalDetails(), user);
			ticket.setFiles(ticketInDb.getFiles());
			ticket.setComments(ticketInDb.getComments());
			ticket.initializeCreatedAt();
			if (ticketInDb.getStatus().equals("done") || ticketInDb.getStatus().equals("resubmit")) {
				ticket.setStatus("resubmit");
				ticket.setStatusCode(0);
			}
			ticket = ticketRepository.save(ticket);
			registerTicketDto.setId(ticket.getId());
			return registerTicketDto;
		}
		return null;
	}

//	ADMIN CONTROLS
//	Get all paginated tickets
	public Page<Ticket> getPaginationTicketsAdmin(Pageable pageable, String header) {
		if (authService.ifAdminByHeader(header)) {
			Page<Ticket> ticketsPage = ticketRepository.findByStatusCode(0, pageable);
			for (Ticket ticket : ticketsPage) {
				ticket.setFiles(null);
				ticket.setUser(null);
			}
			return ticketsPage;
		}
		return null;
	}

//	Update admin ticket
	public TicketDto updateTicketAdmin(String ticketId, String comment, String status, String header) {
		if (ticketRepository.existsById(ticketId)) {
			Ticket ticket = ticketRepository.findById(ticketId).get();
			ticket.setComments(comment);
			ticket.setStatus(status);
			ticket.setStatusCode(0);
			if (ticket.getStatus().equals("done"))
				ticket.setStatusCode(1);
			ticketRepository.save(ticket);
			TicketConversion ticketConversion = new TicketConversion();
			return ticketConversion.toTicketDto(ticket);
		}
		return null;
	}

//  Search by priority
	public Page<Ticket> getPaginationTicketsByPriority(Pageable pageable, String priority, String header) {
		if (!authService.ifAdminByHeader(header)) {
			return null;
		}
		priority = priority.toLowerCase();
		if (priority.equals("immediate")) {
			priority = "a_immediate";
		} else if (priority.equals("urgent")) {
			priority = "b_urgent";
		} else if (priority.equals("normal")) {
			priority = "c_normal";
		}
		Page<Ticket> ticketsPage = ticketRepository.findByPriorityAndStatusCode(priority, 0, pageable);
		for (Ticket ticket : ticketsPage) {
			ticket.setFiles(null);
			ticket.setUser(null);
		}
		return ticketsPage;
	}

//	Search by projectName
	public Page<Ticket> getPaginationTicketsByProjectName(Pageable pageable, String projectName, String header) {
		if (!authService.ifAdminByHeader(header)) {
			return null;
		}
		Page<Ticket> ticketsPage = ticketRepository.findByProjectNameAndStatusCode(projectName, 0, pageable);
		for (Ticket ticket : ticketsPage) {
			ticket.setFiles(null);
			ticket.setUser(null);
		}
		return ticketsPage;
	}

//	Search by Buisness Unit
	public Page<Ticket> getPaginationTicketsByBU(Pageable pageable, String buisnessUnit, String header) {
		if (!authService.ifAdminByHeader(header)) {
			return null;
		}
		Page<Ticket> ticketsPage = ticketRepository.findByUserBuisnessUnitAndStatusCode(buisnessUnit, 0, pageable);
		for (Ticket ticket : ticketsPage) {
			ticket.setFiles(null);
			ticket.setUser(null);
		}
		return ticketsPage;
	}

//	Search by username
	public Page<Ticket> getPaginationTicketsByUsername(Pageable pageable, String username, String header) {
		if (!authService.ifAdminByHeader(header)) {
			return null;
		}
		try {
			Page<Ticket> ticketsPage = ticketRepository.findByUserUserNameAndStatusCode(username, 0, pageable);
			for (Ticket ticket : ticketsPage) {
				ticket.setFiles(null);
				ticket.setUser(null);
			}
			return ticketsPage;
		} catch (Exception e) {
			return null;
		}
	}

//  Search by travel city
	public Page<Ticket> getPaginationTicketsByTravelCity(Pageable pageable, String travelCity, String header) {
		if (!authService.ifAdminByHeader(header)) {
			return null;
		}
		Page<Ticket> ticketsPage = ticketRepository.findByTravelCityAndStatusCode(travelCity, 0, pageable);
		for (Ticket ticket : ticketsPage) {
			ticket.setFiles(null);
			ticket.setUser(null);
		}
		return ticketsPage;
	}

}
