package com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa;

import com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.projection.GetDataMutation;
import com.ciptadana.intra_day_loan_balance.database.oracle.entity.NativeEntity;
import com.ciptadana.intra_day_loan_balance.database.oracle.repository.jpa.projection.GetDataClient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DataTransactionJpaRepositoryOracle extends JpaRepository<NativeEntity, String> {

    @Query(value = "SELECT * FROM RDI_BALANCE_INTRADAY_LOG ORDER BY RECDATE, RECTIME ", nativeQuery = true)
    List<String> getDataExistOnOracle(
            @Param("trxDate") String trxDate
    );

    @Query(value =  "SELECT CLIENTCODE, NAME FROM CORE.RDI_BALANCE " +
                    "LEFT JOIN DENPASAR.CLIENT@BOS ON CLIENTCODE = CODE " +
                    "WHERE NOREK = :accountNo AND CLOSURED_BY IS NULL AND ROWNUM = 1 ", nativeQuery = true)
    GetDataClient getClientCodeFromRdiBalance(@Param("accountNo") String accountNo);

    @Query(value =  "SELECT CLIENT_ID CLIENTCODE, BANK_ACC_NAME_INVESTOR NAME FROM DENPASAR.CLIENT_PROPERTY@BOS " +
                    "WHERE BANK_ACC_NO_INVESTOR = :accountNo AND ROWNUM = 1 ", nativeQuery = true)
    GetDataClient getClientCodeFromClientProperty(@Param("accountNo") String accountNo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CORE.RDI_BALANCE_INTRADAY_LOG(RECDATE, RECTIME, CLIENTCODE, NOREK, AMOUNT, EXTERNALREF, REMARKS) " +
            "VALUES (:recDate, :recTime, :clientCode, :noRek, :amount, :externalRef, :remarks )", nativeQuery = true)
    void insertRdiBalanceIntraDayLog(@Param("recDate") String recDate,
                                     @Param("recTime") String recTime,
                                     @Param("clientCode") String clientCode,
                                     @Param("noRek") String noRek,
                                     @Param("amount") Long amount,
                                     @Param("externalRef") String externalRef,
                                     @Param("remarks") String remarks
    );
}
