package com.cenmw.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.CookieUtils;
import com.cenmw.util.EncryptUtil;
import com.cenmw.util.WebUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MemberLoginFileter implements Filter {
    private String redirectURL = null;
    private String sessionKey = null;

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = WebUtil.getRequestURI(req);
        HttpSession session = req.getSession();
        HttpServletRequest hrequest = (HttpServletRequest) request;
        // 如果没有sessionKey值将不允许进入下一步,停留在登陆页面中.
        if (sessionKey == null) {
            res.sendRedirect(req.getContextPath() + redirectURL);
            return;
        }
        MemberInfo member = (MemberInfo) session.getAttribute(sessionKey);// 获取会员信息
        if (member != null) {
            if (url.indexOf("/membercenter") >= 0) {
                chain.doFilter(request, response);
                return;
            } else {
                res.sendRedirect(req.getContextPath() + redirectURL);
                return;
            }
        } else {
            try {
                String token = CookieUtils.getCookieValue(hrequest, ConstantUtils.TOKEN);
                if (token != null) {
                    token = EncryptUtil.urlParamDecrypt(token);
                    ServletContext sc = req.getSession().getServletContext();
                    XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
                    MemberInfoFrontService memberInfoFrontService = null;
                    if (cxt != null && cxt.getBean("memberInfoFrontService") != null && memberInfoFrontService == null)
                        memberInfoFrontService = (MemberInfoFrontService) cxt.getBean("memberInfoFrontService");
                    MemberInfo memberInfo = memberInfoFrontService.getMemberInfoById(Integer.parseInt(token));
                    req.getSession().setAttribute(ConstantUtils.MEMBERINFO, memberInfo);
                    chain.doFilter(request, response);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.sendRedirect(req.getContextPath() + redirectURL);
            return;
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        redirectURL = filterConfig.getInitParameter("redirectURL");
        sessionKey = filterConfig.getInitParameter("sessionKey");
    }

}
