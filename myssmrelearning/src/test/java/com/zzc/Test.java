package com.zzc;

import com.zzc.mappers.FruitMapper;
import com.zzc.pojo.Fruit;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author bling
 * @create 2022-09-18 22:28
 */
public class Test {
    @org.junit.Test
    public void test() {
        SqlSessionFactory sessionFactory = DataSourceSessionFactory.getSessionFactory(DataSourceSessionFactory.DataSourceEnvironment.Mybatis);
        SqlSession sqlSession = sessionFactory.openSession(true);
        FruitMapper fruitMapper = sqlSession.getMapper(FruitMapper.class);
//        int count = fruitMapper.insertFruit();
//        System.out.println(count);
        Fruit fruit = fruitMapper.getFruitByName("红富士");
        System.out.println(fruit.toString());


    }
}
