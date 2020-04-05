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
import com.diesgut.collectionflattextgenerator.model.enums.collect.bbva.DetailBbvaEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.bbva.HeadBbvaEnum;
import com.diesgut.collectionflattextgenerator.repository.IBankReferene;
import com.diesgut.collectionflattextgenerator.service.ICollectService;

@Component("bbvaCollectServiceImp")
public class BbvaCollectServiceImp implements ICollectService {

	private static final Logger log = LoggerFactory.getLogger(BbvaCollectServiceImp.class);

	@Autowired
	private IBankReferene bankReferene;

	Random random = new Random();

	public void collectFileGenerator() {

		BankEnum bankEnum = BankEnum.BBVA;

		List<BankReference> banksReferences = bankReferene.allByBankAndId(bankEnum);
		List<String> accountsNumbers = banksReferences.stream().map(x -> x.getAccountNumber()).distinct()
				.collect(Collectors.toList());

		LocalDateTime now = LocalDateTime.now();
		String sNow = now.format(DateTimeFormatter.BASIC_ISO_DATE);

		String sTime = now.format(DateTimeFormatter.ofPattern("HHmmss"));

		LocalDateTime fechaVencimiento = LocalDateTime.now().plusDays(10L);
		String sFechaVencimiento = fechaVencimiento.format(DateTimeFormatter.BASIC_ISO_DATE);

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
				header.add(this.headBBVA(HeadBbvaEnum.TIPO_REGISTRO, "1"));
				header.add(this.headBBVA(HeadBbvaEnum.RUC_EMPRESA, "666"));
				header.add(this.headBBVA(HeadBbvaEnum.CODIGO_CLASE, "666"));
				header.add(this.headBBVA(HeadBbvaEnum.TIPO_MONEDA, bankReferenceFirst.getCurrencyEnum().getCodeBBVA()));
				header.add(this.headBBVA(HeadBbvaEnum.FECHA_PROCESO, sNow)); // "20201025"
				header.add(this.headBBVA(HeadBbvaEnum.CUENTA_RECAUDA, accountNumber));
				header.add(this.headBBVA(HeadBbvaEnum.VACIO, "1"));
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
					details.add(this.detailBBVA(DetailBbvaEnum.TIPO_REGISTRO, "2"));
					details.add(this.detailBBVA(DetailBbvaEnum.NOMBRE_CLIENTE, bankReference.getHolderFullName()));
					details.add(this.detailBBVA(DetailBbvaEnum.REFERENCIA, bankReference.getBankReference()));
					details.add(this.detailBBVA(DetailBbvaEnum.IMPORTE_ORIGEN, sOperationAmount));
					details.add(this.detailBBVA(DetailBbvaEnum.IMPORTE_DEPOSITO, sOperationAmount));
					details.add(this.detailBBVA(DetailBbvaEnum.IMPORTE_MORA, "0"));
					details.add(this.detailBBVA(DetailBbvaEnum.OFICINA_PAGO, "666"));
					details.add(this.detailBBVA(DetailBbvaEnum.NRO_MOVIMIENTO, sOperationNumber));
					details.add(this.detailBBVA(DetailBbvaEnum.FECHA_PAGO, sNow)); // 20201025"
					details.add(this.detailBBVA(DetailBbvaEnum.TIPO_VALOR, "01"));
					details.add(this.detailBBVA(DetailBbvaEnum.CANAL_ENTRADA, "01"));
					details.add(this.detailBBVA(DetailBbvaEnum.VACIO, ""));
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

	public Field headBBVA(HeadBbvaEnum head, String value) {
		Field field = new Field();
		field.setHeadBBVA(head, value);
		return field;
	}

	public Field detailBBVA(DetailBbvaEnum det, String value) {
		Field field = new Field();
		field.setDetailBBVA(det, value);
		return field;
	}

}
