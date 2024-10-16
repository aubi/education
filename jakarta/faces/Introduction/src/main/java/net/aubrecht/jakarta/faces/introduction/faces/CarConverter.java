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

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import net.aubrecht.jakarta.faces.introduction.faces.model.Car;

/**
 *
 * @author aubi
 */
@FacesConverter(value = "carConverter", managed = true)
public class CarConverter implements Converter {

    @Inject
    private CarsBean carsBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        // Convert the string id to a Car object
        Car selectedCar = carsBean.findByRegistration(value);
        return selectedCar;  // Return null if no car is found
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Car)) {
            return "";
        }

        // Convert the Car object to its id as a string
        Car car = (Car) value;
        return car.getRegistration();
    }
}
