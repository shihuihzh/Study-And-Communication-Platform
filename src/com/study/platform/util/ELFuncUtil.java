package com.study.platform.util;

import org.springframework.security.authentication.DisabledException;

public class ELFuncUtil {

    public static boolean isDisableException(Object e) {
    	return e instanceof DisabledException;
    }
}