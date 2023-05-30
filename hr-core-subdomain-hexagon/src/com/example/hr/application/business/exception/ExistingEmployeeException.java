package com.example.hr.application.business.exception;

import com.example.ddd.BusinessException;
import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
@BusinessException
public class ExistingEmployeeException extends RuntimeException {

	private final TcKimlikNo identityNo;

	public ExistingEmployeeException(TcKimlikNo identityNo) {
		super("Employee with identity no (%s) already exists.".formatted(identityNo.getValue()));
		this.identityNo = identityNo;
	}

	public TcKimlikNo getIdentityNo() {
		return identityNo;
	}

}
