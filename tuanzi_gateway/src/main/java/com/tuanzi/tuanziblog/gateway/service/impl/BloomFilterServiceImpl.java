package com.tuanzi.tuanziblog.gateway.service.impl;

import com.tuanzi.tuanziblog.gateway.service.BloomFilterService;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloomFilterServiceImpl implements BloomFilterService {
    @Autowired
    private Redisson redisson;

    /**
     * 布隆过滤器
     * @return
     */
    @Override
    public String filtrationRequest() {
        return null;
    }
}
