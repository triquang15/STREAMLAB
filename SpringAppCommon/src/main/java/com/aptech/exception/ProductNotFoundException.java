package com.aptech.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String message) {
		super(message);
	}

}
