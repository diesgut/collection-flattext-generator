package com.diesgut.collectionflattextgenerator.misc;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.diesgut.collectionflattextgenerator.model.Field;
import com.diesgut.collectionflattextgenerator.model.enums.collect.bbva.DetailBbvaEnum;

public class FlatFileGenerator {
	
	public static void writeLine(Writer writer, List<Field> fieldsLine) throws IOException {

        StringBuilder sb = new StringBuilder();
        for (Field field : fieldsLine) {
        	String fillChar=(field.getFillData()==null || field.getFillData().equals("null")) ? "": field.getFillData();

        	String preData=field.getData()==null ? "":field.getData();
			if (preData.length() >=field.getLength()) {
				preData = preData.substring(0, field.getLength());
			}
        	String data=StringUtils.leftPad(preData, field.getLength(), fillChar);
        	sb.append(data);
        }
        sb.append("\n");
        writer.append(sb.toString());


    }


}
