package com.aptech.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryNotFoundException extends Exception {

	public CategoryNotFoundException(String message) {
		super(message);
	}

}
