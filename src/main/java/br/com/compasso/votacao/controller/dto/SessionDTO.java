package br.com.compasso.votacao.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.votacao.entity.Session;

public class SessionDTO {

	private Long id;
	private Integer minutes;
	private LocalDateTime beginAt;
	private LocalDateTime endAt;
	private String scheduleTitle;
	private String description;

	public SessionDTO(Session session) {
		this.id = session.getId();
		this.minutes = session.getMinutes();
		this.beginAt = session.getBegining();
		this.endAt = session.getEnding();
		this.scheduleTitle = session.getTopic().getTitle();
		this.description = session.getTopic().getDescription();
	}

	public Long getId() {
		return id;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public LocalDateTime getBeginAt() {
		return beginAt;
	}

	public String getScheduleTitle() {
		return scheduleTitle;
	}

	public String getDescription() {
		return description;
	}

	public static List<SessionDTO> convert(List<Session> sessions) {
		return sessions.stream().map(SessionDTO::new).collect(Collectors.toList());
	}

	public LocalDateTime getEndAt() {
		return endAt;
	}

}
