/*
 * The MIT License
 *
 * Copyright 2024 aubi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.aubrecht.jakarta.faces.introduction.faces;

import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A little bit more complex CDI bean for a GCD calculation with validation and
 * a bit of navigation.
 *
 * @author aubi
 */
@Named(value = "calculateWithValidationsBean")
@ViewScoped
public class CalculateWithValidationsBean implements Serializable {

    private Long input1 = null;
    private Long input2 = null;
    private Long result = null; // null means not calculated

    List<String> explanation = null;

    /**
     * Creates a new instance of GCDBean
     */
    public CalculateWithValidationsBean() {
    }

    public String calculateGCD() {
        long i1 = Math.min(input1, input2);
        long i2 = Math.max(input1, input2);
        result = calc(i1, i2);
        return null; // stay in the same form
    }

    public String calculateGCDWithExplanation() {
        long i1 = Math.min(input1, input2);
        long i2 = Math.max(input1, input2);
        explanation = new ArrayList<>();
        result = calcWithExplanation(i1, i2);

//        return "gcd-explanation"; // go to explanation
        return "gcd-explanation?faces-redirect=true";
    }

    private long calc(long lower, long higher) {
        return lower > 0 ? calc(higher % lower, lower) : higher;
    }

    private long calcWithExplanation(long lower, long higher) {
        if (lower > 0) {
            explanation.add("%d %% %d = %d".formatted(higher, lower, higher % lower));
            return calcWithExplanation(higher % lower, lower);
        } else {
            explanation.add("Result is %,d".formatted(higher));
            return higher;
        }
    }

    public void validateNum(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Number must be entered!", null));
        }
        String strValue = value.toString();
        try {
            long longValue = Long.parseLong(strValue);
            if (longValue <= 0) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Number must be higher than 0!", null));
            }
            if (longValue % 2 == 1) {
                FacesContext.getCurrentInstance().addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "Really? Odd number?", "Really? Odd number? Odd numbers are boring!"));
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entered string '%s' is not a number!!".formatted(strValue), null));
        }
    }

    /**
     * @return true if the result should be displayed, if it was already
     * calculated
     */
    public boolean getDisplayResult() {
        return result != null;
    }

    public Long getInput1() {
        return input1;
    }

    public void setInput1(Long input1) {
        this.input1 = input1;
    }

    public Long getInput2() {
        return input2;
    }

    public void setInput2(Long input2) {
        this.input2 = input2;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public List<String> getExplanation() {
        return explanation;
    }

    public void setExplanation(List<String> explanation) {
        this.explanation = explanation;
    }

}
