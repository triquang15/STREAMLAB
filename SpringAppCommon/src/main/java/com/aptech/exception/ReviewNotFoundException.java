package com.aptech.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewNotFoundException extends Exception {

	public ReviewNotFoundException(String message) {
		super(message);
	}
}
