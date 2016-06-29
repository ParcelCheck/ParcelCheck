package com.parcelcheck.parcelchecklib;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import static junit.framework.Assert.fail;

public class ReflectiveEqualChecker {
    public boolean checkEquality(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            return o1 == null && o2 == null;
        }

        if (o1.getClass().equals(o2.getClass())) {
            if (likeAPrimitive(o1)) {
                return o1.equals(o2);
            } else {
                if (o1.getClass().isAssignableFrom(ArrayList.class)) {
                    ArrayList list1 = (ArrayList) o1;
                    ArrayList list2 = (ArrayList) o2;
                    return isSubset(list1, list2) && isSubset(list2, list1);
                } else {
                    for (Field field : o1.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        try {
                            Object o1Field = field.get(o1);
                            Object o2Field = field.get(o2);
                            if (!checkEquality(o1Field, o2Field)) {
                                return false;
                            }
                            ;
                        } catch (IllegalAccessException e) {
                            fail("Could not access field");
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSubset(ArrayList listA, ArrayList listB) {
        if(listA.size() != listB.size()){
            return false;
        }
        boolean aIncludesB = true;
        for (Object a : listA) {
            boolean found = false;
            for (Object b : listB) {
                if (checkEquality(a, b)) {
                    found = true;
                }
            }
            if (!found) {
                aIncludesB = false;
            }
        }
        return aIncludesB;
    }

    private boolean likeAPrimitive(Object o) {
        return o.getClass().isPrimitive()
                || o instanceof BigDecimal
                || o instanceof BigInteger
                || o instanceof Boolean
                || o instanceof Byte
                || o instanceof Character
                || o instanceof Double
                || o instanceof Float
                || o instanceof Integer
                || o instanceof Long
                || o instanceof Short
                || o instanceof String
                || o instanceof CharSequence
                || o instanceof Void;
    }
}
