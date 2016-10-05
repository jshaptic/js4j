package com.github.jshaptic.js4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Java port of the javascript's famous library <a href="https://lodash.com">Lodash</a>. This class consists
 * exclusively of static methods that operate on {@link UniversalContainer} containers.
 * <p>
 * Note: Implementation based on the Lodash 3.10.1 [https://lodash.com/license]
 * </p>
 * 
 * @author Eugene Shatilo
 * @version 0.1
 *
 */
public class Lodash
{
	private Lodash() {}
	
	/**
	 * Checks if value is classified as an ARRAY container. ARRAY containers, instances of List and Set classes, and
	 * java arrays of any type are considered as arrays. For null values method will return <code>false</code>.
	 * 
	 * @param 	value object of any class, which will be tested
	 * @return	<code>true</code> if value is classified as ARRAY container; <code>false</code> otherwise.
	 */
	public static boolean isArray(Object value)
	{
		return value != null && value instanceof UniversalContainer ? ((UniversalContainer)value).isArray() : (value instanceof List || value instanceof Set || value instanceof Object[]);
	}
	
	/**
	 * Checks if value is classified as an OBJECT container. OBJECT and ARRAY containers, instances of Map, List and Set
	 * classes are considered as objects. For null values method will return <code>false</code>.
	 * 
	 * @param 	value object of any class, which will be tested
	 * @return	<code>true</code> if value is classified as OBJECT container; <code>false</code> otherwise.
	 */
	public static boolean isObject(Object value)
	{
		return value != null && value instanceof UniversalContainer ? !((UniversalContainer)value).isNull() && ((UniversalContainer)value).isObject() : (value instanceof Map || value instanceof List || value instanceof Set);
	}
	
	/**
	 * Checks if value is classified as a NULL container. Non null NULL containers and null instances of all other
	 * classes are considered as nulls.
	 * 
	 * @param 	value object of any class, which will be tested
	 * @return	<code>true</code> if value is classified as NULL container; <code>false</code> otherwise.
	 */
	public static boolean isNull(Object value)
	{
		return (value != null && value instanceof UniversalContainer && ((UniversalContainer)value).isNull()) || (value == null && !(value instanceof UniversalContainer));
	}
	
	/**
	 * Checks if value is classified as a UNDEFINED container. Any null values and UNDEFINED containers are considered
	 * as undefineds.
	 * 
	 * @param	value object of any class, which will be tested
	 * @return	<code>true</code> if value is classified as UNDEFINED container; <code>false</code> otherwise.
	 */
	public static boolean isUndefined(Object value)
	{
		return value == null || (value instanceof UniversalContainer && ((UniversalContainer)value).isUndefined());
	}
	
	/**
	 * Performs a deep comparison between two values to determine if they are equivalent.
	 * <p>
	 * Note: This method supports comparing all known containers, objects of primitive classes and classes like
	 * List, Set, Map, and also arrays of any type. OBJECT containers are compared by their own, not inherited,
	 * enumerable properties.
	 * </p>
	 * 
	 * @param	value the container to compare
	 * @param	other the other value of any class to compare 
	 * @return	<code>true</code> if the values are equivalent; <code>false</code> otherwise.
	 */
	public static boolean isEqual(UniversalContainer value, Object other)
	{
		return baseIsEqual(value, other, null, null);
	}
	
	private static boolean baseIsEqual(UniversalContainer value, Object other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		if (value == other) return true;
		if (value == null) return ContainerFactory.undefinedContainer().equals(other);
		if (other == null) return value.equals(other);
		
		if (isUndefined(value) || isUndefined(other) || isNull(value) || isNull(other) || (!isObject(value) && !isObject(other)))
		{
			return value.equals(other);
		}
		
		return baseIsEqualDeep(value, other, stackA, stackB);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean baseIsEqualDeep(UniversalContainer object, Object other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		boolean isObjectArray = isArray(object);
		boolean isOtherArray = isArray(other);
		
		if (isObjectArray != isOtherArray)
		{
			return false;
		}
		
		stackA = (stackA != null ? stackA : new ArrayList<UniversalContainer>());
		stackB = (stackB != null ? stackB : new ArrayList<Object>());
		
		int length = stackA.size();
		while (length-- > 0)
		{
			if (stackA.get(length) == object)
			{
				return stackB.get(length) == other;
			}
		}
		
		stackA.add(object);
		stackB.add(other);
		
		boolean result = false;
		
		if (other instanceof UniversalContainer)
		{
			result = isObjectArray ? equalArrays(object, (UniversalContainer)other, stackA, stackB) : equalObjects(object, (UniversalContainer)other, stackA, stackB);
		}
		else if (other instanceof List)
		{
			result = equalArrays(object, (List)other, stackA, stackB);
		}
		else if (other instanceof Set)
		{
			result = equalArrays(object, (Set)other, stackA, stackB);
		}
		else if (other instanceof Object[])
		{
			result = equalArrays(object, (Object[])other, stackA, stackB);
		}
		else if (other instanceof Map)
		{
			result = equalObjects(object, (Map)other, stackA, stackB);
		}
		
		if (stackA.size() > 0) stackA.remove(stackA.size()-1);
		if (stackB.size() > 0) stackB.remove(stackB.size()-1);
		
		return result;
	}
	
	private static boolean equalArrays(UniversalContainer array, UniversalContainer other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		int index = -1;
		int arrayLength = array.getLength(0);
		int otherLength = other.getLength(0);
		
		if (arrayLength != otherLength)
		{
			return false;
		}
		
		while (++index < arrayLength)
		{
			UniversalContainer arrayValue = array.get(index);
			UniversalContainer otherValue = other.get(index);
			
			if (!(arrayValue == otherValue || baseIsEqual(arrayValue, otherValue, stackA, stackB)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean equalArrays(UniversalContainer array, List<Object> other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		int index = -1;
		int arrayLength = array.getLength(0);
		int otherLength = other.size();
		
		if (arrayLength != otherLength)
		{
			return false;
		}
		
		while (++index < arrayLength)
		{
			UniversalContainer arrayValue = array.get(index);
			Object otherValue = other.get(index);
			
			if (!(arrayValue == otherValue || baseIsEqual(arrayValue, otherValue, stackA, stackB)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean equalArrays(UniversalContainer array, Set<Object> other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		int arrayLength = array.getLength(0);
		int otherLength = other.size();
		
		if (arrayLength != otherLength)
		{
			return false;
		}
		
		Set<Integer> indexSet = new HashSet<Integer>();
		
		for (Object otherValue : other)
		{
			boolean isMatchFound = false;
			
			for (int i = 0; i < arrayLength; i++)
			{
				UniversalContainer arrayValue = array.get(i);
				
				if (!indexSet.contains(i) && (arrayValue == otherValue || baseIsEqual(arrayValue, otherValue, stackA, stackB)))
				{
					isMatchFound = true;
					indexSet.add(i);
					break;
				}
				else if (indexSet.contains(i))
				{
					return false;
				}
			}
			
			if (!isMatchFound)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean equalArrays(UniversalContainer array, Object[] other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		int index = -1;
		int arrayLength = array.getLength(0);
		int otherLength = other.length;
		
		if (arrayLength != otherLength)
		{
			return false;
		}
		
		while (++index < arrayLength)
		{
			UniversalContainer arrayValue = array.get(index);
			Object otherValue = other[index];
			
			if (!(arrayValue == otherValue || baseIsEqual(arrayValue, otherValue, stackA, stackB)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean equalObjects(UniversalContainer object, UniversalContainer other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		Set<String> objectProperties = object.keys();
		Set<String> otherProperties = other.keys();
		
		if (object.getConstructor() != other.getConstructor() && object.getConstructor() != null && other.getConstructor() != null)
		{
			return false;
		}
		
		if (!objectProperties.equals(otherProperties))
		{
			return false;
		}
		
		for (String key : objectProperties)
		{
			UniversalContainer arrayValue = object.get(key);
			UniversalContainer otherValue = other.get(key);
			
			if (!(arrayValue == otherValue || baseIsEqual(arrayValue, otherValue, stackA, stackB)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean equalObjects(UniversalContainer object, Map<Object, Object> other, List<UniversalContainer> stackA, List<Object> stackB)
	{
		Set<String> objectProperties = object.keys();
		Set<Object> otherProperties = other.keySet();
		
		if (!objectProperties.equals(otherProperties))
		{
			return false;
		}
		
		for (String key : objectProperties)
		{
			UniversalContainer arrayValue = object.get(key);
			Object otherValue = other.get(key);
			
			if (!(arrayValue == otherValue || baseIsEqual(arrayValue, otherValue, stackA, stackB)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Creates a shallow copy of the value. Nested containers are assigned by reference.
	 * 
	 * @param 	value any container to clone
	 * @return	cloned value
	 */
	public static UniversalContainer clone(UniversalContainer value)
	{
		if (value == null) return ContainerFactory.undefinedContainer();
		if (!isObject(value)) return value;
		
		if (isArray(value))
		{
			return arrayCopy(value, ContainerFactory.createArray());
		}
		else
		{
			return extend(ContainerFactory.createObject(), value);
		}
	}
	
	private static UniversalContainer arrayCopy(UniversalContainer source, UniversalContainer array)
	{
		int index = -1;
		int length = source.getLength(0);
		
		if (!array.test()) array = ContainerFactory.createArray();
		
		while (++index < length)
		{
			array.set(index, source.get(index));
		}
		
		return array;
	}
	
	/**
	 * Gets the last element of enumerable container like ARRAY or STRING LITERAL.
	 * 
	 * @param 	array any enumerable container to query
	 * @return 	the last element of enumerable container
	 */
	public static UniversalContainer last(UniversalContainer array)
	{
		int length = array.test() ? array.getLength(0) : 0;
		return length > 0 ? array.get(length-1) : ContainerFactory.undefinedContainer();
	}
	
	/**
	 * Assigns own enumerable properties of source OBJECT container(s) to the destination OBJECT container. Subsequent
	 * sources overwrite property assignments of previous sources.
	 * <p>
	 * Note: This method mutates destination OBJECT container.
	 * </p>
	 * 
	 * @param	object	destination container
	 * @param 	sources	source containers, which will override properties in the destination container
	 * @return	destination container with the overriden properties
	 */
	public static UniversalContainer extend(UniversalContainer object, UniversalContainer... sources)
	{
		if (object == null) return ContainerFactory.undefinedContainer(); 
		if (sources == null || !isObject(object)) return object;
		
		for (UniversalContainer source : sources)
		{
			if (source != null && source.test())
			{
				for (String key : source.keys())
				{
					object.set(key, source.get(key));
				}
			}
		}
		
		return object;
	}
	
	/**
	 * Recursively merges own enumerable properties of the source OBJECT container(s), that don't resolve to UNDEFINED
	 * into the destination OBJECT container.
	 * This method works as if invoking it were equivalent to evaluating the expression:
	 * <blockquote><pre>
	 * merge(object, null, sources)
	 * </pre></blockquote>
	 * <p>
	 * Note: This method mutates destination OBJECT container.
	 * </p>
	 * 
	 * @param 	object destination container
	 * @param 	sources source containers, which properties will be merged into the destination container
	 * @return	destination container with the merged properties
	 */
	public static UniversalContainer merge(UniversalContainer object, UniversalContainer... sources)
	{
		return merge(object, null, sources);
	}
	
	/**
	 * Recursively merges own enumerable properties of the source OBJECT container(s), that don't resolve to UNDEFINED
	 * into the destination OBJECT container. Subsequent sources overwrite property assignments of previous sources. If
	 * customizer is provided it's invoked to produce the merged values of the destination and source properties. If
	 * customizer returns UNDEFINED container merging is handled by the method instead. The customizer is invoked with
	 * two arguments: (objectValue, sourceValue).
	 * <p>
	 * Note: This method mutates destination OBJECT container.
	 * </p>
	 * 
	 * @param 	object destination container
	 * @param 	customizer instance of {@link ValueCustomizer} class with a custom logic, which will be aplied to every pair
	 * @param 	sources source containers, which properties will be merged into the destination container
	 * @return	destination container with the merged properties
	 */
	public static UniversalContainer merge(UniversalContainer object, ValueCustomizer customizer, UniversalContainer... sources)
	{
		if (object == null) return ContainerFactory.undefinedContainer();
		if (sources == null || !isObject(object)) return object;
		
		for (UniversalContainer source : sources)
		{
			if (source != null && source.test())
			{
				baseMerge(object, source, customizer, null, null);
			}
		}
		
		return object;
	}
	
	private static UniversalContainer baseMerge(UniversalContainer object, UniversalContainer source, ValueCustomizer customizer, List<UniversalContainer> stackA, List<UniversalContainer> stackB)
	{
		boolean isSourceArray = isArray(source);
		UniversalContainer elements = (isSourceArray ? source : new UniversalContainer(source.keys()));
		int index = -1;
		
		for (UniversalContainer e : elements)
		{
			String key = (isSourceArray ? String.valueOf(++index) : e.asString());
			UniversalContainer sourceValue = (isSourceArray ? e : source.get(key));
			
			if (isObject(sourceValue))
			{
				stackA = (stackA != null ? stackA : new ArrayList<UniversalContainer>());
				stackB = (stackB != null ? stackB : new ArrayList<UniversalContainer>());
				baseMergeDeep(object, source, key, customizer, stackA, stackB);
			}
			else
			{
				UniversalContainer objectValue = object.get(key);
				UniversalContainer result = (customizer != null ? customizer.customize(objectValue, sourceValue) : ContainerFactory.undefinedContainer());
				boolean isCommon = result.isUndefined();
				
				if (isCommon)
				{
					result = sourceValue;
				}
				if ((!result.isUndefined() || (isSourceArray && !object.has(key, false))) && (isCommon || !result.equals(objectValue)))
				{
					object.set(key, result);
				}
			}
		}
		
		return object;
	}
	
	private static void baseMergeDeep(UniversalContainer object, UniversalContainer source, String key, ValueCustomizer customizer, List<UniversalContainer> stackA, List<UniversalContainer> stackB)
	{
		int length = stackA.size();
		UniversalContainer sourceValue = source.get(key);
		
		while(length-- > 0)
		{
			if (stackA.get(length) == sourceValue)
			{
				object.set(key, stackB.get(length));
				return;
			}
		}
		
		UniversalContainer objectValue = object.get(key);
		UniversalContainer result = (customizer != null ? customizer.customize(objectValue, sourceValue) : ContainerFactory.undefinedContainer());
		boolean isCommon = result.isUndefined();
		
		if (isCommon)
		{
			result = sourceValue;
			if (isArray(sourceValue))
			{
				result = (isArray(objectValue) ? objectValue : ContainerFactory.createArray());
			}
			else if (isObject(sourceValue))
			{
				result = (isObject(objectValue) ? objectValue : ContainerFactory.createObject());
			}
			else
			{
				isCommon = false;
			}
		}
		
		stackA.add(sourceValue);
		stackB.add(result);
		
		if (isCommon)
		{
			object.set(key, baseMerge(result, sourceValue, customizer, stackA, stackB));
		}
		else if (!result.equals(objectValue))
		{
			object.set(key, result);
		}
	}
	
	/**
	 * Creates an ARRAY continer of the own enumerable string keyed property values of OBJECT container.
	 * 
	 * @param 	object OBJECT container to query for values.
	 * @return 	ARRAY container of property values.
	 */
	public static UniversalContainer values(UniversalContainer object)
	{
		UniversalContainer result = ContainerFactory.createArray();
		
		if (object == null) return result;
		
		for (String key : object.keys())
		{
			result.push(object.get(key));
		}
		
		return result;
	}
}
