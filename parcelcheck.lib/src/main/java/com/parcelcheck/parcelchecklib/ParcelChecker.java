package com.parcelcheck.parcelchecklib;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                field.setAccessible(true);

                if (fieldType.isAssignableFrom(String.class)) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextString());
                } else if (fieldType.getName().equals(BigDecimal.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextBigDecimal());
                } else if (fieldType.getName().equals(BigInteger.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextBigInteger());
                } else if (fieldType.isAssignableFrom(int.class) || fieldType.getName().equals(Integer.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextInt());
                } else if (fieldType.isAssignableFrom(double.class) || fieldType.getName().equals(Double.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextDouble());
                } else if (fieldType.isAssignableFrom(short.class) || fieldType.getName().equals(Short.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextShort());
                } else if (fieldType.isAssignableFrom(float.class) || fieldType.getName().equals(Float.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextFloat());
                } else if (fieldType.isAssignableFrom(long.class) || fieldType.getName().equals(Long.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextLong());
                } else if (fieldType.isAssignableFrom(boolean.class) || fieldType.getName().equals(Boolean.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextBoolean());
                } else if (fieldType.isAssignableFrom(byte.class) || fieldType.getName().equals(Byte.class.getName())) {
                    field.set(newObjectToFillWithData, randomFieldGenerator.getNextByte());
                } else if (fieldType.isAssignableFrom(ArrayList.class)){
                    ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                    Class<?> genericClass = (Class<?>) stringListType.getActualTypeArguments()[0];
                    Object filledObject = createFilledObject(genericClass.newInstance().getClass());
                    ArrayList<Object> value = new ArrayList<>();
                    value.add(filledObject);
                    field.set(newObjectToFillWithData, value);
                }
                else {
                    Object filledObject = createFilledObject(fieldType.newInstance().getClass());
                    field.set(newObjectToFillWithData, filledObject);
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
