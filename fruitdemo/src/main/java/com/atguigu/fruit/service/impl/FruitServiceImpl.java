package com.atguigu.fruit.service.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.service.FruitService;

import java.util.List;

/**
 * @author bling
 * @create 2022-09-15 10:03
 */
public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO = null;
    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(int fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public int getFruitCount(String keyword) {
        return fruitDAO.getFruitCount(keyword);
    }
}
