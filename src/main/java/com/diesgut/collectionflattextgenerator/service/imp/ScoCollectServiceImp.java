package com.diesgut.collectionflattextgenerator.service.imp;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diesgut.collectionflattextgenerator.StartApplication;
import com.diesgut.collectionflattextgenerator.misc.FlatFileGenerator;
import com.diesgut.collectionflattextgenerator.model.BankReference;
import com.diesgut.collectionflattextgenerator.model.Field;
import com.diesgut.collectionflattextgenerator.model.enums.BankEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.sco.DetailScoEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.sco.HeadScoEnum;
import com.diesgut.collectionflattextgenerator.repository.IBankReferene;
import com.diesgut.collectionflattextgenerator.service.ICollectService;

@Component("scoCollectServiceImp")
public class ScoCollectServiceImp implements ICollectService {

	private static final Logger log = LoggerFactory.getLogger(ScoCollectServiceImp.class);

	@Autowired
	private IBankReferene bankReferene;

	Random random = new Random();

	public void collectFileGenerator() {

		BankEnum bankEnum = BankEnum.SCO;

		List<BankReference> banksReferences = bankReferene.allByBankAndId(bankEnum);
		List<String> accountsNumbers = banksReferences.stream().map(x -> x.getAccountNumber()).distinct()
				.collect(Collectors.toList());

		LocalDateTime now = LocalDateTime.now();
		String sNow = now.format(DateTimeFormatter.BASIC_ISO_DATE);

		LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(10L);
		String sFechaVencimiento = fechaVencimiento.format(DateTimeFormatter.BASIC_ISO_DATE);
		String sTime = now.format(DateTimeFormatter.ofPattern("HHmmss"));

		for (String accountNumber : accountsNumbers) {
			try {
				List<BankReference> banksReferencesByAccountNumber = banksReferences.stream()
						.filter(x -> x.getAccountNumber().equals(accountNumber)).collect(Collectors.toList());
				if (banksReferencesByAccountNumber.isEmpty()) {
					continue;
				}
				BankReference bankReferenceFirst = banksReferencesByAccountNumber.get(0);

				String csvFile = StartApplication.FOLDER_DEPOSIT + bankEnum.name() + sNow + "_" + accountNumber
						+ "_depositos.txt";
				FileWriter writer = new FileWriter(csvFile);

				List<List<Field>> records = new ArrayList<List<Field>>();
				List<Field> header = new ArrayList<Field>();
				header.add(this.headSCO(HeadScoEnum.TIPO_REGISTRO, "H"));
				header.add(this.headSCO(HeadScoEnum.CUENTA_EMPRESA, accountNumber));
				header.add(this.headSCO(HeadScoEnum.SECUENCIA_SERVICIO, "666"));
				header.add(this.headSCO(HeadScoEnum.DOCUMENTOS, "666"));
				header.add(this.headSCO(HeadScoEnum.TOTAL_SOLES, "666"));
				header.add(this.headSCO(HeadScoEnum.TOTAL_DOLARES, "666"));
				header.add(this.headSCO(HeadScoEnum.FECHA_MOVIMIENTO, sNow)); // "20201025"
				header.add(this.headSCO(HeadScoEnum.FILLER, ""));
				header.add(this.headSCO(HeadScoEnum.FIN_REGISTRO, "*"));
				records.add(header);

				for (BankReference bankReference : banksReferencesByAccountNumber) {
					int amount = random.nextInt(
							StartApplication.MAXIUM_OPERATION_AMOUNT - StartApplication.MINIMUM_OPERATION_AMOUNT)
							+ StartApplication.MINIMUM_OPERATION_AMOUNT;
					String sOperationAmount = String.valueOf(amount);

					int operationNumber = random.nextInt(
							StartApplication.MAXIUM_OPERATION_AMOUNT - StartApplication.MINIMUM_OPERATION_AMOUNT)
							+ StartApplication.MINIMUM_OPERATION_AMOUNT;
					String sOperationNumber = String.valueOf(operationNumber);

					List<Field> details = new ArrayList<Field>();
					details.add(this.detailSCO(DetailScoEnum.TIPO_REGISTRO, "D"));
					details.add(this.detailSCO(DetailScoEnum.CUENTA_EMPRESA, accountNumber));
					details.add(this.detailSCO(DetailScoEnum.SECUENCIA_SERVICIO, "666"));
					details.add(this.detailSCO(DetailScoEnum.CODIGO_USUARIO, bankReference.getBankReference()));
					details.add(this.detailSCO(DetailScoEnum.NUMERO_RECIBO, sOperationNumber));
					details.add(this.detailSCO(DetailScoEnum.NOMBRE_USUARIO, bankReference.getHolderFullName()));
					details.add(this.detailSCO(DetailScoEnum.MONEDA_COBRO, bankReferenceFirst.getCurrencyEnum().getCodeSCO()));
					details.add(this.detailSCO(DetailScoEnum.IMPORTE1, sOperationAmount));
					details.add(this.detailSCO(DetailScoEnum.IMPORTE2, "0"));
					details.add(this.detailSCO(DetailScoEnum.IMPORTE3, "0"));
					details.add(this.detailSCO(DetailScoEnum.IMPORTE4, "0"));
					details.add(this.detailSCO(DetailScoEnum.IMPORTE5, "0"));
					details.add(this.detailSCO(DetailScoEnum.IMPORTE_MORA_6, "0"));
					details.add(this.detailSCO(DetailScoEnum.FECHA_VENCIMIENTO, sFechaVencimiento)); // "20201025"
					details.add(this.detailSCO(DetailScoEnum.FECHA_PAGO, sNow)); // "20201025"
					details.add(this.detailSCO(DetailScoEnum.TIPO_PAGO, "1"));
					details.add(this.detailSCO(DetailScoEnum.MEDIO_PAGO, "1"));
					details.add(this.detailSCO(DetailScoEnum.NRO_OPERACION, sOperationNumber));
					details.add(this.detailSCO(DetailScoEnum.REFERENCIA_COBRO, "XXXXXXXXX"));
					details.add(this.detailSCO(DetailScoEnum.HORA_PAGO, sTime));// "050144"
					details.add(this.detailSCO(DetailScoEnum.FECHA_PAGO_REAL, sNow)); // "20201025"
					details.add(this.detailSCO(DetailScoEnum.CANAL, "01"));
					details.add(this.detailSCO(DetailScoEnum.FIN_REGISTRO, "*"));
					records.add(details);
				}

				for (List<Field> record : records) {
					FlatFileGenerator.writeLine(writer, record);
				}

				writer.flush();
				writer.close();

			} catch (Exception e) {
				log.error("Error", e);
			}
		}
	}

	public Field headSCO(HeadScoEnum head, String value) {
		Field field = new Field();
		field.setHeadSCO(head, value);
		return field;
	}

	public Field detailSCO(DetailScoEnum head, String value) {
		Field field = new Field();
		field.setDetailSCO(head, value);
		return field;
	}

}
