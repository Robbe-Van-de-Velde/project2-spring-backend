package project2team17.Product.controllers;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project2team17.Product.model.BankAccount;
import project2team17.Product.repositories.BankAccountsRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@CrossOrigin( origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountRestController {

    BankAccountsRepository repository;

    @Autowired
    public void setProductRepository(BankAccountsRepository repository){
        this.repository = repository;

        BankAccount acc1 = new BankAccount();
        acc1.setAccountNumber("r0543212");
        acc1.setAmount(50.0);
        acc1.setName("Robbe");

        BankAccount acc2 = new BankAccount();
        acc2.setAccountNumber("r0512454");
        acc2.setAmount(80.0);
        acc2.setName("Test");
        this.repository.save(acc1);
        this.repository.save(acc2);
    }

    @GetMapping("/overview")
    public Iterable<BankAccount> getAll(){
        return repository.findAll();
    }

    @PostMapping("/add")
    public BankAccount addBankAccount(@Valid @RequestBody BankAccount bankAccount){
        return repository.save(bankAccount);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException)ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }
        else if (ex instanceof ServiceException) {
            errors.put(((ServiceException) ex).getLocalizedMessage(), ex.getMessage());
        }
        else {
            errors.put(((ResponseStatusException)ex).getReason(), ex.getCause().getMessage());
        }
        return errors;
    }
}
