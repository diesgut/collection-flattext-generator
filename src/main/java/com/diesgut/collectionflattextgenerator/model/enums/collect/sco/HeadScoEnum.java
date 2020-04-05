package com.diesgut.collectionflattextgenerator.model.enums.collect.sco;

public enum HeadScoEnum  {
	
	TIPO_REGISTRO(1,""),
	CUENTA_EMPRESA(14,""),
	SECUENCIA_SERVICIO(3,"0"),
	DOCUMENTOS(7,"0"),
	TOTAL_SOLES(15,"0"),
	TOTAL_DOLARES(12,"0"),
	FECHA_MOVIMIENTO(8,""),
	FILLER(157,""),
	FIN_REGISTRO(1,""); 
	
	private Integer dataLength;
	private String dataFill;
	
	private HeadScoEnum(Integer dataLength, String dataFill){
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
