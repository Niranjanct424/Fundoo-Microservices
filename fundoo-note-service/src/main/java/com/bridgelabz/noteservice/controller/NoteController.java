package com.bridgelabz.noteservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.noteservice.dto.NoteDto;
import com.bridgelabz.noteservice.dto.NoteUpdateDto;
import com.bridgelabz.noteservice.dto.User;
import com.bridgelabz.noteservice.model.NoteEntity;
import com.bridgelabz.noteservice.response.Response;
import com.bridgelabz.noteservice.service.NoteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${note.getUser}")
	private String getUser;

	public User getUser(String token) {
		
		User user = restTemplate.getForObject(getUser + token, User.class);
		return user;
	}

	/**
	 * Api to create note
	 * 
	 * @param noteDto to map with request object
	 * @param token   to get user id
	 * 
	 */
	@SuppressWarnings("unused")
	@PostMapping("notes/create")
	public ResponseEntity<Response> createNotes(@RequestBody NoteDto noteDto, @RequestHeader String token) {
		log.info("token:" + token);
		log.info("not:" + noteDto);

		User user = getUser(token);

		log.info("user got:" + user.getUserId());
		if (user != null) {
			noteService.createNote(noteDto, token, user.getUserId());
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Note created ", noteDto));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("user not found", noteDto));
		}

	}

	/**
	 * Api to delete note 
	 * @param noteId to delete note
	 * @param token  to get user id
	 * 
	 */
	@DeleteMapping("/notes/deletePermanently/{noteId}")
	public ResponseEntity<Response> deleteNoteParmanently(@PathVariable long noteId, @RequestHeader String token) {
		User user = getUser(token);
		if (user != null) {
			noteService.deleteNotePermanently(noteId);
			return ResponseEntity.status(HttpStatus.OK).body(new Response("note deleted success"));
		} else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user not found"));
	}

	/**
	 * Api to update note
	 * 
	 * @param noteUpdateDto to bind the request object
	 * @param token         to get user id
	 * 
	 */
	@PutMapping("/notes/update/{noteId}")
	public ResponseEntity<Response> updateNote(@PathVariable long noteId, @RequestBody NoteUpdateDto noteUpdateDto,
			@RequestHeader String token) {
		User user = getUser(token);
		if (user != null) {
			noteService.updateNote(noteId, noteUpdateDto);
			return ResponseEntity.status(HttpStatus.OK).body(new Response("update success"));
		} else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("user not found"));
	}

	/**
	 * Api to get all notes
	 * 
	 * @param token to identify user
	 * @return list of notes
	 */
	@GetMapping("/notes/getNotes")
	public ResponseEntity<Response> getAllNotes(@RequestHeader String token) {
		User user = getUser(token);

		if (user != null) {
			List<NoteEntity> notes = noteService.fetchAllNotes(user.getUserId());

			if (notes.size() > 0)
				return ResponseEntity.status(HttpStatus.OK).body(new Response("fetched all notes", notes));
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new Response("notes not found!! create note", notes));

		} else

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("user not found!"));

	}

}
