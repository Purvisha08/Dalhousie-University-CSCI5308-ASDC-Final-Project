package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.Transaction;
import com.eventmanagement.EventManagement.model.TransactionSource;
import com.eventmanagement.EventManagement.model.TransactionType;
import com.eventmanagement.EventManagement.model.interfaces.ITransaction;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<ITransaction> {
    @Override
    public ITransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactiondId(rs.getInt("transactionId"));
        transaction.setStudentId(rs.getInt("studentId"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setTransactonType(TransactionType.valueOf(rs.getString("transactionType")));
        transaction.setTransactionDate(rs.getTimestamp("transactionDate").toLocalDateTime());
        transaction.setDescription(rs.getString("description"));
        transaction.setTransactionSource(TransactionSource.valueOf(rs.getString("transactionSource")));
        return transaction;
    }
}
