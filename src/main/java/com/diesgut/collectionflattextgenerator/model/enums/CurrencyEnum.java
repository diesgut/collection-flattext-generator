package com.diesgut.collectionflattextgenerator.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CurrencyEnum {

	USD(Integer.valueOf(11), "DOLARES AMERICANOS", "USD", "1", "0001", "USD"),
	PEN(Integer.valueOf(10), "NUEVOS SOLES", "PEN", "0", "0000", "PEN");


	public final static List<CurrencyEnum> list = new ArrayList<CurrencyEnum>();


	public final static Map<Integer, CurrencyEnum> lookup = new HashMap<Integer, CurrencyEnum>();
	public final static Map<String, CurrencyEnum> byCodeBCP = new HashMap<String, CurrencyEnum>();
	public final static Map<String, CurrencyEnum> byCodeBBVA = new HashMap<String, CurrencyEnum>();
	public final static Map<String, CurrencyEnum> byCodeSCO = new HashMap<String, CurrencyEnum>();

	static {
		for (CurrencyEnum c : CurrencyEnum.values()) {
			lookup.put(c.getCode(), c);
			byCodeBCP.put(c.getCodeBCP(), c);
			byCodeBBVA.put(c.getCodeBBVA(), c);
			byCodeSCO.put(c.getCodeSCO(), c);
			list.add(c);
		}
	}

	private Integer code;
	private String value;


	private String codeIso;

	private String codeBCP;
	private String codeSCO;
	private String codeBBVA;

	private CurrencyEnum(Integer code, String value, String codeIso, String codeBCP, String codeSCO, String codeBBVA) {
		this.code = code;
		this.value = value;
		this.codeIso = codeIso;
		this.codeBCP = codeBCP;
		this.codeSCO = codeSCO;
		this.codeBBVA = codeBBVA;
	}

	public static CurrencyEnum get(Integer code) {
		return lookup.get(code);
	}

	public Integer getCode() {
		return this.code;
	}

	public String getValue() {
		return this.value;
	}

	public String getCodeIso() {
		return codeIso;
	}

	public void setCodeIso(String codeIso) {
		this.codeIso = codeIso;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCodeBCP() {
		return codeBCP;
	}

	public String getCodeSCO() {
		return codeSCO;
	}

	public String getCodeBBVA() {
		return codeBBVA;
	}

	public static List<CurrencyEnum> listSomeElements(CurrencyEnum... transferSecuritiesTypeParams) {
		List<CurrencyEnum> retorno = new ArrayList<CurrencyEnum>();
		for (CurrencyEnum TransferSecuritiesType : transferSecuritiesTypeParams) {
			retorno.add(TransferSecuritiesType);
		}
		return retorno;
	}
}
