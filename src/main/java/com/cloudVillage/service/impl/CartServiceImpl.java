package com.cloudVillage.service.impl;

import com.cloudVillage.entity.Cart;
import com.cloudVillage.mapper.CartMapper;
import com.cloudVillage.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊炜
 * @since 2022-05-24
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
