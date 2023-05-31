package com.example.hr.application.business.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends EmployeeEvent {

	public EmployeeHiredEvent(TcKimlikNo identityNo) {
		super(identityNo);
	}
	
}
