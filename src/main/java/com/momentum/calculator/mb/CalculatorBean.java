package com.momentum.calculator.mb;

import com.momentum.calculator.domain.Calculator;
import com.momentum.calculator.domain.CalculationAudit;
import com.momentum.calculator.domain.User;
import com.momentum.calculator.service.CalculationAuditService;
import com.momentum.calculator.service.UserService;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@ViewScoped
public class CalculatorBean extends BaseBean<CalculationAudit> {

    @Autowired
    private UserService userService;
    @Autowired
    private CalculationAuditService calculationAuditService;

    private float number1;
    private float number2;
    private float result;
    private int operator;

    public float getNumber1() {
        return number1;
    }

    public void setNumber1(float number1) {
        this.number1 = number1;
    }

    public float getNumber2() {
        return number2;
    }

    public void setNumber2(float number2) {
        this.number2 = number2;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String calculate() {
        try {
            Calculator cal = new Calculator();
            String operation = "";

            switch (operator) {
                case 0:
                    setResult(cal.add(number1, number2));
                    operation = "+";
                    break;
                case 1:
                    setResult(cal.sub(number1, number2));
                    operation = "-";
                    break;
                case 2:
                    setResult(cal.mul(number1, number2));
                    operation = "*";
                    break;
                case 3:
                    setResult(cal.div(number1, number2));
                    operation = "/";
                    break;
                default:
                    throw new RuntimeException("This operation is not supported");
            }

            // Create audit record
            CalculationAudit audit = new CalculationAudit();

            // Format the expression
            String expression = number1 + " " + operation + " " + number2;
            audit.setExpression(expression);
            audit.setResult("Result: " + result);

            // Get current user from session
            if (!getActiveUser().isUserLoginIndicator()) {
                addErrorMessage("No active user found. Please log in again.");
                return null;
            }

            // Get the User entity from the username
            User currentUser = userService.findUserByUserName(getActiveUser().getUsername());
            if (currentUser == null) {
                addErrorMessage("User information not found.");
                return null;
            }

            // Set audit fields including the User entity
            audit.setUser(currentUser);  // This is crucial - set the actual User entity
            audit.setCreatedBy(currentUser.getUserName());
            audit.setCreatedDate(new Date());

            calculationAuditService.save(audit);

            return "result.xhtml";
        } catch (Exception e) {
            addErrorMessage("Error: " + e.getMessage());
            return null;
        }
    }
}
