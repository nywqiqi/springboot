package com.nyw.cateringsystem.repository;

import com.nyw.cateringsystem.bean.TableInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableInfoMapper {
    int deleteByIds(List<Long> ids);

    int insert(TableInfo tableInfo);

    TableInfo selectById(Long id);

    List<TableInfo> selectAll();

    List<TableInfo> selectByTableNum(String tableNumber);

    int updateByID(TableInfo tableInfo);

}