package br.com.kenzie.learningSpring.controller;

import br.com.kenzie.learningSpring.dto.CreateTransactionDto;
import br.com.kenzie.learningSpring.model.Transaction;
import br.com.kenzie.learningSpring.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    final TransactionsService transactionsService;



    public TransactionController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody final CreateTransactionDto transactionData )  {
        final Transaction createdTransaction = transactionsService.createTransaction(transactionData);

        return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> retriveTransaction (@Valid @PathVariable final String id) throws  Exception{
        final Transaction transaction = transactionsService.retriveTransaction(Long.parseLong(id));
        return new ResponseEntity<Transaction>(transaction,HttpStatus.OK);


    }


}
