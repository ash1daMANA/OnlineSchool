package com.ty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by thinkpad on 2017/10/11.
 */

@Configuration
public class BeanTest {

        @Bean
        public String Stringtest(){
            return "stringtest";
        }

}
