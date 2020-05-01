package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Entity
@Table(name = "note")
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noteId;
	
	private long userId;
	
	private String title;
	
	private String description;
	
	private boolean isPinned;
	
	private boolean isArchieved;
	
	private boolean isTrashed;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime reminder;
	
	private String color;
	

}
