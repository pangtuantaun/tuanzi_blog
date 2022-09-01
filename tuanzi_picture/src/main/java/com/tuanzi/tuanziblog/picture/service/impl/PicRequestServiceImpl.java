package com.tuanzi.tuanziblog.picture.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moxi.mougblog.base.enums.EStatus;
import com.moxi.mougblog.base.exception.exceptionType.QueryException;
import com.moxi.mougblog.base.global.ErrorCode;
import com.tuanzi.tuanziblog.commons.entity.SystemConfig;
import com.tuanzi.tuanziblog.picture.global.RedisConf;
import com.tuanzi.tuanziblog.picture.global.SQLConf;
import com.tuanzi.tuanziblog.picture.service.PicRequestService;
import com.tuanzi.tuanziblog.utils.JsonUtils;
import com.tuanzi.tuanziblog.utils.RedisUtil;
import com.tuanzi.tuanziblog.xo.global.MessageConf;
import com.tuanzi.tuanziblog.xo.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class PicRequestServiceImpl implements PicRequestService {
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void requestUrl(HttpServletRequest request, HttpServletResponse response, String bucket, String url, String referer) throws Exception {
        String minioPictureBaseUrl = "";
        // 从Redis中获取系统配置
        String systemConfigJson = redisUtil.get(RedisConf.SYSTEM_CONFIG);
        if (StringUtils.isEmpty(systemConfigJson)) {
            QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SQLConf.STATUS, EStatus.ENABLE);
            SystemConfig systemConfig = systemConfigService.getOne(queryWrapper);
            if (systemConfig == null) {
                throw new QueryException(MessageConf.SYSTEM_CONFIG_IS_NOT_EXIST);
            } else {
                // 将系统配置存入Redis中【设置过期时间24小时】
                redisUtil.setEx(RedisConf.SYSTEM_CONFIG, JsonUtils.objectToJson(systemConfig), 24, TimeUnit.HOURS);
            }
            minioPictureBaseUrl = systemConfig.getMinioPictureBaseUrl();
        } else {
            SystemConfig systemConfig = JsonUtils.jsonToPojo(systemConfigJson, SystemConfig.class);
            if (systemConfig == null) {
                throw new QueryException(ErrorCode.QUERY_DEFAULT_ERROR, "系统配置转换错误，请检查系统配置，或者清空Redis后重试！");
            }
            minioPictureBaseUrl = systemConfig.getMinioPictureBaseUrl();
        }
        //校验请求头是否符合要求
        if (!verifyPic(referer)) {
            StringBuilder builder = StrUtil.builder();
            builder.append(minioPictureBaseUrl).append("/tuantuanblog/violation-del.png");
            HttpResponse data = HttpUtil.createGet(builder.toString(), true).timeout(-1).executeAsync();
            response.setContentType(data.header("Content-Type"));
            response.getOutputStream().write(data.bodyBytes());
        } else {
            StringBuilder builder = StrUtil.builder();
            builder.append(minioPictureBaseUrl).append("/").append(bucket).append("/").append(url);
            HttpResponse data = HttpUtil.createGet(builder.toString(), true).timeout(-1).executeAsync();
            response.setContentType(data.header("Content-Type"));
            response.getOutputStream().write(data.bodyBytes());
        }
    }

    /**
     * 校验请求头是否符合要求
     *
     * @param referer
     * @return
     */
    private Boolean verifyPic(String referer) {
        if (StrUtil.isEmpty(referer)) {
            return false;
        }
        String afterInfo = StrUtil.subAfter(referer, "//", false);
        String beforeInfo = StrUtil.subBefore(afterInfo, "/", false);
        final String info = StrUtil.subBefore(beforeInfo, ":", false);
        final boolean localhost = StrUtil.equals(info, "localhost");
        return localhost;
    }
}
