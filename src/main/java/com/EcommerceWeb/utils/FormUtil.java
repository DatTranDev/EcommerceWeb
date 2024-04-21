package com.EcommerceWeb.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
        T object = null;
        try{
            object = (T) tClass.newInstance();
            BeanUtils.populate(object, request.getParameterMap());

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e){
            System.out.println(e.getMessage());
        }
        return object;
    }
}
