package com.shopping.service;

import com.shopping.model.Car;
import com.shopping.model.view.CarView;

import java.util.List;

public interface CarService {
    void addCar(Car car) throws Exception;

    //List<CarView> getCarInfoByUserId(int user_id);
    List<Car> getCarInfoByUserId(int user_id);

    Car getCarInfoById(int user_id,int book_id);

    /**
     * 修改购物车中商品的数量
     * @param car
     * @return
     */
    void updateCarCount(Car car);

    void delCar(int user_id, int book_id);

    Integer getCarCount(Integer user_id);


}
