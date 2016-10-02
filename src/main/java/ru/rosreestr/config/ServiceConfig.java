package ru.rosreestr.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.rosreestr.ws.ServiceWS;

import javax.xml.ws.Endpoint;

/**
 * Created by KatrinaBosh on 02.10.2016.
 */
@Configuration
//@EnableWebMvc
@ImportResource({"classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml"})
public class ServiceConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Bus bus;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
//    }
//
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }

    @Bean
    public Endpoint myServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ServiceWS());
        endpoint.publish("/Service");
        return endpoint;
    }

//    @Bean
//    public ServletRegistrationBean cxfServlet() {
//        ServletRegistrationBean servlet = new ServletRegistrationBean(new CXFServlet(), "/*");
//        servlet.setLoadOnStartup(1);
//        return servlet;
//    }
}
