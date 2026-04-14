package com.nyw.cateringsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.TableInfo;
import com.nyw.cateringsystem.consts.StatusEnum;
import com.nyw.cateringsystem.converter.TableInfoConverter;
import com.nyw.cateringsystem.dto.TableInfoDTO;
import com.nyw.cateringsystem.exception.AlreadyExistException;
import com.nyw.cateringsystem.exception.SourceNotFoundException;
import com.nyw.cateringsystem.repository.TableInfoMapper;
import com.nyw.cateringsystem.service.TableService;
import com.nyw.cateringsystem.util.EnumUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className TableServiceImpl
 */
@Service
@Transactional
public class TableServiceImpl implements TableService {

    @Resource
    private TableInfoMapper tableInfoMapper;


    @Override
    public PageInfo<TableInfoDTO> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TableInfoDTO> tableInfoDTOS = new ArrayList<>();
        List<TableInfo> tableInfos = tableInfoMapper.selectAll();
        tableInfos.forEach(tableInfo -> {
            tableInfoDTOS.add(TableInfoConverter.converterToTableInfoDTO(tableInfo));
        });
        return new PageInfo<>(tableInfoDTOS);
    }

    @Override
    public PageInfo<TableInfoDTO> findByTableNum(String tableNumber,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TableInfo> tableInfos = tableInfoMapper.selectByTableNum(tableNumber.toUpperCase());
        List<TableInfoDTO> tableInfoDTOS = new ArrayList<>();
        if (tableInfos.isEmpty()) {
            throw new SourceNotFoundException("桌面不存在");
        }
        tableInfos.forEach(tableInfo -> {
            tableInfoDTOS.add(TableInfoConverter.converterToTableInfoDTO(tableInfo));
        });
        return new PageInfo<>(tableInfoDTOS);
    }

    @Override
    public TableInfoDTO findById(Long id) {
        TableInfo tableInfo = tableInfoMapper.selectById(id);
        if (tableInfo == null) {
            throw new SourceNotFoundException("找不到桌面");
        }
        return TableInfoConverter.converterToTableInfoDTO(tableInfo);
    }

    @Override
    public Long insert(TableInfoDTO tableInfoDTO) {
        TableInfo tableInfoTemp = tableInfoMapper.selectById(tableInfoDTO.getId());
        if (tableInfoTemp != null) {
            throw new AlreadyExistException("桌面已存在");
        }
        String tableStatusTemp = tableInfoDTO.getTableStatus();
        if (!EnumUtils.isBelongTo(tableStatusTemp, StatusEnum.class)) {
            throw new IllegalArgumentException("状态输入有误");
        }
        tableInfoDTO.setTableStatus(tableStatusTemp.toUpperCase());
        tableInfoDTO.setTableNumber(tableInfoDTO.getTableNumber().toUpperCase());
        TableInfo tableInfo = TableInfoConverter.converterToTableInfo(tableInfoDTO);
        tableInfoMapper.insert(tableInfo);
        return tableInfo.getId();
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("删除id不能为空");
        }
        ids.forEach(id -> {
            if (tableInfoMapper.selectById(id) == null) {
                throw new SourceNotFoundException("桌面不存在");
            }
        });
        return tableInfoMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updateByID(Long id,TableInfoDTO tableInfoDTO) {
        TableInfo tableInfo = tableInfoMapper.selectById(id);
        if (tableInfo == null) {
            throw new SourceNotFoundException("桌面不存在");
        }
        if (!EnumUtils.isBelongTo(tableInfoDTO.getTableStatus(), StatusEnum.class)) {
            throw new IllegalArgumentException("状态输入有误");
        }
        if (tableInfoDTO.getTableNumber() != null) {
            tableInfoDTO.setTableNumber(tableInfoDTO.getTableNumber().toUpperCase());
        }
        if (tableInfoDTO.getTableStatus() != null) {
            tableInfoDTO.setTableStatus(tableInfoDTO.getTableStatus().toUpperCase());
        }
        tableInfoDTO.setId(id);
        return tableInfoMapper.updateByID(TableInfoConverter.converterToTableInfo(tableInfoDTO)) > 0;
    }

}
