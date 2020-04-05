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
import com.diesgut.collectionflattextgenerator.model.enums.collect.bcp.DetailBcpEnum;
import com.diesgut.collectionflattextgenerator.model.enums.collect.bcp.HeadBcpEnum;
import com.diesgut.collectionflattextgenerator.repository.IBankReferene;
import com.diesgut.collectionflattextgenerator.service.ICollectService;

@Component("bcpCollectServiceImp")
public class BcpCollectServiceImp implements ICollectService {

	private static final Logger log = LoggerFactory.getLogger(BcpCollectServiceImp.class);

	@Autowired
	private IBankReferene bankReferene;

	Random random = new Random();

	public void collectFileGenerator() {

		BankEnum bankEnum = BankEnum.BCP;

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
				header.add(this.headBCP(HeadBcpEnum.TIPO_REGISTRO, "CC"));
				header.add(this.headBCP(HeadBcpEnum.CODIGO_CUENTA, accountNumber));
				header.add(this.headBCP(HeadBcpEnum.CODIGO_MONEDA, bankReferenceFirst.getCurrencyEnum().getCodeBCP()));
				header.add(this.headBCP(HeadBcpEnum.NRO_CTA_EMP_AFI, accountNumber));
				header.add(this.headBCP(HeadBcpEnum.TIPO_VALIDACION, "C")); // COMPLETA
				header.add(this.headBCP(HeadBcpEnum.FECHA_PROCESO, sNow));
				header.add(this.headBCP(HeadBcpEnum.CANTIDAD_TOTAL_REGISTROS, "666"));
				header.add(this.headBCP(HeadBcpEnum.MONTO_TOTAL, "10000"));
				header.add(this.headBCP(HeadBcpEnum.CODIGO_INTERNO_CTA_RECAUDA, "666"));
				header.add(this.headBCP(HeadBcpEnum.CASILLA_USU_TELE_TRANF, "666"));
				header.add(this.headBCP(HeadBcpEnum.HORA_CORTE, sTime)); // "050144"
				header.add(this.headBCP(HeadBcpEnum.CODIGO_SERVICIO, "666"));
				header.add(this.headBCP(HeadBcpEnum.CAMPO_LIBRE, ""));
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
					details.add(this.detailBCP(DetailBcpEnum.TIPO_REGISTRO, "DD"));
					details.add(this.detailBCP(DetailBcpEnum.CODIGO_CUENTA, accountNumber));
					details.add(this.detailBCP(DetailBcpEnum.CODIGO_MONEDA, bankReference.getCurrencyEnum().getCodeBCP()));
					details.add(this.detailBCP(DetailBcpEnum.NRO_CTA_EMP_AFI, accountNumber));
					details.add(this.detailBCP(DetailBcpEnum.CODIGO_ID_DEPOSITANTE, bankReference.getBankReference()));
					details.add(this.detailBCP(DetailBcpEnum.ADITIONAL_DATA_DEPOSITANTE, ""));
					details.add(this.detailBCP(DetailBcpEnum.PAYMENT_DATE, sNow));
					details.add(this.detailBCP(DetailBcpEnum.FECHA_VENCIMIENTO, sFechaVencimiento));
					details.add(this.detailBCP(DetailBcpEnum.MONTO_PAGADO, sOperationAmount));
					details.add(this.detailBCP(DetailBcpEnum.MONTO_MORA, "0"));
					details.add(this.detailBCP(DetailBcpEnum.MONTO_TOTAL, sOperationAmount));
					details.add(this.detailBCP(DetailBcpEnum.SUCURSAL, "666"));
					details.add(this.detailBCP(DetailBcpEnum.NRO_OPERACION, sOperationNumber));
					details.add(this.detailBCP(DetailBcpEnum.REFERENCIA, bankReference.getBankReference()));
					details.add(this.detailBCP(DetailBcpEnum.TERMINAL_ID, "666"));
					details.add(this.detailBCP(DetailBcpEnum.MEDIO_ATENCION, "666"));
					details.add(this.detailBCP(DetailBcpEnum.HORA_ATENCION, sTime));
					details.add(this.detailBCP(DetailBcpEnum.NRO_CHEQUE, ""));
					details.add(this.detailBCP(DetailBcpEnum.CODIGO_BANCO, ""));
					details.add(this.detailBCP(DetailBcpEnum.CARGO_FIJO_PAGADO, ""));
					details.add(this.detailBCP(DetailBcpEnum.ES_EXTORNADO, ""));
					details.add(this.detailBCP(DetailBcpEnum.NRO_DOCUMENTO_PAGO, bankReference.getBankReference()));
					details.add(this.detailBCP(DetailBcpEnum.NRO_OPERACION_BD, "666"));
					details.add(this.detailBCP(DetailBcpEnum.CAMPO_LIBRE, ""));
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

	public Field headBCP(HeadBcpEnum head, String value) {
		Field field = new Field();
		field.setHeadBCP(head, value);
		return field;
	}

	public Field detailBCP(DetailBcpEnum det, String value) {
		Field field = new Field();
		field.setDetailBCP(det, value);
		return field;
	}

}
