package com.zzc;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author bling
 * @create 2022-09-18 22:01
 */


public final class DataSourceSessionFactory {

    private static String MybatisConfigPath = "mybatis-config.xml";
    private static Map<DataSourceEnvironment, SqlSessionFactory> sessionFactoryMap = new HashMap<>();

    /**
     * Mybatis 配置的 Environment 中的数据源的枚举类型（即 Environment 的 id 属性值）
     */
    public static enum DataSourceEnvironment {
        Spring,
        Mybatis;
    }

    public static SqlSessionFactory getSessionFactory(DataSourceEnvironment dataSourceEnvironment) {
        SqlSessionFactory sqlSessionFactory = DataSourceSessionFactory.sessionFactoryMap.get(dataSourceEnvironment);
        sqlSessionFactory = Optional.ofNullable(sqlSessionFactory).orElseGet(()->{
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(DataSourceSessionFactory.MybatisConfigPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SqlSessionFactory temp = new SqlSessionFactoryBuilder().build(inputStream, dataSourceEnvironment.name());
            sessionFactoryMap.put(dataSourceEnvironment, temp);
            return temp;
        });

        return sqlSessionFactory;
    }
}

