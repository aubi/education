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
package net.aubrecht.jakarta.faces.introduction.faces.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author aubi
 */
public class Car {
    private String registration;
    private String manufacturer;
    private LocalDate assembly;
    private String color;

    public Car() {
    }

    public Car(String registration, String manufacturer, LocalDate assembly, String color) {
        this.registration = registration;
        this.manufacturer = manufacturer;
        this.assembly = assembly;
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.registration);
        hash = 37 * hash + Objects.hashCode(this.manufacturer);
        hash = 37 * hash + Objects.hashCode(this.assembly);
        hash = 37 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.registration, other.registration)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return Objects.equals(this.assembly, other.assembly);
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getAssembly() {
        return assembly;
    }

    public void setAssembly(LocalDate assembly) {
        this.assembly = assembly;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
