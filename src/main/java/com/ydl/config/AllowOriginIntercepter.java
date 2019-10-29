//package com.ydl.config;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.ws.spi.http.HttpHandler;
//
//
///**
// *
// * @author 劳先生
// * 解决浏览器出现的【已拦截跨源请求：同源策略禁止读取】问题
// */
//public class AllowOriginIntercepter implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        String origin = request.getHeader(HttpHeaders.ORIGIN);
//        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
//        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//    }
//
//}
//
