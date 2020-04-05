package com.diesgut.collectionflattextgenerator.model.enums.collect.bcp;

public enum DetailBcpEnum {
	
	TIPO_REGISTRO(1,""),
	CODIGO_CUENTA(3,""),
	CODIGO_MONEDA(1,""), 
	NRO_CTA_EMP_AFI(7,""),
	CODIGO_ID_DEPOSITANTE(14,""),
	ADITIONAL_DATA_DEPOSITANTE(30,""),
	PAYMENT_DATE(8,""),
	FECHA_VENCIMIENTO(8,""),
	MONTO_PAGADO(15,"0"),
	MONTO_MORA(15,"0"),
	MONTO_TOTAL(15,"0"),
	SUCURSAL(6,""),
	NRO_OPERACION(6,""),
	REFERENCIA(22,""),
	TERMINAL_ID(4,""),
	MEDIO_ATENCION(12,""),
	HORA_ATENCION(6,""),
	NRO_CHEQUE(10,""),
	CODIGO_BANCO(2,""),
	CARGO_FIJO_PAGADO(10,""),
	ES_EXTORNADO(1,""),
	NRO_DOCUMENTO_PAGO(20,""),
	NRO_OPERACION_BD(8,""),
	CAMPO_LIBRE(25,""),
	;
	
	private Integer dataLength;
	private String dataFill;
	
	private DetailBcpEnum(Integer dataLength, String dataFill){
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
