package com.nyw.cateringsystem.controller;

import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.dto.TableInfoDTO;
import com.nyw.cateringsystem.result.R;
import com.nyw.cateringsystem.service.TableService;
import jakarta.annotation.Resource;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className TableController
 */
@RestController
public class TableController {

    @Resource
    private TableService tableService;

    @GetMapping("/tables")
    public R<PageInfo<TableInfoDTO>> getAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int PageSize) {
        return R.ok(tableService.findByPage(pageNum, PageSize));
    }

    @GetMapping("/tables/{id}")
    public R<TableInfoDTO> getById(@PathVariable(required = false) Long id) {
        return R.ok(tableService.findById(id));
    }

    @GetMapping("/tables/{tableNumber}")
    public R<PageInfo<TableInfoDTO>> getByTableNumber(@PathVariable String tableNumber, @RequestParam(defaultValue = "1") int pageNum,
                                                      @RequestParam(defaultValue = "5") int PageSize) {
        return R.ok(tableService.findByTableNum(tableNumber, pageNum, PageSize));
    }

    @PostMapping("/tables")
    public R<Long> save(@RequestBody TableInfoDTO tableInfoDTO) {
        return R.ok(tableService.insert(tableInfoDTO));
    }

    @DeleteMapping("/tables")
    public R<Boolean> delete(@RequestParam(defaultValue = "") List<Long> id) {
        return R.ok(tableService.deleteByIds(id));
    }

    @PutMapping("/tables/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody TableInfoDTO tableInfoDTO) {
        return R.ok(tableService.updateByID(id,tableInfoDTO));
    }
}
