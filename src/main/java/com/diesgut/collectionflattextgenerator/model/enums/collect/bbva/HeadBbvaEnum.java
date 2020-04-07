package com.diesgut.collectionflattextgenerator.model.enums.collect.bbva;

public enum HeadBbvaEnum  {
	
	TIPO_REGISTRO(2,"0"),
	RUC_EMPRESA(11,"0"),
	CODIGO_CLASE(3,"0"),
	TIPO_MONEDA(3,""),
	FECHA_PROCESO(8,"0"),
	CUENTA_RECAUDA(18,""),
	VACIO(107,""); //documen says 105
	
	private Integer dataLength;
	private String dataFill;
	
	private HeadBbvaEnum(Integer dataLength, String dataFill){
		this.dataLength=dataLength;
		this.dataFill=dataFill;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public String getDataFill() {
		return dataFill;
	}
	
}
