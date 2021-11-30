package com.pifrans.texo.utilities;

import java.math.BigDecimal;

public class ResultSetUtil {

    public static Long toLong(Object obj) {
        Long value = null;
        if (obj != null) {
            value = Long.valueOf(obj.toString());
        }
        return value;
    }

    public static BigDecimal toBigDecimal(Object obj) {
        BigDecimal value = null;
        if (obj != null) {
            value = new BigDecimal(obj.toString());
        }
        return value;
    }

    public static Integer toInteger(Object obj) {
        Integer value = null;
        if (obj != null) {
            value = Integer.valueOf(obj.toString());
        }
        return value;
    }

    public static String toString(Object obj) {
        if (obj != null) {
            return obj.toString().trim();
        }
        return null;
    }

    public static Boolean toBoolean(Object obj) {
        Boolean value = null;
        if (obj != null) {
            value = Boolean.valueOf(obj.toString());
        }
        return value;
    }

    public static Double toDouble(Object obj) {
        Double value = null;
        if (obj != null) {
            value = Double.valueOf(obj.toString());
        }
        return value;
    }
}
