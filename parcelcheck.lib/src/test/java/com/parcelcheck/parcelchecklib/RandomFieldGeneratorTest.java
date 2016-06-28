package com.parcelcheck.parcelchecklib;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class RandomFieldGeneratorTest {

    @Test
    public void testValuesAreReturned() throws Exception {


        RandomFieldGenerator randomFieldGenerator = new RandomFieldGenerator();

        String string1 = randomFieldGenerator.getNextString();
        String string2 = randomFieldGenerator.getNextString();
        assertNotNull(string1);
        assertNotNull(string2);
        assertNotEquals(string1, string2);

        int int1 = randomFieldGenerator.getNextInt();
        int int2 = randomFieldGenerator.getNextInt();
        assertNotEquals(0, int1);
        assertNotEquals(0, int2);
        assertNotEquals(int1, int2);

        double double1 = randomFieldGenerator.getNextDouble();
        double double2 = randomFieldGenerator.getNextDouble();
        assertNotEquals(double1, double2);

        float float1 = randomFieldGenerator.getNextFloat();
        float float2 = randomFieldGenerator.getNextFloat();
        assertNotEquals(float1, float2);

        long long1 = randomFieldGenerator.getNextLong();
        long long2 = randomFieldGenerator.getNextLong();
        assertNotEquals(long1, long2);

        BigDecimal bigDecimal1 = randomFieldGenerator.getNextBigDecimal();
        BigDecimal bigDecimal2 = randomFieldGenerator.getNextBigDecimal();
        assertNotNull(bigDecimal1);
        assertNotNull(bigDecimal2);
        assertTrue(bigDecimal1.compareTo(bigDecimal2) != 0);

        BigInteger bigInt1 = randomFieldGenerator.getNextBigInteger();
        BigInteger bigInt2 = randomFieldGenerator.getNextBigInteger();
        assertNotNull(bigInt1);
        assertNotNull(bigInt2);
        assertTrue(bigInt1.compareTo(bigInt2) != 0);

        byte byte1 = randomFieldGenerator.getNextByte();
        byte byte2 = randomFieldGenerator.getNextByte();
        assertNotEquals(byte1, byte2);

        short short1 = randomFieldGenerator.getNextShort();
        short short2 = randomFieldGenerator.getNextShort();
        assertNotEquals(short1, short2);

        char char1 = randomFieldGenerator.getNextChar();
        char char2 = randomFieldGenerator.getNextChar();
        assertNotEquals(char1, char2);
    }
}