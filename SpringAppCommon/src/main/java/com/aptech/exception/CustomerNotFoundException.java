package com.aptech.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerNotFoundException extends Exception {

	public CustomerNotFoundException(String message) {
		super(message);
	}

}
