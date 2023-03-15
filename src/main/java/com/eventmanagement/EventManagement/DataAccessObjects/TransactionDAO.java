package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.interfaces.ITransaction;
import com.eventmanagement.EventManagement.rowmapper.TransactionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TransactionDAO implements ITransactionDAO
{
    public JdbcTemplate jdbcTemplate ;

    public TransactionDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    @Override
    public boolean addTransaction(int studentId, double amount, String transactionType, String description, String transactionSource)
    {
        try
        {
            String sql = "CALL add_transaction(?, ?, ?, ?, ?)";
            int res =  jdbcTemplate.update(sql, studentId,
                    amount,
                    transactionType,
                    description,
                    transactionSource);
            if(res == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<ITransaction> getTransactions(int studentId)
    {
        try
        {
            String sqlQuery = "CALL sel_transaction_by_student(?)";
            List<ITransaction> transactions = jdbcTemplate.query(sqlQuery, new TransactionRowMapper(), studentId);
            return transactions;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
