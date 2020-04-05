package com.diesgut.collectionflattextgenerator.model.enums.collect.sco;

public enum DetailScoEnum {

	
	TIPO_REGISTRO(1,""),
	CUENTA_EMPRESA(14,""),
	SECUENCIA_SERVICIO(3,"0"),
	CODIGO_USUARIO(15,""),
	NUMERO_RECIBO(15,""),
	NOMBRE_USUARIO(20,""),
	MONEDA_COBRO(4,""), //0000:SOLES 0001:DOLARES
	IMPORTE1(11,"0"),
	IMPORTE2(11,"0"),
	IMPORTE3(11,"0"),
	IMPORTE4(11,"0"),
	IMPORTE5(11,"0"),
	IMPORTE_MORA_6(11,"0"),
	FECHA_VENCIMIENTO(8,""),
	FECHA_PAGO(8,""), //FECHA REGISTRO EN SISTEMA DEL BANCO 
	TIPO_PAGO(1,""), //1: EFECTIVO 2: CHEQUE MISMO BCO 3:TARJETA DE CREDITO 4. CHEQUE OTRO BCO
	MEDIO_PAGO(1,""), //1: CARGO EN CTA 2: VENTANILLA, VIRTUAL 3:TARJ.CRED
	NRO_OPERACION(13,""),
	REFERENCIA_COBRO(30,""),
	HORA_PAGO(8,""),
	FECHA_PAGO_REAL(8,""),
	CANAL(2,""),
	FIN_REGISTRO(1,""),
	;
	
	private Integer dataLength;
	private String dataFill;
	
	private DetailScoEnum(Integer dataLength, String dataFill){
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
