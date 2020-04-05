package com.diesgut.collectionflattextgenerator.repository;

import java.util.List;

import com.diesgut.collectionflattextgenerator.model.BankReference;
import com.diesgut.collectionflattextgenerator.model.enums.BankEnum;

public interface IBankReferene {

	List<BankReference> allByBankAndId(BankEnum bankEnum,Long... id);

}
