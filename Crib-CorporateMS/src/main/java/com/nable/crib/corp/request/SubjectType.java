/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 22, 2022 - 1:14:40 AM
 *  ***************************************
 */

package com.nable.crib.corp.request;

public enum SubjectType {
	CONSUMER(1), CORPORATE(0);
	
	private final int value;
	
	SubjectType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
