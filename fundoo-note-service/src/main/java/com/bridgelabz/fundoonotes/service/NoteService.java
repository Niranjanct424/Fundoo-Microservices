package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.NoteUpdateDto;
import com.bridgelabz.fundoonotes.model.NoteEntity;

public interface NoteService {

	boolean createNote(NoteDto noteDto, String token , long userId);

	boolean deleteNotePermanently(long noteId);
	
	boolean updateNote(long noteId, NoteUpdateDto noteUpdateDto);

	List<NoteEntity> fetchAllNotes(long userId);
	
}