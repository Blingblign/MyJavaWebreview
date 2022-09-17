package com.atguigu.fruit.service;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author bling
 * @create 2022-09-15 10:02
 */
public interface FruitService {
    void updateFruit(Fruit fruit);
    Fruit getFruitByFid(Integer fid);

    void delFruit(int fid);

    void addFruit(Fruit fruit);

    List<Fruit> getFruitList(String keyword, Integer pageNo);

    int getFruitCount(String keyword);
}
