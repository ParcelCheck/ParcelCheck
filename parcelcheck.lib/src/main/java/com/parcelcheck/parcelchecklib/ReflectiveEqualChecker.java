package com.parcelcheck.parcelchecklib;

public class ReflectiveEqualChecker {
    public boolean checkEquality(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            return o1 == null && o2 == null;
        }
        return o1.equals(o2);
    }
}
