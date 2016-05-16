package com.parcelcheck.parcelchecklib;

import java.lang.reflect.Field;
import java.util.UUID;

public class ParcelChecker {

    private final RandomFieldGenerator randomFieldGenerator;

    public ParcelChecker(RandomFieldGenerator randomFieldGenerator){

        this.randomFieldGenerator = randomFieldGenerator;
    }


    public Object createFilledObject(Class clazz) {

        Object newObjectToFillWithData = null;
        try {
            newObjectToFillWithData = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {

                Class<?> fieldType = field.getType();

                if (fieldType.isAssignableFrom(String.class)) {
                    field.setAccessible(true);
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextString());
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newObjectToFillWithData;
    }
}
