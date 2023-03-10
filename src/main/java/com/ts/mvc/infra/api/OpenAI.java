package com.ts.mvc.infra.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.ts.mvc.infra.api.dto.PetQuestionResponse;
import com.ts.mvc.infra.code.Code;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenAI {
	
	private final String PET_QUESTION_URL = "https://api.openai.com/v1/chat/completions";
	
	private final RestTemplate restTemplate;
	
	public PetQuestionResponse petQuestion(String question) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("model","gpt-3.5-turbo");
		body.put("messages", List.of(Map.of("role","user","content", question)));
		
		ResponseEntity<JsonNode> response = restTemplate.exchange(
													RequestEntity.post(PET_QUESTION_URL)
													.header("Authorization", "Bearer " + Code.OPENAI_API_KEY)
													.contentType(MediaType.APPLICATION_JSON)
													.body(body)
													, JsonNode.class);
		
		JsonNode node = response.getBody();
		
		PetQuestionResponse res = new PetQuestionResponse();
		res.setCreated(node.at("/created").asLong());
		res.setMessage(node.at("/choices/0/message/content").asText());
		res.setRole(node.at("/choices/0/message/role").asText());
		
		return res;

	}
}
