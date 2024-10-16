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
import jakarta.faces.model.DataModel;
import jakarta.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import net.aubrecht.jakarta.faces.introduction.faces.model.Car;
import net.aubrecht.jakarta.faces.introduction.services.CarDatabaseService;

/**
 *
 * @author aubi
 */
@Named(value = "tableCarsBean")
@SessionScoped
public class TableCarsBean implements Serializable {
    @EJB
    private CarDatabaseService carDatabaseService;

    private DataModel<Car> carsList;

    private Car selectedCar = null;

    private String selectedCarParameter = null;

    /**
     * Creates a new instance of CarsBean
     */
    public TableCarsBean() {
    }

    @PostConstruct
    public void initDb() {
        carsList = new ListDataModel<>(new ArrayList<>(carDatabaseService.listAllCars()));
    }

    /**
     * Load bookmarkable detail page data.
     */
    public void loadSelectedCar() {
        selectedCar = carDatabaseService.findByRegistration(selectedCarParameter);
    }

    public void selectCar() {
        selectedCar = carsList.getRowData();
    }

    public String goToDetail() {
        selectedCar = carsList.getRowData();
        return "table-detail.xhtml";
    }

    public String goToDetailRedirect() {
        selectedCar = carsList.getRowData();
        return "table-detail.xhtml?faces-redirect=true";
    }

    public void clearSelectedCar() {
        selectedCar = null;
    }

    public DataModel<Car> getCarsList() {
        return carsList;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public String getSelectedCarParameter() {
        return selectedCarParameter;
    }

    public void setSelectedCarParameter(String selectedCarParameter) {
        this.selectedCarParameter = selectedCarParameter;
    }
}
