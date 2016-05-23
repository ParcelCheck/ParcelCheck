package com.parcelcheck.parcelchecklib;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParcelCheckerTest {

    private RandomFieldGenerator randomFieldGenerator;
    private ObjectHydrator testObject;

    @Before
    public void setUp() throws Exception {
        randomFieldGenerator = mock(RandomFieldGenerator.class);
        when(randomFieldGenerator.getNextString()).thenReturn("string1", "string2", "string3", "string4", "string5");
        when(randomFieldGenerator.getNextInt()).thenReturn(1, 2);
        when(randomFieldGenerator.getNextLong()).thenReturn(1L, 2L);
        when(randomFieldGenerator.getNextBoolean()).thenReturn(true);
        when(randomFieldGenerator.getNextDouble()).thenReturn(1.0, 2.0);
        when(randomFieldGenerator.getNextFloat()).thenReturn(1.0f, 2.0f);
        when(randomFieldGenerator.getNextBigDecimal()).thenReturn(new BigDecimal(99));
        when(randomFieldGenerator.getNextBigInteger()).thenReturn(new BigInteger("101"));
        when(randomFieldGenerator.getNextByte()).thenReturn((byte) 1, (byte) 2);
        when(randomFieldGenerator.getNextShort()).thenReturn((short) 1, (short) 2);

        testObject = new ObjectHydrator(randomFieldGenerator);
    }

    @Test
    public void stringsAreFilledWithRandomValues() throws Exception {
        Object filledObject = testObject.createFilledObject(BillingAddressForm.class);

        assertTrue(filledObject instanceof BillingAddressForm);
        BillingAddressForm actual = (BillingAddressForm) filledObject;

        assertNotNull(actual);
        assertEquals("string1", actual.address1);
        assertEquals("string2", actual.city);
        assertEquals("string3", actual.phone);
        assertEquals("string4", actual.postalCode);
        assertEquals("string5", actual.state);
    }

    @Test
    public void primitivesAreFilledWithRandomValues() throws Exception {
        Object filledObject = testObject.createFilledObject(PrimitiveFilledObject.class);

        PrimitiveFilledObject actual = (PrimitiveFilledObject) filledObject;

        assertNotNull(actual);

        assertEquals("string1", actual.getString());

        assertPrimitiveIsFilled(actual);
    }

    @Test
    public void oneLevelDeep() throws Exception {
        Object filledObject = testObject.createFilledObject(LevelOneObject.class);

        LevelOneObject actual = (LevelOneObject) filledObject;

        assertNotNull(actual);
        assertNotNull(actual.getPrimitiveFilledObject());
        assertPrimitiveIsFilled(actual.getPrimitiveFilledObject());
    }

    @Test
    public void twoLevelsDeep() throws Exception {
        Object filledObject = testObject.createFilledObject(LevelTwoObject.class);

        LevelTwoObject actual = (LevelTwoObject) filledObject;

        assertNotNull(actual);
        assertNotNull(actual.getLevelOneObject());
        assertNotNull(actual.getLevelOneObject().getPrimitiveFilledObject());
        assertPrimitiveIsFilled(actual.getLevelOneObject().getPrimitiveFilledObject());
    }

    @Test
    public void threeLevelsDeep() throws Exception {
        Object filledObject = testObject.createFilledObject(LevelThreeObject.class);

        LevelThreeObject actual = (LevelThreeObject) filledObject;

        assertNotNull(actual);
        LevelTwoObject levelTwoObject = actual.getLevelTwoObject();
        assertNotNull(levelTwoObject);
        assertNotNull(levelTwoObject.getLevelOneObject());
        assertNotNull(levelTwoObject.getLevelOneObject().getPrimitiveFilledObject());
        assertPrimitiveIsFilled(levelTwoObject.getLevelOneObject().getPrimitiveFilledObject());
    }

    private void assertPrimitiveIsFilled(PrimitiveFilledObject actual) {
        assertEquals(1, actual.getInteger());
        assertEquals(2, actual.getClassInt().intValue());

        assertTrue(actual.isPrimitiveBoolean());
        assertTrue(actual.getClassBoolean());

        assertEquals(1.0, actual.getPrimitiveDouble(), 0.00001);
        assertEquals(2.0, actual.getClassDouble().doubleValue(), 0.00001);

        assertEquals(1.0F, actual.getPrimitiveFloat(), 0.00001);
        assertEquals(2.0F, actual.getClassFloat().floatValue(), 0.00001);

        assertEquals(1L, actual.getPrimitiveLong());
        assertEquals(2L, actual.getClassLong().intValue());

        assertEquals(99, actual.getBigDecimal().intValue());
        assertEquals(101, actual.getBigInteger().intValue());

        assertEquals((byte) 1, actual.getPrimitiveByte());
        assertEquals((byte) 2, actual.getClassByte().byteValue());

        assertEquals((short) 1, actual.getPrimitiveShort());
        assertEquals((short) 2, actual.getClassShort().shortValue());
    }

    @Test
    public void levelOneListObject() throws Exception {
        Object filledObject = testObject.createFilledObject(LevelOneListObject.class);

        LevelOneListObject actual = (LevelOneListObject) filledObject;
        assertNotNull(actual.getListOfPrimitiveFilledObject());
        assertEquals(1, actual.getListOfPrimitiveFilledObject().size());
        assertPrimitiveIsFilled(actual.getListOfPrimitiveFilledObject().get(0));
    }

    @Test
    public void levelTwoListObject() throws Exception {
        Object filledObject = testObject.createFilledObject(LevelTwoListObject.class);

        LevelTwoListObject actual = (LevelTwoListObject) filledObject;

        ArrayList<LevelOneListObject> levelOneListObjects = actual.getLevelOneListObjects();
        assertNotNull(levelOneListObjects);
        assertEquals(1, levelOneListObjects.size());
        ArrayList<PrimitiveFilledObject> listOfPrimitiveFilledObject = levelOneListObjects.get(0).getListOfPrimitiveFilledObject();
        assertNotNull(listOfPrimitiveFilledObject);
        assertEquals(1, listOfPrimitiveFilledObject.size());
        assertPrimitiveIsFilled(listOfPrimitiveFilledObject.get(0));
    }
}