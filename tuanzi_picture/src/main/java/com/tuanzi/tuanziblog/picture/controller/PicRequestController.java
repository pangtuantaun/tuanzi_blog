package com.tuanzi.tuanziblog.picture.controller;

import com.tuanzi.tuanziblog.picture.service.PicRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/picRequest")
public class PicRequestController {
    @Autowired
    private PicRequestService picRequestService;

    /**
     * 过滤图片请求
     *
     * @return
     */
    @GetMapping(value = "/{bucket}/{url}")
    public void getPic(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable("bucket") String bucket,
                       @PathVariable("url") String url,
                       @RequestHeader(value = "Referer",required = false,defaultValue = "")String referer) throws Exception {
        picRequestService.requestUrl(request, response, bucket,url,referer);
    }
}
