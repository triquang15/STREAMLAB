package com.aptech.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderNotFoundException extends Exception {

	public OrderNotFoundException(String message) {
		super(message);
	}

}
