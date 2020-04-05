package com.diesgut.collectionflattextgenerator.model.enums;

public enum BankEnum {
	
	BCP(2L),
	SCO(4L),
	BBVA(3L);
	
	private Long code;
	
	private BankEnum(Long code) {
		this.code = code;
	}

	public Long getCode() {
		return code;
	}

}
