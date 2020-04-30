package com.bridgelabz.noteservice.service;

import java.util.List;

import com.bridgelabz.noteservice.dto.NoteDto;
import com.bridgelabz.noteservice.dto.NoteUpdateDto;
import com.bridgelabz.noteservice.model.NoteEntity;

public interface NoteService {

	boolean createNote(NoteDto noteDto, String token , long userId);

	boolean deleteNotePermanently(long noteId);
	
	boolean updateNote(long noteId, NoteUpdateDto noteUpdateDto);

	List<NoteEntity> fetchAllNotes(long userId);
	
}