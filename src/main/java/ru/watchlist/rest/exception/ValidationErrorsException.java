package ru.watchlist.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import javax.validation.ValidationException;
import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorsException extends ValidationException {

    private Errors errors;

    public List<ObjectError> getAllErrors() {
        return errors.getAllErrors();
    }
}
