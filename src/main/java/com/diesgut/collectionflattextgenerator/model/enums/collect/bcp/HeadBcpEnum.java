package com.diesgut.collectionflattextgenerator.model.enums.collect.bcp;

public enum HeadBcpEnum {
	
	TIPO_REGISTRO(2,""),
	CODIGO_CUENTA(3,""),
	CODIGO_MONEDA(1,""),
	NRO_CTA_EMP_AFI(7,""),
	TIPO_VALIDACION(1,""),
	FECHA_PROCESO(8,""),
	CANTIDAD_TOTAL_REGISTROS(9,"0"),
	MONTO_TOTAL(15,"0"),
	CODIGO_INTERNO_CTA_RECAUDA(4,""),
	CASILLA_USU_TELE_TRANF(6,""),
	HORA_CORTE(6,""),
	CODIGO_SERVICIO(6,""),
	CAMPO_LIBRE(182,"0"),
	;
	
	private Integer dataLength;
	private String dataFill;
	
	private HeadBcpEnum(Integer dataLength, String dataFill){
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
