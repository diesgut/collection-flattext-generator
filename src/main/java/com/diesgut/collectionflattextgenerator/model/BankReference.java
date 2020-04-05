package com.diesgut.collectionflattextgenerator.model;

import com.diesgut.collectionflattextgenerator.model.enums.CurrencyEnum;

public class BankReference {

	private Long idBankReferencePk;
	private String bankReference;
	private Integer currency;
	private String accountNumber;
	private String holderFullName;

	public BankReference() {
		super();
	}

	public BankReference(Long idBankReferencePk, Integer currency, String accountNumber) {
		super();
		this.idBankReferencePk = idBankReferencePk;
		this.currency = currency;
		this.accountNumber = accountNumber;
	}

	public Long getIdBankReferencePk() {
		return idBankReferencePk;
	}

	public void setIdBankReferencePk(Long idBankReferencePk) {
		this.idBankReferencePk = idBankReferencePk;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public CurrencyEnum getCurrencyEnum() {
		return CurrencyEnum.lookup.get(this.currency);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHolderFullName() {
		return holderFullName;
	}

	public void setHolderFullName(String holderFullName) {
		this.holderFullName = holderFullName;
	}

	public String getBankReference() {
		return bankReference;
	}

	public void setBankReference(String bankReference) {
		this.bankReference = bankReference;
	}

	@Override
	public String toString() {
		return "BankReference [idBankReferencePk=" + idBankReferencePk + ", bankReference=" + bankReference
				+ ", currency=" + currency + ", accountNumber=" + accountNumber + ", holderFullName=" + holderFullName
				+ "]";
	}

}
