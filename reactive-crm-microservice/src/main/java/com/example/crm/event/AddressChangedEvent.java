package com.example.crm.event;

import java.util.List;

import com.example.crm.document.Address;

public class AddressChangedEvent extends CustomerEvent {
	private List<Address> oldAddresses;
	private List<Address> newAddresses;

	public AddressChangedEvent(String identity, List<Address> oldAddresses, List<Address> newAddresses) {
		super(identity);
		this.newAddresses = newAddresses;
		this.oldAddresses = oldAddresses;
	}

	public List<Address> getOldAddresses() {
		return oldAddresses;
	}

	public void setOldAddresses(List<Address> oldAddresses) {
		this.oldAddresses = oldAddresses;
	}

	public List<Address> getNewAddresses() {
		return newAddresses;
	}

	public void setNewAddresses(List<Address> newAddresses) {
		this.newAddresses = newAddresses;
	}

}
