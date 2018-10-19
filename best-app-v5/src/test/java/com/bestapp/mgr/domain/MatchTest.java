package com.bestapp.mgr.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class MatchTest {
    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testConstraintsValidation() {
        Match match = new Match();
        match.setLocal("local");

        Set<ConstraintViolation<Match>> violations = validator.validate(match);
        assertThat(violations).isNotEmpty();
    }

}