package Library.Scholars_Heaven;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class CorsConfig extends WebMvcAutoConfiguration {

    public void addCorsmappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://locolhost:4200")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
