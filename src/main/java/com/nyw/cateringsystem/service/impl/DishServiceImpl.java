package com.nyw.cateringsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nyw.cateringsystem.bean.Dish;
import com.nyw.cateringsystem.converter.DishConverter;
import com.nyw.cateringsystem.dto.DishDTO;
import com.nyw.cateringsystem.exception.AlreadyExistException;
import com.nyw.cateringsystem.exception.SourceNotFoundException;
import com.nyw.cateringsystem.repository.DishMapper;
import com.nyw.cateringsystem.service.DishService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className DishServiceImpl
 */
@Transactional
@Service
public class DishServiceImpl implements DishService {

    @Resource
    private DishMapper dishMapper;

    @Override
    public DishDTO findById(Long dishId) {
        Dish dish = dishMapper.selectByPrimaryKey(dishId);
        if (dish == null) {
            throw new SourceNotFoundException();
        }
        return DishConverter.convertToDishDTO(dish);
    }

    @Override
    public List<DishDTO> findAll(boolean isCustomer) {
        List<DishDTO> dishDTOList = new ArrayList<>();
        List<Dish> dishes = dishMapper.selectAll(isCustomer);
        dishes.forEach(dish -> {
            dishDTOList.add(DishConverter.convertToDishDTO(dish));
        });
        return dishDTOList;
    }

    @Override
    public PageInfo<DishDTO> findByPage(int pageNum, int pageSize,boolean isCustomer) {
        PageHelper.startPage(pageNum, pageSize);
        List<DishDTO> dishDTOList = findAll(isCustomer);
        PageInfo<DishDTO> pageInfo = new PageInfo<>(dishDTOList);
        return pageInfo;
    }

    @Override
    public PageInfo<DishDTO> findByName(String name) {
        List<DishDTO> dishDTOList = new ArrayList<>();
        List<Dish> dishes = dishMapper.selectByName(name);
        dishes.forEach(dish -> {
            dishDTOList.add(DishConverter.convertToDishDTO(dish));
        });
        PageInfo<DishDTO> pageInfo = new PageInfo<>(dishDTOList);
        return pageInfo;
    }

    @Override
    public PageInfo<DishDTO> findByCategory(String category) {
        List<DishDTO> dishDTOList = new ArrayList<>();
        List<Dish> dishes = dishMapper.selectByCategory(category);
        dishes.forEach(dish -> {
            dishDTOList.add(DishConverter.convertToDishDTO(dish));
        });
        PageInfo<DishDTO> pageInfo = new PageInfo<>(dishDTOList);
        return pageInfo;
    }

    @Override
    public Long save(DishDTO dishDTO) {
        if (!dishMapper.selectByName(dishDTO.getName()).isEmpty()) {
            throw new AlreadyExistException("菜品已存在");
        }
        Dish dish = DishConverter.convertToDish(dishDTO);
        dishMapper.insert(dish);
        return dish.getId();
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw  new IllegalArgumentException("删除id不能为空");
        }
        ids.forEach(id -> {
            if (dishMapper.selectByPrimaryKey(id) == null) {
                throw new SourceNotFoundException("菜品不存在");
            }
        });
        return dishMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updateDishById(DishDTO dishDTO) {
        Dish dish = dishMapper.selectByPrimaryKey(dishDTO.getId());
        if (dish == null) {
            throw new SourceNotFoundException();
        }
        Dish dishTemp = DishConverter.convertToDish(dishDTO);
        dishTemp.setId(dish.getId());
        return dishMapper.updateDishById(dishTemp) > 0;
    }
}
