package com.frj.flyeducation.controller;

import com.frj.flyeducation.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/checkToken")
public class WeChatController extends AbstractController {
    @Autowired
    private SubscriberService subscriberService;

    /**
     * 微信验证token
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping(produces = "text/html;charset=utf-8")
    public String checkToken(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        return subscriberService.checkToken(signature,timestamp,nonce,echostr);
    }

    /**
     * 监听公众号关注事件
     * @param req
     * @return
     */
    @PostMapping
    public void createOfficialUser(HttpServletRequest req) {
        subscriberService.createOfficialUser(req);
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return null;
    }
}
