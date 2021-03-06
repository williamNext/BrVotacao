package br.com.compasso.votacao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.votacao.controller.dto.DetailTopicDTO;
import br.com.compasso.votacao.controller.dto.TopicDTO;
import br.com.compasso.votacao.controller.form.TopicForm;
import br.com.compasso.votacao.entity.Topic;
import br.com.compasso.votacao.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	public TopicService service;

	@GetMapping
	public List<TopicDTO> lista() {
		List<Topic> topics = service.findAll();
		return TopicDTO.convert(topics);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailTopicDTO> detail(@PathVariable Long id) {
		Optional<Topic> topic = service.getOne(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailTopicDTO(topic.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<TopicDTO> cadastrar(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.convert();
		service.save(topic);

		URI uri = uriBuilder.path("/schedules/{$id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDTO(topic));
	}

}
