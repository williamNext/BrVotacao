package br.com.compasso.votacao.controller.form;

import com.sun.istack.NotNull;

import br.com.compasso.votacao.entity.Associate;
import br.com.compasso.votacao.entity.Session;
import br.com.compasso.votacao.entity.Vote;
import br.com.compasso.votacao.enumeration.VoteEnum;
import br.com.compasso.votacao.service.AssociateService;
import br.com.compasso.votacao.service.SessionService;

public class VoteForm {
	
	@NotNull
	private Long idAssociate;
	@NotNull
	private Long idSession;
	@NotNull
	private String vote;

	public Long getIdAssociate() {
		return idAssociate;
	}

	public void setIdAssociate(Long idAssociate) {
		this.idAssociate = idAssociate;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}


	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	
	public Vote convert(AssociateService associateService, SessionService sessionService) {
		Associate associate = associateService.getOne(idAssociate);
		Session session = sessionService.getOne(idSession).get();
		return new Vote(associate, session, VoteEnum.valueOf(getVote()));
	}
	
}
