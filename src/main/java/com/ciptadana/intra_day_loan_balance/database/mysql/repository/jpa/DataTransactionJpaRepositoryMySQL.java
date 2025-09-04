package com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa;

import com.ciptadana.intra_day_loan_balance.database.mysql.entity.NativeEntity;
import com.ciptadana.intra_day_loan_balance.database.mysql.repository.jpa.projection.GetDataMutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataTransactionJpaRepositoryMySQL extends JpaRepository<NativeEntity, String> {

    @Query(value = "SELECT * FROM v_all_bank_mutasi " +
                    "WHERE vdate = :trxDate " +
                    "AND  vdate >= :trxTime " +
                    "AND remarks NOT LIKE 'Remarks KI%' " +
                    "AND remarks NOT LIKE 'Remarks Internal K%' " +
                    "AND remarks NOT LIKE '1-1' " +
                    "AND remarks NOT LIKE 'Remarks KI-Contrabal-Receipt' " +
                    "ORDER BY vdate, vtime ", nativeQuery = true)
    List<GetDataMutation> getDataMutation(
            @Param("trxDate") String trxDate,
            @Param("trxTime") int trxTime
    );

}
