package ru.watchlist.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.util.FieldUtils;
import ru.watchlist.domain.User;
import ru.watchlist.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

   @Autowired
   private UserRepository userRepository;

   private UniqueFields fieldName;

   @Override
   public void initialize(UniqueField constraint) {
      this.fieldName = constraint.fieldName();
   }

   public boolean isValid(Object object, ConstraintValidatorContext context) {

      String fieldValue;
      Long id;

      try {
         fieldValue = (String) FieldUtils.getFieldValue(object, fieldName.getName());
         id = (Long) FieldUtils.getFieldValue(object, "id");
      } catch (IllegalAccessException ex) {
         ex.printStackTrace();
         return false;
      }

      switch (fieldName) {
         case EMAIL: {
            User user = userRepository.findByEmail(fieldValue);
            if (user != null) {
               return (id != null && id.equals(user.getId())) || !fieldValue.equals(user.getEmail());
            } else {
               return true;
            }
         }
         case USERNAME: {
            User user = userRepository.findByUsername(fieldValue);
            if (user != null) {
               return (id != null && id.equals(user.getId())) || !fieldValue.equals(user.getUsername());
            } else {
               return true;
            }
         }
      }
      return false;
   }
}
