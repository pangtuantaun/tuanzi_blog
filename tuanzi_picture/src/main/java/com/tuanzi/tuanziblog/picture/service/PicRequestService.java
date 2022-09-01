package com.tuanzi.tuanziblog.picture.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PicRequestService {
    /**
     * 请求路径
     * @param url
     * @return
     */
    void requestUrl(HttpServletRequest request, HttpServletResponse response,String bucket, String url,String referer)throws Exception ;
}
