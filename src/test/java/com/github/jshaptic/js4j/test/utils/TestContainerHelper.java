package com.github.jshaptic.js4j.test.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import com.github.jshaptic.js4j.ContainerFactory;
import com.github.jshaptic.js4j.UniversalContainer;
import org.testng.Assert;

public class TestContainerHelper extends Assert
{
	private Map<String, List<UniversalContainer>> cache = null;
	
	public void assertContainer(UniversalContainer array, Object[] values)
	{
		assertTrue(array.getLength(0).equals(values.length));
		
		int index = 0;
		for (UniversalContainer e : array)
		{
			assertTrue(e.deepEquals(values[index]), "Value '" + e.asString() + "' at index " + index + " does not match reference value '" + values[index] + "' !");
			index++;
		}
		
		for (int i = 0; i < array.getLength(0); i++)
		{
			assertTrue(array.get(i).deepEquals(values[i]), "Value '" + array.get(i).asString() + "' at index " + i + " does not match reference value '" + values[i] + "' !");
		}
	}
	
	public void assertContainer(UniversalContainer container, boolean own, String[] keys, Object[] values)
	{
		assertEquals(keys.length, values.length, "Sizes of keys and values arrays are different, please correct test case");
		
		assertContainerKeys(container, own, keys);
		
		for (int i = 0; i < keys.length; i++)
		{
			assertTrue(container.get(keys[i]).deepEquals(values[i]), "Container value '" + container.get(keys[i]).asString() + "' doesn't match reference value '" + values[i] + "' within key '" + keys[i] + "' !");
		}
	}
	
	public void assertContainerKeys(UniversalContainer container, boolean own, String[] keys)
	{
		assertContainerKeys(container.keys(own), keys);
		
		for (int i = 0; i < keys.length; i++)
		{
			assertTrue(container.has(keys[i], own), "Reference key '" + keys[i] + "' does not exist in container!");
		}
	}
	
	public void assertContainerKeys(Set<String> containerKeys, String[] referenceKeys)
	{
		assertEquals(containerKeys.size(), referenceKeys.length);
		
		for (String key : containerKeys)
		{
			assertTrue(ArrayUtils.contains(referenceKeys, key), "Key '" + key + "' does not exist in the reference key array!");
		}
		
		for (int i = 0; i < referenceKeys.length; i++)
		{
			assertTrue(containerKeys.contains(referenceKeys[i]), "Reference key '" + referenceKeys[i] + "' does not exist in container!");
		}
	}
	
	public void cacheSamples(String pattern)
	{
		cache = generateSamples(pattern);
	}
	
	public Object getSamplePlainObject()
	{
		return new Object()
		{
			@Override public String toString(){return "literal-object-sample";}
			@Override public boolean equals(Object obj){return toString().equals(obj.toString());}
		};
	}
	
	public UniversalContainer getSample(String pattern)
	{
		return getSamples(pattern).get(0);
	}
	
	public List<UniversalContainer> getSamples(String pattern)
	{
		Map<String, List<UniversalContainer>> samples = generateSamples(pattern);
		List<UniversalContainer> result = new ArrayList<UniversalContainer>();
		
		for (String name : samples.keySet())
		{
			result.addAll(samples.get(name));
		}
		
		return result;
	}
	
	public List<UniversalContainer> getCachedSamples(String pattern)
	{
		List<UniversalContainer> result = new ArrayList<UniversalContainer>();
		
		if (cache != null)
		{
			for (String name : cache.keySet())
			{
				if (isSampleNameMatch(name, pattern))
					result.addAll(cache.get(name));
			}
		}
		
		return result;
	}
	
	private Map<String, List<UniversalContainer>> generateSamples(String pattern)
	{
		Map<String, List<UniversalContainer>> samples = new HashMap<String, List<UniversalContainer>>();
		
		samples.putAll(generateUndefinedSamples(pattern, ""));
		samples.putAll(generateNullSamples(pattern, ""));
		samples.putAll(generateLiteralSamples(pattern, ""));
		samples.putAll(generateObjectSamples(pattern, ""));
		samples.putAll(generateArraySamples(pattern, ""));
		
		return samples;
	}
	
