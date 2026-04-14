package com.nyw.cateringsystem.converter;

import com.nyw.cateringsystem.bean.TableInfo;
import com.nyw.cateringsystem.dto.TableInfoDTO;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className TableInfoConverter
 */
public class TableInfoConverter {

    public static TableInfoDTO converterToTableInfoDTO(TableInfo tableInfo) {
        return TableInfoDTO.builder()
                .id(tableInfo.getId())
                .tableNumber(tableInfo.getTableNumber())
                .tableStatus(tableInfo.getTableStatus())
                .capacity(tableInfo.getCapacity())
                .build();
    }

    public static TableInfo converterToTableInfo(TableInfoDTO tableInfoDTO) {
        return TableInfo.builder()
                .id(tableInfoDTO.getId())
                .tableNumber(tableInfoDTO.getTableNumber())
                .tableStatus(tableInfoDTO.getTableStatus())
                .capacity(tableInfoDTO.getCapacity())
                .build();
    }
}
