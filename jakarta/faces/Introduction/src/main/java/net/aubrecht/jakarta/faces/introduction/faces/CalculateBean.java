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
import jakarta.enterprise.context.RequestScoped;

/**
 * A simple CDI bean for calculation of GCD.
 *
 * @author aubi
 */
@Named(value = "calculateBean")
@RequestScoped
public class CalculateBean {

    private long input1 = 1;
    private long input2 = 1;
    private long result = 1; // null means not calculated
    /**
     * Creates a new instance of GCDBean
     */
    public CalculateBean() {
    }

    public void calculateGCD() {
        long i1 = Math.min(input1, input2);
        long i2 = Math.max(input1, input2);
        result = calc(i1, i2);
    }

    private long calc(long lower, long higher) {
        return lower > 0 ? calc(higher % lower, lower) : higher;
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
}
