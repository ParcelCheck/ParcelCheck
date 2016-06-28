package com.parcelcheck.parcelchecklib;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class RandomFieldGenerator {

    private Random random = new Random();

    public String getNextString() {
        return UUID.randomUUID().toString();
    }

    public int getNextInt() {
        random = new Random();
        return random.nextInt();
    }

    public boolean getNextBoolean() {
        return random.nextBoolean();
    }

    public double getNextDouble() {
        return random.nextDouble();
    }

    public float getNextFloat() {
        return random.nextFloat();
    }

    public long getNextLong() {
        return random.nextLong();
    }

    public BigDecimal getNextBigDecimal() {
        return new BigDecimal(random.nextDouble());
    }

    public BigInteger getNextBigInteger() {
        return new BigInteger(15, random);
    }

    public byte getNextByte() {
        return (byte) random.nextInt();
    }

    public short getNextShort() {
        return (short) random.nextInt();
    }

    public char getNextChar() {
        return (char) random.nextInt();
    }
}
