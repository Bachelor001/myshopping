package com.shopping.controller;

import com.shopping.commons.CurUser;
import com.shopping.commons.Result;
import com.shopping.model.Car;
import com.shopping.model.view.CarView;
import com.shopping.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
@SessionAttributes("curUser")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    HttpSession session;
    public CarController(){
        System.out.println("carController对象被撞见...");
    }
    /**
     * 添加购物
     * @param car
     * @return
     */
    @PostMapping("/addCar")
    public Result addCar(@RequestBody Car car,Model model){
        try {
            System.out.println("执行添加购物车方法.....");
            CurUser curUser = (CurUser) model.getAttribute("curUser");
            //设置当前登录的用户编号
            car.setUser_id(curUser.getUser_id());

            carService.addCar(car);
            return Result.success("购物车添加成功");
        } catch (Exception exception) {
            exception.printStackTrace();
            return Result.fail(500,"商品添加失败");
        }

    }

    /*@GetMapping("/getCarInfo")
    public List<CarView> getCarInfo(){
        //获得当前登陆者的编号
        int user_id = 93;
        return carService.getCarInfoByUserId(user_id);
    }*/

    @GetMapping("/getCarInfo")
    public List<Car> getCarInfo(){
        CurUser curUser = (CurUser) session.getAttribute("curUser");
        //获得当前登陆者的编号
        return carService.getCarInfoByUserId(curUser.getUser_id());
    }



    @PutMapping("/updateCarCount")
    public Result updateCarCount(@RequestBody Car car){
        CurUser curUser = (CurUser) session.getAttribute("curUser");
        car.setUser_id(curUser.getUser_id());
        carService.updateCarCount(car);
        return Result.success();
    }

    @DeleteMapping("/delCar")
    public Result delCar(int book_id, Model model){
        CurUser curUser = (CurUser) model.getAttribute("curUser");
        carService.delCar(curUser.getUser_id(),book_id);
        return Result.success();
    }

    @GetMapping("/getCarCount")
    public Integer getCarCount(){
        CurUser curUser = (CurUser) session.getAttribute("curUser");
        return carService.getCarCount(curUser.getUser_id());
    }
}
