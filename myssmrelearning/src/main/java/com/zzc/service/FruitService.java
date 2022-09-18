package com.zzc.service;

import com.zzc.DataSourceSessionFactory;
import com.zzc.mappers.FruitMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author bling
 * @create 2022-09-18 23:26
 */
public class FruitService {
    public static void main(String[] args) throws IOException {
//        SqlSessionFactory sessionFactory = DataSourceSessionFactory.getSessionFactory(DataSourceSessionFactory.DataSourceEnvironment.Mybatis);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FruitMapper fruitMapper = sqlSession.getMapper(FruitMapper.class);
//        int count = fruitMapper.insertFruit();
//        System.out.println(count);
        fruitMapper.deleteFruit();
    }
}
