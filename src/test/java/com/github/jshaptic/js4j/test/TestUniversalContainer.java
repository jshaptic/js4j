package com.github.jshaptic.js4j.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.jshaptic.js4j.test.utils.TestContainerHelper;
import com.github.jshaptic.js4j.ContainerConstructor;
import com.github.jshaptic.js4j.ContainerException;
import com.github.jshaptic.js4j.ContainerFactory;
import com.github.jshaptic.js4j.JsonParser;
import com.github.jshaptic.js4j.Lodash;
import com.github.jshaptic.js4j.UniversalContainer;
import com.github.jshaptic.js4j.ValueCustomizer;
import org.testng.Assert;
import org.testng.annotations.Test;

//TODO: split this test by container types
public class TestUniversalContainer extends Assert
{
	private TestContainerHelper th = new TestContainerHelper();
	
	@Test
	public void testUndefined()
	{
		th.cacheSamples("undefined");
		List<UniversalContainer> samples = th.getCachedSamples("undefined");
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertTrue(c.isUndefined());
			assertFalse(c.isNull());
			assertFalse(c.isBoolean());
			assertFalse(c.isNumber());
			assertFalse(c.isString());
			assertFalse(c.isObject());
			assertFalse(c.isArray());
			assertFalse(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), false);
			assertEquals(c.asInt(), 0);
			assertEquals(c.asString(), "");
			assertEquals(c.toString(), "undefined");
			
