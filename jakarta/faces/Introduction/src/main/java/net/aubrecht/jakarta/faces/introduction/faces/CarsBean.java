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

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.aubrecht.jakarta.faces.introduction.faces.model.Car;
import net.aubrecht.jakarta.faces.introduction.services.CarDatabaseService;

/**
 *
 * @author aubi
 */
@Named(value = "carsBean")
@SessionScoped
public class CarsBean implements Serializable {
    @EJB
    private CarDatabaseService carDatabaseService;

    private List<Car> carsList = new ArrayList<>();

    private Car selectedCar = null;

    /**
     * Creates a new instance of CarsBean
     */
    public CarsBean() {
    }

    @PostConstruct
    public void initDb() {
        carsList = carDatabaseService.listAllCars();
    }

    public void clearSelectedCar() {
        selectedCar = null;
    }


    public List<Car> getCarsList() {
        return carsList;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    /**
     * Find car by registration, used by the CarConverter.
     */
    public Car findByRegistration(String registration) {
        return carDatabaseService.findByRegistration(registration);
    }

}
