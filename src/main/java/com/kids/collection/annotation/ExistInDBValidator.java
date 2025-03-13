package com.kids.collection.annotation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ExistInDBValidator implements ConstraintValidator<ExistInDatabase, Long> {

    private final EntityManager entityManager;
    @Autowired
    public ExistInDBValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private String table;
    private String column;

    @Override
    public void initialize(ExistInDatabase constraintAnnotation) {
        table = constraintAnnotation.table();
        column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }

        String query = String.format("select exists(select 1 from %s where %s=:value)", table, column);
        Boolean exists = (Boolean) entityManager.createNativeQuery(query)
                .setParameter("value", value)
                .getSingleResult();

        return exists != null && exists;
    }
}
