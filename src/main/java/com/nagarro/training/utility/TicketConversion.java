package com.nagarro.training.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nagarro.training.dto.TicketDto;
import com.nagarro.training.model.File;
import com.nagarro.training.model.Ticket;

public class TicketConversion {
	@Autowired

	public TicketDto toTicketDto(Ticket ticket) {
		TicketDto ticketDto = new TicketDto(ticket.getId(), ticket.getRequestType(), ticket.getPriority(),
				ticket.getTravelCity(), ticket.getFromLocationCity(), ticket.getTravelStartDate(),
				ticket.getTravelEndDate(), ticket.getPassportNumber(), ticket.getProjectName(),
				ticket.getExpenseBorneBy(), ticket.getTravelApproverName(), ticket.getExpectedDurationOfTravel(),
				ticket.getUpperBound(), ticket.getAnyAdditionalDetails(), ticket.getComments(), ticket.getStatus(),
				ticket.getUser().getUserName());
		List<File> files = ticket.getFiles();
		List<ResponseFile> responseFiles = new ArrayList<>();
		for (File file : files) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(file.getId()).toUriString();
			responseFiles.add(new ResponseFile(file.getName(), fileDownloadUri));
		}
		ticketDto.setResponseFiles(responseFiles);
		return ticketDto;
	}
}
