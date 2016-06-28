package com.parcelcheck.parcelchecklib;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.*;

public class ReflectiveEqualCheckerTest {

    private ReflectiveEqualChecker testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new ReflectiveEqualChecker();
    }

    @Test
    public void primitivesAreChecked() throws Exception {
        assertTrue(testObject.checkEquality(1234, 1234));
        assertFalse(testObject.checkEquality(1234, 4321));

        assertTrue(testObject.checkEquality(new Integer(1234), new Integer(1234)));
        assertFalse(testObject.checkEquality(new Integer(1234), new Integer(4321)));

        assertTrue(testObject.checkEquality("string1", "string1"));
        assertTrue(testObject.checkEquality("string1", "string" + 1));
        assertFalse(testObject.checkEquality("string1", "string2"));
        assertFalse(testObject.checkEquality(null, "string2"));
        assertFalse(testObject.checkEquality("string1", null));

        Boolean nullBool = null;
        assertTrue(testObject.checkEquality(true, true));
        assertTrue(testObject.checkEquality(false, false));
        assertTrue(testObject.checkEquality(Boolean.TRUE, Boolean.TRUE));
        assertTrue(testObject.checkEquality(true, Boolean.TRUE));
        assertTrue(testObject.checkEquality(Boolean.FALSE, Boolean.FALSE));
        assertTrue(testObject.checkEquality(false, Boolean.FALSE));
        assertTrue(testObject.checkEquality(nullBool, nullBool));
        assertFalse(testObject.checkEquality(true, false));
        assertFalse(testObject.checkEquality(nullBool, Boolean.FALSE));
        assertFalse(testObject.checkEquality(Boolean.TRUE, nullBool));

        Double nullDouble = null;
        assertTrue(testObject.checkEquality(1234.567d, 1234.567d));
        assertTrue(testObject.checkEquality(1234.567d, new Double(1234.567)));
        assertTrue(testObject.checkEquality(new Double(1234.567), new Double(1234.567)));
        assertTrue(testObject.checkEquality(nullDouble, nullDouble));

        assertFalse(testObject.checkEquality(123.45d, 123.456d));
        assertFalse(testObject.checkEquality(123.45d, new Double(123.456)));
        assertFalse(testObject.checkEquality(new Double(123.45), new Double(123.456)));
        assertFalse(testObject.checkEquality(nullDouble, new Double(123.456)));

        Float nullFloat = null;
        assertTrue(testObject.checkEquality(1234.567f, 1234.567f));
        assertTrue(testObject.checkEquality(1234.567f, new Float(1234.567)));
        assertTrue(testObject.checkEquality(new Float(1234.567), new Float(1234.567)));
        assertTrue(testObject.checkEquality(nullFloat, nullFloat));

        assertFalse(testObject.checkEquality(123.45f, 123.456f));
        assertFalse(testObject.checkEquality(123.45f, new Float(123.456)));
        assertFalse(testObject.checkEquality(new Float(123.45), new Float(123.456)));
        assertFalse(testObject.checkEquality(nullFloat, new Float(123.456)));

        CharSequence sequence1 = "foo";
        CharSequence sequence2 = "foo";
        assertTrue(testObject.checkEquality(sequence1, sequence2));
    }

    @Test
    public void bigDecimalChecked() throws Exception {
        assertTrue(testObject.checkEquality(BigDecimal.TEN, BigDecimal.TEN));
        assertTrue(testObject.checkEquality(BigDecimal.ONE, BigDecimal.ONE));
        assertTrue(testObject.checkEquality(new BigDecimal("12345.678901"), new BigDecimal("12345.678901")));

        assertFalse(testObject.checkEquality(null, BigDecimal.ONE));
        assertFalse(testObject.checkEquality(BigDecimal.ONE, null));
        assertFalse(testObject.checkEquality(BigDecimal.ONE, BigDecimal.TEN));
        assertFalse(testObject.checkEquality(BigDecimal.ONE, BigDecimal.ZERO));
    }

    @Test
    public void bigIntegerChecked() throws Exception {
        assertTrue(testObject.checkEquality(BigInteger.ONE, BigInteger.ONE));
        assertTrue(testObject.checkEquality(BigInteger.TEN, BigInteger.TEN));
        assertTrue(testObject.checkEquality(new BigInteger("987654321000"), new BigInteger("987654321000")));

        assertFalse(testObject.checkEquality(null, BigInteger.ONE));
        assertFalse(testObject.checkEquality(BigInteger.ONE, null));
        assertFalse(testObject.checkEquality(BigInteger.ONE, BigInteger.TEN));
        assertFalse(testObject.checkEquality(BigInteger.ONE, BigInteger.ZERO));
    }

    @Test
    public void notEqualIfSameValueButDifferentClass() throws Exception {
        assertFalse(testObject.checkEquality(new Integer(123), new Long(123) ));
        assertFalse(testObject.checkEquality(null, new Long(123) ));
        assertFalse(testObject.checkEquality(new Long(123), null ));
    }

    @Test
    public void checkEqualityOfPrimitiveFilledObject() throws Exception {

        PrimitiveFilledObject primitiveFilledObject1 = getPrimitive1();
        PrimitiveFilledObject primitiveFilledObject2 = getPrimitive1();

        assertTrue(testObject.checkEquality(primitiveFilledObject1, primitiveFilledObject2));
    }

    @Test
    public void entirelyDifferentPrimitiveFilledObjectsNotEqual() {

        PrimitiveFilledObject primitiveFilledObject1 = getPrimitive1();
        PrimitiveFilledObject primitiveFilledObject2 = getPrimitive2();

        assertFalse(testObject.checkEquality(primitiveFilledObject1, primitiveFilledObject2));
    }

    private PrimitiveFilledObject getPrimitive1(){
        PrimitiveFilledObject primitiveFilledObject1 = new PrimitiveFilledObject();
        primitiveFilledObject1.string = "string";
        primitiveFilledObject1.integer = 123;
        primitiveFilledObject1.classInt = 123;
        primitiveFilledObject1.primitiveBoolean = true;
        primitiveFilledObject1.classBoolean = true;
        primitiveFilledObject1.primitiveDouble = 222.2222d;
        primitiveFilledObject1.classDouble = 222.2222d;
        primitiveFilledObject1.primitiveFloat = 999.777f;
        primitiveFilledObject1.classFloat = 999.777f;
        primitiveFilledObject1.primitiveLong = 123456L;
        primitiveFilledObject1.classLong = 123456L;
        primitiveFilledObject1.bigDecimal = new BigDecimal(55.4467898);
        primitiveFilledObject1.bigInteger = new BigInteger("654654");
        primitiveFilledObject1.primitiveShort = 1111;
        primitiveFilledObject1.classShort = 1112;
        primitiveFilledObject1.primitiveChar = 'z';
        primitiveFilledObject1.classChar = 'p';
        primitiveFilledObject1.charSequence = "charcharchar";
        primitiveFilledObject1.primitiveByte = 55;
        primitiveFilledObject1.classByte = 24;

        return primitiveFilledObject1;
    }

    private PrimitiveFilledObject getPrimitive2(){
        PrimitiveFilledObject primitiveFilledObject1 = new PrimitiveFilledObject();
        primitiveFilledObject1.string = "string1";
        primitiveFilledObject1.integer = 1234;
        primitiveFilledObject1.classInt = 1234;
        primitiveFilledObject1.primitiveBoolean = false;
        primitiveFilledObject1.classBoolean = false;
        primitiveFilledObject1.primitiveDouble = 222.2223d;
        primitiveFilledObject1.classDouble = 222.2223d;
        primitiveFilledObject1.primitiveFloat = 999.774f;
        primitiveFilledObject1.classFloat = 999.774f;
        primitiveFilledObject1.primitiveLong = 123457L;
        primitiveFilledObject1.classLong = 123457L;
        primitiveFilledObject1.bigDecimal = new BigDecimal(55.4467);
        primitiveFilledObject1.bigInteger = new BigInteger("65465");
        primitiveFilledObject1.primitiveShort = 111;
        primitiveFilledObject1.classShort = 11;
        primitiveFilledObject1.primitiveChar = 'y';
        primitiveFilledObject1.classChar = 'o';
        primitiveFilledObject1.charSequence = "charcharcharfoo";
        primitiveFilledObject1.primitiveByte = 56;
        primitiveFilledObject1.classByte = 23;

        return primitiveFilledObject1;
    }
}