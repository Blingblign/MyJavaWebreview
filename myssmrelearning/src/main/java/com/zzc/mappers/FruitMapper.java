package com.zzc.mappers;

import com.zzc.pojo.Fruit;
import org.apache.ibatis.annotations.Param;

/**
 * @author bling
 * @create 2022-09-18 22:17
 */
public interface FruitMapper {
    /**
     * 添加水果
     *
     */
    int insertFruit();
    void deleteFruit();
    Fruit getFruitByName(String name);
}
