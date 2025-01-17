package com.momentum.calculator.mb;

import com.momentum.calculator.domain.CalculationAudit;
import com.momentum.calculator.domain.User;
import com.momentum.calculator.service.CalculationAuditService;
import com.momentum.calculator.service.UserService;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@ManagedBean
@ViewScoped
public class AdvCalculatorBean extends BaseBean<CalculationAudit> {

    private static final Map<String, Double> CONSTANTS = new HashMap<>();

    static {
        CONSTANTS.put("pi", Math.PI);
        CONSTANTS.put("e", Math.E);
    }

    @Autowired
    private CalculationAuditService calculationAuditService;
    
    @Autowired
    private UserService userService;
    
    private String inputExpression = "";
    private String result = "";

    public String getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(String inputExpression) {
        this.inputExpression = inputExpression != null ? inputExpression : "";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void appendToExpression(String value) {
        this.inputExpression = (this.inputExpression != null ? this.inputExpression : "") + value;
    }

    public void clearExpression() {
        this.inputExpression = "";
        this.result = "";
    }

   public void calculate() {
    try {
        if (inputExpression == null || inputExpression.trim().isEmpty()) {
            this.result = "Error: Empty expression";
            return;
        }
        double evaluation = evaluateExpression(inputExpression);
        this.result = "Result: " + evaluation;
        
        // Create audit record
        CalculationAudit audit = new CalculationAudit();
        audit.setExpression(inputExpression);
        audit.setResult(this.result);
        
        // Get the actual logged-in user from active user bean
        if (!getActiveUser().isUserLoginIndicator()) {
            addErrorMessage("No active user found. Please log in again.");
            return;
        }
        
        String username = getActiveUser().getUsername();
        User user = userService.findUserByUserName(username);
        
        if (user == null) {
            addErrorMessage("User information not found.");
            return;
        }
        
        // Set audit fields using the active user information
        audit.setUser(user);  // This is crucial - set the actual User entity
        audit.setCreatedBy(username);
        audit.setCreatedDate(new Date());
        
        calculationAuditService.save(audit);
        
    } catch (Exception e) {
        this.result = "Error: " + e.getMessage();
        addErrorMessage(e.getMessage());
    }
}

    private double evaluateExpression(String expression) {
        try {
            expression = expression.replaceAll("\\s+", "").toLowerCase();
            
            // Replace constants
            for (Map.Entry<String, Double> entry : CONSTANTS.entrySet()) {
                expression = expression.replace(entry.getKey(), entry.getValue().toString());
            }
            
            return evaluatePostfix(infixToPostfix(expression));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    private int getPrecedence(char operator) {
        switch (operator) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return 0;
        }
    }

    private String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean lastWasNumber = false;

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                if (lastWasNumber) {
                    postfix.append(c);
                } else {
                    postfix.append(' ').append(c);
                }
                lastWasNumber = true;
            } else if (c == '(') {
                stack.push(c);
                lastWasNumber = false;
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(' ').append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop(); // Remove '('
                lastWasNumber = false;
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '(' && 
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    postfix.append(' ').append(stack.pop());
                }
                stack.push(c);
                lastWasNumber = false;
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(' ').append(stack.pop());
        }

        return postfix.toString();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.trim().split("\\s+");

        for (String token : tokens) {
            if (token.length() == 1 && isOperator(token.charAt(0))) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(performOperation(a, b, token.charAt(0)));
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    private double performOperation(double a, double b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new IllegalArgumentException("Division by zero");
                return a / b;
            case '^': return Math.pow(a, b);
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
