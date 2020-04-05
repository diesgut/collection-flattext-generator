package com.diesgut.collectionflattextgenerator.model.enums.collect.bbva;

public enum DetailBbvaEnum {

	TIPO_REGISTRO(2,"0"),
	NOMBRE_CLIENTE(30,""),
	REFERENCIA(48,""), //identificador del cliente
	IMPORTE_ORIGEN(15,"0"),
	IMPORTE_DEPOSITO(15,"0"),
	IMPORTE_MORA(15,"0"),
	OFICINA_PAGO(4,"0"),
	NRO_MOVIMIENTO(6,"0"),
	FECHA_PAGO(8,"0"),
	TIPO_VALOR(2,"0"),
	CANAL_ENTRADA(2,"0"),
	VACIO(5,"");
	
	private Integer dataLength;
	private String dataFill;
	
	private DetailBbvaEnum(Integer dataLength, String dataFill){
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
