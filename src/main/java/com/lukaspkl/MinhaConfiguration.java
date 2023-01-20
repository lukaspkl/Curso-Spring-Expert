package com.lukaspkl;

import com.lukaspkl.service.Development;
import org.springframework.context.annotation.Bean;


@Development
public class MinhaConfiguration {

    @Bean(name = "applicationName" )
    public String applicationName (){
        return "Sistema de vendas";
    }
}
