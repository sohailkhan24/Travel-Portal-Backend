package com.nagarro.training.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.training.dto.RegisterTicketDto;
import com.nagarro.training.dto.TicketDto;
import com.nagarro.training.model.Ticket;
import com.nagarro.training.service.AuthService;
import com.nagarro.training.service.FileService;
import com.nagarro.training.service.TicketService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private FileService fileService;

	@Autowired
	private AuthService authService;

	// Create ticket
	@RequestMapping(method = RequestMethod.POST, value = "/tickets")
	public ResponseEntity<RegisterTicketDto> addTicket(@RequestBody RegisterTicketDto registerTicketDto,
			@RequestHeader("Authorization") String header) {
		RegisterTicketDto addTicket = ticketService.addTicket(registerTicketDto, header);
		if (addTicket != null)
			return ResponseEntity.status(HttpStatus.OK).body(addTicket);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

//	Get all paginated tickets user
	@RequestMapping(value = "/tickets/user", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsUser(Pageable pageable,
			@RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getPaginationTicketsUser(pageable, header));
	}

//	Get ticket by id
	@RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
	public ResponseEntity<TicketDto> getTicket(@PathVariable String id, @RequestHeader("Authorization") String header) {
		TicketDto ticketDto = ticketService.getTicket(id, header);
		if (ticketDto != null)
			return ResponseEntity.status(HttpStatus.OK).body(ticketDto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

//	Update ticket user
	@RequestMapping(method = RequestMethod.PUT, value = "/tickets")
	public ResponseEntity<RegisterTicketDto> updateTicket(@RequestBody RegisterTicketDto registerTicketDto,
			@RequestHeader("Authorization") String header) {
		RegisterTicketDto updateTicket = ticketService.updateTicket(registerTicketDto, header);
		if (updateTicket != null)
			return ResponseEntity.status(HttpStatus.OK).body(updateTicket);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

// 	ADMIN CONTROLS
//	Get all paginated tickets admin
	@RequestMapping(value = "/tickets/admin", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsAdmin(Pageable pageable,
			@RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getPaginationTicketsAdmin(pageable, header));
	}

//	Update ticket admin
	@RequestMapping(method = RequestMethod.PUT, value = "/tickets/admin", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<TicketDto> updateTicketAdmin(@RequestParam("files") MultipartFile[] files,
			@RequestParam("ticketId") String ticketId, @RequestParam("comment") String comment,
			@RequestParam("status") String status, @RequestHeader("Authorization") String header) {

		if (!authService.ifAdminByHeader(header)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		try {
			Arrays.asList(files).stream().forEach(file -> {
				fileService.addFile(file, ticketId);
			});
			System.out.print("\n\n\nGood\n\n\n");
			return ResponseEntity.status(HttpStatus.OK)
					.body(ticketService.updateTicketAdmin(ticketId, comment, status, header));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/tickets/admin/priority", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsByPriority(Pageable pageable,
			@RequestParam("priority") String priority, @RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ticketService.getPaginationTicketsByPriority(pageable, priority, header));
	}

	@RequestMapping(value = "/tickets/admin/projectName", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsByProjectName(Pageable pageable,
			@RequestParam("projectName") String projectName, @RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ticketService.getPaginationTicketsByProjectName(pageable, projectName, header));
	}

	@RequestMapping(value = "/tickets/admin/buisnessUnit", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsByBU(Pageable pageable,
			@RequestParam("buisnessUnit") String buisnessUnit, @RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ticketService.getPaginationTicketsByBU(pageable, buisnessUnit, header));
	}

	@RequestMapping(value = "/tickets/admin/username", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsByUsername(Pageable pageable,
			@RequestParam("username") String username, @RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ticketService.getPaginationTicketsByUsername(pageable, username, header));
	}

	@RequestMapping(value = "/tickets/admin/travelCity", method = RequestMethod.GET)
	public ResponseEntity<Page<Ticket>> getPaginationTicketsByTravelCity(Pageable pageable,
			@RequestParam("travelCity") String travelCity, @RequestHeader("Authorization") String header) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ticketService.getPaginationTicketsByTravelCity(pageable, travelCity, header));
	}
}
