package com.diesgut.collectionflattextgenerator.repository.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.diesgut.collectionflattextgenerator.model.BankReference;
import com.diesgut.collectionflattextgenerator.model.enums.BankEnum;
import com.diesgut.collectionflattextgenerator.repository.IBankReferene;

@Repository
public class BankReferenceJdbc implements IBankReferene{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
    public List<BankReference> allByBankAndId(BankEnum bankEnum,Long... id) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("BANK", bankEnum.getCode());
        if(id.length!=BigDecimal.ZERO.intValue()) {
        	mapSqlParameterSource.addValue("IDS", Arrays.asList(id));
        }
        StringBuilder sQuery=new StringBuilder();
        sQuery.append(" SELECT  ");
        sQuery.append(" 	br.id_bank_reference_pk,   ");
        sQuery.append(" 	br.bank_reference,   ");
        sQuery.append("  	br.currency, ");
        sQuery.append("  	ba.account_number, ");
        sQuery.append("  	h.full_name as holderFullName");
        sQuery.append(" FROM	  ");
        sQuery.append(" 	bank_reference br   ");
        sQuery.append(" INNER JOIN	  ");
        sQuery.append(" 	bank_account ba ON br.id_bank_account_fk=ba.id_bank_account_pk  ");
        sQuery.append(" INNER JOIN	  ");
        sQuery.append(" 	bank b ON b.id_bank_pk=ba.id_bank_fk  ");
        sQuery.append(" INNER JOIN	  ");
        sQuery.append(" 	holder h ON h.id_holder_pk=br.id_holder_fk  ");
        sQuery.append(" WHERE	  ");
        sQuery.append(" 1=1	  ");
        sQuery.append(" and 	br.register_status!=1349  ");
        sQuery.append(" and 	b.id_bank_pk=:BANK  ");
        if(id.length!=BigDecimal.ZERO.intValue()) {
        	sQuery.append(" and 	br.id_bank_reference_pk in (:IDS)  ");
        }
        
        //dont work
//        return namedParameterJdbcTemplate.query(
//        		sQuery.toString(),
//                mapSqlParameterSource,
//                new RowMapper<BankReference>() {
//                    public BankReference mapRow(ResultSet rs, int rowNum) throws SQLException {
//            			BankReference bankReference= new BankReference();
//            			bankReference.setIdBankReferencePk( rs.getLong( "id_bank_reference_pk" ) );
//            			bankReference.setCurrency( rs.getInt( rs.getInt( "currency" ) ) );
//            			bankReference.setAccountNumber(  rs.getString( "account_number" )  );
//            			return bankReference;
//                    }
//				}
//        );
//        return namedParameterJdbcTemplate.query(
//        		sQuery.toString(),
//        		mapSqlParameterSource,
//        		(rs, rowNum) -> {
//        			BankReference bankReference= new BankReference();
//        			bankReference.setIdBankReferencePk( rs.getLong( "id_bank_reference_pk" ) );
//        			bankReference.setCurrency( rs.getInt( rs.getInt( "currency" ) ) );
//        			bankReference.setAccountNumber(  rs.getString( "account_number" )  );
//        			return bankReference;
//        		}
//        		);
        //work
//        return namedParameterJdbcTemplate.query(
//        		sQuery.toString(),
//                mapSqlParameterSource,
//                (rs, rowNum) ->
//                        new BankReference(
//                                rs.getLong("id_bank_reference_pk"),
//                                rs.getInt("currency"),
//                                rs.getString("account_number")
//                        )
//        );
      
        List<BankReference> banksReferences=new ArrayList<>();
        List<Map<String, Object>> rows = namedParameterJdbcTemplate.queryForList(sQuery.toString(), mapSqlParameterSource);
        for (Map row : rows) {
        	BankReference bankReference= new BankReference();
        	
        	bankReference.setIdBankReferencePk( (Long)row.get( "id_bank_reference_pk" ) );
        	bankReference.setCurrency( Integer.parseInt( row.get("currency").toString() ));
        	bankReference.setAccountNumber( (String) row.get( "account_number" )  );
        	bankReference.setHolderFullName( (String) row.get( "holderFullName" )  );
        	bankReference.setBankReference( (String) row.get( "bank_reference" )  );
            banksReferences.add(bankReference);
        }
        return banksReferences;
        
    }
	
	
//    public Customer findByCustomerId2(Long id) {
//
//        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";
//
//        return (Customer) jdbcTemplate.queryForObject(
//			sql, 
//			new Object[]{id}, 
//			new BeanPropertyRowMapper(Customer.class));
//
//    }

}
