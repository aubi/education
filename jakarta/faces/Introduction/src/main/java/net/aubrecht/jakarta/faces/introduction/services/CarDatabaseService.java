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
package net.aubrecht.jakarta.faces.introduction.services;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.aubrecht.jakarta.faces.introduction.faces.model.Car;

/**
 *
 * @author aubi
 */
@Singleton
public class CarDatabaseService {
    private List<Car> database = new ArrayList<>();

    @PostConstruct
    public void initDb() {
        database.add(new Car("ABC1234", "VW", LocalDate.of(2015, 3, 25), "blue"));
        database.add(new Car("DEF5678", "Skoda", LocalDate.of(2012, 9, 15), "red"));
        database.add(new Car("GHI9012", "Audi", LocalDate.of(2022, 1, 3), "black"));
        database.add(new Car("JKL3456", "Mercedes", LocalDate.of(2019, 11, 1), "blue"));
        database.add(new Car("XYZ789", "Ford", LocalDate.of(2019, 3, 15), "blue"));
        database.add(new Car("LMN456", "Tesla", LocalDate.of(2021, 5, 20), "gray"));
    }

    public List<Car> listAllCars() {
        return Collections.unmodifiableList(database);
    }

    /**
     * Find car by registration, used by the CarConverter.
     */
    public Car findByRegistration(String registration) {
        return database.stream()
                .filter(c -> c.getRegistration().equals(registration))
                .findFirst()
                .orElse(null);
    }

}
