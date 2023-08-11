package com.asc.rest.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int age;
	private String designation;
	
}
