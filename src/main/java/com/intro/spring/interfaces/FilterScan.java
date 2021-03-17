package com.intro.spring.interfaces;

import com.intro.spring.service.UserServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = "com.intro.spring" includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes =  {UserServiceImpl.class})
})
public interface FilterScan {
}