	private Map<String, List<UniversalContainer>> generateUndefinedSamples(String include, String exclude)
	{
		String prefix = "undefined";
		String name = prefix;
		
		Map<String, List<UniversalContainer>> samples = new HashMap<String, List<UniversalContainer>>();
		
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			UniversalContainer c1 = ContainerFactory.undefinedContainer();
			UniversalContainer c2 = ContainerFactory.undefinedContainer().clone();
			
			samples.put(name, Arrays.asList(c1,c2));
		}
		
		return samples;
	}
	
	private Map<String, List<UniversalContainer>> generateNullSamples(String include, String exclude)
	{
		String prefix = "null";
		String name;
		
		Map<String, List<UniversalContainer>> samples = new HashMap<String, List<UniversalContainer>>();
		
		UniversalContainer c1 = ContainerFactory.nullContainer();
		UniversalContainer c2 = ContainerFactory.nullContainer().clone();
		
		name = prefix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(c1,c2));
		
		return samples;
	}
	
	private Map<String, List<UniversalContainer>> generateLiteralSamples(String include, String exclude)
	{
		String prefix = "literal";
		String base;
		String postfix;
		String name;
		
		Map<String, List<UniversalContainer>> samples = new HashMap<String, List<UniversalContainer>>();
		
		base = "boolean";
		
		postfix = "true";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(true), new UniversalContainer(new Boolean(true)), new UniversalContainer(true).clone()));
		postfix = "false";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(false), new UniversalContainer(new Boolean(false)), new UniversalContainer(false).clone()));
		
		base = "number-integer";
		
		postfix = "minus-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(-1), new UniversalContainer(new Integer(-1)), new UniversalContainer(-1).clone()));
		postfix = "zero";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(0), new UniversalContainer(new Integer(0)), new UniversalContainer(0).clone()));
		postfix = "plus-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(1), new UniversalContainer(new Integer(1)), new UniversalContainer(1).clone()));
		postfix = "12345";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(12345), new UniversalContainer(new Integer(12345)), new UniversalContainer(12345).clone()));
		
		base = "number-double";
		
		postfix = "minus-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(-1.0), new UniversalContainer(new Double(-1.0)), new UniversalContainer(-1.0).clone()));
		postfix = "zero";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(0.0), new UniversalContainer(new Double(0.0)), new UniversalContainer(0.0).clone()));
		postfix = "plus-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(1.0), new UniversalContainer(new Double(1.0)), new UniversalContainer(1.0).clone()));
		postfix = "plus-one-point-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(1.1), new UniversalContainer(new Double(1.1)), new UniversalContainer(1.1).clone()));
		
		base = "string";
		
		postfix = "empty";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(""), new UniversalContainer("").clone()));
		postfix = "true";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("true"), new UniversalContainer("true").clone()));
		postfix = "false";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("false"), new UniversalContainer("false").clone()));
		postfix = "minus-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("-1"), new UniversalContainer("-1").clone()));
		postfix = "zero";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("0"), new UniversalContainer("0").clone()));
		postfix = "plus-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("1"), new UniversalContainer("1").clone()));
		postfix = "plus-one-point-one";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("1.1"), new UniversalContainer("1.1").clone()));
		postfix = "letter-a";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("a"), new UniversalContainer("a").clone()));
		postfix = "abcde";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer("abcde"), new UniversalContainer("abcde").clone()));
		
		base = "null";
		
		postfix = "";
		name = prefix + "-" + base;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer((Object)null), new UniversalContainer((Object)null).clone()));
		
		base = "other";
		
		postfix = "";
		name = prefix + "-" + base;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(new UniversalContainer(getSamplePlainObject()), new UniversalContainer(getSamplePlainObject()).clone()));
		
		return samples;
	}
	
	private Map<String, List<UniversalContainer>> generateObjectSamples(String include, String exclude)
	{
		String prefix = "object";
		String base;
		String postfix;
		String name;
		
		Map<String, List<UniversalContainer>> samples = new HashMap<String, List<UniversalContainer>>();
		
		UniversalContainer c1 = ContainerFactory.createObject();
		UniversalContainer c2 = new UniversalContainer(new HashMap<String, Object>());
		UniversalContainer c3;
		
		//GENERATE EMPTY OBJECTS
		base = "empty";
		name = prefix + "-" + base;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(c1,c2));
		
		base = "with-one-entry-key-a";
		
		//GENERATE OBJECTS WITH UNDEFINED ENTRY
		postfix = "undefined";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			samples.put(name, new ArrayList<UniversalContainer>());
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				c1 = ContainerFactory.createObject().set("a", c);
				samples.get(name).add(c1);
			}
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				c2 = ContainerFactory.createObject("a", c);
				samples.get(name).add(c2);
			}
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				Map<String, Object> entries = new HashMap<String, Object>();
				entries.put("a", c);
				c3 = new UniversalContainer(entries);
				samples.get(name).add(c3);
			}
		}
		
		//GENERATE OBJECTS WITH NULL ENTRY
		postfix = "null";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			samples.put(name, new ArrayList<UniversalContainer>());
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				c1 = ContainerFactory.createObject().set("a", c);
				samples.get(name).add(c1);
			}
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				c2 = ContainerFactory.createObject("a", c);
				samples.get(name).add(c2);
			}
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				Map<String, Object> entries = new HashMap<String, Object>();
				entries.put("a", c);
				c3 = new UniversalContainer(entries);
				samples.get(name).add(c3);
			}
		}
		
		//GENERATE OBJECTS WITH LITERAL ENTRY
		postfix = "literal";
		name = prefix + "-" + base + "-" + postfix;
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					c1 = ContainerFactory.createObject().set("a", c);
					samples.get(name).add(c1);
				}
			}
		}
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					c2 = ContainerFactory.createObject("a", c);
					samples.get(name).add(c2);
				}
			}
		}
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					Map<String, Object> entries = new HashMap<String, Object>();
					entries.put("a", c);
					c3 = new UniversalContainer(entries);
					samples.get(name).add(c3);
				}
			}
		}
		
		//GENERATE OBJECTS WITH ARRAY ENTRY
		postfix = "array";
		name = prefix + "-" + base + "-" + postfix;
		if (!isSampleNameMatch(name, exclude))
		{
			for (Entry<String, List<UniversalContainer>> ls : generateArraySamples("*", "*-object").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						c1 = ContainerFactory.createObject().set("a", c);
						samples.get(name).add(c1);
					}
				}
			}
			for (Entry<String, List<UniversalContainer>> ls : generateArraySamples("*", "*-object").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						c2 = ContainerFactory.createObject("a", c);
						samples.get(name).add(c2);
					}
				}
			}
			for (Entry<String, List<UniversalContainer>> ls : generateArraySamples("*", "*-object").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						Map<String, Object> entries = new HashMap<String, Object>();
						entries.put("a", c);
						c3 = new UniversalContainer(entries);
						samples.get(name).add(c3);
					}
				}
			}
		}
		
		base = "with-five-entries";
		
		//GENERATE OBJECTS WITH VARIOUS DATA
		postfix = "abcde";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			c1 = ContainerFactory.createObject().set("a", "aaa").set("b", "bbb").set("c", "ccc").set("d", "ddd").set("e", "eee");
			c2 = ContainerFactory.createObject("a", "aaa", "b", "bbb", "c", "ccc", "d", "ddd", "e", "eee");
			Map<String, Object> entries = new HashMap<String, Object>();
			entries.put("a", "aaa");
			entries.put("b", "bbb");
			entries.put("c", "ccc");
			entries.put("d", "ddd");
			entries.put("e", "eee");
			c3 = new UniversalContainer(entries);
			
			samples.put(name, Arrays.asList(c1,c2,c3));
		}
		
		return samples;
	}
	
	private Map<String, List<UniversalContainer>> generateArraySamples(String include, String exclude)
	{
		String prefix = "array";
		String base;
		String postfix;
		String name;
		
		Map<String, List<UniversalContainer>> samples = new HashMap<String, List<UniversalContainer>>();
		
		UniversalContainer c1 = ContainerFactory.createArray();
		UniversalContainer c2 = new UniversalContainer(new ArrayList<Object>());
		UniversalContainer c3 = new UniversalContainer(new Object[]{});
		UniversalContainer c4;
		
		//GENERATE EMPTY ARRAYS
		base = "empty";
		name = prefix + "-" + base;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude)) samples.put(name, Arrays.asList(c1,c2,c3));
		
		base = "with-one-entry";
		
		//GENERATE ARRAYS WITH UNDEFINED ENTRY
		postfix = "undefined";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			samples.put(name, new ArrayList<UniversalContainer>());
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				c1 = ContainerFactory.createArray().set(0, c);
				samples.get(name).add(c1);
			}
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				c2 = ContainerFactory.createArray(c);
				samples.get(name).add(c2);
			}
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				c3 = new UniversalContainer(Arrays.asList(c));
				samples.get(name).add(c3);
			}
			for (UniversalContainer c : generateUndefinedSamples("*", "").get(postfix))
			{
				c4 = new UniversalContainer(new Object[]{c});
				samples.get(name).add(c4);
			}
		}
		
		//GENERATE ARRAYS WITH NULL ENTRY
		postfix = "null";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			samples.put(name, new ArrayList<UniversalContainer>());
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				c1 = ContainerFactory.createArray().set(0, c);
				samples.get(name).add(c1);
			}
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				c2 = ContainerFactory.createArray(c);
				samples.get(name).add(c2);
			}
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				c3 = new UniversalContainer(Arrays.asList(c));
				samples.get(name).add(c3);
			}
			for (UniversalContainer c : generateNullSamples("*", "").get(postfix))
			{
				c4 = new UniversalContainer(new Object[]{c});
				samples.get(name).add(c4);
			}
		}
		
		//GENERATE ARRAYS WITH LITERAL ENTRY
		postfix = "literal";
		name = prefix + "-" + base + "-" + postfix;
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					c1 = ContainerFactory.createArray().set(0, c);
					samples.get(name).add(c1);
				}
			}
		}
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					c2 = ContainerFactory.createArray(c);
					samples.get(name).add(c2);
				}
			}
		}
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					c3 = new UniversalContainer(Arrays.asList(c));
					samples.get(name).add(c3);
				}
			}
		}
		for (Entry<String, List<UniversalContainer>> ls : generateLiteralSamples("*", "").entrySet())
		{
			postfix = ls.getKey();
			name = prefix + "-" + base + "-" + postfix;
			if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
			{
				samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
				for (UniversalContainer c : ls.getValue())
				{
					c4 = new UniversalContainer(new Object[]{c});
					samples.get(name).add(c4);
				}
			}
		}
		
		//GENERATE ARRAYS WITH OBJECT ENTRY
		postfix = "object";
		name = prefix + "-" + base + "-" + postfix;
		if (!isSampleNameMatch(name, exclude))
		{
			for (Entry<String, List<UniversalContainer>> ls : generateObjectSamples("*", "*-array").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						c1 = ContainerFactory.createArray().set(0, c);
						samples.get(name).add(c1);
					}
				}
			}
			for (Entry<String, List<UniversalContainer>> ls : generateObjectSamples("*", "*-array").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						c2 = ContainerFactory.createArray(c);
						samples.get(name).add(c2);
					}
				}
			}
			for (Entry<String, List<UniversalContainer>> ls : generateObjectSamples("*", "*-array").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						c3 = new UniversalContainer(Arrays.asList(c));
						samples.get(name).add(c3);
					}
				}
			}
			for (Entry<String, List<UniversalContainer>> ls : generateObjectSamples("*", "*-array").entrySet())
			{
				postfix = ls.getKey();
				name = prefix + "-" + base + "-" + postfix;
				if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
				{
					samples.put(name, ObjectUtils.defaultIfNull(samples.get(name), new ArrayList<UniversalContainer>()));
					for (UniversalContainer c : ls.getValue())
					{
						c4 = new UniversalContainer(new Object[]{c});
						samples.get(name).add(c4);
					}
				}
			}
		}
		
		base = "with-five-entries";
		
		//GENERATE ARRAYS WITH VARIOUS DATA
		postfix = "abcde";
		name = prefix + "-" + base + "-" + postfix;
		if (isSampleNameMatch(name, include) && !isSampleNameMatch(name, exclude))
		{
			c1 = ContainerFactory.createArray().set(0, "aaa").set("1", "bbb").set(2, "ccc").set("3", "ddd").set(4, "eee");
			c2 = ContainerFactory.createArray("aaa", "bbb", "ccc", "ddd", "eee");
			c3 = new UniversalContainer(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee"));
			c4 = new UniversalContainer(new String[]{"aaa", "bbb", "ccc", "ddd", "eee"});
			
			samples.put(name, Arrays.asList(c1,c2,c3,c4));
		}
		
		return samples;
	}
	
	private boolean isSampleNameMatch(String name, String pattern)
	{
		if (pattern.isEmpty()) return false;
		if (pattern.equals("*")) return true;
		
		return name.matches("^" + pattern.replace("?", ".?").replace("*", ".*?") + "$");
	}
}