package com.nyw.cateringsystem.service;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.TableInfo;
import com.nyw.cateringsystem.dto.TableInfoDTO;

import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className TableService
 */
public interface TableService {

    /**
     * 分页查询所有
     **/
    PageInfo<TableInfoDTO> findByPage(int pageNum, int pageSize);

    /**
     * 根据桌号查询桌面
     **/
    PageInfo<TableInfoDTO> findByTableNum(String tableNumber,int pageNum,int pageSize);

    /**
     * 根据id查询桌面
     **/
    TableInfoDTO findById(Long id);

    /**
     * 保存桌面信息
     **/
    Long insert(TableInfoDTO tableInfoDTO);

    /**
     * 根据id删除桌面
     **/
    boolean deleteByIds(List<Long> ids);

    /**
     * 根据id更新桌面
     **/
    boolean updateByID(Long id,TableInfoDTO tableInfoDTO);

}
