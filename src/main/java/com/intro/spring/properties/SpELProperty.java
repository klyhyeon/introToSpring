package com.intro.spring.properties;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELProperty {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("joinedYear");
        Staff staff = new Staff();
        expression.setValue(staff, "2000");
        Integer joinedYear = staff.getJoinedYear();
    }
}