			//positive equal tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.equals(cc));
			}
			assertEquals(c.valueOf(), (Object)null);
			assertTrue(c.equals(null));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer().create()));
			assertFalse(c.equals(true));
			assertFalse(c.equals(false));
			assertFalse(c.equals(0));
			assertFalse(c.equals(""));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createObject(0, ContainerFactory.undefinedContainer())));
			assertFalse(c.equals(ContainerFactory.createObject(0, ContainerFactory.undefinedContainer()).create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			assertFalse(c.equals(ContainerFactory.createArray(ContainerFactory.undefinedContainer())));
			assertFalse(c.equals(ContainerFactory.createArray(ContainerFactory.undefinedContainer()).create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			assertTrue(c.deepEquals((null)));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer().create()));
			assertFalse(c.deepEquals(true));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(0));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, ContainerFactory.undefinedContainer())));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, ContainerFactory.undefinedContainer()).create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray(ContainerFactory.undefinedContainer())));
			assertFalse(c.deepEquals(ContainerFactory.createArray(ContainerFactory.undefinedContainer()).create()));
		}
	}
	
	@Test
	public void testNull()
	{
		th.cacheSamples("null");
		List<UniversalContainer> samples = th.getCachedSamples("null");
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined());
			assertTrue(c.isNull());
			assertFalse(c.isBoolean());
			assertFalse(c.isNumber());
			assertFalse(c.isString());
			assertTrue(c.isObject());
			assertFalse(c.isArray());
			assertFalse(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), false);
			assertEquals(c.asInt(), 0);
			assertEquals(c.asString(), "");
			assertEquals(c.toString(), "null");
			
			//positive equal tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.equals(cc));
			}
			assertEquals(c.valueOf(), (Object)null);
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer().create()));
			assertFalse(c.equals(null));
			assertFalse(c.equals(true));
			assertFalse(c.equals(false));
			assertFalse(c.equals(0));
			assertFalse(c.equals(""));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createObject(0, ContainerFactory.nullContainer())));
			assertFalse(c.equals(ContainerFactory.createObject(0, ContainerFactory.nullContainer()).create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			assertFalse(c.equals(ContainerFactory.createArray(ContainerFactory.nullContainer())));
			assertFalse(c.equals(ContainerFactory.createArray(ContainerFactory.nullContainer()).create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer().create()));
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(true));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(0));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, ContainerFactory.nullContainer())));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, ContainerFactory.nullContainer()).create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray(ContainerFactory.nullContainer())));
			assertFalse(c.deepEquals(ContainerFactory.createArray(ContainerFactory.nullContainer()).create()));
		}
	}
	
	private void testLiteralBoolean(String sampleName, boolean isTest, boolean boolValue, int intValue, String strValue)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined());
			assertFalse(c.isNull());
			assertTrue(c.isBoolean());
			assertFalse(c.isNumber());
			assertFalse(c.isString());
			assertFalse(c.isObject());
			assertFalse(c.isArray());
			if (isTest) assertTrue(c.test()); else assertFalse(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), boolValue);
			assertEquals(c.asInt(), intValue);
			assertEquals(c.asString(), strValue);
			assertEquals(c.toString(), strValue);
			
			//positive equal tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.equals(cc));
			}
			assertEquals(c.valueOf(), Boolean.valueOf(boolValue));
			assertTrue(c.equals(boolValue));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(new UniversalContainer(!boolValue)));
			assertFalse(c.equals(new UniversalContainer(!boolValue).clone()));
			assertFalse(c.equals(null));
			assertFalse(c.equals(!boolValue));
			assertFalse(c.equals(intValue));
			assertFalse(c.equals(""));
			assertFalse(c.equals(strValue));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createObject(0, boolValue)));
			assertFalse(c.equals(ContainerFactory.createObject(0, boolValue).create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			assertFalse(c.equals(ContainerFactory.createArray(boolValue)));
			assertFalse(c.equals(ContainerFactory.createArray(boolValue).create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			assertTrue(c.deepEquals(boolValue));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(new UniversalContainer(!boolValue)));
			assertFalse(c.deepEquals(new UniversalContainer(!boolValue).clone()));
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(!boolValue));
			assertFalse(c.deepEquals(intValue));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals(strValue));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, boolValue)));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, boolValue).create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray(boolValue)));
			assertFalse(c.deepEquals(ContainerFactory.createArray(boolValue).create()));
		}
	}
	
	@Test
	public void testLiteralBoolean()
	{
		th.cacheSamples("literal-boolean-*");
		
		testLiteralBoolean("literal-boolean-false", false, false, 0, "false");
		testLiteralBoolean("literal-boolean-true", true, true, 1, "true");
	}
	
	private void testLiteralNumber(String sampleName, boolean isTest, boolean boolValue, boolean isIntAndDoubleEqual, int intValue, double doubleValue, String strValue)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined());
			assertFalse(c.isNull());
			assertFalse(c.isBoolean());
			assertTrue(c.isNumber());
			assertFalse(c.isString());
			assertFalse(c.isObject());
			assertFalse(c.isArray());
			if (isTest) assertTrue(c.test()); else assertFalse(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), boolValue);
			assertEquals(c.asInt(), intValue);
			assertEquals(c.asString(), strValue);
			assertEquals(c.toString(), strValue);
			
			//positive equal tests
			if (isIntAndDoubleEqual)
			{
				assertTrue(c.equals(new UniversalContainer(intValue)));
				assertTrue(c.equals(new UniversalContainer(intValue).clone()));
				assertTrue(c.equals(intValue));
			}
			assertTrue(c.equals(new UniversalContainer(doubleValue)));
			assertTrue(c.equals(new UniversalContainer(doubleValue).clone()));
			assertTrue(c.equals(doubleValue));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			if (!isIntAndDoubleEqual)
			{
				assertFalse(c.equals(new UniversalContainer(intValue)));
				assertFalse(c.equals(new UniversalContainer(intValue).clone()));
				assertFalse(c.equals(intValue));
			} 
			assertFalse(c.equals(null));
			assertFalse(c.equals(true));
			assertFalse(c.equals(false));
			assertFalse(c.equals(""));
			assertFalse(c.equals(strValue));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createObject(0, intValue)));
			assertFalse(c.equals(ContainerFactory.createObject(0, intValue).create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			assertFalse(c.equals(ContainerFactory.createArray(intValue)));
			assertFalse(c.equals(ContainerFactory.createArray(intValue).create()));
			
			//positive deepEqual tests
			if (isIntAndDoubleEqual)
			{
				assertTrue(c.deepEquals(new UniversalContainer(intValue)));
				assertTrue(c.deepEquals(new UniversalContainer(intValue).clone()));
				assertTrue(c.deepEquals(intValue));
			}
			assertTrue(c.deepEquals(new UniversalContainer(doubleValue)));
			assertTrue(c.deepEquals(new UniversalContainer(doubleValue).clone()));
			assertTrue(c.deepEquals(doubleValue));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			if (!isIntAndDoubleEqual)
			{
				assertFalse(c.deepEquals(new UniversalContainer(intValue)));
				assertFalse(c.deepEquals(new UniversalContainer(intValue).clone()));
				assertFalse(c.deepEquals(intValue));
			}
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(true));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals(strValue));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, intValue)));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, intValue).create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray(intValue)));
			assertFalse(c.deepEquals(ContainerFactory.createArray(intValue).create()));
		}
	}
	
	@Test
	public void testLiteralNumber()
	{
		th.cacheSamples("literal-number-*");
		
		testLiteralNumber("literal-number-*-minus-one", true, false, true, -1, -1.0, "-1");
		testLiteralNumber("literal-number-*-zero", false, false, true, 0, 0.0, "0");
		testLiteralNumber("literal-number-*-plus-one", true, true, true, 1, 1.0, "1");
		testLiteralNumber("literal-number-double-plus-one-point-one", true, false, false, 1, 1.1, "1.1");
	}
	
	private void testLiteralString(String sampleName, boolean isTest, boolean boolValue, int intValue, double doubleValue, String strValue)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined());
			assertFalse(c.isNull());
			assertFalse(c.isBoolean());
			assertFalse(c.isNumber());
			assertTrue(c.isString());
			assertFalse(c.isObject());
			assertFalse(c.isArray());
			if (isTest) assertTrue(c.test()); else assertFalse(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), boolValue);
			assertEquals(c.asInt(), intValue);
			assertEquals(c.asString(), strValue);
			assertEquals(c.toString(), strValue);
			
			//positive equal tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.equals(cc));
			}
			assertEquals(c.valueOf(), strValue);
			assertTrue(c.equals(strValue));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(null));
			assertFalse(c.equals(true));
			assertFalse(c.equals(false));
			assertFalse(c.equals(intValue));  
			assertFalse(c.equals(doubleValue));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createObject(0, strValue)));
			assertFalse(c.equals(ContainerFactory.createObject(0, strValue).create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			assertFalse(c.equals(ContainerFactory.createArray(strValue)));
			assertFalse(c.equals(ContainerFactory.createArray(strValue).create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			assertTrue(c.deepEquals(strValue));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(true));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(intValue));  
			assertFalse(c.deepEquals(doubleValue)); 
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, strValue)));
			assertFalse(c.deepEquals(ContainerFactory.createObject(0, strValue).create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray(strValue)));
			assertFalse(c.deepEquals(ContainerFactory.createArray(strValue).create()));
		}
	}
	
	@Test
	public void testLiteralString()
	{	
		th.cacheSamples("literal-string-*");
		
		testLiteralString("literal-string-true", true, false, 0, 0.0, "true");
		testLiteralString("literal-string-false", true, false, 0, 0.0, "false");
		testLiteralString("literal-string-minus-one", true, false, -1, -1.0, "-1");
		testLiteralString("literal-string-zero", true, false, 0, 0.0, "0");
		testLiteralString("literal-string-plus-one", true, true, 1, 1.0, "1");
		testLiteralString("literal-string-plus-one-point-one", true, false, 1, 1.1, "1.1");
		testLiteralString("literal-string-empty", false, false, 0, 0.0, "");
		testLiteralString("literal-string-abcde", true, false, 0, 0.0, "abcde");
	}
	
	@Test
	public void testLiteralNull()
	{
		th.cacheSamples("literal-null");
		List<UniversalContainer> samples = th.getCachedSamples("literal-null");
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined());
			assertFalse(c.isNull());
			assertFalse(c.isBoolean());
			assertFalse(c.isNumber());
			assertFalse(c.isString());
			assertFalse(c.isObject());
			assertFalse(c.isArray());
			assertFalse(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), false);
			assertEquals(c.asInt(), 0);
			assertEquals(c.asString(), "");
			assertEquals(c.toString(), "");
			
			//positive equal tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.equals(cc));
			}
			assertEquals(c.valueOf(), (Object)null);
			assertTrue(c.equals(null));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(true));
			assertFalse(c.equals(false));
			assertFalse(c.equals(0));
			assertFalse(c.equals(""));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			assertTrue(c.deepEquals(null));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(true));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(0));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
		}
	}
	
	@Test
	public void testLiteralOther()
	{
		th.cacheSamples("literal-other");
		List<UniversalContainer> samples = th.getCachedSamples("literal-other");
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined());
			assertFalse(c.isNull());
			assertFalse(c.isBoolean());
			assertFalse(c.isNumber());
			assertFalse(c.isString());
			assertFalse(c.isObject());
			assertFalse(c.isArray());
			assertTrue(c.test());
			
			//check cast functions
			assertEquals(c.asBoolean(), false);
			assertEquals(c.asInt(), 0);
			assertEquals(c.asString(), "literal-object-sample");
			assertEquals(c.toString(), "literal-object-sample");
			
			//positive equal tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.equals(cc));
			}
			assertEquals(c.valueOf(), th.getSamplePlainObject());
			assertTrue(c.equals(th.getSamplePlainObject()));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(null));
			assertFalse(c.equals(true));
			assertFalse(c.equals(false));
			assertFalse(c.equals(0));
			assertFalse(c.equals(""));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			assertTrue(c.deepEquals(th.getSamplePlainObject()));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(true));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(0));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
		}
	}
	
	private void testObject(String sampleName)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined() || c.create().isUndefined());
			assertFalse(c.isNull() || c.create().isNull());
			assertFalse(c.isBoolean() || c.create().isBoolean());
			assertFalse(c.isNumber() || c.create().isNumber());
			assertFalse(c.isString() || c.create().isString());
			assertTrue(c.isObject() && c.create().isObject());
			assertFalse(c.isArray() || c.create().isArray());
			assertTrue(c.test() && c.create().test());
			
			//check cast functions
			assertEquals(c.asBoolean(), false);
			assertEquals(c.create().asBoolean(), false);
			assertEquals(c.asInt(), 0);
			assertEquals(c.create().asInt(), 0);
			assertEquals(c.asString(), "[object Object]");
			assertEquals(c.create().asString(), "[object Object]");
			assertEquals(c.toString(), "[object Object]");
			assertEquals(c.create().toString(), "[object Object]");
			
			//positive equal tests
			assertTrue(c.equals(c));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(null));
			assertFalse(c.equals(false));
			assertFalse(c.equals(0));
			assertFalse(c.equals(""));
			assertFalse(c.equals("[object Object]"));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(c.create()));
			assertFalse(c.equals(c.clone()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			
			//positive deepEqual tests
			assertTrue(c.deepEquals(c));
			assertTrue(c.deepEquals(c.clone()));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(false));
			assertFalse(c.deepEquals(0));
			assertFalse(c.deepEquals(""));
			assertFalse(c.deepEquals("[object Object]"));
			assertFalse(c.deepEquals(ContainerFactory.createArray()));
			assertFalse(c.deepEquals(ContainerFactory.createArray().create()));
		}
	}
	
	private void testObject(String sampleName, boolean boolValue, int intValue, double doubleValue, String strValue)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			assertFalse(c.equals(boolValue));
			assertFalse(c.equals(intValue));
			assertFalse(c.equals(doubleValue));
			assertFalse(c.equals(strValue));
			
			assertFalse(c.deepEquals(boolValue));
			assertFalse(c.deepEquals(intValue));
			assertFalse(c.deepEquals(doubleValue));
			assertFalse(c.deepEquals(strValue));
		}
	}
	
	private void testObject(String sampleName, UniversalContainer expected)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			assertTrue(c.deepEquals(expected));
			assertTrue(c.deepEquals(expected.clone()));
		}
	}
	
	private void testObject(String sampleName, Map<String, Object> expected)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			assertTrue(c.deepEquals(expected));
		}
	}
	
	@Test
	public void testObject()
	{
		th.cacheSamples("object-*");
		
		testObject("object-*");
		
		Map<String, Object> expected = new HashMap<String, Object>();
		
		testObject("object-empty", false, 0, 0.0, "");
		testObject("object-empty", ContainerFactory.createObject());
		testObject("object-empty", ContainerFactory.nullContainer().create());
		testObject("object-empty", new UniversalContainer(expected));
		testObject("object-empty", expected);
		
		expected.put("a", null);
		testObject("object-with-one-entry-key-a-undefined", false, 0, 0.0, "");
		testObject("object-with-one-entry-key-a-undefined", ContainerFactory.createObject("a", null));
		testObject("object-with-one-entry-key-a-undefined", ContainerFactory.createObject("a", ContainerFactory.undefinedContainer()));
		testObject("object-with-one-entry-key-a-undefined", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-undefined", expected);
		
		expected.put("a", ContainerFactory.nullContainer());
		testObject("object-with-one-entry-key-a-null", false, 0, 0.0, "");
		testObject("object-with-one-entry-key-a-null", ContainerFactory.createObject("a", ContainerFactory.nullContainer()));
		testObject("object-with-one-entry-key-a-null", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-null", expected);
		
		expected.put("a", false);
		testObject("object-with-one-entry-key-a-literal-boolean-false", false, 0, 0.0, "false");
		testObject("object-with-one-entry-key-a-literal-boolean-false", ContainerFactory.createObject("a", false));
		testObject("object-with-one-entry-key-a-literal-boolean-false", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-boolean-false", expected);
		
		expected.put("a", true);
		testObject("object-with-one-entry-key-a-literal-boolean-true", true, 1, 1.0, "true");
		testObject("object-with-one-entry-key-a-literal-boolean-true", ContainerFactory.createObject("a", true));
		testObject("object-with-one-entry-key-a-literal-boolean-true", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-boolean-true", expected);
		
		expected.put("a", -1);
		testObject("object-with-one-entry-key-a-literal-number-*-minus-one", false, -1, -1.0, "-1");
		testObject("object-with-one-entry-key-a-literal-number-*-minus-one", ContainerFactory.createObject("a", -1));
		testObject("object-with-one-entry-key-a-literal-number-*-minus-one", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-number-*-minus-one", expected);
		
		expected.put("a", 0);
		testObject("object-with-one-entry-key-a-literal-number-*-zero", false, 0, 0.0, "0");
		testObject("object-with-one-entry-key-a-literal-number-*-zero", ContainerFactory.createObject("a", 0));
		testObject("object-with-one-entry-key-a-literal-number-*-zero", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-number-*-zero", expected);
		
		expected.put("a", 1);
		testObject("object-with-one-entry-key-a-literal-number-*-plus-one", true, 1, 1.0, "1");
		testObject("object-with-one-entry-key-a-literal-number-*-plus-one", ContainerFactory.createObject("a", 1));
		testObject("object-with-one-entry-key-a-literal-number-*-plus-one", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-number-*-plus-one", expected);
		
		expected.put("a", 1.1);
		testObject("object-with-one-entry-key-a-literal-number-double-plus-one-point-one", false, 1, 1.1, "1.1");
		testObject("object-with-one-entry-key-a-literal-number-double-plus-one-point-one", ContainerFactory.createObject("a", 1.1));
		testObject("object-with-one-entry-key-a-literal-number-double-plus-one-point-one", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-number-double-plus-one-point-one", expected);
		
		expected.put("a", "true");
		testObject("object-with-one-entry-key-a-literal-string-true", false, 0, 0.0, "true");
		testObject("object-with-one-entry-key-a-literal-string-true", ContainerFactory.createObject("a", "true"));
		testObject("object-with-one-entry-key-a-literal-string-true", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-true", expected);
		
		expected.put("a", "false");
		testObject("object-with-one-entry-key-a-literal-string-false", false, 0, 0.0, "false");
		testObject("object-with-one-entry-key-a-literal-string-false", ContainerFactory.createObject("a", "false"));
		testObject("object-with-one-entry-key-a-literal-string-false", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-false", expected);
		
		expected.put("a", "-1");
		testObject("object-with-one-entry-key-a-literal-string-minus-one", false, -1, -1.0, "-1");
		testObject("object-with-one-entry-key-a-literal-string-minus-one", ContainerFactory.createObject("a", "-1"));
		testObject("object-with-one-entry-key-a-literal-string-minus-one", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-minus-one", expected);
		
		expected.put("a", "0");
		testObject("object-with-one-entry-key-a-literal-string-zero", false, 0, 0.0, "0");
		testObject("object-with-one-entry-key-a-literal-string-zero", ContainerFactory.createObject("a", "0"));
		testObject("object-with-one-entry-key-a-literal-string-zero", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-zero", expected);
		
		expected.put("a", "1");
		testObject("object-with-one-entry-key-a-literal-string-plus-one", true, 1, 1.0, "1");
		testObject("object-with-one-entry-key-a-literal-string-plus-one", ContainerFactory.createObject("a", "1"));
		testObject("object-with-one-entry-key-a-literal-string-plus-one", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-plus-one", expected);
		
		expected.put("a", "1.1");
		testObject("object-with-one-entry-key-a-literal-string-plus-one-point-one", false, 1, 1.1, "1.1");
		testObject("object-with-one-entry-key-a-literal-string-plus-one-point-one", ContainerFactory.createObject("a", "1.1"));
		testObject("object-with-one-entry-key-a-literal-string-plus-one-point-one", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-plus-one-point-one", expected);
		
		expected.put("a", "");
		testObject("object-with-one-entry-key-a-literal-string-empty", false, 0, 0.0, "");
		testObject("object-with-one-entry-key-a-literal-string-empty", ContainerFactory.createObject("a", ""));
		testObject("object-with-one-entry-key-a-literal-string-empty", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-empty", expected);
		
		expected.put("a", "abcde");
		testObject("object-with-one-entry-key-a-literal-string-abcde", false, 0, 0.0, "abcde");
		testObject("object-with-one-entry-key-a-literal-string-abcde", ContainerFactory.createObject("a", "abcde"));
		testObject("object-with-one-entry-key-a-literal-string-abcde", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-string-abcde", expected);
		
		expected.put("a", th.getSamplePlainObject());
		testObject("object-with-one-entry-key-a-literal-other", false, 0, 0.0, "literal-object-sample");
		testObject("object-with-one-entry-key-a-literal-other", ContainerFactory.createObject("a", th.getSamplePlainObject()));
		testObject("object-with-one-entry-key-a-literal-other", new UniversalContainer(expected));
		testObject("object-with-one-entry-key-a-literal-other", expected);
		
		expected.put("a", "aaa");
		expected.put("b", "bbb");
		expected.put("c", "ccc");
		expected.put("d", "ddd");
		expected.put("e", "eee");
		testObject("object-with-five-entries-abcde", false, 0, 0.0, "");
		testObject("object-with-five-entries-abcde", ContainerFactory.createObject("a", "aaa", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee"));
		testObject("object-with-five-entries-abcde", new UniversalContainer(expected));
		testObject("object-with-five-entries-abcde", expected);
	}
	
	private void testArray(String sampleName, boolean boolValue, int intValue, double doubleValue, String strValue)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			//check test functions
			assertFalse(c.isUndefined() || c.create().isUndefined());
			assertFalse(c.isNull() || c.create().isNull());
			assertFalse(c.isBoolean() || c.create().isBoolean());
			assertFalse(c.isNumber() || c.create().isNumber());
			assertFalse(c.isString() || c.create().isString());
			assertTrue(c.isObject() && c.create().isObject());
			assertTrue(c.isArray() && !c.create().isArray());
			assertTrue(c.test() && c.create().test());
			
			//check cast functions
			assertEquals(c.asBoolean(), boolValue);
			assertEquals(c.create().asBoolean(), boolValue);
			assertEquals(c.asInt(), intValue);
			assertEquals(c.create().asInt(), intValue);
			assertEquals(c.asString(), strValue);
			assertEquals(c.create().asString(), strValue);
			assertEquals(c.toString(), strValue);
			assertEquals(c.create().toString(), strValue);
			
			//positive equal tests
			assertTrue(c.equals(c));
			
			//negative equal tests
			assertFalse(c.equals(ContainerFactory.undefinedContainer()));
			assertFalse(c.equals(ContainerFactory.nullContainer()));
			assertFalse(c.equals(null));
			assertFalse(c.equals(boolValue));
			assertFalse(c.equals(intValue));
			assertFalse(c.equals(doubleValue));
			assertFalse(c.equals(strValue));
			assertFalse(c.equals(ContainerFactory.createObject()));
			assertFalse(c.equals(ContainerFactory.createObject().create()));
			assertFalse(c.equals(c.create()));
			assertFalse(c.equals(c.clone()));
			assertFalse(c.equals(ContainerFactory.createArray()));
			assertFalse(c.equals(ContainerFactory.createArray().create()));
			
			//positive deepEqual tests
			for (UniversalContainer cc : samples)
			{
				assertTrue(c.deepEquals(cc));
			}
			assertTrue(c.deepEquals(c.clone()));
			
			//negative deepEqual tests
			assertFalse(c.deepEquals(ContainerFactory.undefinedContainer()));
			assertFalse(c.deepEquals(ContainerFactory.nullContainer()));
			assertFalse(c.deepEquals(null));
			assertFalse(c.deepEquals(boolValue));
			assertFalse(c.deepEquals(intValue));
			assertFalse(c.deepEquals(doubleValue));
			assertFalse(c.deepEquals(strValue));
			assertFalse(c.deepEquals(ContainerFactory.createObject()));
			assertFalse(c.deepEquals(ContainerFactory.createObject().create()));
		}
	}
	
	private void testArray(String sampleName, UniversalContainer expected)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			assertTrue(c.deepEquals(expected));
			assertTrue(c.deepEquals(expected.clone()));
		}
	}
	
	private void testArray(String sampleName, List<Object> expected)
	{
		List<UniversalContainer> samples = th.getCachedSamples(sampleName);
		
		assertTrue(samples.size() > 0);
		for (UniversalContainer c : samples)
		{
			assertTrue(c.deepEquals(expected));
		}
	}
	
	@Test
	public void testArray()
	{
		th.cacheSamples("array-*");
		
		List<Object> expected = new ArrayList<Object>();
		
		testArray("array-empty", false, 0, 0.0, "");
		testArray("array-empty", ContainerFactory.createArray());
		testArray("array-empty", new UniversalContainer(expected));
		testArray("array-empty", expected);
		
		expected.add(null);
		testArray("array-with-one-entry-undefined", false, 0, 0.0, "");
		testArray("array-with-one-entry-undefined", ContainerFactory.createArray((Object)null));
		testArray("array-with-one-entry-undefined", ContainerFactory.createArray(ContainerFactory.undefinedContainer()));
		testArray("array-with-one-entry-undefined", new UniversalContainer(expected));
		testArray("array-with-one-entry-undefined", expected);
		
		expected.set(0, ContainerFactory.nullContainer());
		testArray("array-with-one-entry-null", false, 0, 0.0, "");
		testArray("array-with-one-entry-null", ContainerFactory.createArray(ContainerFactory.nullContainer()));
		testArray("array-with-one-entry-null", new UniversalContainer(expected));
		testArray("array-with-one-entry-null", expected);
		
		expected.set(0, false);
		testArray("array-with-one-entry-literal-boolean-false", false, 0, 0.0, "false");
		testArray("array-with-one-entry-literal-boolean-false", ContainerFactory.createArray(false));
		testArray("array-with-one-entry-literal-boolean-false", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-boolean-false", expected);
		
		expected.set(0, true);
		testArray("array-with-one-entry-literal-boolean-true", true, 1, 1.0, "true");
		testArray("array-with-one-entry-literal-boolean-true", ContainerFactory.createArray(true));
		testArray("array-with-one-entry-literal-boolean-true", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-boolean-true", expected);
		
		expected.set(0, -1);
		testArray("array-with-one-entry-literal-number-*-minus-one", false, -1, -1.0, "-1");
		testArray("array-with-one-entry-literal-number-*-minus-one", ContainerFactory.createArray(-1));
		testArray("array-with-one-entry-literal-number-*-minus-one", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-number-*-minus-one", expected);
		
		expected.set(0, 0);
		testArray("array-with-one-entry-literal-number-*-zero", false, 0, 0.0, "0");
		testArray("array-with-one-entry-literal-number-*-zero", ContainerFactory.createArray(0));
		testArray("array-with-one-entry-literal-number-*-zero", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-number-*-zero", expected);
		
		expected.set(0, 1);
		testArray("array-with-one-entry-literal-number-*-plus-one", true, 1, 1.0, "1");
		testArray("array-with-one-entry-literal-number-*-plus-one", ContainerFactory.createArray(1));
		testArray("array-with-one-entry-literal-number-*-plus-one", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-number-*-plus-one", expected);
		
		expected.set(0, 1.1);
		testArray("array-with-one-entry-literal-number-double-plus-one-point-one", false, 1, 1.1, "1.1");
		testArray("array-with-one-entry-literal-number-double-plus-one-point-one", ContainerFactory.createArray(1.1));
		testArray("array-with-one-entry-literal-number-double-plus-one-point-one", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-number-double-plus-one-point-one", expected);
		
		expected.set(0, "true");
		testArray("array-with-one-entry-literal-string-true", false, 0, 0.0, "true");
		testArray("array-with-one-entry-literal-string-true", ContainerFactory.createArray("true"));
		testArray("array-with-one-entry-literal-string-true", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-true", expected);
		
		expected.set(0, "false");
		testArray("array-with-one-entry-literal-string-false", false, 0, 0.0, "false");
		testArray("array-with-one-entry-literal-string-false", ContainerFactory.createArray("false"));
		testArray("array-with-one-entry-literal-string-false", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-false", expected);
		
		expected.set(0, "-1");
		testArray("array-with-one-entry-literal-string-minus-one", false, -1, -1.0, "-1");
		testArray("array-with-one-entry-literal-string-minus-one", ContainerFactory.createArray("-1"));
		testArray("array-with-one-entry-literal-string-minus-one", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-minus-one", expected);
		
		expected.set(0, "0");
		testArray("array-with-one-entry-literal-string-zero", false, 0, 0.0, "0");
		testArray("array-with-one-entry-literal-string-zero", ContainerFactory.createArray("0"));
		testArray("array-with-one-entry-literal-string-zero", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-zero", expected);
		
		expected.set(0, "1");
		testArray("array-with-one-entry-literal-string-plus-one", true, 1, 1.0, "1");
		testArray("array-with-one-entry-literal-string-plus-one", ContainerFactory.createArray("1"));
		testArray("array-with-one-entry-literal-string-plus-one", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-plus-one", expected);
		
		expected.set(0, "1.1");
		testArray("array-with-one-entry-literal-string-plus-one-point-one", false, 1, 1.1, "1.1");
		testArray("array-with-one-entry-literal-string-plus-one-point-one", ContainerFactory.createArray("1.1"));
		testArray("array-with-one-entry-literal-string-plus-one-point-one", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-plus-one-point-one", expected);
		
		expected.set(0, "");
		testArray("array-with-one-entry-literal-string-empty", false, 0, 0.0, "");
		testArray("array-with-one-entry-literal-string-empty", ContainerFactory.createArray(""));
		testArray("array-with-one-entry-literal-string-empty", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-empty", expected);
		
		expected.set(0, "abcde");
		testArray("array-with-one-entry-literal-string-abcde", false, 0, 0.0, "abcde");
		testArray("array-with-one-entry-literal-string-abcde", ContainerFactory.createArray("abcde"));
		testArray("array-with-one-entry-literal-string-abcde", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-string-abcde", expected);
		
		expected.set(0, th.getSamplePlainObject());
		testArray("array-with-one-entry-literal-other", false, 0, 0.0, "literal-object-sample");
		testArray("array-with-one-entry-literal-other", ContainerFactory.createArray(th.getSamplePlainObject()));
		testArray("array-with-one-entry-literal-other", new UniversalContainer(expected));
		testArray("array-with-one-entry-literal-other", expected);
		
		expected.set(0, "aaa");
		expected.add("bbb");
		expected.add("ccc");
		expected.add("ddd");
		expected.add("eee");
		testArray("array-with-five-entries-abcde", false, 0, 0.0, "aaa,bbb,ccc,ddd,eee");
		testArray("array-with-five-entries-abcde", ContainerFactory.createArray("aaa","bbb","ccc","ddd","eee"));
		testArray("array-with-five-entries-abcde", new UniversalContainer(expected));
		testArray("array-with-five-entries-abcde", expected);
	}
	
	@Test
	public void testPrototype()
	{
		try
		{
			ContainerFactory.undefinedContainer().create();
			fail("In this case create should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'create' is not available for Undefined container"); }
		
		try
		{
			new UniversalContainer(true).create();
			fail("In this case create should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'create' is not available for Boolean container"); }
		
		try
		{
			new UniversalContainer(1234).create();
			fail("In this case create should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'create' is not available for Number container"); }
		
		try
		{
			new UniversalContainer("abcdabcd").create();
			fail("In this case create should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'create' is not available for String container"); }
		
		assertTrue(ContainerFactory.nullContainer().create().deepEquals(ContainerFactory.createObject()));
		
		UniversalContainer c1 = ContainerFactory.createObject();
		UniversalContainer c2 = th.getSample("object-with-five-entries-abcde");
		UniversalContainer c3 = ContainerFactory.createArray();
		UniversalContainer c4 = th.getSample("array-with-five-entries-abcde");
		
		UniversalContainer cc1 = c1.create();
		UniversalContainer cc2 = c2.create();
		UniversalContainer cc3 = c3.create();
		UniversalContainer cc4 = c4.create();
		
		c1.set("f", "fff");
		th.assertContainerKeys(cc1, true, new String[]{});
		th.assertContainer(cc1, false, new String[]{"f"}, new Object[]{"fff"});
		
		cc2.set("b", "XXX");
		th.assertContainer(c2, true, new String[]{"a","b","c","d","e"}, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c2, false, new String[]{"a","b","c","d","e"}, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(cc2, true, new String[]{"b"}, new Object[]{"XXX"});
		th.assertContainer(cc2, false, new String[]{"a","b","c","d","e"}, new Object[]{"aaa","XXX","ccc","ddd","eee"});
		
		c3.set(2, "XXX");
		th.assertContainer(c3, new Object[]{ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX"});
		th.assertContainer(cc3, new Object[]{ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX"});
		
		assertEquals(cc4.getLength(), c4.getLength());
		th.assertContainer(cc4, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		
		c4.set(1, "XXX");
		th.assertContainer(cc4, new Object[]{"aaa","XXX","ccc","ddd","eee"});
		
		c4.push("fff");
		th.assertContainer(cc4, new Object[]{"aaa","XXX","ccc","ddd","eee","fff"});
		
		cc4.push("ggg");
		th.assertContainer(c4, new Object[]{"aaa","XXX","ccc","ddd","eee","fff"});
		th.assertContainer(cc4, new Object[]{"aaa","XXX","ccc","ddd","eee","fff","ggg"});
		assertTrue(c4.get(6).equals(ContainerFactory.undefinedContainer()));
		assertTrue(cc4.get(6).equals("ggg"));
	}
	
	@Test
	public void testGetter()
	{
		try
		{
			ContainerFactory.undefinedContainer().get(0);
			fail("In this case get should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get '0' property from Undefined container"); }
		
		try
		{
			ContainerFactory.undefinedContainer().get("a");
			fail("In this case get should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'a' property from Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().get(0);
			fail("In this case get should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get '0' property from Null container"); }
		
		try
		{
			ContainerFactory.nullContainer().get("a");
			fail("In this case get should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'a' property from Null container"); }
		
		assertTrue(ContainerFactory.nullContainer().create().get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.nullContainer().create().get("a").equals(ContainerFactory.undefinedContainer()));
		
		UniversalContainer c1 = th.getSample("literal-boolean-true");
		assertTrue(c1.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.get("a").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c1.valueOf(), Boolean.TRUE);
		assertEquals(c1.valueOf(-1), (Object)null);
		assertEquals(c1.valueOf(0), (Object)null);
		assertEquals(c1.valueOf(1), (Object)null);
		assertEquals(c1.valueOf("a"), (Object)null);
		
		UniversalContainer c2 = th.getSample("literal-number-integer-12345");
		assertTrue(c2.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get("a").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c2.valueOf(), Integer.valueOf(12345));
		assertEquals(c2.valueOf(-1), (Object)null);
		assertEquals(c2.valueOf(0), (Object)null);
		assertEquals(c2.valueOf(1), (Object)null);
		assertEquals(c2.valueOf("a"), (Object)null);
		
		UniversalContainer c3 = th.getSample("literal-string-abcde");
		assertTrue(c3.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c3.get(0).equals("a"));
		assertTrue(c3.get(1).equals("b"));
		assertTrue(c3.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c3.get("a").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c3.valueOf(), "abcde");
		assertEquals(c3.valueOf(-1), (Object)null);
		assertEquals(c3.valueOf(0), "a");
		assertEquals(c3.valueOf(1), "b");
		assertEquals(c3.valueOf("a"), (Object)null);
		
		UniversalContainer c4 = th.getSample("literal-other");
		assertTrue(c4.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c4.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c4.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c4.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c4.get("a").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c4.valueOf(), th.getSamplePlainObject());
		assertEquals(c4.valueOf(-1), (Object)null);
		assertEquals(c4.valueOf(0), (Object)null);
		assertEquals(c4.valueOf(1), (Object)null);
		assertEquals(c4.valueOf("a"), (Object)null);
		
		UniversalContainer c5 = ContainerFactory.createObject();
		assertTrue(c5.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c5.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c5.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c5.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c5.get("a").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c5.valueOf(), (Object)null);
		assertEquals(c5.valueOf(-1), (Object)null);
		assertEquals(c5.valueOf(0), (Object)null);
		assertEquals(c5.valueOf(1), (Object)null);
		assertEquals(c5.valueOf("a"), (Object)null);
		
		UniversalContainer c6 = th.getSample("object-with-five-entries-abcde").set("", "XXX");
		assertTrue(c6.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c6.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c6.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c6.get(null).equals("XXX"));
		assertTrue(c6.get("").equals("XXX"));
		assertTrue(c6.get("a").equals("aaa"));
		assertEquals(c6.valueOf(), (Object)null);
		assertEquals(c6.valueOf(-1), (Object)null);
		assertEquals(c6.valueOf(0), (Object)null);
		assertEquals(c6.valueOf(1), (Object)null);
		assertEquals(c6.valueOf("a"), "aaa");
		
		UniversalContainer c7 = c6.create();
		assertTrue(c7.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c7.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c7.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c7.get(null).equals("XXX"));
		assertTrue(c7.get("").equals("XXX"));
		assertTrue(c7.get("a").equals("aaa"));
		assertEquals(c7.valueOf(), (Object)null);
		assertEquals(c7.valueOf(-1), (Object)null);
		assertEquals(c7.valueOf(0), (Object)null);
		assertEquals(c7.valueOf(1), (Object)null);
		assertEquals(c7.valueOf("a"), "aaa");
		
		c6.set("a", "XXX");
		assertTrue(c7.get("a").equals("XXX"));
		assertEquals(c7.valueOf("a"), "XXX");
		c7.set("a", "a");
		assertTrue(c7.get("a").equals("a"));
		assertEquals(c7.valueOf("a"), "a");
		
		UniversalContainer c8 = ContainerFactory.createArray();
		assertTrue(c8.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c8.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c8.get(1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c8.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c8.get("a").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c8.valueOf(), (Object)null);
		assertEquals(c8.valueOf(-1), (Object)null);
		assertEquals(c8.valueOf(0), (Object)null);
		assertEquals(c8.valueOf(1), (Object)null);
		assertEquals(c8.valueOf("a"), (Object)null);
		
		UniversalContainer c9 = th.getSample("array-with-five-entries-abcde");
		assertTrue(c9.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c9.get(0).equals("aaa"));
		assertTrue(c9.get("0").equals("aaa"));
		assertTrue(c9.get(1).equals("bbb"));
		assertTrue(c9.get("1").equals("bbb"));
		assertTrue(c9.get(100).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c9.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c9.get("aaa").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c9.valueOf(), (Object)null);
		assertEquals(c9.valueOf(-1), (Object)null);
		assertEquals(c9.valueOf(0), "aaa");
		assertEquals(c9.valueOf("0"), "aaa");
		assertEquals(c9.valueOf(1), "bbb");
		assertEquals(c9.valueOf("1"), "bbb");
		assertEquals(c9.valueOf(100), (Object)null);
		assertEquals(c9.valueOf("aaa"), (Object)null);
		
		UniversalContainer c10 = c9.create();
		assertTrue(c10.get(-1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c10.get(0).equals("aaa"));
		assertTrue(c10.get("0").equals("aaa"));
		assertTrue(c10.get(1).equals("bbb"));
		assertTrue(c10.get("1").equals("bbb"));
		assertTrue(c10.get(100).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c10.get(null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c10.get("aaa").equals(ContainerFactory.undefinedContainer()));
		assertEquals(c10.valueOf(), (Object)null);
		assertEquals(c10.valueOf(-1), (Object)null);
		assertEquals(c10.valueOf(0), "aaa");
		assertEquals(c10.valueOf("0"), "aaa");
		assertEquals(c10.valueOf(1), "bbb");
		assertEquals(c10.valueOf("1"), "bbb");
		assertEquals(c10.valueOf(2), "ccc");
		assertEquals(c10.valueOf("2"), "ccc");
		assertEquals(c10.valueOf(100), (Object)null);
		assertEquals(c10.valueOf("aaa"), (Object)null);
		
		c9.set(0, "XXX");
		assertTrue(c10.get(0).equals("XXX"));
		assertEquals(c10.valueOf(0), "XXX");
		c10.set(0, "aaa");
		assertTrue(c10.get(0).equals("aaa"));
		assertEquals(c10.valueOf(0), "aaa");
	}
	
	@Test
	public void testSetter()
	{
		try
		{
			ContainerFactory.undefinedContainer().set(0, "a");
			fail("In this case set should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot set '0' property in Undefined container"); }
		
		try
		{
			ContainerFactory.undefinedContainer().set("a", "a");
			fail("In this case set should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot set 'a' property in Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().set(0, "a");;
			fail("In this case set should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot set '0' property in Null container"); }
		
		try
		{
			ContainerFactory.nullContainer().set("a", "a");
			fail("In this case set should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot set 'a' property in Null container"); }
		
		UniversalContainer c1 = new UniversalContainer(true);
		c1.set(-1, "a");
		c1.set(0, "a");
		c1.set(1, "a");
		c1.set(null, null);
		c1.set("", "");
		c1.set("a", "a");
		th.assertContainerKeys(c1, true, new String[]{});
		th.assertContainerKeys(c1, false, new String[]{});
		
		UniversalContainer c2 = new UniversalContainer(1234);
		c2.set(-1, "a");
		c2.set(0, "a");
		c2.set(1, "a");
		c2.set(null, null);
		c2.set("", "");
		c2.set("a", "a");
		th.assertContainerKeys(c2, true, new String[]{});
		th.assertContainerKeys(c2, false, new String[]{});
		
		UniversalContainer c3 = new UniversalContainer("abcdabcd");
		c3.set(-1, "XXX");
		c3.set(0, "XXX");
		c3.set(1, "XXX");
		c3.set(100, "XXX");
		c3.set(null, null);
		c3.set("", "");
		c3.set("a", "a");
		th.assertContainer(c3, new Object[]{"a", "b", "c", "d", "a", "b", "c", "d"});
		th.assertContainerKeys(c3, true, new String[]{"0", "1", "2", "3", "4", "5", "6", "7"});
		th.assertContainerKeys(c3, false, new String[]{"0", "1", "2", "3", "4", "5", "6", "7"});
		
		UniversalContainer c4 = new UniversalContainer(th.getSamplePlainObject());
		c4.set(-1, "a");
		c4.set(0, "a");
		c4.set(1, "a");
		c4.set(null, null);
		c4.set("", "");
		c4.set("a", "a");
		th.assertContainerKeys(c4, true, new String[]{});
		th.assertContainerKeys(c4, false, new String[]{});
		
		UniversalContainer c5 = ContainerFactory.createObject();
		c5.set(-1, "XXX");
		c5.set(0, "YYY");
		c5.set(1, "ZZZ");
		c5.set("a", "aaa");
		c5.set(null, "null");
		assertTrue(c5.get("").equals("null"));
		c5.set("", "empty");
		th.assertContainer(c5, true, new String[]{"-1", "0", "1", "", "a"}, new Object[]{"XXX", "YYY", "ZZZ", "empty", "aaa"});
		th.assertContainer(c5, false, new String[]{"-1", "0", "1", "", "a"}, new Object[]{"XXX", "YYY", "ZZZ", "empty", "aaa"});
		
		UniversalContainer c6 = th.getSample("object-with-five-entries-abcde");
		c6.set("a", "XXX");
		th.assertContainer(c6, true, new String[]{"a", "b", "c", "d", "e"}, new Object[]{"XXX", "bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c6, false, new String[]{"a", "b", "c", "d", "e"}, new Object[]{"XXX", "bbb", "ccc", "ddd", "eee"});
		
		UniversalContainer c7 = c6.create();
		th.assertContainerKeys(c7, true, new String[]{});
		th.assertContainer(c7, false, new String[]{"a", "b", "c", "d", "e"}, new Object[]{"XXX", "bbb", "ccc", "ddd", "eee"});
		c6.set("b", "XXX");
		th.assertContainerKeys(c7, true, new String[]{});
		th.assertContainer(c7, false, new String[]{"a", "b", "c", "d", "e"}, new Object[]{"XXX", "XXX", "ccc", "ddd", "eee"});
		c7.set("b", "YYY");
		th.assertContainer(c7, true, new String[]{"b"}, new Object[]{"YYY"});
		th.assertContainer(c7, false, new String[]{"a", "b", "c", "d", "e"}, new Object[]{"XXX", "YYY", "ccc", "ddd", "eee"});
		
		UniversalContainer c8 = ContainerFactory.createArray();
		c8.set(-1, "XXX");
		c8.set(0, "YYY");
		c8.set(1, "ZZZ");
		c8.set("a", "aaa");
		c8.set(null, "null");
		assertTrue(c8.get(null).equals("null"));
		c8.set("", "empty");
		th.assertContainer(c8, new Object[]{"YYY", "ZZZ"});
		th.assertContainer(c8, true, new String[]{"-1", "0", "1", "", "a"}, new Object[]{"XXX", "YYY", "ZZZ", "empty", "aaa"});
		th.assertContainer(c8, false, new String[]{"-1", "0", "1", "", "a"}, new Object[]{"XXX", "YYY", "ZZZ", "empty", "aaa"});
		
		UniversalContainer c9 = th.getSample("array-with-five-entries-abcde");
		c9.set(0, "XXX");
		th.assertContainer(c9, new Object[]{"XXX", "bbb", "ccc", "ddd", "eee"});
		
		UniversalContainer c10 = c9.create();
		th.assertContainer(c10, new Object[]{"XXX", "bbb", "ccc", "ddd", "eee"});
		c9.set("1", "XXX");
		th.assertContainer(c10, new Object[]{"XXX", "XXX", "ccc", "ddd", "eee"});
		c10.set(1, "YYY");
		th.assertContainer(c10, new Object[]{"XXX", "YYY", "ccc", "ddd", "eee"});
	}
	
	@Test
	public void testLength()
	{
		try
		{
			ContainerFactory.undefinedContainer().getLength();
			fail("In this case getLength should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'length' property from Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().getLength();
			fail("In this case getLength should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'length' property from Null container"); }
		
		//Test literals
		assertEquals(th.getSample("literal-boolean-true").getLength(), null);
		assertEquals(th.getSample("literal-boolean-false").getLength(), null);
		assertEquals(th.getSample("literal-number-integer-12345").getLength(), null);
		assertEquals(th.getSample("literal-string-abcde").getLength(), new Integer(5));
		assertEquals(th.getSample("literal-other").getLength(), null);
		
		//Test objects
		assertEquals(ContainerFactory.nullContainer().create().getLength(), null);
		assertEquals(ContainerFactory.createObject().getLength(), null);
		assertEquals(th.getSample("object-with-five-entries-abcde").getLength(), null);
		
		//Test arrays
		assertEquals(ContainerFactory.createArray().getLength(), new Integer(0));
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		UniversalContainer c3 = c2.create();
		
		assertEquals(c1.getLength(), new Integer(5));
		assertEquals(c2.getLength(), new Integer(5));
		assertEquals(c3.getLength(), new Integer(5));
		
		c1.push("XXX");
		assertEquals(c1.getLength(), new Integer(6));
		assertEquals(c2.getLength(), new Integer(6));
		assertEquals(c3.getLength(), new Integer(6));
		
		c1.set(9, "XXX");
		assertEquals(c1.getLength(), new Integer(10));
		assertEquals(c2.getLength(), new Integer(10));
		assertEquals(c3.getLength(), new Integer(10));
		
		c2.push("YYY");
		assertEquals(c1.getLength(), new Integer(10));
		assertEquals(c2.getLength(), new Integer(11));
		assertEquals(c3.getLength(), new Integer(11));
		
		c1.pop();
		assertEquals(c1.getLength(), new Integer(9));
		assertEquals(c2.getLength(), new Integer(11));
		assertEquals(c3.getLength(), new Integer(11));
	}
	
	@Test
	public void testConstructor()
	{
		try
		{
			ContainerFactory.undefinedContainer().getConstructor();
			fail("In this case getLength should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'constructor' property from Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().getConstructor();
			fail("In this case getLength should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'constructor' property from Null container"); }
		
		//Test literals
		assertEquals(th.getSample("literal-boolean-true").getConstructor(), ContainerConstructor.LITERAL);
		assertEquals(th.getSample("literal-boolean-false").getConstructor(), ContainerConstructor.LITERAL);
		assertEquals(th.getSample("literal-number-integer-12345").getConstructor(), ContainerConstructor.LITERAL);
		assertEquals(th.getSample("literal-string-abcde").getConstructor(), ContainerConstructor.LITERAL);
		assertEquals(th.getSample("literal-other").getConstructor(), ContainerConstructor.LITERAL);
		
		//Test objects
		assertEquals(ContainerFactory.nullContainer().create().getConstructor(), null);
		assertEquals(th.getSample("object-with-five-entries-abcde").getConstructor(), ContainerConstructor.OBJECT);
		assertEquals(th.getSample("object-with-five-entries-abcde").create().getConstructor(), ContainerConstructor.OBJECT);
		
		//Test arrays
		assertEquals(th.getSample("array-with-five-entries-abcde").getConstructor(), ContainerConstructor.ARRAY);
		assertEquals(th.getSample("array-with-five-entries-abcde").create().getConstructor(), ContainerConstructor.ARRAY);
		assertEquals(th.getSample("array-with-five-entries-abcde").create().create().getConstructor(), ContainerConstructor.ARRAY);
	}
	
	@Test
	public void testToStringOtherCases()
	{
		assertEquals(ContainerFactory.createArray(null,null,null).asString(), ",,");
		assertEquals(ContainerFactory.createArray(null,null,null).toString(), ",,");
		
		assertEquals(ContainerFactory.createArray(ContainerFactory.nullContainer(),ContainerFactory.nullContainer(),ContainerFactory.nullContainer()).asString(), ",,");
		assertEquals(ContainerFactory.createArray(ContainerFactory.nullContainer(),ContainerFactory.nullContainer(),ContainerFactory.nullContainer()).toString(), ",,");
		
		assertEquals(ContainerFactory.createArray(ContainerFactory.createArray(null,null),null,null).asString(), ",,,");
		assertEquals(ContainerFactory.createArray(ContainerFactory.createArray(null,null),null,null).toString(), ",,,");
		
		assertEquals(ContainerFactory.createArray(null,null,ContainerFactory.createArray()).asString(), ",,");
		assertEquals(ContainerFactory.createArray(null,null,ContainerFactory.createArray()).toString(), ",,");
		
		assertEquals(ContainerFactory.createArray(ContainerFactory.createArray(null,null)).asString(), ",");
		assertEquals(ContainerFactory.createArray(ContainerFactory.createArray(null,null)).toString(), ",");
	}
	
	@Test
	public void testToStringPrototypedContainer()
	{
		assertEquals(ContainerFactory.nullContainer().create().asString(), "[object Object]");
		assertEquals(ContainerFactory.nullContainer().create().toString(), "[object Object]");
		
		assertEquals(ContainerFactory.createObject().create().asString(), "[object Object]");
		assertEquals(ContainerFactory.createObject().create().toString(), "[object Object]");
		
		assertEquals(ContainerFactory.createArray(1,2,3).create().asString(), "1,2,3");
		assertEquals(ContainerFactory.createArray(1,2,3).create().toString(), "1,2,3");
		
		assertEquals(ContainerFactory.createArray(1,2,3).create().create().asString(), "1,2,3");
		assertEquals(ContainerFactory.createArray(1,2,3).create().create().toString(), "1,2,3");
	}
	
	@Test
	public void testToStringCyclicContainer()
	{
		UniversalContainer c1 = ContainerFactory.createArray()
			.set(0, "aaa")
			.set(1, "bbb")
			.set(2, ContainerFactory.createArray()
				.set(0, "aa")
				.set(1, ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set(2, "cc"));
		
		UniversalContainer c2 = ContainerFactory.createArray()
			.set(0, "aaa")
			.set(1, "bbb")
			.set(2, ContainerFactory.createArray()
				.set(0, "aa")
				.set(1, ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set(2, "cc"));
		
		UniversalContainer c3 = ContainerFactory.createArray()
			.set(0, "aaa")
			.set(1, "bbb")
			.set(2, ContainerFactory.createArray()
				.set(0, "aa")
				.set(1, ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set(2, "cc"));
		
		c1.get(2).get(1).set(4, c1);
		assertEquals(c1.asString(), "aaa,bbb,aa,a,,,[object Object],,,,cc");
		assertEquals(c1.toString(), "aaa,bbb,aa,a,,,[object Object],,,,cc");
		
		c2.get(2).get(1).set(4, c1);
		assertEquals(c2.asString(), "aaa,bbb,aa,a,,,[object Object],aaa,bbb,aa,a,,,[object Object],,,,cc,,,cc");
		assertEquals(c2.toString(), "aaa,bbb,aa,a,,,[object Object],aaa,bbb,aa,a,,,[object Object],,,,cc,,,cc");
		
		c2.get(2).get(1).set(4, c3);
		c3.get(2).get(1).set(4, c2);
		assertEquals(c2.asString(), "aaa,bbb,aa,a,,,[object Object],aaa,bbb,aa,a,,,[object Object],,,,cc,,,cc");
		assertEquals(c2.toString(), "aaa,bbb,aa,a,,,[object Object],aaa,bbb,aa,a,,,[object Object],,,,cc,,,cc");
	}
	
	@Test
	public void testJsonStringify()
	{
		UniversalContainer c1 = ContainerFactory.createArray()
			.set(0, "aaa")
			.set(1, true)
			.set(2, ContainerFactory.createArray()
				.set(0, 555)
				.set(1, ContainerFactory.createArray()
					.set(0, null)
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject(0,null,1,null))
					.set(6, ContainerFactory.undefinedContainer()))
				.set(2, "cc"));
		
		UniversalContainer c2 = ContainerFactory.createObject()
			.set(0, "aaa")
			.set("b", false)
			.set("2", ContainerFactory.createObject()
				.set(0, 000)
				.set(1, ContainerFactory.createArray()
					.set(0, null)
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set(2, "cc"));
		
		UniversalContainer c3 = ContainerFactory.createArray()
			.set(0, "aaa")
			.set(1, "bbb")
			.set(2, ContainerFactory.createArray(1,2,3).create()
				.set(0, "aa")
				.set(1, ContainerFactory.createObject("a",1,"b",2).create()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set("c", "cc"));
		
		assertEquals(JsonParser.stringify(null), "");
		assertEquals(JsonParser.stringify(ContainerFactory.undefinedContainer()), "");
		assertEquals(JsonParser.stringify(ContainerFactory.nullContainer()), "null");
		assertEquals(JsonParser.stringify(new UniversalContainer(false)), "false");
		assertEquals(JsonParser.stringify(new UniversalContainer(000)), "0");
		assertEquals(JsonParser.stringify(new UniversalContainer("0")), "\"0\"");
		assertEquals(JsonParser.stringify(new UniversalContainer("[]")), "\"[]\"");
		assertEquals(JsonParser.stringify(ContainerFactory.createObject()), "{}");
		assertEquals(JsonParser.stringify(ContainerFactory.createArray()), "[]");
		assertEquals(JsonParser.stringify(ContainerFactory.createArray(null,null,null)), "[null,null,null]");
		assertEquals(JsonParser.stringify(c1), "[\"aaa\",true,[555,[null,null,null,{},null,null,null],\"cc\"]]");
		
		String c2str = JsonParser.stringify(c2);
		c2str = c2str.replace("\"0\":0", "")
			 		 .replace("\"1\":[null,null,null,{},null,null,null]", "")
			 		 .replace("\"2\":\"cc\"", "")
			 		 .replace("\"0\":\"aaa\"", "")
			 		 .replace("\"b\":false", "")
			 		 .replace("\"2\":{,,}", "");
		assertEquals(c2str, "{,,}");
		
		String c3str = JsonParser.stringify(c3);
		c3str = c3str.replace("\"0\":\"a\"", "")
	 		 		 .replace("\"3\":{}", "")
	 		 		 .replace("\"4\":null", "")
	 		 		 .replace("\"0\":\"aa\"", "")
	 		 		 .replace("\"1\":{,,}", "")
	 		 		 .replace("\"c\":\"cc\"", "");
		assertEquals(c3str, "[\"aaa\",\"bbb\",{,,}]");
		
		// For recursive containers stringify should throw an error
		
		try
		{
			c1.get(2).get(1).set(4, c1);
			JsonParser.stringify(c1);
			fail("For recursive containers stringify should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot convert circular container to JSON string"); }
		
		try
		{
			c2.get(2).get(1).set(4, c1);
			JsonParser.stringify(c2);
			fail("For recursive containers stringify should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot convert circular container to JSON string"); }
		
		try
		{
			c2.get(2).get(1).set(4, c3);
			c3.get(2).get(1).set(4, c2);
			JsonParser.stringify(c2);
			fail("For recursive containers stringify should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot convert circular container to JSON string"); }
	}
	
	@Test
	public void testClone()
	{
		assertTrue(ContainerFactory.undefinedContainer() == ContainerFactory.undefinedContainer().clone());
		assertTrue(ContainerFactory.nullContainer() == ContainerFactory.nullContainer().clone());
		
		UniversalContainer c1 = new UniversalContainer(th.getSamplePlainObject());
		assertTrue(c1 == c1.clone());
		
		UniversalContainer c2 = th.getSample("object-with-five-entries-abcde");
		assertTrue(c2.deepEquals(c2.clone()));
		
		UniversalContainer c3 = c2.create();
		assertTrue(c3.deepEquals(c3.clone()));
		assertTrue(c3.clone().deepEquals(c3));
		assertFalse(c3.clone().deepEquals(c2));
		
		c2.set("a", "XXX");
		th.assertContainer(c3, true, new String[]{}, new Object[]{});
		th.assertContainer(c3, false, new String[]{"a", "b", "c", "d", "e"}, new Object[]{"XXX", "bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c3.clone(), true, new String[]{}, new Object[]{});
		th.assertContainer(c3.clone(), false, new String[]{}, new Object[]{});
		
		UniversalContainer c4 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		assertTrue(c4.deepEquals(c4.clone()));
		th.assertContainer(c4.clone(), new Object[]{"aaa", "bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c4.clone(), true, new String[]{"0", "1", "2", "3", "4"}, new Object[]{"aaa", "bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c4.clone(), false, new String[]{"0", "1", "2", "3", "4"}, new Object[]{"aaa", "bbb", "ccc", "ddd", "eee"});
		assertTrue(c4.get("a").equals("XXX"));
		assertFalse(c4.clone().get("a").equals("XXX"));
		
		UniversalContainer c5 = c4.create();
		th.assertContainer(c5, new Object[]{"aaa", "bbb", "ccc", "ddd", "eee"});
		th.assertContainerKeys(c5, true, new String[]{});
		th.assertContainer(c5, false, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa", "bbb", "ccc", "ddd", "eee", "XXX"});
		
		UniversalContainer c6 = c5.clone();
		th.assertContainer(c6, true, new String[]{}, new Object[]{});
		th.assertContainer(c6, false, new String[]{}, new Object[]{});
		c5.set(0, "XXX");
		c5.set("a", "YYY");
		th.assertContainer(c6, true, new String[]{}, new Object[]{});
		th.assertContainer(c6, false, new String[]{}, new Object[]{});
		c6 = c5.clone();
		th.assertContainer(c6, true, new String[]{"0","a"}, new Object[]{"XXX", "YYY"});
		th.assertContainer(c6, false, new String[]{"0","a"}, new Object[]{"XXX", "YYY"});
		
		//Test hierarchy and recursive hierarchy
		UniversalContainer c7 = th.getSample("array-with-five-entries-abcde");
		c7.push(th.getSample("array-with-five-entries-abcde"));
		c7.push(c7);
		assertEquals(c7.clone().getLength(), c7.getLength());
		assertTrue(c7.clone().get(1).equals("bbb"));
		assertTrue(c7.clone().get(5).deepEquals(th.getSample("array-with-five-entries-abcde")));
	}
	
	@Test
	public void testConcat()
	{
		try
		{
			ContainerFactory.undefinedContainer().concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Null container"); }
		
		try
		{
			th.getSample("literal-boolean-true").concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Boolean container"); }
		
		try
		{
			th.getSample("literal-number-integer-12345").concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Number container"); }
		
		try
		{
			th.getSample("literal-other").concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Literal container"); }
		
		try
		{
			ContainerFactory.createObject().concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Object container"); }
		
		try
		{
			ContainerFactory.nullContainer().create().concat();
			fail("In this case concat should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'concat' is not available for Object container"); }
		
		//Test string concat
		UniversalContainer c1 = th.getSample("literal-string-abcde");
		assertTrue(c1.concat((Object)null).equals("abcdeundefined"));
		assertTrue(c1.concat((Object[])null).equals("abcde"));
		assertTrue(c1.concat(ContainerFactory.undefinedContainer()).equals("abcdeundefined"));
		assertTrue(c1.concat(ContainerFactory.nullContainer()).equals("abcdenull"));
		assertTrue(c1.concat(ContainerFactory.nullContainer().create()).equals("abcde[object Object]"));
		assertTrue(c1.concat(true).equals("abcdetrue"));
		assertTrue(c1.concat(12345).equals("abcde12345"));
		assertTrue(c1.concat("").equals("abcde"));
		assertTrue(c1.concat("XXX").equals("abcdeXXX"));
		assertTrue(c1.concat(c1,c1,c1).equals("abcdeabcdeabcdeabcde"));
		assertTrue(c1.concat(th.getSamplePlainObject()).equals("abcdeliteral-object-sample"));
		assertTrue(c1.concat(ContainerFactory.createObject()).equals("abcde[object Object]"));
		assertTrue(c1.concat(ContainerFactory.createArray()).equals("abcde"));
		assertTrue(c1.concat(th.getSample("array-with-five-entries-abcde")).equals("abcdeaaa,bbb,ccc,ddd,eee"));
		assertTrue(c1.equals("abcde"));
		
		//Test array concat
		UniversalContainer c2 = ContainerFactory.createArray();
		UniversalContainer c3 = th.getSample("array-with-five-entries-abcde");
		c3.set("a", "XXX");
		
		th.assertContainer(c2.concat(), new Object[]{});
		th.assertContainer(c2.concat((Object)null), new Object[]{ContainerFactory.undefinedContainer()});
		th.assertContainer(c2.concat((Object[])null), new Object[]{});
		th.assertContainer(c2.concat(ContainerFactory.undefinedContainer()), new Object[]{ContainerFactory.undefinedContainer()});
		th.assertContainer(c2.concat(ContainerFactory.nullContainer()), new Object[]{ContainerFactory.nullContainer()});
		th.assertContainer(c2.concat(ContainerFactory.nullContainer().create()), new Object[]{ContainerFactory.nullContainer().create()});
		
		th.assertContainer(c2.concat("aaa", "bbb"), new Object[]{"aaa","bbb"});
		th.assertContainer(c2, new Object[]{});
		
		UniversalContainer c4 = c3.concat("YYY");
		th.assertContainer(c4, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY"});
		th.assertContainerKeys(c4, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c4, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainer(c3, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		
		UniversalContainer c5 = c2.concat("aaa", "bbb", c3, th.getSamplePlainObject(), 12345, ContainerFactory.createObject("a", c3));
		th.assertContainer(c5, new Object[]{"aaa","bbb","aaa","bbb","ccc","ddd","eee",th.getSamplePlainObject(),12345,ContainerFactory.createObject("a",ContainerFactory.createArray("aaa","bbb","ccc","ddd","eee"))});
		th.assertContainerKeys(c5, true, new String[]{"0","1","2","3","4","5","6","7","8","9"});
		th.assertContainerKeys(c5, false, new String[]{"0","1","2","3","4","5","6","7","8","9"});
		th.assertContainer(c3, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		
		c2.set(3, "ZZZ");
		th.assertContainer(c2.concat("YYY"), new Object[]{ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"ZZZ","YYY"});
		
		c3.set(1, "WWW");
		th.assertContainer(c5, new Object[]{"aaa","bbb","aaa","bbb","ccc","ddd","eee",th.getSamplePlainObject(),12345,ContainerFactory.createObject("a",ContainerFactory.createArray("aaa","WWW","ccc","ddd","eee"))});
		
		//Test cyclic containers
		th.assertContainer(c3.concat(c3), new Object[]{"aaa","WWW","ccc","ddd","eee","aaa","WWW","ccc","ddd","eee"});
		c3.push(c3);
		th.assertContainerKeys(c3.concat(c3), true, new String[]{"0","1","2","3","4","5","6","7","8","9","10","11"});
		th.assertContainerKeys(c3.concat(c3), false, new String[]{"0","1","2","3","4","5","6","7","8","9","10","11"});
		
		//Test prototyped containers
		UniversalContainer c6 = c2.create();
		th.assertContainer(c6.concat("aaa", "bbb"), new Object[]{c6,"aaa","bbb"});
		th.assertContainer(ContainerFactory.createArray("aaa","bbb").concat(c6), new Object[]{"aaa","bbb",c6});
		th.assertContainer(c6, new Object[]{ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"ZZZ"});
		
		//Test multi hierachy arrays
		c4.push(ContainerFactory.createArray("aaa","bbb"));
		th.assertContainer(c4.concat(ContainerFactory.createArray("aaa","bbb")), new Object[]{"aaa","bbb","ccc","ddd","eee","YYY",ContainerFactory.createArray("aaa","bbb"),"aaa","bbb"});
		th.assertContainer(ContainerFactory.createArray("aaa","bbb").concat(c4), new Object[]{"aaa","bbb","aaa","bbb","ccc","ddd","eee","YYY",ContainerFactory.createArray("aaa","bbb")});
	}
	
	@Test
	public void testDeepEquality()
	{
		UniversalContainer c1 = ContainerFactory.createObject()
			.set("a", 1)
			.set("b", 2)
			.set("c", ContainerFactory.createObject()
				.set("aa", 11)
				.set("bb", 22)
				.set("cc", ContainerFactory.createArray()
					.set(0, 111)
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer())
			));
		UniversalContainer c2 = ContainerFactory.createObject()
			.set("a", 1)
			.set("b", 2)
			.set("c", ContainerFactory.createObject()
				.set("aa", 11)
				.set("bb", 22)
				.set("cc", ContainerFactory.createArray()
					.set(0, 111)
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer())
			));
		UniversalContainer c3 = ContainerFactory.createObject()
			.set("a", 1)
			.set("b", 2)
			.set("c", ContainerFactory.createObject()
				.set("aa", 11)
				.set("bb", 22)
				.set("cc", ContainerFactory.createArray()
					.set(0, 111)
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
			));
		UniversalContainer c4 = ContainerFactory.createObject()
			.set("a", 1)
			.set("b", 2)
			.set("c", ContainerFactory.createObject()
				.set("aa", 11)
				.set("bb", 22)
			);
		UniversalContainer c5 = ContainerFactory.createObject()
			.set("a", 1)
			.set("b", 2);
		
		assertTrue(c1.deepEquals(c2));
		assertFalse(c1.deepEquals(c3));
		assertFalse(c1.deepEquals(c4));
		assertFalse(c1.deepEquals(c5));
		
		assertTrue(ContainerFactory.undefinedContainer().deepEquals(null));
		assertFalse(ContainerFactory.nullContainer().deepEquals(null));
		
		// null != Object.create(null)
		assertFalse(ContainerFactory.nullContainer().deepEquals(ContainerFactory.nullContainer().create()));
		assertFalse(ContainerFactory.nullContainer().create().deepEquals(ContainerFactory.nullContainer()));
		
		// {} == Object.create(null)
		assertTrue(ContainerFactory.createObject().deepEquals(ContainerFactory.nullContainer().create()));
		assertTrue(ContainerFactory.nullContainer().create().deepEquals(ContainerFactory.createObject()));
		
		// [] != Object.create([])
		assertFalse(ContainerFactory.createArray().deepEquals(ContainerFactory.createArray().create()));
		assertFalse(ContainerFactory.createArray().create().deepEquals(ContainerFactory.createArray()));
		
		// {} != Object.create([])
		assertFalse(ContainerFactory.createObject().deepEquals(ContainerFactory.createArray().create()));
		assertFalse(ContainerFactory.createArray().create().deepEquals(ContainerFactory.createObject()));
		
		// Object.create({}) != Object.create([])
		assertFalse(ContainerFactory.createObject().create().deepEquals(ContainerFactory.createArray().create()));
		assertFalse(ContainerFactory.createArray().create().deepEquals(ContainerFactory.createObject().create()));
		
		// Object.create([]) == Object.create([])
		assertTrue(ContainerFactory.createArray().create().deepEquals(ContainerFactory.createArray().create()));
		
		// Object.create(Object.create([])) == Object.create([])
		assertTrue(ContainerFactory.createArray().create().create().deepEquals(ContainerFactory.createArray().create()));
		assertTrue(ContainerFactory.createArray().create().deepEquals(ContainerFactory.createArray().create().create()));
		
		// {} == Object.create({})
		assertTrue(ContainerFactory.createObject().deepEquals(ContainerFactory.createObject().create()));
		assertTrue(ContainerFactory.createObject().create().deepEquals(ContainerFactory.createObject()));
		
		// Object.create({}) == Object.create({})
		assertTrue(ContainerFactory.createArray().create().deepEquals(ContainerFactory.createArray().create()));
		
		// Object.create(null) == Object.create({})
		assertTrue(ContainerFactory.nullContainer().create().deepEquals(ContainerFactory.createObject().create()));
		assertTrue(ContainerFactory.createObject().create().deepEquals(ContainerFactory.nullContainer().create()));
		
		UniversalContainer c6 = ContainerFactory.createObject(0, "a");
		UniversalContainer c7 = ContainerFactory.createArray("a");
		
		// {0:"a"} != ["a"]
		assertFalse(c6.deepEquals(c7));
		assertFalse(c7.deepEquals(c6));
		
		// {0:"a"} != Object.create({0:"a"})
		assertFalse(c6.deepEquals(c6.create()));
		assertFalse(c6.create().deepEquals(c6));
		
		// {0:"a"} != Object.create(["a"])
		assertFalse(c6.deepEquals(c7.create()));
		assertFalse(c7.create().deepEquals(c6));
		
		// Object.create(null) == Object.create({0:"a"})
		assertTrue(ContainerFactory.nullContainer().create().deepEquals(c6.create()));
		assertTrue(c6.create().deepEquals(ContainerFactory.nullContainer().create()));
		
		UniversalContainer c8 = ContainerFactory.createObject("a", "a");
		UniversalContainer c9 = ContainerFactory.createArray().set("a", "a");
		
		// {"a":"a"} != ["a":"a"]
		assertFalse(c8.deepEquals(c9));
		assertFalse(c9.deepEquals(c8));
	}
	
	@Test
	public void testDeepEquilityCyclicContainer()
	{
		UniversalContainer c1 = ContainerFactory.createObject()
			.set("a", "aaa")
			.set("b", "bbb")
			.set("c", ContainerFactory.createObject()
				.set("aa", "aa")
				.set("bb", ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set("cc", "cc"));
		
		UniversalContainer c2 = ContainerFactory.createObject()
			.set("a", "aaa")
			.set("b", "bbb")
			.set("c", ContainerFactory.createObject()
				.set("aa", "aa")
				.set("bb", ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set("cc", "cc"));
		
		UniversalContainer c3 = ContainerFactory.createObject()
			.set("a", "aaa")
			.set("b", "bbb")
			.set("c", ContainerFactory.createObject()
				.set("aa", "aa")
				.set("bb", ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set("cc", "cc"));
		
		UniversalContainer c4 = ContainerFactory.createObject()
			.set("a", "aaa")
			.set("b", "bbb")
			.set("c", ContainerFactory.createObject()
				.set("aa", "aa")
				.set("bb", ContainerFactory.createArray()
					.set(0, "a")
					.set(4, ContainerFactory.nullContainer())
					.set(3, ContainerFactory.createObject())
					.set(6, ContainerFactory.undefinedContainer()))
				.set("cc", "cc"));
		
		c1.get("c").get("bb").get(3).set("cyclic", c1);
		c2.get("c").get("bb").get(3).set("cyclic", c2);
		assertTrue(c1.deepEquals(c2));
		assertFalse(c1.deepEquals(c4));
		
		c2.get("c").get("bb").push(c1);
		assertFalse(c2.deepEquals(c1));
		
		c2.get("c").get("bb").pop();
		c2.get("c").get("bb").get(3).set("cyclic", c3);
		c3.get("c").get("bb").get(3).set("cyclic", c2);
		assertTrue(c3.deepEquals(c1));
		
		c1.get("c").get("bb").get(3).set("cyclic", c4);
		assertFalse(c3.deepEquals(c1));
	}
	
	@Test
	public void testDelete()
	{
		try
		{
			ContainerFactory.undefinedContainer().delete(0);
			fail("In this case delete should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get '0' property from Undefined container"); }
		
		try
		{
			ContainerFactory.undefinedContainer().delete("a");
			fail("In this case delete should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'a' property from Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().delete(0);
			fail("In this case delete should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get '0' property from Null container"); }
		
		try
		{
			ContainerFactory.nullContainer().delete("a");
			fail("In this case delete should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Cannot get 'a' property from Null container"); }
		
		UniversalContainer c1 = new UniversalContainer(th.getSamplePlainObject());
		c1.delete(3);
		c1.delete("a");
		c1.delete(null);
		
		UniversalContainer c2 = th.getSample("object-with-five-entries-abcde");
		c2.delete("a");
		c2.delete("XXX");
		c2.delete("");
		c2.delete(null);
		th.assertContainer(c2, true, new String[]{"b","c","d","e"}, new Object[]{"bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c2, false, new String[]{"b","c","d","e"}, new Object[]{"bbb", "ccc", "ddd", "eee"});
		
		UniversalContainer c3 = c2.create();
		c3.delete("b");
		th.assertContainerKeys(c3, true, new String[]{});
		th.assertContainer(c3, false, new String[]{"b","c","d","e"}, new Object[]{"bbb", "ccc", "ddd", "eee"});
		c2.delete("b");
		th.assertContainerKeys(c3, true, new String[]{});
		th.assertContainer(c3, false, new String[]{"c","d","e"}, new Object[]{"ccc", "ddd", "eee"});
		
		UniversalContainer c4 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c4.delete(0);
		th.assertContainer(c4, new Object[]{ContainerFactory.undefinedContainer(), "bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c4, true, new String[]{"1","2","3","4","a"}, new Object[]{"bbb", "ccc", "ddd", "eee", "XXX"});
		th.assertContainer(c4, false, new String[]{"1","2","3","4","a"}, new Object[]{"bbb", "ccc", "ddd", "eee", "XXX"});
		c4.delete("3");
		th.assertContainer(c4, new Object[]{ContainerFactory.undefinedContainer(), "bbb", "ccc", ContainerFactory.undefinedContainer(), "eee"});
		th.assertContainer(c4, true, new String[]{"1","2","4","a"}, new Object[]{"bbb", "ccc", "eee", "XXX"});
		th.assertContainer(c4, false, new String[]{"1","2","4","a"}, new Object[]{"bbb", "ccc", "eee", "XXX"});
		c4.delete(-1);
		th.assertContainer(c4, new Object[]{ContainerFactory.undefinedContainer(), "bbb", "ccc", ContainerFactory.undefinedContainer(), "eee"});
		th.assertContainer(c4, true, new String[]{"1","2","4","a"}, new Object[]{"bbb", "ccc", "eee", "XXX"});
		th.assertContainer(c4, false, new String[]{"1","2","4","a"}, new Object[]{"bbb", "ccc", "eee", "XXX"});
		c4.delete("a");
		th.assertContainer(c4, new Object[]{ContainerFactory.undefinedContainer(), "bbb", "ccc", ContainerFactory.undefinedContainer(), "eee"});
		th.assertContainer(c4, true, new String[]{"1","2","4"}, new Object[]{"bbb", "ccc", "eee"});
		th.assertContainer(c4, false, new String[]{"1","2","4"}, new Object[]{"bbb", "ccc", "eee"});
		
		UniversalContainer c5 = c4.create();
		c5.delete(1);
		th.assertContainer(c5, new Object[]{ContainerFactory.undefinedContainer(), "bbb", "ccc", ContainerFactory.undefinedContainer(), "eee"});
		th.assertContainerKeys(c5, true, new String[]{});
		th.assertContainer(c5, false, new String[]{"1","2","4"}, new Object[]{"bbb", "ccc", "eee"});
		c4.delete(1);
		th.assertContainer(c5, new Object[]{ContainerFactory.undefinedContainer(), ContainerFactory.undefinedContainer(), "ccc", ContainerFactory.undefinedContainer(), "eee"});
		th.assertContainerKeys(c5, true, new String[]{});
		th.assertContainer(c5, false, new String[]{"2","4"}, new Object[]{"ccc", "eee"});
		c4.delete(4);
		th.assertContainer(c5, new Object[]{ContainerFactory.undefinedContainer(), ContainerFactory.undefinedContainer(), "ccc", ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer()});
		th.assertContainerKeys(c5, true, new String[]{});
		th.assertContainer(c5, false, new String[]{"2"}, new Object[]{"ccc"});
	}
	
	@Test
	public void testExtend()
	{
		UniversalContainer c1 = ContainerFactory.undefinedContainer();
		UniversalContainer c2 = ContainerFactory.nullContainer();
		UniversalContainer c3 = new UniversalContainer(true);
		UniversalContainer c4 = new UniversalContainer(1);
		UniversalContainer c5 = new UniversalContainer("abcde");
		UniversalContainer c6 = new UniversalContainer(th.getSamplePlainObject());
		UniversalContainer c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		UniversalContainer c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		
		assertTrue(ContainerFactory.undefinedContainer().extend().equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c2).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c3).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c4).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c6).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c7).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().extend(c8).equals(ContainerFactory.undefinedContainer()));
		
		assertTrue(ContainerFactory.nullContainer().extend().equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c1).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c2).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c3).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c4).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c5).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c6).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c7).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().extend(c8).equals(ContainerFactory.nullContainer()));
		
		assertTrue(new UniversalContainer(true).extend().equals(true));
		assertTrue(new UniversalContainer(true).extend(c1).equals(true));
		assertTrue(new UniversalContainer(true).extend(c2).equals(true));
		assertTrue(new UniversalContainer(true).extend(c3).equals(true));
		assertTrue(new UniversalContainer(true).extend(c4).equals(true));
		assertTrue(new UniversalContainer(true).extend(c5).equals(true));
		assertTrue(new UniversalContainer(true).extend(c6).equals(true));
		assertTrue(new UniversalContainer(true).extend(c7).equals(true));
		assertTrue(new UniversalContainer(true).extend(c8).equals(true));
		
		assertTrue(new UniversalContainer(1).extend().equals(1));
		assertTrue(new UniversalContainer(1).extend(c1).equals(1));
		assertTrue(new UniversalContainer(1).extend(c2).equals(1));
		assertTrue(new UniversalContainer(1).extend(c3).equals(1));
		assertTrue(new UniversalContainer(1).extend(c4).equals(1));
		assertTrue(new UniversalContainer(1).extend(c5).equals(1));
		assertTrue(new UniversalContainer(1).extend(c6).equals(1));
		assertTrue(new UniversalContainer(1).extend(c7).equals(1));
		assertTrue(new UniversalContainer(1).extend(c8).equals(1));
		
		assertTrue(new UniversalContainer("edcba").extend().equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c1).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c2).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c3).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c4).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c5).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c6).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c7).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").extend(c8).equals("edcba"));
		
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend().equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c1).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c2).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c3).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c4).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c5).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c6).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c7).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).extend(c8).equals(th.getSamplePlainObject()));
		
		assertTrue(ContainerFactory.createObject().extend().deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c1).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c2).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c3).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c4).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c5).deepEquals(ContainerFactory.createObject(0, "a", 1, "b", 2, "c", 3, "d", 4, "e")));
		assertTrue(ContainerFactory.createObject().extend(c6).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c7).deepEquals(c7));
		assertTrue(ContainerFactory.createObject().extend(c8).deepEquals(ContainerFactory.createObject(0, "aaa", 1, "bbb", 2, "ccc", 3, "ddd", 4, "eee", "a", "XXX")));
		
		assertTrue(ContainerFactory.createArray().extend().deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c1).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c2).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c3).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c4).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c5).deepEquals(ContainerFactory.createArray("a", "b", "c", "d", "e")));
		assertTrue(ContainerFactory.createArray().extend(c6).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c7).deepEquals(ContainerFactory.createArray("000", ContainerFactory.undefinedContainer(), "222")));
		assertTrue(ContainerFactory.createArray().extend(c8).deepEquals(c8));
		
		assertTrue(ContainerFactory.createObject().extend(c7.create()).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().extend(c8.create()).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createArray().extend(c7.create()).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().extend(c8.create()).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createObject().create().extend(c7).deepEquals(c7));
		assertTrue(ContainerFactory.createObject().create().extend(c8).deepEquals(ContainerFactory.createObject(0, "aaa", 1, "bbb", 2, "ccc", 3, "ddd", 4, "eee", "a", "XXX")));
		assertTrue(ContainerFactory.createArray().create().extend(c7).deepEquals(ContainerFactory.createArray().create().set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee").set("0", "000").set("2", "222")));
		assertTrue(ContainerFactory.createArray().create().extend(c8).deepEquals(ContainerFactory.createArray().create().set(0, "aaa").set(1, "bbb").set(2, "ccc").set(3, "ddd").set(4, "eee").set("a", "XXX")));
		
		UniversalContainer c9, c10, c11;
		
		// Object.extend simple cases
		assertTrue(c7.extend().equals(c7));
		assertTrue(c7.extend().deepEquals(c7));
		assertTrue(c7.extend(c7).equals(c7));
		assertTrue(c7.extend(c7).deepEquals(c7));
		
		// Object.extend advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		assertTrue(c7.extend(c8).deepEquals(ContainerFactory.createObject(0, "aaa", 1, "bbb", 2, "ccc", 3, "ddd", 4, "eee", "a", "XXX", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee")));
		
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		assertTrue(c7.extend(c9).deepEquals(c9));
		
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		assertTrue(c7.extend(c10).deepEquals(ContainerFactory.createObject(0, "AAA", 1, "bbb", 2, "CCC", 3, "ddd", 4, "EEE", 5, "fff", 6, "GGG", "a", "YYY", "b", "ZZZ", "c", "ccc", "d", "ddd", "e", "eee")));
		
		// Array.extend simple cases
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c11 = c8.extend();
		assertTrue(c11.equals(c8));
		th.assertContainer(c11, true, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c11, false, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c11 = c8.extend(c8);
		assertTrue(c11.equals(c8));
		th.assertContainer(c11, true, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c11, false, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		
		// Array.extend advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c11 = c8.extend(c7);
		assertTrue(c11.deepEquals(ContainerFactory.createArray("000", "bbb", "222", "ddd", "eee")));
		th.assertContainer(c11, true, new String[]{"0","1","2","3","4","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","eee","aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c11, false, new String[]{"0","1","2","3","4","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","eee","aaa","bbb","ccc","ddd","eee"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c8.extend(c9);
		assertTrue(c8.deepEquals(ContainerFactory.createArray("XXX", "bbb", "YYY", "ZZZ", "eee")));
		th.assertContainer(c8, true, new String[]{"0","1","2","3","4","a","b","c","d","e","f","g"}, new Object[]{"XXX","bbb","YYY","ZZZ","eee","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		th.assertContainer(c8, false, new String[]{"0","1","2","3","4","a","b","c","d","e","f","g"}, new Object[]{"XXX","bbb","YYY","ZZZ","eee","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c8.extend(c10);
		assertTrue(c8.deepEquals(ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG")));
		th.assertContainer(c8, true, new String[]{"0","1","2","3","4","5","6","a","b"}, new Object[]{"AAA","bbb","CCC","ddd","EEE","fff","GGG","YYY","ZZZ"});
		th.assertContainer(c8, false, new String[]{"0","1","2","3","4","5","6","a","b"}, new Object[]{"AAA","bbb","CCC","ddd","EEE","fff","GGG","YYY","ZZZ"});
		
		// Object.extend even more advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c9.extend(c7);
		assertTrue(c9.deepEquals(ContainerFactory.createObject("0", "000", "2", "222", 3, "ZZZ", "a", "aaa", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee", "f", "fff", "g", "GGG")));
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c9.extend(c8);
		assertTrue(c9.deepEquals(ContainerFactory.createObject("0", "aaa", "1", "bbb", "2", "ccc", 3, "ddd", "4", "eee", "a", "XXX", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG")));
		
		// Array.extend even more advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c10.extend(c7);
		assertTrue(c10.deepEquals(ContainerFactory.createArray("000", "bbb", "222", "ddd", "EEE", "fff", "GGG")));
		th.assertContainer(c10, true, new String[]{"0","1","2","3","4","5","6","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","EEE","fff","GGG","aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c10, false, new String[]{"0","1","2","3","4","5","6","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","EEE","fff","GGG","aaa","bbb","ccc","ddd","eee"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c10.extend(c8);
		assertTrue(c10.deepEquals(ContainerFactory.createArray("aaa", "bbb", "ccc", "ddd", "eee", "fff", "GGG")));
		th.assertContainer(c10, true, new String[]{"0","1","2","3","4","5","6","a","b"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","GGG","XXX","ZZZ"});
		th.assertContainer(c10, false, new String[]{"0","1","2","3","4","5","6","a","b"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","GGG","XXX","ZZZ"});
		
		//Test several containers in one call
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c7.extend(c8, c9, c10);
		assertTrue(c7.equals(c7));
		assertTrue(c7.deepEquals(ContainerFactory.createObject("0", "AAA", "1", "bbb", "2", "CCC", 3, "ddd", "4", "EEE", "5", "fff", "6", "GGG", "a", "YYY", "b", "ZZZ", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG")));
	}
	
	@Test
	public void testExtendHierarchy()
	{
		UniversalContainer c1;
		UniversalContainer c2;
		
		//Test Object containers
		c1 = th.getSample("object-with-five-entries-abcde").set("0", "000").set("111", th.getSample("object-with-five-entries-abcde"));
		c2 = ContainerFactory.createObject("a", "XXX", "f", "fff", "g", "ggg", "0", "000", "111", ContainerFactory.createObject("a", "YYY", 0, "ZZZ", "f", "fff", "g", "ggg"));
		c1.extend(c2);
		assertTrue(c1.deepEquals(ContainerFactory.createObject("0", "000", "111", ContainerFactory.createObject("a", "YYY", 0, "ZZZ", "f", "fff", "g", "ggg"), "a", "XXX", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee", "f", "fff", "g", "ggg")));
		
		//Test array containers
		c1 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG", th.getSample("array-with-five-entries-abcde")).set("a", "YYY").set("b", "ZZZ").set("c", th.getSample("object-with-five-entries-abcde"));
		c2 = ContainerFactory.createArray(th.getSample("array-with-five-entries-abcde"), ContainerFactory.createArray("AAA","BBB","CCC")).set("a", "XXX").set("c", ContainerFactory.createArray("AAA"));
		c1.extend(c2);
		th.assertContainer(c1, true, new String[]{"0","1","2","3","4","5","6","7","a","b","c"}, new Object[]{th.getSample("array-with-five-entries-abcde"),ContainerFactory.createArray("AAA","BBB","CCC"),"CCC","ddd","EEE","fff","GGG",th.getSample("array-with-five-entries-abcde"),"XXX","ZZZ",ContainerFactory.createArray("AAA")});
		th.assertContainer(c1, false, new String[]{"0","1","2","3","4","5","6","7","a","b","c"}, new Object[]{th.getSample("array-with-five-entries-abcde"),ContainerFactory.createArray("AAA","BBB","CCC"),"CCC","ddd","EEE","fff","GGG",th.getSample("array-with-five-entries-abcde"),"XXX","ZZZ",ContainerFactory.createArray("AAA")});
	}
	
	@Test
	public void testExtendRecursiveHierarchy()
	{
		UniversalContainer c1;
		
		//Test Object containers
		c1 = th.getSample("object-with-five-entries-abcde");
		c1.set("f", c1);
		c1.extend(c1);
		th.assertContainerKeys(c1, true, new String[]{"a","b","c","d","e","f"});
		th.assertContainerKeys(c1, false, new String[]{"a","b","c","d","e","f"});
		
		//Test array containers
		c1 = th.getSample("array-with-five-entries-abcde");
		c1.push(c1);
		c1.extend(c1);
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
	}
	
	@Test
	public void testExtendPrototypes()
	{
		UniversalContainer c1 = th.getSample("object-with-five-entries-abcde");
		UniversalContainer c2 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		UniversalContainer c3;
		
		//Test Object containers
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.extend(c1);
		th.assertContainer(c3, true, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","aaa","bbb","ccc","ddd","eee","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","aaa","bbb","ccc","ddd","eee","fff","GGG"});
		
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.extend(c1.create());
		th.assertContainer(c3, true, new String[]{"0","2","3","f","g"}, new Object[]{"XXX","YYY","ZZZ","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.extend(c2);
		th.assertContainer(c3, true, new String[]{"0","1","2","3","4","a","f","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","a","b","c","d","e","f","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX","bbb","CCC","ddd","EEE","fff","GGG"});
		
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.extend(c2.create());
		th.assertContainer(c3, true, new String[]{"0","2","3","f","g"}, new Object[]{"XXX","YYY","ZZZ","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		//Test prototyped arrays
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.extend(c1);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","a","b","c","d","e","g"}, new Object[]{"XXX","aaa","bbb","ccc","ddd","eee","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","a","b","c","d","e","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff","aaa","bbb","ccc","ddd","eee","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.extend(c1.create());
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","b","g"}, new Object[]{"XXX","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff","YYY","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.extend(c2);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "aaa").set("1", "bbb").set("2", "ccc").set("3", "ddd").set("4", "eee").set("a", "XXX").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","1","2","3","4","a","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","a","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","XXX","YYY","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.extend(c2.create());
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","b","g"}, new Object[]{"XXX","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff","YYY","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.push(12345);
		c3.extend(c1);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("6", 12345).set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","6","a","b","c","d","e","g"}, new Object[]{"XXX",12345,"aaa","bbb","ccc","ddd","eee","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","6","a","b","c","d","e","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff",12345,"aaa","bbb","ccc","ddd","eee","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.shift();
		c3.extend(c2);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "aaa").set("1", "bbb").set("2", "ccc").set("3", "ddd").set("4", "eee").set("a", "XXX").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","1","2","3","4","a","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","a","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","XXX","YYY","GGG"});
	}
	
	@Test
	public void testHas()
	{
		assertFalse(ContainerFactory.undefinedContainer().has("0"));
		assertFalse(ContainerFactory.undefinedContainer().has("0", true));
		assertFalse(ContainerFactory.undefinedContainer().has("0", false));
		
		assertFalse(ContainerFactory.nullContainer().has("0"));
		assertFalse(ContainerFactory.nullContainer().has("0", true));
		assertFalse(ContainerFactory.nullContainer().has("0", false));
		
		assertFalse(new UniversalContainer(true).has("0"));
		assertFalse(new UniversalContainer(true).has("0", true));
		assertFalse(new UniversalContainer(true).has("0", false));
		
		assertFalse(new UniversalContainer(0).has("0"));
		assertFalse(new UniversalContainer(0).has("0", true));
		assertFalse(new UniversalContainer(0).has("0", false));
		
		assertTrue(new UniversalContainer("edcba").has("2"));
		assertTrue(new UniversalContainer("edcba").has("2", true));
		assertTrue(new UniversalContainer("edcba").has("2", false));
		
		assertFalse(new UniversalContainer("edcba").has("100"));
		assertFalse(new UniversalContainer("edcba").has("100", true));
		assertFalse(new UniversalContainer("edcba").has("100", false));
		
		assertFalse(new UniversalContainer(th.getSamplePlainObject()).has("0"));
		assertFalse(new UniversalContainer(th.getSamplePlainObject()).has("0", true));
		assertFalse(new UniversalContainer(th.getSamplePlainObject()).has("0", false));
		
		assertFalse(ContainerFactory.createObject().has("a"));
		assertFalse(ContainerFactory.createObject().has("a", true));
		assertFalse(ContainerFactory.createObject().has("a", false));
		
		assertTrue(th.getSample("object-with-five-entries-abcde").has("a"));
		assertTrue(th.getSample("object-with-five-entries-abcde").has("a", true));
		assertTrue(th.getSample("object-with-five-entries-abcde").has("a", false));
		
		assertFalse(th.getSample("object-with-five-entries-abcde").create().has("a"));
		assertFalse(th.getSample("object-with-five-entries-abcde").create().has("a", true));
		assertTrue(th.getSample("object-with-five-entries-abcde").create().has("a", false));
		
		assertFalse(ContainerFactory.createArray().has("0"));
		assertFalse(ContainerFactory.createArray().has("0", true));
		assertFalse(ContainerFactory.createArray().has("0", false));
		
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde").set(10, "XXX").set("a", "YYY");
		UniversalContainer c2 = c1.create();
		
		assertTrue(c1.has("1"));
		assertTrue(c1.has("1", true));
		assertTrue(c1.has("1", false));
		
		assertTrue(c1.has("a"));
		assertTrue(c1.has("a", true));
		assertTrue(c1.has("a", false));
		
		assertFalse(c1.has("100"));
		assertFalse(c1.has("100", true));
		assertFalse(c1.has("100", false));
		
		assertFalse(c1.has("8"));
		assertFalse(c1.has("8", true));
		assertFalse(c1.has("8", false));
		
		assertFalse(c2.has("0"));
		assertFalse(c2.has("0", true));
		assertTrue(c2.has("0", false));
		
		assertFalse(c2.set(10, "XXX").has("8"));
		assertFalse(c2.set(10, "XXX").has("8", true));
		assertFalse(c2.set(10, "XXX").has("8", false));
		
		c2.push("ZZZ");
		assertFalse(c2.set(10, "XXX").has("9"));
		assertFalse(c2.set(10, "XXX").has("9", true));
		assertFalse(c2.set(10, "XXX").has("9", false));
		
		c2.shift();
		assertTrue(c2.set(10, "XXX").has("9"));
		assertTrue(c2.set(10, "XXX").has("9", true));
		assertTrue(c2.set(10, "XXX").has("9", false));
	}
	
	@Test
	public void testIndexOf()
	{
		try
		{
			ContainerFactory.undefinedContainer().indexOf(0);
			fail("In this case indexOf should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'indexOf' is not available for Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().indexOf(0);
			fail("In this case indexOf should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'indexOf' is not available for Null container"); }
		
		try
		{
			new UniversalContainer(true).indexOf(0);
			fail("In this case indexOf should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'indexOf' is not available for Boolean container"); }
		
		try
		{
			new UniversalContainer(1234).indexOf(0);
			fail("In this case indexOf should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'indexOf' is not available for Number container"); }
		
		try
		{
			new UniversalContainer(th.getSamplePlainObject()).indexOf(0);
			fail("In this case indexOf should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'indexOf' is not available for Literal container"); }
		
		try
		{
			ContainerFactory.createObject().indexOf(0);
			fail("In this case indexOf should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'indexOf' is not available for Object container"); }
		
		assertEquals(new UniversalContainer("abcdabcd").indexOf("b"), 1);
		assertEquals(new UniversalContainer("abcdabcd").indexOf("abc"), 0);
		assertEquals(new UniversalContainer("abcdabcd").indexOf("abce"), -1);
		
		assertEquals(ContainerFactory.createArray().indexOf("a"), -1);
		assertEquals(th.getSample("array-with-five-entries-abcde").indexOf("ZZZ"), -1);
		assertEquals(th.getSample("array-with-five-entries-abcde").indexOf("bbb"), 1);
		assertEquals(th.getSample("array-with-five-entries-abcde").indexOf("aaa"), 0);
		
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		c2.push("fff");
		assertEquals(c2.indexOf("bbb"), 1);
		assertEquals(c2.indexOf("fff"), 5);
		assertEquals(c2.indexOf("ZZZ"), -1);
		c2.delete(1);
		assertEquals(c2.indexOf(ContainerFactory.undefinedContainer()), -1);
		
		UniversalContainer c3 = c1.create().set(1, "XXX");
		assertEquals(c3.indexOf("ccc"), 2);
		assertEquals(c3.indexOf("XXX"), 1);
		
		c1.set(1, "ZZZ");
		assertEquals(c2.indexOf("ZZZ"), 1);
		assertEquals(c3.indexOf("ZZZ"), -1);
		
		c1.set(100, "YYY");
		assertEquals(c1.indexOf(ContainerFactory.undefinedContainer()), -1);
		assertEquals(c2.indexOf(ContainerFactory.undefinedContainer()), -1);
		assertEquals(c3.indexOf(ContainerFactory.undefinedContainer()), -1);
		
		c1.set(50, ContainerFactory.undefinedContainer());
		assertEquals(c1.indexOf(ContainerFactory.undefinedContainer()), 50);
		assertEquals(c2.indexOf(ContainerFactory.undefinedContainer()), -1);
		assertEquals(c3.indexOf(ContainerFactory.undefinedContainer()), 50);
	}
	
	@Test
	public void testIteration()
	{
		for (UniversalContainer v : ContainerFactory.undefinedContainer())
		{
			fail("In this case shouldn't be any iterations, but following value was returned: " + v);
		}
		
		for (UniversalContainer v : ContainerFactory.nullContainer())
		{
			fail("In this case shouldn't be any iterations, but following value was returned: " + v);
		}
		
		for (UniversalContainer v : new UniversalContainer(true))
		{
			fail("In this case shouldn't be any iterations, but following value was returned:" + v);
		}
		
		for (UniversalContainer v : new UniversalContainer(1234))
		{
			fail("In this case shouldn't be any iterations, but following value was returned:" + v);
		}
		
		for (UniversalContainer v : new UniversalContainer(th.getSamplePlainObject()))
		{
			fail("In this case shouldn't be any iterations, but following value was returned:" + v);
		}
		
		for (UniversalContainer v : ContainerFactory.createObject())
		{
			fail("In this case shouldn't be any iterations, but following value was returned:" + v);
		}
		
		for (UniversalContainer v : ContainerFactory.createArray())
		{
			fail("In this case shouldn't be any iterations, but following value was returned:" + v);
		}
		
		th.assertContainer(th.getSample("literal-string-abcde"), new String[]{"a", "b", "c", "d", "e"});
		
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		th.assertContainer(c1, new String[]{"aaa", "bbb", "ccc", "ddd", "eee"});
		
		UniversalContainer c2 = c1.create();
		c2.push("XXX");
		th.assertContainer(c2, new String[]{"aaa", "bbb", "ccc", "ddd", "eee", "XXX"});
		
		UniversalContainer c3 = c1.create().set(1, "XXX");
		th.assertContainer(c3, new String[]{"aaa", "XXX", "ccc", "ddd", "eee"});
		
		c1.set(0, "YYY");
		th.assertContainer(c1, new String[]{"YYY", "bbb", "ccc", "ddd", "eee"});
		th.assertContainer(c2, new String[]{"YYY", "bbb", "ccc", "ddd", "eee", "XXX"});
		th.assertContainer(c3, new String[]{"YYY", "XXX", "ccc", "ddd", "eee"});
		assertTrue(c1.get(5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(5).equals("XXX"));
		assertTrue(c3.get(5).equals(ContainerFactory.undefinedContainer()));
		
		c1.set(7, "ZZZ");
		th.assertContainer(c1, new Object[]{"YYY", "bbb", "ccc", "ddd", "eee", ContainerFactory.undefinedContainer(), ContainerFactory.undefinedContainer(), "ZZZ"});
		th.assertContainer(c2, new Object[]{"YYY", "bbb", "ccc", "ddd", "eee", "XXX"});
		th.assertContainer(c3, new Object[]{"YYY", "XXX", "ccc", "ddd", "eee", ContainerFactory.undefinedContainer(), ContainerFactory.undefinedContainer(), "ZZZ"});
		assertTrue(c1.get(7).equals("ZZZ"));
		assertTrue(c2.get(7).equals("ZZZ"));
		assertTrue(c3.get(7).equals("ZZZ"));
	}
	
	@Test
	public void testKeyList()
	{
		th.assertContainerKeys(ContainerFactory.undefinedContainer(), true, new String[]{});
		th.assertContainerKeys(ContainerFactory.undefinedContainer(), false, new String[]{});
		th.assertContainerKeys(ContainerFactory.nullContainer(), true, new String[]{});
		th.assertContainerKeys(ContainerFactory.nullContainer(), false, new String[]{});
		th.assertContainerKeys(new UniversalContainer(true), true, new String[]{});
		th.assertContainerKeys(new UniversalContainer(true), false, new String[]{});
		th.assertContainerKeys(new UniversalContainer(1234), true, new String[]{});
		th.assertContainerKeys(new UniversalContainer(1234), false, new String[]{});
		th.assertContainerKeys(ContainerFactory.createObject(), true, new String[]{});
		th.assertContainerKeys(ContainerFactory.createObject(), false, new String[]{});
		th.assertContainerKeys(ContainerFactory.createArray(), true, new String[]{});
		th.assertContainerKeys(ContainerFactory.createArray(), false, new String[]{});
		
		th.assertContainerKeys(th.getSample("literal-string-abcde").keys(), new String[]{"0", "1", "2", "3", "4"});
		th.assertContainerKeys(th.getSample("literal-string-abcde").keys(true), new String[]{"0", "1", "2", "3", "4"});
		th.assertContainerKeys(th.getSample("literal-string-abcde").keys(false), new String[]{"0", "1", "2", "3", "4"});
		
		th.assertContainerKeys(th.getSample("object-with-five-entries-abcde").keys(), new String[]{"a", "b", "c", "d", "e"});
		th.assertContainerKeys(th.getSample("object-with-five-entries-abcde").keys(true), new String[]{"a", "b", "c", "d", "e"});
		th.assertContainerKeys(th.getSample("object-with-five-entries-abcde").keys(false), new String[]{"a", "b", "c", "d", "e"});
		
		th.assertContainerKeys(th.getSample("object-with-five-entries-abcde").create().keys(), new String[]{});
		th.assertContainerKeys(th.getSample("object-with-five-entries-abcde").create().keys(true), new String[]{});
		th.assertContainerKeys(th.getSample("object-with-five-entries-abcde").create().keys(false), new String[]{"a", "b", "c", "d", "e"});
		
		th.assertContainerKeys(th.getSample("array-with-five-entries-abcde").keys(), new String[]{"0", "1", "2", "3", "4"});
		th.assertContainerKeys(th.getSample("array-with-five-entries-abcde").keys(true), new String[]{"0", "1", "2", "3", "4"});
		th.assertContainerKeys(th.getSample("array-with-five-entries-abcde").keys(false), new String[]{"0", "1", "2", "3", "4"});
		
		th.assertContainerKeys(th.getSample("array-with-five-entries-abcde").create().keys(), new String[]{});
		th.assertContainerKeys(th.getSample("array-with-five-entries-abcde").create().keys(true), new String[]{});
		th.assertContainerKeys(th.getSample("array-with-five-entries-abcde").create().keys(false), new String[]{"0", "1", "2", "3", "4"});
	}
	
	@Test
	public void testLast()
	{
		assertTrue(ContainerFactory.undefinedContainer().last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.nullContainer().last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(new UniversalContainer(true).last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(new UniversalContainer(1234).last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(th.getSample("literal-string-abcde").last().equals("e"));
		assertTrue(ContainerFactory.createObject().last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(th.getSample("object-with-five-entries-abcde").last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(th.getSample("object-with-five-entries-abcde").create().last().equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.createArray().last().equals(ContainerFactory.undefinedContainer()));
		
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		assertTrue(c1.last().equals("eee"));
		
		UniversalContainer c2 = c1.create();
		assertTrue(c2.last().equals("eee"));
		
		c1.set(4, "XXX");
		assertTrue(c2.last().equals("XXX"));
		
		c2.set(5, "YYY");
		assertTrue(c2.last().equals("XXX"));
		
		c2.set(4, "ZZZ");
		assertTrue(c2.last().equals("ZZZ"));
		
		c1.set(5, "XXX");
		assertTrue(c2.last().equals("YYY"));
		
		c1.set(10, "WWW");
		assertTrue(c1.last().equals("WWW"));
	}
	
	@Test
	public void testMerge()
	{
		UniversalContainer c1 = ContainerFactory.undefinedContainer();
		UniversalContainer c2 = ContainerFactory.nullContainer();
		UniversalContainer c3 = new UniversalContainer(true);
		UniversalContainer c4 = new UniversalContainer(1);
		UniversalContainer c5 = new UniversalContainer("abcde");
		UniversalContainer c6 = new UniversalContainer(th.getSamplePlainObject());
		UniversalContainer c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		UniversalContainer c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		
		assertTrue(ContainerFactory.undefinedContainer().merge().equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge((UniversalContainer[])null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge((UniversalContainer)null).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c1).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c2).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c3).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c4).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c6).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c7).equals(ContainerFactory.undefinedContainer()));
		assertTrue(ContainerFactory.undefinedContainer().merge(c8).equals(ContainerFactory.undefinedContainer()));
		
		assertTrue(ContainerFactory.nullContainer().merge().equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge((UniversalContainer[])null).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge((UniversalContainer)null).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c1).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c2).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c3).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c4).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c5).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c6).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c7).equals(ContainerFactory.nullContainer()));
		assertTrue(ContainerFactory.nullContainer().merge(c8).equals(ContainerFactory.nullContainer()));
		
		assertTrue(new UniversalContainer(true).merge().equals(true));
		assertTrue(new UniversalContainer(true).merge((UniversalContainer[])null).equals(true));
		assertTrue(new UniversalContainer(true).merge((UniversalContainer)null).equals(true));
		assertTrue(new UniversalContainer(true).merge(c1).equals(true));
		assertTrue(new UniversalContainer(true).merge(c2).equals(true));
		assertTrue(new UniversalContainer(true).merge(c3).equals(true));
		assertTrue(new UniversalContainer(true).merge(c4).equals(true));
		assertTrue(new UniversalContainer(true).merge(c5).equals(true));
		assertTrue(new UniversalContainer(true).merge(c6).equals(true));
		assertTrue(new UniversalContainer(true).merge(c7).equals(true));
		assertTrue(new UniversalContainer(true).merge(c8).equals(true));
		
		assertTrue(new UniversalContainer(1).merge().equals(1));
		assertTrue(new UniversalContainer(1).merge((UniversalContainer[])null).equals(1));
		assertTrue(new UniversalContainer(1).merge((UniversalContainer)null).equals(1));
		assertTrue(new UniversalContainer(1).merge(c1).equals(1));
		assertTrue(new UniversalContainer(1).merge(c2).equals(1));
		assertTrue(new UniversalContainer(1).merge(c3).equals(1));
		assertTrue(new UniversalContainer(1).merge(c4).equals(1));
		assertTrue(new UniversalContainer(1).merge(c5).equals(1));
		assertTrue(new UniversalContainer(1).merge(c6).equals(1));
		assertTrue(new UniversalContainer(1).merge(c7).equals(1));
		assertTrue(new UniversalContainer(1).merge(c8).equals(1));
		
		assertTrue(new UniversalContainer("edcba").merge().equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge((UniversalContainer[])null).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge((UniversalContainer)null).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c1).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c2).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c3).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c4).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c5).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c6).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c7).equals("edcba"));
		assertTrue(new UniversalContainer("edcba").merge(c8).equals("edcba"));
		
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge().equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge((UniversalContainer[])null).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge((UniversalContainer)null).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c1).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c2).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c3).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c4).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c5).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c6).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c7).equals(th.getSamplePlainObject()));
		assertTrue(new UniversalContainer(th.getSamplePlainObject()).merge(c8).equals(th.getSamplePlainObject()));
		
		assertTrue(ContainerFactory.createObject().merge().deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge((UniversalContainer[])null).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge((UniversalContainer)null).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c1).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c2).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c3).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c4).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c5).deepEquals(ContainerFactory.createObject(0, "a", 1, "b", 2, "c", 3, "d", 4, "e")));
		assertTrue(ContainerFactory.createObject().merge(c6).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c7).deepEquals(c7));
		assertTrue(ContainerFactory.createObject().merge(c8).deepEquals(ContainerFactory.createObject(0, "aaa", 1, "bbb", 2, "ccc", 3, "ddd", 4, "eee")));
		
		assertTrue(ContainerFactory.createArray().merge().deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge((UniversalContainer[])null).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge((UniversalContainer)null).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c1).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c2).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c3).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c4).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c5).deepEquals(ContainerFactory.createArray("a", "b", "c", "d", "e")));
		assertTrue(ContainerFactory.createArray().merge(c6).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c7).deepEquals(ContainerFactory.createArray("000", ContainerFactory.undefinedContainer(), "222")));
		assertTrue(ContainerFactory.createArray().merge(c8).deepEquals(c8));
		
		assertTrue(ContainerFactory.createObject().merge(c7.create()).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createObject().merge(c8.create()).deepEquals(ContainerFactory.createObject()));
		assertTrue(ContainerFactory.createArray().merge(c7.create()).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createArray().merge(c8.create()).deepEquals(ContainerFactory.createArray()));
		assertTrue(ContainerFactory.createObject().create().merge(c7).deepEquals(c7));
		assertTrue(ContainerFactory.createObject().create().merge(c8).deepEquals(ContainerFactory.createObject(0, "aaa", 1, "bbb", 2, "ccc", 3, "ddd", 4, "eee")));
		assertTrue(ContainerFactory.createArray().create().merge(c7).deepEquals(ContainerFactory.createArray().create().set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee").set("0", "000").set("2", "222")));
		assertTrue(ContainerFactory.createArray().create().merge(c8).deepEquals(ContainerFactory.createArray().create().set(0, "aaa").set(1, "bbb").set(2, "ccc").set(3, "ddd").set(4, "eee")));
		
		UniversalContainer c9, c10, c11;
		
		//Object.merge simple cases
		assertTrue(c7.merge().equals(c7));
		assertTrue(c7.merge().deepEquals(c7));
		assertTrue(c7.merge(c7).equals(c7));
		assertTrue(c7.merge(c7).deepEquals(c7));
		
		// Object.merge advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		assertTrue(c7.merge(c8).deepEquals(ContainerFactory.createObject(0, "aaa", 1, "bbb", 2, "ccc", 3, "ddd", 4, "eee", "a", "aaa", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee")));
		
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		assertTrue(c7.merge(c9).deepEquals(c9));
		
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		assertTrue(c7.merge(c10).deepEquals(ContainerFactory.createObject(0, "AAA", 1, "bbb", 2, "CCC", 3, "ddd", 4, "EEE", 5, "fff", 6, "GGG", "a", "aaa", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee")));
		
		// Array.merge simple cases
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c11 = c8.merge();
		assertTrue(c11.equals(c8));
		th.assertContainer(c11, true, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c11, false, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c11 = c8.merge(c8);
		assertTrue(c11.equals(c8));
		th.assertContainer(c11, true, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c11, false, new String[]{"0","1","2","3","4","a"}, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		
		// Array.merge advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c11 = c8.merge(c7);
		assertTrue(c11.deepEquals(ContainerFactory.createArray("000", "bbb", "222", "ddd", "eee")));
		th.assertContainer(c11, true, new String[]{"0","1","2","3","4","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","eee","aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c11, false, new String[]{"0","1","2","3","4","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","eee","aaa","bbb","ccc","ddd","eee"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c8.merge(c9);
		assertTrue(c8.deepEquals(ContainerFactory.createArray("XXX", "bbb", "YYY", "ZZZ", "eee")));
		th.assertContainer(c8, true, new String[]{"0","1","2","3","4","a","b","c","d","e","f","g"}, new Object[]{"XXX","bbb","YYY","ZZZ","eee","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		th.assertContainer(c8, false, new String[]{"0","1","2","3","4","a","b","c","d","e","f","g"}, new Object[]{"XXX","bbb","YYY","ZZZ","eee","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c8.merge(c10);
		assertTrue(c8.deepEquals(ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG")));
		th.assertContainer(c8, true, new String[]{"0","1","2","3","4","5","6","a"}, new Object[]{"AAA","bbb","CCC","ddd","EEE","fff","GGG","XXX"});
		th.assertContainer(c8, false, new String[]{"0","1","2","3","4","5","6","a"}, new Object[]{"AAA","bbb","CCC","ddd","EEE","fff","GGG","XXX"});
		
		// Object.merge even more advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c9.merge(c7);
		assertTrue(c9.deepEquals(ContainerFactory.createObject("0", "000", "2", "222", 3, "ZZZ", "a", "aaa", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee", "f", "fff", "g", "GGG")));
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c9.merge(c8);
		assertTrue(c9.deepEquals(ContainerFactory.createObject("0", "aaa", "1", "bbb", "2", "ccc", 3, "ddd", "4", "eee", "a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG")));
		
		// Array.merge even more advanced cases
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c10.merge(c7);
		assertTrue(c10.deepEquals(ContainerFactory.createArray("000", "bbb", "222", "ddd", "EEE", "fff", "GGG")));
		th.assertContainer(c10, true, new String[]{"0","1","2","3","4","5","6","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","EEE","fff","GGG","aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c10, false, new String[]{"0","1","2","3","4","5","6","a","b","c","d","e"}, new Object[]{"000","bbb","222","ddd","EEE","fff","GGG","aaa","bbb","ccc","ddd","eee"});
		
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c10.merge(c8);
		assertTrue(c10.deepEquals(ContainerFactory.createArray("aaa", "bbb", "ccc", "ddd", "eee", "fff", "GGG")));
		th.assertContainer(c10, true, new String[]{"0","1","2","3","4","5","6","a","b"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","GGG","YYY","ZZZ"});
		th.assertContainer(c10, false, new String[]{"0","1","2","3","4","5","6","a","b"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","GGG","YYY","ZZZ"});
		
		//Test several containers in one call
		c7 = th.getSample("object-with-five-entries-abcde").set("0", "000").set(2, "222");
		c8 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		c9 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG", 0, "XXX", "2", "YYY", 3, "ZZZ");
		c10 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff", "GGG").set("a", "YYY").set("b", "ZZZ");
		c7.merge(c8, c9, c10);
		assertTrue(c7.equals(c7));
		assertTrue(c7.deepEquals(ContainerFactory.createObject("0", "AAA", "1", "bbb", "2", "CCC", 3, "ddd", "4", "EEE", "5", "fff", "6", "GGG", "a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE", "f", "fff", "g", "GGG")));
	}
	
	@Test
	public void testMergeHierarchy()
	{
		UniversalContainer c1;
		UniversalContainer c2;
		
		//Test Object containers
		c1 = th.getSample("object-with-five-entries-abcde").set("0", ContainerFactory.createArray(ContainerFactory.createObject("a", "AAA", "b", "BBB"), "XXX")).set("111", th.getSample("object-with-five-entries-abcde"));
		c2 = ContainerFactory.createObject("a", "XXX", "b", th.getSample("object-with-five-entries-abcde"), "f", null, "0", ContainerFactory.createArray(ContainerFactory.createObject("a", "ZZZ", "c", ContainerFactory.undefinedContainer(), "d", ContainerFactory.nullContainer()), "YYY"), "111", true);
		c1.merge(c2);
		assertTrue(c1.deepEquals(ContainerFactory.createObject("0", ContainerFactory.createArray(ContainerFactory.createObject("a", "ZZZ", "b", "BBB", "d", ContainerFactory.nullContainer()), "YYY"), "111", true, "a", "XXX", "b", th.getSample("object-with-five-entries-abcde"), "c", "ccc", "d", "ddd", "e", "eee")));
		
		//Test array containers
		c1 = ContainerFactory.createArray("AAA", "bbb", "CCC", th.getSample("array-with-five-entries-abcde")).set("a", ContainerFactory.createArray()).set("b", ContainerFactory.createObject("a", ContainerFactory.createArray("eee","fff","ggg"), "b", "BBB"));
		c2 = ContainerFactory.createArray("aaa", "BBB", "ccc", ContainerFactory.createArray("AAA","BBB","CCC")).set("a", ContainerFactory.createObject("a","aaa",0,"XXX")).set("b", ContainerFactory.createObject("a", ContainerFactory.createArray("aaa","FFF","ggg","YYY"), "b", ContainerFactory.createArray()));
		c1.merge(c2);
		th.assertContainer(c1, true, new String[]{"0","1","2","3","a","b"}, new Object[]{"aaa","BBB","ccc",ContainerFactory.createArray("AAA","BBB","CCC","ddd","eee"),ContainerFactory.createArray(),ContainerFactory.createObject("a", ContainerFactory.createArray("eee","fff","ggg"), "b", "BBB")});
		th.assertContainer(c1, false, new String[]{"0","1","2","3","a","b"}, new Object[]{"aaa","BBB","ccc",ContainerFactory.createArray("AAA","BBB","CCC","ddd","eee"),ContainerFactory.createArray(),ContainerFactory.createObject("a", ContainerFactory.createArray("eee","fff","ggg"), "b", "BBB")});
	}
	
	@Test
	public void testMergeCyclicContainer()
	{
		UniversalContainer c1, c2;
		
		//Test Object containers
		c1 = th.getSample("object-with-five-entries-abcde");
		c1.set("f", c1);
		c1.merge(c1);
		th.assertContainerKeys(c1, true, new String[]{"a","b","c","d","e","f"});
		th.assertContainerKeys(c1, false, new String[]{"a","b","c","d","e","f"});
		
		//Test array containers
		c2 = th.getSample("array-with-five-entries-abcde");
		c2.push(c2);
		c2.merge(c2);
		th.assertContainerKeys(c2, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		
		c1.set("g", c2);
		c2.set(0, c1);
		
		c1.merge(c1);
		th.assertContainerKeys(c1, true, new String[]{"a","b","c","d","e","f","g"});
		th.assertContainerKeys(c1, false, new String[]{"a","b","c","d","e","f","g"});
		
		c2.merge(c2);
		th.assertContainerKeys(c2, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		
		c1.merge(c2);
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5","a","b","c","d","e","f","g"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5","a","b","c","d","e","f","g"});
		
		c2.merge(c1);
		th.assertContainerKeys(c2, true, new String[]{"0","1","2","3","4","5","a","b","c","d","e","f","g"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5","a","b","c","d","e","f","g"});
	}
	
	@Test
	public void testMergePrototypes()
	{
		UniversalContainer c1 = th.getSample("object-with-five-entries-abcde");
		UniversalContainer c2 = th.getSample("array-with-five-entries-abcde").set("a", "XXX");
		UniversalContainer c3;
		
		//Test Object containers
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.merge(c1);
		th.assertContainer(c3, true, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","aaa","bbb","ccc","ddd","eee","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","aaa","bbb","ccc","ddd","eee","fff","GGG"});
		
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.merge(c1.create());
		th.assertContainer(c3, true, new String[]{"0","2","3","f","g"}, new Object[]{"XXX","YYY","ZZZ","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.merge(c2);
		th.assertContainer(c3, true, new String[]{"0","1","2","3","4","f","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","a","b","c","d","e","f","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		c3 = ContainerFactory.createObject("a", "AAA", "b", "bbb", "c", "CCC", "d", "ddd", "e", "EEE").create().set("f", "fff").set("g", "GGG").set(0, "XXX").set("2", "YYY").set(3, "ZZZ");
		c3.merge(c2.create());
		th.assertContainer(c3, true, new String[]{"0","2","3","f","g"}, new Object[]{"XXX","YYY","ZZZ","fff","GGG"});
		th.assertContainer(c3, false, new String[]{"0","2","3","a","b","c","d","e","f","g"}, new Object[]{"XXX","YYY","ZZZ","AAA","bbb","CCC","ddd","EEE","fff","GGG"});
		
		//Test prototyped arrays
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.merge(c1);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","a","b","c","d","e","g"}, new Object[]{"XXX","aaa","bbb","ccc","ddd","eee","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","a","b","c","d","e","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff","aaa","bbb","ccc","ddd","eee","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.merge(c1.create());
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","b","g"}, new Object[]{"XXX","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff","YYY","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.merge(c2);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "aaa").set("1", "bbb").set("2", "ccc").set("3", "ddd").set("4", "eee").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","1","2","3","4","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","YYY","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.merge(c2.create());
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","b","g"}, new Object[]{"XXX","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff","YYY","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.push(12345);
		c3.merge(c1);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "XXX").set("6", 12345).set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","6","a","b","c","d","e","g"}, new Object[]{"XXX",12345,"aaa","bbb","ccc","ddd","eee","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","6","a","b","c","d","e","g"}, new Object[]{"XXX","bbb","CCC","ddd","EEE","fff",12345,"aaa","bbb","ccc","ddd","eee","GGG"});
		
		c3 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		c3.shift();
		c3.merge(c2);
		assertTrue(c3.deepEquals(ContainerFactory.createArray().create().set("0", "aaa").set("1", "bbb").set("2", "ccc").set("3", "ddd").set("4", "eee").set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c3, true, new String[]{"0","1","2","3","4","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY","GGG"});
		th.assertContainer(c3, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"aaa","bbb","ccc","ddd","eee","fff","YYY","GGG"});
	}
	
	@Test
	public void testMergeCustomizer()
	{
		UniversalContainer c1 = ContainerFactory.createObject(
			"fruits", ContainerFactory.createArray("apple"),
			"vegetables", ContainerFactory.createArray("beet"));
		
		UniversalContainer c2 = ContainerFactory.createObject(
			"fruits", ContainerFactory.createArray("banana"),
			"vegetables", ContainerFactory.createArray("carrot"));
		
		Lodash.merge(c1, new ValueCustomizer()
		{
			@Override
			public UniversalContainer customize(UniversalContainer a, UniversalContainer b)
			{
				if (a.isArray())
				{
					return a.concat(b);
				}
				return ContainerFactory.undefinedContainer();
			}
		}, c2);
		
		assertTrue(c1.deepEquals(ContainerFactory.createObject("fruits", ContainerFactory.createArray("apple", "banana"), "vegetables", ContainerFactory.createArray("beet", "carrot"))));
	}
	
	@Test
	public void testPop()
	{
		try
		{
			ContainerFactory.undefinedContainer().pop();
			fail("In this case pop should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'pop' is not available for Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().pop();
			fail("In this case pop should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'pop' is not available for Null container"); }
		
		try
		{
			th.getSample("literal-string-abcde").pop();
			fail("In this case pop should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'pop' is not available for String container"); }
		
		try
		{
			ContainerFactory.createObject().pop();
			fail("In this case pop should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'pop' is not available for Object container"); }
		
		assertTrue(ContainerFactory.createArray().pop().equals(ContainerFactory.undefinedContainer()));
		
		//Simple array container and prototyped array container
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		
		assertTrue(c1.pop().equals("eee"));
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd"});
		
		assertTrue(c2.pop().equals("ddd"));
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc"});
		assertTrue(c1.get(3).equals("ddd"));
		assertTrue(c2.get(3).equals("ddd"));
		
		assertTrue(c1.pop().equals("ddd"));
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc"});
		
		assertTrue(c1.pop().equals("ccc"));
		assertTrue(c1.pop().equals("bbb"));
		assertTrue(c1.pop().equals("aaa"));
		assertTrue(c1.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.pop().equals(ContainerFactory.undefinedContainer()));
		assertEquals(c1.getLength(), new Integer(0));
		
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertEquals(c2.getLength(), new Integer(0));
		
		//Simple array container and locked prototyped array container
		c1 = th.getSample("array-with-five-entries-abcde");
		c2 = c1.create();
		c2.set(0, "XXX");
		c2.push("YYY");
			
		assertTrue(c1.pop().equals("eee"));
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","bbb", "ccc", "ddd", ContainerFactory.undefinedContainer(), "YYY"});
		assertTrue(c1.get(5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(5).equals("YYY"));
		
		assertTrue(c2.pop().equals("YYY"));
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","bbb", "ccc", "ddd", ContainerFactory.undefinedContainer()});
		
		assertTrue(c1.pop().equals("ddd"));
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc"});
		th.assertContainer(c2, new Object[]{"XXX","bbb", "ccc", ContainerFactory.undefinedContainer(), ContainerFactory.undefinedContainer()});
		
		assertTrue(c1.pop().equals("ccc"));
		assertTrue(c1.pop().equals("bbb"));
		assertTrue(c1.pop().equals("aaa"));
		assertTrue(c1.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.pop().equals(ContainerFactory.undefinedContainer()));
		assertEquals(c1.getLength(), new Integer(0));
		
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals("XXX"));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.pop().equals(ContainerFactory.undefinedContainer()));
		assertEquals(c2.getLength(), new Integer(0));
	}
	
	@Test
	public void testPush()
	{
		try
		{
			ContainerFactory.undefinedContainer().push(0);
			fail("In this case push should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'push' is not available for Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().push(0);
			fail("In this case push should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'push' is not available for Null container"); }
		
		try
		{
			th.getSample("literal-string-abcde").push(0);
			fail("In this case push should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'push' is not available for String container"); }
		
		try
		{
			ContainerFactory.createObject().push(0);
			fail("In this case push should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'push' is not available for Object container"); }
		
		UniversalContainer c1 = ContainerFactory.createArray();
		UniversalContainer c2 = c1.create();
		
		assertEquals(c1.push(0), 1);
		th.assertContainer(c1, new Object[]{0});
		th.assertContainer(c2, new Object[]{0});
		
		assertEquals(c1.push("XXX"), 2);
		th.assertContainer(c1, new Object[]{0, "XXX"});
		th.assertContainer(c2, new Object[]{0, "XXX"});
		
		assertEquals(c1.push(th.getSamplePlainObject()), 3);
		th.assertContainer(c1, new Object[]{0, "XXX", th.getSamplePlainObject()});
		th.assertContainer(c2, new Object[]{0, "XXX", th.getSamplePlainObject()});
		
		assertEquals(c2.push("YYY"), 4);
		th.assertContainer(c1, new Object[]{0, "XXX", th.getSamplePlainObject()});
		th.assertContainer(c2, new Object[]{0, "XXX", th.getSamplePlainObject(), "YYY"});
		assertTrue(c1.get(3).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(3).equals("YYY"));
		
		assertEquals(c1.push("ZZZ"), 4);
		th.assertContainer(c1, new Object[]{0, "XXX", th.getSamplePlainObject(), "ZZZ"});
		th.assertContainer(c2, new Object[]{0, "XXX", th.getSamplePlainObject(), "YYY"});
		
		assertEquals(c1.push(1234), 5);
		th.assertContainer(c1, new Object[]{0, "XXX", th.getSamplePlainObject(), "ZZZ", 1234});
		th.assertContainer(c2, new Object[]{0, "XXX", th.getSamplePlainObject(), "YYY"});
		assertTrue(c1.get(4).equals(1234));
		assertTrue(c2.get(4).equals(1234));
		
		c1.set(10, "WWW");
		assertEquals(c1.push(ContainerFactory.createArray(), th.getSamplePlainObject(), 1234, "WWW", c1), 16);
	}
	
	@Test
	public void testShift()
	{
		try
		{
			ContainerFactory.undefinedContainer().shift();
			fail("In this case shift should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'shift' is not available for Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().shift();
			fail("In this case shift should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'shift' is not available for Null container"); }
		
		try
		{
			th.getSample("literal-string-abcde").shift();
			fail("In this case shift should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'shift' is not available for String container"); }
		
		try
		{
			ContainerFactory.createObject().shift();
			fail("In this case shift should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'shift' is not available for Object container"); }
		
		assertTrue(ContainerFactory.createArray().shift().equals(ContainerFactory.undefinedContainer()));
		
		//Simple array container and prototyped array container
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		
		assertTrue(c1.shift().equals("aaa"));
		th.assertContainer(c1, new Object[]{"bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"bbb","ccc","ddd","eee"});
		
		assertTrue(c1.shift().equals("bbb"));
		th.assertContainer(c1, new Object[]{"ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"ccc","ddd","eee"});
		
		assertTrue(c2.shift().equals("ccc"));
		th.assertContainer(c1, new Object[]{"ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"ddd","eee"});
		assertTrue(c1.get(2).equals("eee"));
		assertTrue(c2.get(2).equals("eee"));
		
		assertTrue(c2.shift().equals("ddd"));
		th.assertContainer(c1, new Object[]{"ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"eee"});
		assertTrue(c1.get(1).equals("ddd"));
		assertTrue(c2.get(1).equals("ddd"));
		assertTrue(c1.get(2).equals("eee"));
		assertTrue(c2.get(2).equals("eee"));
		
		assertTrue(c1.shift().equals("ccc"));
		th.assertContainer(c1, new Object[]{"ddd","eee"});
		th.assertContainer(c2, new Object[]{"eee"});
		assertTrue(c1.get(1).equals("eee"));
		assertTrue(c2.get(1).equals("eee"));
		
		//Simple array container and locked prototyped array container
		c1 = th.getSample("array-with-five-entries-abcde");
		c2 = c1.create();
		c2.set(0, "XXX");
		c2.push("YYY");
		
		assertTrue(c1.shift().equals("aaa"));
		th.assertContainer(c1, new Object[]{"bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"XXX","ccc","ddd","eee",ContainerFactory.undefinedContainer(),"YYY"});
		
		assertTrue(c2.shift().equals("XXX"));
		th.assertContainer(c1, new Object[]{"bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"ccc","ddd","eee","eee","YYY"});
		assertTrue(c1.get(4).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(4).equals("YYY"));
		
		assertTrue(c2.shift().equals("ccc"));
		th.assertContainer(c1, new Object[]{"bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"ddd","eee","eee","YYY"});
		
		assertTrue(c1.shift().equals("bbb"));
		th.assertContainer(c1, new Object[]{"ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"ddd","eee","eee","YYY"});
		assertTrue(c1.get(3).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(3).equals("YYY"));
		
		assertTrue(c1.shift().equals("ccc"));
		assertTrue(c1.shift().equals("ddd"));
		assertTrue(c1.shift().equals("eee"));
		assertTrue(c1.shift().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.shift().equals(ContainerFactory.undefinedContainer()));
		assertEquals(c1.getLength(), new Integer(0));
		
		assertTrue(c2.shift().equals("ddd"));
		assertTrue(c2.shift().equals("eee"));
		assertTrue(c2.shift().equals("eee"));
		assertTrue(c2.shift().equals("YYY"));
		assertTrue(c2.shift().equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.shift().equals(ContainerFactory.undefinedContainer()));
		assertEquals(c2.getLength(), new Integer(0));
	}
	
	@Test
	public void testSplice()
	{
		try
		{
			ContainerFactory.undefinedContainer().splice(0, 1);
			fail("In this case splice should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'splice' is not available for Undefined container"); }
		
		try
		{
			ContainerFactory.nullContainer().splice(0, 1);
			fail("In this case splice should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'splice' is not available for Null container"); }
		
		try
		{
			th.getSample("literal-string-abcde").splice(0, 1);
			fail("In this case splice should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'splice' is not available for String container"); }
		
		try
		{
			ContainerFactory.createObject().splice(0, 1);
			fail("In this case splice should throw an error!");
		}
		catch (ContainerException e){ assertEquals(e.getMessage(), "Method 'splice' is not available for Object container"); }
		
		th.assertContainer(ContainerFactory.createArray().splice(-1, -1), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(-1, 0), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(-1, 1), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(0, -1), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(0, 0), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(0, 1), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(1, -1), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(1, 0), new Object[]{});
		th.assertContainer(ContainerFactory.createArray().splice(1, 1), new Object[]{});
		
		//Simple array container and prototyped array container
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		
		th.assertContainer(c1.splice(-1, -1), new Object[]{});
		th.assertContainer(c1.splice(-1, 0), new Object[]{});
		th.assertContainer(c1.splice(-1, 1), new Object[]{"eee"});
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd"});
		
		th.assertContainer(c1.splice(0, -1), new Object[]{});
		th.assertContainer(c1.splice(0, 0), new Object[]{});
		th.assertContainer(c1.splice(0, 1), new Object[]{"aaa"});
		th.assertContainer(c1, new Object[]{"bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"bbb","ccc","ddd"});
		
		th.assertContainer(c1.splice(1, -1), new Object[]{});
		th.assertContainer(c1.splice(1, 0), new Object[]{});
		th.assertContainer(c1.splice(1, 1), new Object[]{"ccc"});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{"bbb","ddd"});
		
		th.assertContainer(c2.splice(1, 1), new Object[]{"ddd"});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{"bbb"});
		assertTrue(c1.get(1).equals("ddd"));
		assertTrue(c2.get(1).equals("ddd"));
		
		th.assertContainer(c1.splice(-5, 5), new Object[]{"bbb","ddd"});
		th.assertContainer(c1, new Object[]{});
		th.assertContainer(c2, new Object[]{ContainerFactory.undefinedContainer()});
		assertTrue(c1.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(0).equals(ContainerFactory.undefinedContainer()));
		
		th.assertContainer(c1.splice(0, 1), new Object[]{});
		assertEquals(c1.getLength(), new Integer(0));
		
		th.assertContainer(c2.splice(0, 1), new Object[]{ContainerFactory.undefinedContainer()});
		assertEquals(c2.getLength(), new Integer(0));
		
		//Simple array container and locked prototyped array container
		c1 = th.getSample("array-with-five-entries-abcde");
		c2 = c1.create();
		c2.set(0, "XXX");
		c2.push("YYY");
		
		th.assertContainer(c1.splice(-1, 1), new Object[]{"eee"});
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","bbb","ccc","ddd",ContainerFactory.undefinedContainer(),"YYY"});
		assertTrue(c1.get(4).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(4).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c1.get(5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(5).equals("YYY"));
		
		th.assertContainer(c1.splice(0, 1), new Object[]{"aaa"});
		th.assertContainer(c1, new Object[]{"bbb","ccc","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","ccc","ddd",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"YYY"});
		
		th.assertContainer(c1.splice(1, 1), new Object[]{"ccc"});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","ddd",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"YYY"});
		
		th.assertContainer(c2.splice(2, 2), new Object[]{ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer()});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","ddd",ContainerFactory.undefinedContainer(),"YYY"});
		
		th.assertContainer(c2.splice(-3, 2), new Object[]{"ddd",ContainerFactory.undefinedContainer()});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{"XXX","YYY"});
		
		th.assertContainer(c2.splice(-10, 5), new Object[]{"XXX","YYY"});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{});
		assertTrue(c1.get(0).equals("bbb"));
		assertTrue(c2.get(0).equals("bbb"));
		assertTrue(c1.get(1).equals("ddd"));
		assertTrue(c2.get(1).equals("ddd"));
		
		th.assertContainer(c1.splice(5, 5), new Object[]{});
		th.assertContainer(c1, new Object[]{"bbb","ddd"});
		th.assertContainer(c2, new Object[]{});
		
		th.assertContainer(c1.splice(0, 3), new Object[]{"bbb","ddd"});
		th.assertContainer(c1, new Object[]{});
		th.assertContainer(c2, new Object[]{});
		
		th.assertContainer(c1.splice(0, 3), new Object[]{});
		assertEquals(c1.getLength(), new Integer(0));
		
		th.assertContainer(c2.splice(2, 2), new Object[]{});
		assertEquals(c2.getLength(), new Integer(0));
	}
	
	@Test
	public void testPushPopCombination()
	{
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		
		c1.push("XXX");
		c1.pop();
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, true, new String[]{});
		assertTrue(c1.get(5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(5).equals(ContainerFactory.undefinedContainer()));
		
		c1.push("XXX");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c2.pop();
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{});
		assertTrue(c1.get(5).equals("XXX"));
		assertTrue(c2.get(5).equals("XXX"));
		
		c1.pop();
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c1.push("YYY");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{});
		assertTrue(c1.get(5).equals("YYY"));
		assertTrue(c2.get(5).equals("YYY"));
	}
	
	@Test
	public void testPushDeleteCombination()
	{
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		
		c2.delete(2);
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c1.push("XXX");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c1.delete(0);
		th.assertContainer(c1, new Object[]{ContainerFactory.undefinedContainer(),"bbb","ccc","ddd","eee","XXX"});
		th.assertContainer(c2, new Object[]{ContainerFactory.undefinedContainer(),"bbb","ccc","ddd","eee","XXX"});
		th.assertContainerKeys(c1, false, new String[]{"1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{});
		assertTrue(c1.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(0).equals(ContainerFactory.undefinedContainer()));
		
		c1.push("YYY");
		th.assertContainer(c1, new Object[]{ContainerFactory.undefinedContainer(),"bbb","ccc","ddd","eee","XXX","YYY"});
		th.assertContainer(c2, new Object[]{ContainerFactory.undefinedContainer(),"bbb","ccc","ddd","eee","XXX","YYY"});
		th.assertContainerKeys(c1, false, new String[]{"1","2","3","4","5","6"});
		th.assertContainerKeys(c1, true, new String[]{"1","2","3","4","5","6"});
		th.assertContainerKeys(c2, false, new String[]{"1","2","3","4","5","6"});
		th.assertContainerKeys(c2, true, new String[]{});
	}
	
	@Test
	public void testPushSetCombination()
	{
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();	
		
		c1.set(7, "XXX");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","7"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","7"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","7"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c1.push("YYY");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX","YYY"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX","YYY"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c2.set(9, "ZZZ");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX","YYY"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX","YYY"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","7","8","9"});
		th.assertContainerKeys(c2, true, new String[]{"9"});
		assertTrue(c1.get(9).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(9).equals("ZZZ"));
		
		c2.push("WWW");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX","YYY"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer(),ContainerFactory.undefinedContainer(),"XXX","YYY","WWW"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","7","8"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","7","8","9"});
		th.assertContainerKeys(c2, true, new String[]{"9"});
		assertTrue(c1.get(9).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(9).equals("WWW"));
	}
	
	@Test
	public void testPushSetDeleteCombination()
	{
		UniversalContainer c1 = th.getSample("array-with-five-entries-abcde");
		UniversalContainer c2 = c1.create();
		
		c2.set(5, "XXX");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{"5"});
		assertTrue(c1.get(5).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(5).equals("XXX"));
		
		c1.push("YYY");
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee","XXX"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{"5"});
		
		c2.delete(5);
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY"});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee","YYY"});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4","5"});
		th.assertContainerKeys(c2, true, new String[]{});
		
		c1.delete(5);
		th.assertContainer(c1, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer()});
		th.assertContainer(c2, new Object[]{"aaa","bbb","ccc","ddd","eee",ContainerFactory.undefinedContainer()});
		th.assertContainerKeys(c1, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c1, true, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, false, new String[]{"0","1","2","3","4"});
		th.assertContainerKeys(c2, true, new String[]{});
	}
	
	@Test
	public void testPushPopSetCombination()
	{
		UniversalContainer c1 = ContainerFactory.createArray();
		UniversalContainer c2 = c1.create();
		
		c1.pop();
		c1.set(-1, 1);
		th.assertContainer(c1, new Object[]{});
		th.assertContainer(c2, new Object[]{});
		th.assertContainerKeys(c1, false, new String[]{"-1"});
		th.assertContainerKeys(c1, true, new String[]{"-1"});
		th.assertContainerKeys(c2, false, new String[]{"-1"});
		th.assertContainerKeys(c2, true, new String[]{});
		assertTrue(c1.get(-1).equals(1));
		assertTrue(c2.get(-1).equals(1));
		
		c2.pop();
		c2.push(1);
		th.assertContainer(c1, new Object[]{});
		th.assertContainer(c2, new Object[]{1});
		th.assertContainerKeys(c1, false, new String[]{"-1"});
		th.assertContainerKeys(c1, true, new String[]{"-1"});
		th.assertContainerKeys(c2, false, new String[]{"-1","0"});
		th.assertContainerKeys(c2, true, new String[]{"0"});
		assertTrue(c1.get(-1).equals(1));
		assertTrue(c2.get(-1).equals(1));
		assertTrue(c1.get(0).equals(ContainerFactory.undefinedContainer()));
		assertTrue(c2.get(0).equals(1));
		
		c2.set(-1, 111);
		th.assertContainer(c1, new Object[]{});
		th.assertContainer(c2, new Object[]{1});
		th.assertContainerKeys(c1, false, new String[]{"-1"});
		th.assertContainerKeys(c1, true, new String[]{"-1"});
		th.assertContainerKeys(c2, false, new String[]{"-1","0"});
		th.assertContainerKeys(c2, true, new String[]{"-1","0"});
		assertTrue(c1.get(-1).equals(1));
		assertTrue(c2.get(-1).equals(111));
	}
	
	@Test
	public void testPushShiftCombination()
	{
		UniversalContainer c1 = ContainerFactory.createArray("AAA", "bbb", "CCC", "ddd", "EEE", "fff").create().set("0", "XXX").set("b", "YYY").set("g", "GGG");
		
		c1.push(12345);
		c1.shift();
		
		assertTrue(c1.deepEquals(ContainerFactory.createArray().create().set("0", "bbb").set("1", "CCC").set("2", "ddd").set("3", "EEE").set("4", "fff").set("5", 12345).set("b", "YYY").set("g", "GGG")));
		th.assertContainer(c1, true, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"bbb","CCC","ddd","EEE","fff",12345,"YYY","GGG"});
		th.assertContainer(c1, false, new String[]{"0","1","2","3","4","5","b","g"}, new Object[]{"bbb","CCC","ddd","EEE","fff",12345,"YYY","GGG"});
	}
}