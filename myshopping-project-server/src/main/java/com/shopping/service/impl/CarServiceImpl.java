package com.shopping.service.impl;

import com.shopping.mapper.BookMapper;
import com.shopping.model.Book;
import com.shopping.model.Car;
import com.shopping.model.view.CarView;
import com.shopping.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private RedisTemplate redisTemplate;
    private HashOperations hashOperations;
    private BookMapper bookMapper;

    @Autowired
    public CarServiceImpl(RedisTemplate redisTemplate,BookMapper bookMapper) {
        this.redisTemplate = redisTemplate;
        this.bookMapper = bookMapper;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Car>(Car.class));
        //获得Redis操作对象
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void addCar(Car car) throws Exception {
        /**
         * 向Redis中添加一个商品
         */

      /* 版本1,只能添加1个商品
       List<Car> cars = new ArrayList<>();

        car.setTotal(car.getCar_count()*car.getBook_price());

        cars.add(car);
        //向redis中添加购物车信息
        hashOperations.put("cars","user:"+car.getUser_id(),cars);*/

        /**
         * 版本2
         */
       /* //用于存放购物车商品的list集合
        List<Car> cars = null;
        //检测redis中是否包含购物车(cars)
        boolean boo = redisTemplate.hasKey("cars");
        if(boo){//存在
            //根据key获得指定用户的购物信息
            cars = (List<Car>) hashOperations.get("cars", "user:" + car.getUser_id());
            boolean exists = false;//购物车中是否存在要添加的商品，默认为false(不存在)
            for(Car car1 : cars){
                if(car1.getBook_id().equals(car.getBook_id())){//检测购物车中是否存在新添加商品
                    //如果存在则数量增加、
                    car1.setCar_count(car1.getCar_count()+car.getCar_count());
                    //设置总价格
                    car1.setTotal(car1.getCar_count()*car1.getBook_price());
                    exists = true;
                    break;
                }
            }
            if(!exists){//购物车中不存在
                car.setTotal(car.getCar_count()*car.getBook_price());
                cars.add(car);//向购物车中新增商品
            }


        }else{
            //创建list集合
            cars = new ArrayList<>();
            car.setTotal(car.getCar_count()*car.getBook_price());
            cars.add(car);
        }
        //向redis中添加购物车信息
        hashOperations.put("cars","user:"+car.getUser_id(),cars);*/


        /**
         * 版本3
         */
        //声明用于存储商品的购物车集合(Map<bookId,Car>)
        Map<Integer,Car> carMap = null;
        //检测redis中是否包含购物车(cars)
        boolean boo = redisTemplate.hasKey("cars");
        if(boo){//存在
            //根据key获得指定用户的购物信息
            carMap = (Map<Integer,Car>) hashOperations.get("cars", "user:" + car.getUser_id());
            //判断当前用户的购物车是否存在
            if(carMap==null){//不存在，则创建购物车对象(Map)
                carMap = new HashMap<>();
            }

            //将新添加的商品在购物车中是否存在
            if(carMap.containsKey(car.getBook_id())){//存在
                //更新数量
                Car curCar = carMap.get(car.getBook_id());
                curCar.setCar_count(curCar.getCar_count()+car.getCar_count());
                System.out.println("--------购物车信息--------");
                System.out.println(carMap);
            }else{//不存在
                carMap.put(car.getBook_id(),car);//添加新的商品
            }
        }else{
            //则创建购物车对象(Map)
            carMap = new HashMap<>();
            carMap.put(car.getBook_id(),car);
        }
        //向redis中添加购物车信息
        hashOperations.put("cars","user:"+car.getUser_id(),carMap);
    }

    @Override
    public List<Car> getCarInfoByUserId(int user_id) {
        //从redis中获取购物车信息
        Map<Integer,Car> carMap = (Map<Integer,Car>) hashOperations.get("cars","user:"+user_id);
        if(carMap == null){
            return null;
        }
        List<Car> carList = new ArrayList<>();
        Collection<Car> cars = carMap.values();
        carList.addAll(cars);
        return carList;

    }

    @Override
    public Car getCarInfoById(int user_id, int book_id) {
        Map<Integer,Car> carMap = (Map<Integer,Car>) hashOperations.get("cars","user:"+user_id);
        return carMap.get(book_id);
    }

    @Override
    public void updateCarCount(Car car) {
        //从redis中获取当前用户的购物车
        Map<Integer,Car> carMap = (Map<Integer,Car>) hashOperations.get("cars","user:"+car.getUser_id());

        Car curCar = carMap.get(car.getBook_id());
        curCar.setCar_count(car.getCar_count());
        //向redis中添加购物车信息
        hashOperations.put("cars","user:"+car.getUser_id(),carMap);

    }

    @Override
    public void delCar(int user_id, int book_id) {
        //从redis中获取当前用户的购物车
        Map<Integer,Car> carMap = (Map<Integer,Car>) hashOperations.get("cars","user:"+user_id);
        carMap.remove(book_id);
        //向redis中添加购物车信息
        hashOperations.put("cars","user:"+user_id,carMap);
    }

    @Override
    public Integer getCarCount(Integer user_id) {
        //从redis中获取当前用户的购物车
        Map<Integer,Car> carMap = (Map<Integer,Car>) hashOperations.get("cars","user:"+user_id);
        return carMap.size();
    }

    /**
     * 根据用户编号获得用户的购物车信息
     * @param user_id
     * @return
     */
   /* @Override
    public List<CarView> getCarInfoByUserId(int user_id) {
        //从redis中获取购物车信息
        List<Car> carList = (List<Car>) hashOperations.get("cars","user:"+user_id);
        //将carList中的book_id封装到list集合中
        List<Integer> bookIdList = new ArrayList<>();
        for(Car car : carList){
            bookIdList.add(car.getBook_id());
        }

        //根据bookId获得对应的商品数据
        Integer[] bookIds = new Integer[bookIdList.size()];
        bookIdList.toArray(bookIds);
        List<Book> bookList = bookMapper.getBookById(bookIds);

        //将查询的商品转存到CarView对象集合中
        List<CarView> carViews = new ArrayList<>();

        for(Car car : carList){
            CarView carView = new CarView();
            //设置购买数量
            carView.setCar_count(car.getCar_count());
            carView.setBook_id(car.getBook_id());
            //获得商品数据
            for(Book book : bookList){
                if(book.getBook_id().equals(car.getBook_id())){
                    carView.setBook_image(book.getBook_image());
                    carView.setBook_name(book.getBook_name());
                    carView.setBook_price(book.getBook_price());
                    carView.setTotal(car.getCar_count()*book.getBook_price());
                    break;
                }
            }

            carViews.add(carView);
        }


        return carViews;
    }*/
}
