package com.diesgut.collectionflattextgenerator.model;

import javax.management.RuntimeErrorException;

import com.diesgut.collectionflattextgenerator.model.enums.collect.bbva.DetailBbvaEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.bbva.HeadBbvaEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.bcp.DetailBcpEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.bcp.HeadBcpEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.sco.DetailScoEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.sco.HeadScoEnum;

public class Field {

	private String name;
	private Integer length;
	private String data;
	private String fillData;
	
	public Field() {
		
	}
	
	  /**
	   * Field tipo cabecera recaudacion SCO
	   */
	public void setHeadSCO(HeadScoEnum head, String headerValue) {
		this.name=head.name();
		this.data=headerValue;
		this.length=head.getDataLength();
		this.fillData=head.getDataFill();
	}
	
	  /**
	   * Field tipo detalle recaudacion SCO
	   */
	public void setDetailSCO(DetailScoEnum detai, String headerValue) {
		this.name=detai.name();
		this.data=headerValue;
		this.length=detai.getDataLength();
		this.fillData=detai.getDataFill();
	}
	
	public void setHeadBBVA(HeadBbvaEnum head, String headerValue) {
		this.name=head.name();
		this.data=headerValue;
		this.length=head.getDataLength();
		this.fillData=head.getDataFill();
	}

	public void setDetailBBVA(DetailBbvaEnum detai, String headerValue) {
		this.name=detai.name();
		this.data=headerValue;
		this.length=detai.getDataLength();
		this.fillData=detai.getDataFill();
	}
	
	public void setHeadBCP(HeadBcpEnum head, String headerValue) {
		this.name=head.name();
		this.data=headerValue;
		this.length=head.getDataLength();
		this.fillData=head.getDataFill();
	}
	
	public void setDetailBCP(DetailBcpEnum detai, String headerValue) {
		this.name=detai.name();
		this.data=headerValue;
		this.length=detai.getDataLength();
		this.fillData=detai.getDataFill();
	}
	
	public Field(String data,Integer length, String fillData) {
		this.data=data;
		this.length=length;
		this.fillData=fillData;
	}
	
	public Field(String name,String data,Integer length)  {
		this.name=name;
		this.data=data;
		this.length=length;
		this.fillData=null;
		if(data.length()>length) {
		 throw new RuntimeErrorException(new Error(name+ " " + data +" data lenght mayor de lo permitido"));
		}
	}
	
	public Field(String name,String data,Integer length,String fillData) {
		this.name=name;
		this.data=data;
		this.length=length;
		this.fillData=fillData;
	}
	
	public Field(String data,Integer length) {
		this.data=data;
		this.length=length;
		this.fillData=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFillData() {
		return fillData;
	}

	public void setFillData(String fillData) {
		this.fillData = fillData;
	}
	
}
