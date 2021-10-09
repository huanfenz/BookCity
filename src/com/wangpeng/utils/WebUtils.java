package com.wangpeng.utils;

import com.wangpeng.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {

    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            System.out.println("注入之前" + bean);
            BeanUtils.populate(bean, value);    //注入
            System.out.println("注入之后" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
