package br.com.kenzie.learningSpring.service;

import br.com.kenzie.learningSpring.dto.CreateTransactionDto;
import br.com.kenzie.learningSpring.exception.AppException;
import br.com.kenzie.learningSpring.model.Transaction;
import br.com.kenzie.learningSpring.model.User;
import br.com.kenzie.learningSpring.repository.TransactionRepository;
import br.com.kenzie.learningSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service

public class TransactionsService {
    private  final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionsService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }
    public Transaction createTransaction (final CreateTransactionDto transactionData)  {
        final User foundPayer = userRepository.findById(transactionData.payer_id()).orElseThrow(
                () -> new AppException("payerNotFound", HttpStatus.NOT_FOUND));
        if(foundPayer.getType().equals("SELLER")){
            throw new AppException("invalidUserType", HttpStatus.FORBIDDEN);

        }

        final User foundPayee = userRepository.findById(transactionData.payee_id()).orElseThrow(
                () -> new AppException("payeeNotFound", HttpStatus.NOT_FOUND));

        final float payerCurrentBalance =  foundPayer.getBalance();
        final float payeeCurrentBalance =  foundPayee.getBalance();

        final float transactionValue = transactionData.value();


        if(payerCurrentBalance < transactionValue) {
            throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
        }

        foundPayer.setBalance(payerCurrentBalance - transactionData.value());
        foundPayee.setBalance(transactionData.value() + payeeCurrentBalance);





        final Transaction newTransaction = new Transaction(foundPayer, foundPayee, transactionData.value());

        return transactionRepository.save(newTransaction);


    }

    public Transaction retriveTransaction (final long id)  {
        return transactionRepository.findById(id).orElseThrow(
                () -> new AppException("transactionNotFound", HttpStatus.NOT_FOUND));
    }

}
