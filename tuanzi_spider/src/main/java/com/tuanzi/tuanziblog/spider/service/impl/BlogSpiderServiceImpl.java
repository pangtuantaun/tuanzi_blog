package com.tuanzi.tuanziblog.spider.service.impl;


import com.tuanzi.tuanziblog.spider.entity.BlogSpider;
import com.tuanzi.tuanziblog.spider.mapper.BlogSpiderMapper;
import com.tuanzi.tuanziblog.spider.service.BlogSpiderService;
import com.moxi.mougblog.base.serviceImpl.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客爬取服务实现类
 * </p>
 *
 * @author 陌溪
 * @since 2020年2月7日21:29:42
 */
@Slf4j
@Service
public class BlogSpiderServiceImpl extends SuperServiceImpl<BlogSpiderMapper, BlogSpider> implements BlogSpiderService {

}
