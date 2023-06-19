package com.shopping;

import com.shopping.model.Car;

import java.util.ArrayList;
import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        List<Car> carList = new ArrayList<>();

        Car car = new Car();
        car.setCar_count(10);
        car.setTotal(100.0);
        car.setUser_id(90);
        car.setBook_image("sss");
        car.setBook_name("ddd");
        car.setBook_price(90.0);
        carList.add(car);



        Car car1 = new Car();
        car1.setCar_count(21);
        car1.setTotal(1212.0);
        car1.setUser_id(80);
        car1.setBook_image("sdsd");
        car1.setBook_name("wewwe");
        car1.setBook_price(92.0);
        carList.add(car1);


        System.out.println(carList);
        Car car2 = new Car();
        car2.setBook_id(90);
        carList.remove(car2);


        System.out.println(carList);


    }
}
