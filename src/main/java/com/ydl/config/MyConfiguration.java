package com.ydl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: wyx
 * @Date: 2019-05-09 21:25
 * @Description: 添加对跨域访问的支持
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer  {

 @Bean
 public WebMvcConfigurer corsConfigurer(){
  // 重写父类提供的跨域请求处理的接口
  return new WebMvcConfigurer() {
   @Override
   public void addCorsMappings(CorsRegistry registry) {
    // 添加映射路径
    registry.addMapping("/**")
            // 放行哪些原始域
            .allowedOrigins("*")
            // 放行哪些头部信息
            .allowedHeaders("DNT,ctoken,pagenum,captcha,Authorization,Accept,Origin,Keep-Alive,User-Agent,X-Mx-ReqToken,X-Data-Type,X-Auth-Token,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range")
            .exposedHeaders("ctoken,captcha,*")
            // 放行哪些请求方式
            .allowedMethods("*")
            // 是否允许携带 cookie
            .allowCredentials(true)
            .maxAge(1800);
    // test
   }

  };

 }
 /**
  * 资源映射路径
  */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
   registry.addResourceHandler("/upload/**").addResourceLocations("file:F:/code/test-upload/images/");
//   super.addResourceHandler(registry);
  }

}
