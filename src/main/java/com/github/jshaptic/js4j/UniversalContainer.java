package com.github.jshaptic.js4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Wrapper container for various types of objects, also provides some generic methods for manipulation over stored
 * object regardless of the class. Simply UniversalContainer just emulates behaviour of javascript variables and all
 * methods replicate API of various JS objects. It was created to ease porting of javascript code to Java, but can be
 * used in any other cases.
 * <p>
 * All public methods in this container try to replicate behaviour of JS variables as much as possible, however there
 * can be some differences, but API is quite close to JS. Full list of all implemented javascript methods and properties
 * (fully, partially or differently):
 * <ul>
 * <li>Object.create()</li>
 * <li>Object.keys()</li>
 * </ul>
 * <ul>
 * <li>Object.prototype.constructor</li>
 * <li>Object.prototype.toString()</li>
 * <li>Object.prototype.valueOf()</li>
 * </ul>
 * <ul>
 * <li>String.prototype.length</li>
 * <li>String.prototype.concat()</li>
 * <li>String.prototype.indexOf()</li>
 * </ul>
 * <ul>
 * <li>Array.isArray()</li>
 * </ul>
 * <ul>
 * <li>Array.prototype.length</li>
 * <li>Array.prototype.pop()</li>
 * <li>Array.prototype.push()</li>
 * <li>Array.prototype.shift()</li>
 * <li>Array.prototype.splice()</li>
 * <li>Array.prototype.concat()</li>
 * <li>Array.prototype.indexOf()</li>
 * </ul>
 * </p>
 * <p>
 * Also this container has shortcuts to some popular methods from Lodash library, see {@link Lodash} class.
 * </p>
 * <p>
 * Table of all supported container types and corresponding java raw classes:
 * <table>
 * <tr>
 * <th>Container</th>
 * <th>Java raw class</th>
 * </tr>
 * <tr>
 * <td>UNDEFINED</td>
 * <td>null (any class)</td>
 * </tr>
 * <tr>
 * <td>NULL</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>BOOLEAN LITERAL</td>
 * <td>boolean, {@link Boolean}</td>
 * </tr>
 * <tr>
 * <td>NUMBER LITERAL</td>
 * <td>int, float, double, {@link Integer}, {@link Float}, {@link Double}</td>
 * </tr>
 * <tr>
 * <td>STRING LITERAL</td>
 * <td>{@link String}</td>
 * </tr>
 * <tr>
 * <td>OTHER LITERAL</td>
 * <td>any other class except listed in this table</td>
 * </tr>
 * <tr>
 * <td>OBJECT</td>
 * <td>{@link Map} (and any other class inherited from it)</td>
 * </tr>
 * <tr>
 * <td>ARRAY</td>
 * <td>Java arrays, {@link List} (and any other class inherited from it), {@link Set} (and any other class inherited
 * from it)</td>
 * </tr>
 * </table>
 * </p>
 */
public class UniversalContainer implements Iterable<UniversalContainer>
{
	// CONSTANTS ------------------------------------------------------------------------------------------------------
	
	protected static final String BUILTIN_TYPE_UNDEFINED = "undefined";
	protected static final String BUILTIN_TYPE_NULL = "null";
	protected static final String BUILTIN_TYPE_BOOLEAN = "boolean";
	protected static final String BUILTIN_TYPE_NUMBER = "number";
	protected static final String BUILTIN_TYPE_STRING = "string";
	protected static final String BUILTIN_TYPE_OBJECT = "object";
	protected static final String BUILTIN_TYPE_UNKNOWN = "unknown";
	
	protected static final String BUILTIN_CLASS_UNDEFINED = "Undefined";
	protected static final String BUILTIN_CLASS_NULL = "Null";
	protected static final String BUILTIN_CLASS_BOOLEAN = "Boolean";
	protected static final String BUILTIN_CLASS_NUMBER = "Number";
	protected static final String BUILTIN_CLASS_STRING = "String";
	protected static final String BUILTIN_CLASS_LITERAL = "Literal";
	protected static final String BUILTIN_CLASS_OBJECT = "Object";
	protected static final String BUILTIN_CLASS_ARRAY = "Array";
	
	protected static final String BUILTIN_PROPERTY_CONSTRUCTOR = "constructor";
	protected static final String BUILTIN_PROPERTY_LENGTH = "length";
	
	// MEMBERS --------------------------------------------------------------------------------------------------------
	
	protected ContainerValue inner = null;
	protected UniversalContainer proto = null;
	
	// BUILTIN PROPERTIES ---------------------------------------------------------------------------------------------
	
	protected ContainerConstructor constructor = null;
	protected Integer length = null;
	
	// CONSTRUCTORS ---------------------------------------------------------------------------------------------------
	
	/**
	 * Default constructor, which will produce UNDEFINED container, behaviour is equivalent to the following expression:
	 * <blockquote>
	 * 
	 * <code>
	 * UniversalContainer(ContainerConstructor.UNDEFINED);
	 * </code>
	 * 
	 * </blockquote>
	 */
	protected UniversalContainer()
	{
		this(ContainerConstructor.UNDEFINED);
	}
	
	/**
	 * Constructor, which will produce container depending on the specifed constructor, see {@link ContainerConstructor}
	 * .
	 */
	protected UniversalContainer(ContainerConstructor type)
	{
		if (type == null) type = ContainerConstructor.UNDEFINED;
		
		switch (type)
		{
			case UNDEFINED:
				setUndefined();
				break;
			case NULL:
				setNull();
				break;
			case LITERAL:
				setLiteral(null);
				break;
			case OBJECT:
				setObject();
				break;
			case ARRAY:
				setArray();
				break;
		}
	}
	
	/**
	 * Constructor, which will produce simple LITERAL container with the specified value.
	 */
	public UniversalContainer(Object value)
	{
		wrapValue(this, value);
	}
	
	/**
	 * Constructor, which will produce very shallow copy of the specified container. All changes in the copied container
	 * will be reflected in the specified container and vice versa.
	 */
	public UniversalContainer(UniversalContainer container)
	{
		wrapValue(this, container);
	}
	
	/**
	 * Constructor, which will produce ARRAY container with the values based on the specified array.
	 */
	public UniversalContainer(Object[] array)
	{
		wrapValue(this, array);
	}
	
	/**
	 * Constructor, which will produce ARRAY container with the values based on the specified Iterable object.
	 */
	public UniversalContainer(Iterable<Object> list)
	{
		wrapValue(this, list);
	}
	
	/**
	 * Constructor, which will produce OBJECT container with the values based on the specified Map.
	 */
	public UniversalContainer(Map<Object, Object> map)
	{
		wrapValue(this, map);
	}
	
	// CONVERSION METHODS ---------------------------------------------------------------------------------------------
	
	/**
	 * Converts current value to a boolean value. Number 1 and string "1" both are treated as true. ARRAY containers
	 * with one element will be treated as single value, which means that this single element will be converted instead
	 * of current container, conversino rules will be the same.
	 * 
	 * @return true, if value is one of the following: true, 1, "1", [1], ["1"]; false otherwise.
	 */
	public boolean asBoolean()
	{
		if (!isString() && has(BUILTIN_PROPERTY_LENGTH, false) && getLength(0) == 1)
		{
			return get(0).asBoolean();
		}
		
		return ContainerValue.asBoolean(inner);
	}
	
	/**
	 * Converts value, which is located in the specified position to a boolean value. This method works as if invoking
	 * it were equivalent to evaluating the expression: <blockquote><code>get(index).asBoolean();</code></blockquote>
	 * 
	 * @param index position of value, which will be converted to boolean.
	 * @return true, if value is one of the following: true, 1, "1", [1], ["1"]; false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #asBoolean()
	 */
	public boolean asBoolean(int index)
	{
		return get(index).asBoolean();
	}
	
	/**
	 * Converts value, which is located in the specified property to a boolean value. This method works as if invoking
	 * it were equivalent to evaluating the expression: <blockquote><code>get(property).asBoolean();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be converted to boolean.
	 * @return true, if value is one of the following: true, 1, "1", [1], ["1"]; false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #asBoolean()
	 */
	public boolean asBoolean(String property)
	{
		return get(property).asBoolean();
	}
	
	/**
	 * Converts current value to a double value. Boolean values true and false will be converted to 1.0 and to 0.0
	 * accordingly, strings which can be parsed will be converted as well, otherwise 0.0 will be returned. ARRAY
	 * containers with one element will be treated as single value, which means that this single element will be
	 * converted instead of current container, conversino rules will be the same.
	 * 
	 * @return either a double value, which is currently stored; or will return 1.0 if there is a true boolean value; or
	 *         will try to parse string value; in all negative cases it will return 0.0.
	 */
	public double asDouble()
	{
		if (!isString() && has(BUILTIN_PROPERTY_LENGTH, false) && getLength(0) == 1)
		{
			return get(0).asDouble();
		}
		
		return ContainerValue.asNumber(inner).doubleValue();
	}
	
	/**
	 * Converts value, which is located in the specified position to a double value. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(index).asDouble();</code></blockquote>
	 * 
	 * @param index position of value, which will be converted to double.
	 * @return either a double value, which is currently stored; or will return 1.0 if there is a true boolean value; or
	 *         will try to parse string value; in all negative cases it will return 0.0.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #asDouble()
	 */
	public double asDouble(int index)
	{
		return get(index).asDouble();
	}
	
	/**
	 * Converts value, which is located in the specified property to a double value. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(property).asDouble();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be converted to double.
	 * @return either a double value, which is currently stored; or will return 1.0 if there is a true boolean value; or
	 *         will try to parse string value; in all negative cases it will return 0.0.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #asDouble()
	 */
	public double asDouble(String property)
	{
		return get(property).asDouble();
	}
	
	/**
	 * Converts current value to an integer value. Boolean values true and false will be converted to 1 and to 0
	 * accordingly, strings which can be parsed will be converted as well, otherwise 0 will be returned. ARRAY
	 * containers with one element will be treated as single value, which means that this single element will be
	 * converted instead of current container, conversino rules will be the same.
	 * 
	 * @return either an integer value, which is currently stored; or will return 1 if there is a true boolean value; or
	 *         will try to parse string value; in all negative cases it will return 0.
	 */
	public int asInt()
	{
		if (!isString() && has(BUILTIN_PROPERTY_LENGTH, false) && getLength(0) == 1)
		{
			return get(0).asInt();
		}
		
		return ContainerValue.asNumber(inner).intValue();
	}
	
	/**
	 * Converts value, which is located in the specified position to an integer value. This method works as if invoking
	 * it were equivalent to evaluating the expression: <blockquote><code>get(index).asInt();</code></blockquote>
	 * 
	 * @param index position of value, which will be converted to integer.
	 * @return either an integer value, which is currently stored; or will return 1 if there is a true boolean value; or
	 *         will try to parse string value; in all negative cases it will return 0.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #asInt()
	 */
	public int asInt(int index)
	{
		return get(index).asInt();
	}
	
	/**
	 * Converts value, which is located in the specified property to an integer value. This method works as if invoking
	 * it were equivalent to evaluating the expression: <blockquote><code>get(property).asInt();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be converted to integer.
	 * @return either an integer value, which is currently stored; or will return 1 if there is a true boolean value; or
	 *         will try to parse string value; in all negative cases it will return 0.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #asInt()
	 */
	public int asInt(String property)
	{
		return get(property).asInt();
	}
	
	/**
	 * Converts current value to a string value. Containers which hold primitive values like boolean, double or integer
	 * will be just converted as is; UNDEFIEND or NULL containers will return empty string; OBJECT container will return
	 * special tag "[object Object]"; and ARRAY container will be recursively converted to a comma separated list and
	 * cyclic references will be ignored.
	 * 
	 * @return string value, which represents current container.
	 * @see #toString()
	 */
	public String asString()
	{
		StringBuilder sb = new StringBuilder();
		asString(this, sb, null);
		return sb.toString();
	}
	
	private static void asString(UniversalContainer container, StringBuilder sb, List<UniversalContainer> stack)
	{
		if (!container.isString() && container.has(BUILTIN_PROPERTY_LENGTH, false))
		{
			stack = (stack != null ? stack : new ArrayList<UniversalContainer>());
			
			int index = stack.size();
			while (index-- > 0)
			{
				if (stack.get(index) == container)
				{
					return;
				}
			}
			
			stack.add(container);
			
			int length = container.getLength(0);
			for (int i = 0; i < length; i++)
			{
				UniversalContainer value = container.get(i);
				asString(value, sb, stack);
				if (i != length - 1) sb.append(",");
			}
			
			if (stack.size() > 0) stack.remove(stack.size() - 1);
		}
		else if (container.isObject() && !container.isNull())
		{
			sb.append("[object Object]");
		}
		else
		{
			sb.append(ContainerValue.asString(container));
		}
	}
	
	/**
	 * Converts value, which is located in the specified position to a string value. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(index).asString();</code></blockquote>
	 * 
	 * @param index position of value, which will be converted to string.
	 * @return string value, which represents current container.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #asString()
	 */
	public String asString(int index)
	{
		return get(index).asString();
	}
	
	/**
	 * Converts value, which is located in the specified property to a string value. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(property).asString();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be converted to string.
	 * @return string value, which represents current container.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #asString()
	 */
	public String asString(String property)
	{
		return get(property).asString();
	}
	
	/**
	 * Converts current ARRAY container to List. If current container holds any primitive value or String it return List
	 * with one element. In all other cases it will return empty list.
	 * 
	 * @param clazz class, which will as a type for a resulting List.
	 * @return List object, converted from ARRAY container; or just empty list.
	 * @throws ClassCastException if one of the values cannot be casted to a desired class.
	 */
	public <V> List<V> asList(Class<V> clazz)
	{
		List<V> list = new ArrayList<V>();
		
		if (!isString() && test(BUILTIN_PROPERTY_LENGTH))
		{
			for (UniversalContainer value : this)
			{
				list.add(castValue(value, clazz));
			}
		}
		else if (inner instanceof PrimitiveLiteralValue)
		{
			list.add(castValue(this, clazz));
		}
		
		return list;
	}
	
	/**
	 * Converts current OBJECT container to Map. For all other containers empty map will be returned. Resulting map key
	 * type will always be a String.
	 * 
	 * @param clazz class, which will be used as a type for a resulting Map.
	 * @return Map object, converted from OBJECT container; or just empty map.
	 * @throws ClassCastException if one of the values cannot be casted to a desired class.
	 */
	public <V> Map<String, V> asMap(Class<V> clazz)
	{
		Map<String, V> map = new HashMap<String, V>();
		
		if (isObject())
		{
			for (String k : keys())
			{
				map.put(k, castValue(get(k), clazz));
			}
		}
		
		return map;
	}
	
	/**
	 * Simply extracts value from the current container. If current container is not LITERAL, null value will be
	 * returned, otherwise stored value will be extracted.
	 * 
	 * @return casted value, which is stored in the current container.
	 * @throws ClassCastException if value cannot be casted to a desired class.
	 */
	@SuppressWarnings("unchecked")
	public <T> T valueOf()
	{
		if (inner instanceof PrimitiveLiteralValue)
			return (T) inner.literal;
		else
			return null;
	}
	
	/**
	 * Simply extracts value from the specified position in the current container. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(index).valueOf();</code></blockquote>
	 * 
	 * @param index position of value, which will be extracted.
	 * @return casted value, which is stored in the specified position of the current container.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @throws ClassCastException if value cannot be casted to a desired class.
	 * @see #valueOf()
	 */
	public <T> T valueOf(int index)
	{
		return get(index).valueOf();
	}
	
	/**
	 * Simply extracts value from the specified property in the current container. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(property).valueOf();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be extracted.
	 * @return casted value, which is stored in the specified property of the current container.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @throws ClassCastException if value cannot be casted to a desired class.
	 * @see #valueOf()
	 */
	public <T> T valueOf(String property)
	{
		return get(property).valueOf();
	}
	
	// TEST METHODS ---------------------------------------------------------------------------------------------------
	
	/**
	 * Checks if current container is ARRAY container.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of static javascript method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/isArray">Array.
	 * isArray(obj)</a>.
	 * </p>
	 * 
	 * @return true if current container is ARRAY container, false otherwise.
	 */
	public boolean isArray()
	{
		return inner instanceof ArrayValue;
	}
	
	/**
	 * Checks if value at specified position is ARRAY container. This method works as if invoking it were equivalent to
	 * evaluating the expression: <blockquote><code>get(index).isArray();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is ARRAY container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isArray()
	 */
	public boolean isArray(int index)
	{
		return get(index).isArray();
	}
	
	/**
	 * Checks if value in specified property is ARRAY container. This method works as if invoking it were equivalent to
	 * evaluating the expression: <blockquote><code>get(property).isArray();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is ARRAY container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isArray()
	 */
	public boolean isArray(String property)
	{
		return get(property).isArray();
	}
	
	/**
	 * Checks if current container is BOOLEAN LITERAL container.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of expression <blockquote>
	 * <code>typeof obj === "boolean"</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container is BOOLEAN LITERAL container, false otherwise.
	 */
	public boolean isBoolean()
	{
		return ContainerValue.isBoolean(inner);
	}
	
	/**
	 * Checks if value at specified position is BOOLEAN LITERAL container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>get(index).isBoolean();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is BOOLEAN LITERAL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isBoolean()
	 */
	public boolean isBoolean(int index)
	{
		return get(index).isBoolean();
	}
	
	/**
	 * Checks if value in specified property is BOOLEAN LITERAL container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>get(property).isBoolean();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is BOOLEAN LITERAL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isBoolean()
	 */
	public boolean isBoolean(String property)
	{
		return get(property).isBoolean();
	}
	
	/**
	 * Checks if current container is OBJECT container. It also will return true for NULL and ARRAY containers.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of expression <blockquote>
	 * <code>typeof obj === "object"</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container is OBJECT, ARRAY or NULL container, false otherwise.
	 */
	public boolean isObject()
	{
		return inner instanceof ObjectValue || inner instanceof NullValue;
	}
	
	/**
	 * Checks if value at specified position is OBJECT, ARRAY or NULL container. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(index).isObject();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is OBJECT, ARRAY or NULL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isObject()
	 */
	public boolean isObject(int index)
	{
		return get(index).isObject();
	}
	
	/**
	 * Checks if value in specified property is OBJECT, ARRAY or NULL container. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>get(property).isObject();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is OBJECT, ARRAY or NULL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isObject()
	 */
	public boolean isObject(String property)
	{
		return get(property).isObject();
	}
	
	/**
	 * Checks if current container is NULL container.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of expression <blockquote>
	 * <code>obj === null</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container is NULL container, false otherwise.
	 */
	public boolean isNull()
	{
		return inner instanceof NullValue;
	}
	
	/**
	 * Checks if value at specified position is NULL container. This method works as if invoking it were equivalent to
	 * evaluating the expression: <blockquote><code>get(index).isNull();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is NULL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isNull()
	 */
	public boolean isNull(int index)
	{
		return get(index).isNull();
	}
	
	/**
	 * Checks if value in specified property is NULL container. This method works as if invoking it were equivalent to
	 * evaluating the expression: <blockquote><code>get(property).isNull();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is NULL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isNull()
	 */
	public boolean isNull(String property)
	{
		return get(property).isNull();
	}
	
	/**
	 * Checks if current container is NUMBER LITERAL container.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of expression <blockquote>
	 * <code>typeof obj === "number"</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container is NUMBER LITERAL container, false otherwise.
	 */
	public boolean isNumber()
	{
		return ContainerValue.isNumber(inner);
	}
	
	/**
	 * Checks if value at specified position is NUMBER LITERAL container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>get(index).isNumber();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is NUMBER LITERAL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isNumber()
	 */
	public boolean isNumber(int index)
	{
		return get(index).isNumber();
	}
	
	/**
	 * Checks if value in specified property is NUMBER LITERAL container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>get(property).isNumber();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is NUMBER LITERAL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isNumber()
	 */
	public boolean isNumber(String property)
	{
		return get(property).isNumber();
	}
	
	/**
	 * Checks if current container is STRING LITERAL container.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of expression <blockquote>
	 * <code>typeof obj === "string"</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container is STRING LITERAL container, false otherwise.
	 */
	public boolean isString()
	{
		return ContainerValue.isString(inner);
	}
	
	/**
	 * Checks if value at specified position is STRING LITERAL container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>get(index).isString();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is STRING LITERAL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isString()
	 */
	public boolean isString(int index)
	{
		return get(index).isString();
	}
	
	/**
	 * Checks if value in specified property is STRING LITERAL container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>get(property).isString();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is STRING LITERAL container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isString()
	 */
	public boolean isString(String property)
	{
		return get(property).isString();
	}
	
	/**
	 * Checks if current container is UNDEFINED container.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> can be used as a replacement of expression <blockquote>
	 * <code>obj === undefined</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container is UNDEFINED container, false otherwise.
	 */
	public boolean isUndefined()
	{
		return inner instanceof UndefinedValue;
	}
	
	/**
	 * Checks if value at specified position is UNDEFINED container. This method works as if invoking it were equivalent
	 * to evaluating the expression: <blockquote><code>get(index).isUndefined();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position is UNDEFINED container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #isUndefined()
	 */
	public boolean isUndefined(int index)
	{
		return get(index).isUndefined();
	}
	
	/**
	 * Checks if value in specified property is UNDEFINED container. This method works as if invoking it were equivalent
	 * to evaluating the expression: <blockquote><code>get(property).isUndefined();</code></blockquote>
	 * 
	 * @param property name of the property, which value will be tested.
	 * @return true if value in specified property is UNDEFINED container, false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #isUndefined()
	 */
	public boolean isUndefined(String property)
	{
		return get(property).isUndefined();
	}
	
	/**
	 * Checks if current container can be resolved to true in "if" conditions. Following values will be considered as
	 * false: UNDEFINED, NULL, false, 0, "", null LITERAL, but all other values will be treated as true.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a general way to emulate values checking in "if" conditions in JS. For
	 * example following expression: <blockquote> <code>if (obj) { ... }</code> </blockquote> can be replaced with
	 * something like this: <blockquote> <code>if (obj.test()) { ... }</code> </blockquote>
	 * </p>
	 * 
	 * @return true if current container holds a value, which can be positively resolved in "if" condition; false
	 *         otherwise.
	 */
	public boolean test()
	{
		if (isBoolean())
		{
			return asBoolean();
		}
		else if (isNumber())
		{
			return asInt() != 0;
		}
		else if (isString())
		{
			return !asString().isEmpty();
		}
		else if (inner instanceof PrimitiveLiteralValue)
		{
			return inner.literal != null;
		}
		
		return !isUndefined() && !isNull();
	}
	
	/**
	 * Checks if value at specified position can be resolved to true in "if" conditions. This method works as if
	 * invoking it were equivalent to evaluating the expression: <blockquote>
	 * <code>get(index).test();</code></blockquote>
	 * 
	 * @param index position of value, which will be tested.
	 * @return true if value at specified position can be positively resolved in "if" condition; false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 * @see #test()
	 */
	public boolean test(int index)
	{
		return get(index).test();
	}
	
	/**
	 * Checks if value in specified property can be resolved to true in "if" conditions. In most cases this method works
	 * the same as following expression: <blockquote><code>get(property).test();</code></blockquote>
	 * 
	 * <p>
	 * but it will work differently for built-in properties like "constructor" and "length" - it will check if
	 * {@link #getConstructor()} returns null and if {@link #getLength(Integer)} returns 0 accordingly.
	 * </p>
	 * 
	 * @param property name of the property, which will be tested.
	 * @return true if value in specified property can be positively resolved in "if" condition; false otherwise.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 * @see #test()
	 */
	public boolean test(String property)
	{
		// first check if property name is null and convert it to an empty string
		property = StringUtils.defaultString(property);
		
		// then check for built-in properties
		switch (property)
		{
			case BUILTIN_PROPERTY_CONSTRUCTOR:
				return has(BUILTIN_PROPERTY_CONSTRUCTOR, false) && getConstructor() != null;
			case BUILTIN_PROPERTY_LENGTH:
				return has(BUILTIN_PROPERTY_LENGTH, false) && getLength(0) > 0;
		}
		
		// then do a normal check
		return get(property).test();
	}
	
	// GET METHODS ----------------------------------------------------------------------------------------------------
	
	/**
	 * Reads a value from the current container located at specified index. If current container is a STRING LITERAL it
	 * will return container with a character located at specified position in the string. If index doesn't exist,
	 * UNDEFINED container will be returned. If current container is UNDEFINED or NULL, method will throw
	 * {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method will first scan current container for an index and if nothing will be found it will scan
	 * prototype.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a general way to access values by index, it emulates square brackets in
	 * JS. For example following expression: <blockquote><code>value = obj[index];</code></blockquote> can be replaced
	 * with something like this: <blockquote><code>value = obj.get(index);</code></blockquote>
	 * </p>
	 * 
	 * @param index position in the current container, which will be read.
	 * @return container, which is stored at specified position or UNDEFINED container if nothing was found.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 */
	public UniversalContainer get(int index)
	{
		// for a STRING LITERAL container there is different logic - extract string and read character at specified
		// position
		if (isString())
		{
			String s = asString();
			if (index >= 0 && index < s.length())
			{
				return new UniversalContainer(String.valueOf(s.charAt(index)));
			}
			return ContainerFactory.undefinedContainer();
		}
		
		// for all other containers, just convert int to string and call get(String) method
		return get(indexToProperty(index));
	}
	
	/**
	 * Reads specified property from the current container. If property doesn't exist, UNDEFINED container will be
	 * returned. If current container is UNDEFINED or NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method will first scan current container for a property and if nothing will be found it will
	 * scan prototype.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a general way to access property, it emulates square brackets and dot
	 * notation in JS. For example following expressions: <blockquote><code>value = obj[property];</code></blockquote>
	 * <blockquote><code>value = obj.property;</code></blockquote> can be replaced with something like this:
	 * <blockquote> <code>value = obj.get(property);</code></blockquote>
	 * </p>
	 * 
	 * @param property name of the property in the current container, which will be read.
	 * @return container, which is stored in specified property or UNDEFINED container if nothing was found.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 */
	public UniversalContainer get(String property)
	{
		// first check if property name is null and convert it to an empty string
		property = StringUtils.defaultString(property);
		
		// then validate if property can be read and throw ContainerException if not
		canReadProperty(property, true);
		
		// if current container is a STRING LITERAL and property can be converted to integer call get(int) method
		if (isString() && canConvertPropertyToIndex(property))
		{
			return get(propertyToIndex(property));
		}
		
		// otherwise check if current container is an OBJECT and read property from the internal table
		else if (isObject())
		{
			if (inner.table.containsKey(property))
			{
				return inner.table.get(property);
			}
			else if (proto != null && !proto.isUndefined() && !proto.isNull())
			{
				return proto.get(property);
			}
		}
		
		return ContainerFactory.undefinedContainer();
	}
	
	/**
	 * Reads built-in "constructor" property. If current container is UNDEFINED or NULL, method will throw
	 * {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method will first search current container for a constructor property and if nothing will be
	 * found it will search in prototype and so on.
	 * </p>
	 * 
	 * <p>
	 * <b>Warning:</b> do not use this method, because it's not finalized yet, currently it's only used in
	 * {@link Lodash#isEqual(UniversalContainer, Object)} method.
	 * </p>
	 * 
	 * @return constructor or null if doesn't exist.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             constructor.
	 */
	public ContainerConstructor getConstructor()
	{
		// first validate if property can be read and throw ContainerException if not
		canReadProperty(BUILTIN_PROPERTY_CONSTRUCTOR, true);
		
		// then read constructor and if it's null check prototype's constructor
		if (constructor == null && proto != null && proto.canReadProperty(BUILTIN_PROPERTY_CONSTRUCTOR, false))
		{
			return proto.getConstructor();
		}
		
		return constructor;
	}
	
	/**
	 * Reads built-in "length" property. This method is unsafe, because it can return null value if there is no length
	 * property, so it's better to use method {@link #getLength(Integer)} instead. If current container is UNDEFINED or
	 * NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method will first search current container for a length property and if nothing will be found
	 * it will search in prototype and so on.
	 * </p>
	 * 
	 * @return length or null if doesn't exist.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have length.
	 * @see #getLength(Integer)
	 */
	public Integer getLength()
	{
		return getLength(null);
	}
	
	/**
	 * Reads built-in "length" property. If length doesn't exist defaultValue will be returned instead. If current
	 * container is UNDEFINED or NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method will first search current container for a length property and if nothing will be found
	 * it will search in prototype and so on.
	 * </p>
	 * 
	 * @param defaultLength the value, which will be returned if length doesn't exists.
	 * @return length or <b>defaultValue</b> if doesn't exist.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have length.
	 */
	public Integer getLength(Integer defaultLength)
	{
		// first validate if property can be read and throw ContainerException if not
		canReadProperty(BUILTIN_PROPERTY_LENGTH, true);
		
		// then read length and if it's null check prototype's length
		Integer result = length;
		if (result == null && proto != null && proto.canReadProperty(BUILTIN_PROPERTY_LENGTH, false))
		{
			result = proto.getLength(defaultLength);
		}
		
		return result != null ? result : defaultLength;
	}
	
	/**
	 * Returns current container's prototype.
	 * 
	 * @return prototype container of the current container
	 */
	public UniversalContainer getProto()
	{
		return ContainerFactory.undefinedContainerIfNull(proto);
	}
	
	// SET METHODS ----------------------------------------------------------------------------------------------------
	
	/**
	 * Writes value in the current container under specified index. All raw values will be wrapped into corresponding
	 * containers. If current container is a LITERAL container nothing will happen, value won't be saved. If current
	 * container is UNDEFINED or NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a general way to write values at specified index, it emulates square
	 * brackets in JS. For example following expression: <blockquote><code>obj[index] = value;</code></blockquote> can
	 * be replaced with something like this: <blockquote><code>obj.set(index, value);</code></blockquote>
	 * </p>
	 * 
	 * @param index position in the current container, where object will be stored.
	 * @param value object, which will be stored in the current container under specified position.
	 * @return current container, this can be useful for a piped method call.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have indexes.
	 */
	public UniversalContainer set(int index, Object value)
	{
		// for ARRAY container there is different logic - inrease length property if index exceeds it
		if (isArray())
		{
			if (index >= getLength(0))
			{
				setLength(index + 1);
			}
			inner.table.put(indexToProperty(index), wrapValue(value));
			return this;
		}
		
		// for all other containers, just convert int to string and call get(String) method
		return set(indexToProperty(index), value);
	}
	
	/**
	 * Writes value in the current container under specified property. All raw values will be wrapped into corresponding
	 * containers. If current container is a LITERAL container nothing will happen, value won't be saved. If current
	 * container is UNDEFINED or NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a general way to write value under specified property, it emulates square
	 * brackets and dot notation in JS. For example following expressions: <blockquote>
	 * <code>obj[property] = value;</code></blockquote> <blockquote><code>obj.property = value;</code></blockquote> can
	 * be replaced with something like this: <blockquote> <code>obj.set(property, value);</code></blockquote>
	 * </p>
	 * 
	 * @param property name of the property, under which value will be stored.
	 * @param value object, which will be stored in the current container.
	 * @return current container, this can be useful for a piped method call.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have
	 *             properties.
	 */
	public UniversalContainer set(String property, Object value)
	{
		// first check if property name is null and convert it to an empty string
		property = StringUtils.defaultString(property);
		
		// then validate if property can be written and throw ContainerException if not
		canWriteProperty(property, true);
		
		// if current container is ARRAY and property can be converted to integer call set(int) method
		if (isArray() && canConvertPropertyToIndex(property))
		{
			return set(propertyToIndex(property), value);
		}
		
		// otherwise check if current container is an OBJECT and save property to the internal table
		else if (isObject())
		{
			inner.table.put(property, wrapValue(value));
		}
		
		return this;
	}
	
	/**
	 * Overrides built-in "length" property. If new length is less than current one and current container is ARRAY, then
	 * all values outside of the array boundaries will be deleted. If value less than 0 method will throw
	 * {@link ContainerException}. The same exception will be thrown if current container is UNDEFINED or NULL.
	 * 
	 * @param newLength non-negative integer value, which will override current length.
	 * @throws ContainerException if current container is UNDEFINED or NULL, since these containers can't have length.
	 */
	public void setLength(int newLength)
	{
		// first validate if "length" property can be written and throw ContainerException if not
		canWriteProperty(BUILTIN_PROPERTY_LENGTH, true);
		
		// then validate if new length is equal or greater than 0 and throw ContainerException if not
		if (newLength < 0) throw new ContainerException("Cannot set negative length");
		
		// then check if current container is ARRAY and new length is less than current one - delete all values which
		// will be outscoped in this case
		if (isArray() && length != null && length > newLength)
		{
			for (int i = newLength; i < length; i++)
			{
				delete(i);
			}
		}
		
		length = newLength;
	}
	
	// STANDARD JS METHODS --------------------------------------------------------------------------------------------
	
	/**
	 * This method has two different algorithms depending on the current container type:
	 * 
	 * <p>
	 * 1) If current container is a STRING LITERAL, then this method will convert all sources to a string and will
	 * produce new STRING LITERAL container consisted of all strings appended one by one. This is a precise
	 * implementation of JS method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/concat">String.
	 * prototype.concat()</a>.
	 * </p>
	 * <p>
	 * 2) If current container is an ARRAY, then this method will create new ARRAY container, will add all values of the
	 * current container to the new one and will join all the sources one by one. Depending on the source value
	 * different logic will be applied during concatenation. If source is an ARRAY container or equivalent raw value,
	 * then its own values will be added instead of source itself, otherwise source will be added as a value to the end
	 * of new ARRAY container. This is a precise implementation of JS method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/concat">Array.
	 * prototype.concat()</a>.
	 * </p>
	 * <p>
	 * 3) For all other containers this method will throw {@link ContainerException} with a description that this method
	 * is not supported.
	 * </p>
	 * 
	 * <p>
	 * <b>Note:</b> concatenating array(s)/value(s) will leave the originals untouched. Furthermore, any operation on
	 * the new ARRAY container (only if the element is not object reference) will have no effect on the original
	 * array(s)/value(s), and vice versa.
	 * </p>
	 * 
	 * @param sources list of values, that should be joined with the current container, all raw values will be wrapped
	 *            into corresponding containers.
	 * @return new STRING LITERAL or ARRAY container (type will be same as a current container) with the joined values.
	 * @throws ContainerException if current container is not STRING LITERAL or ARRAY container.
	 */
	public UniversalContainer concat(Object... sources)
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("concat", true);
		
		// then depending on the container type call either concatString or concatArray method
		if (isString())
		{
			return concatString(sources);
		}
		else
		{
			return concatArray(sources);
		}
	}
	
	private UniversalContainer concatString(Object... sources)
	{
		StringBuilder sb = new StringBuilder(asString());
		
		if (sources != null)
		{
			for (Object source : sources)
			{
				// we should use toString instead of asString here, because NULL and UNDEFINED should be converted to
				// "null" and "undefined" strings
				sb.append(wrapValue(source).toString());
			}
		}
		
		return new UniversalContainer(sb.toString());
	}
	
	private UniversalContainer concatArray(Object... sources)
	{
		UniversalContainer result = ContainerFactory.createArray();
		
		// first append all values from the current ARRAY container
		int index = concatArrayAppend(this, result, 0);
		
		if (sources != null)
		{
			for (Object source : sources)
			{
				// then add all other values one by one, if source is array add it's values istead
				index = concatArrayAppend(wrapValue(source), result, index);
			}
		}
		
		return result;
	}
	
	private static int concatArrayAppend(UniversalContainer value, UniversalContainer result, int index)
	{
		if (value.isArray())
		{
			for (UniversalContainer v : value)
			{
				result.set(index++, v);
			}
		}
		else
		{
			result.set(index++, value);
		}
		
		return index;
	}
	
	/**
	 * Creates new OBJECT container, with the current container as a prototype. Method will work only for ARRAY, OBJECT
	 * and NULL containers and for all others it will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method emulates static javascript method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/create">Object.
	 * create()</a>. It's not a precise implementation since current method is not static and parameter propertiesObject
	 * is missing. As an example following expression: <blockquote><code>Object.create(obj);</code></blockquote> can be
	 * replaced with something like this: <blockquote><code>obj.create();</code></blockquote>
	 * </p>
	 * 
	 * @return new OBJECT container with the current container as a prototype.
	 * @throws ContainerException if current container is not ARRAY, OBJECT or NULL container.
	 */
	public UniversalContainer create()
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("create", true);
		
		// then create new OBJECT container with empty constructor and current container as a prototype
		UniversalContainer oc = ContainerFactory.createObject();
		oc.constructor = null;
		oc.proto = this;
		
		return oc;
	}
	
	/**
	 * Removes an index from the current container. If there is no such index nothing will happen. If current container
	 * is UNDEFINED or NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method mutates current OBJECT or ARRAY container.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method emulates javascript
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/delete">delete operator</a>.
	 * As an example following expression: <blockquote><code>delete obj[index];</code></blockquote> can be replaced with
	 * something like this: <blockquote><code>obj.delete(index);</code></blockquote>
	 * </p>
	 * 
	 * @param index position in the container, which will be removed.
	 * @throws ContainerException if current container is not ARRAY or OBJECT container.
	 */
	public void delete(int index)
	{
		delete(indexToProperty(index));
	}
	
	/**
	 * Removes a property from the current container. If there is no such property nothing will happen. If current
	 * container is UNDEFINED or NULL, method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method mutates current OBJECT or ARRAY container.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method emulates javascript
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/delete">delete operator</a>.
	 * As an example following expressions: <blockquote><code>delete obj[property];</code></blockquote><blockquote>
	 * <code>delete obj.property;</code></blockquote> can be replaced with something like this: <blockquote>
	 * <code>obj.delete(property);</code></blockquote>
	 * </p>
	 * 
	 * @param property name of the property in the container, which will be removed.
	 * @throws ContainerException if current container is not ARRAY or OBJECT container.
	 */
	public void delete(String property)
	{
		// first check if property name is null and convert it to an empty string
		property = StringUtils.defaultString(property);
		
		// then we need to check if it is possible to access property
		canReadProperty(property, true);
		
		// then remove it from the inner table
		if (isObject())
		{
			inner.table.remove(property);
		}
	}
	
	/**
	 * Checks if specified index exists in the current container. This method works as if invoking it were equivalent to
	 * evaluating the expression: <blockquote><code>has(index, true);</code></blockquote>
	 * 
	 * @param index position of value, which will be checked.
	 * @return true if specified index exists in the current containter; false otherwise.
	 * @see #has(int, boolean)
	 */
	public boolean has(int index)
	{
		return has(index, true);
	}
	
	/**
	 * Checks if specified property exists in the current container. This method works as if invoking it were equivalent
	 * to evaluating the expression: <blockquote><code>has(property, true);</code></blockquote>
	 * 
	 * @param property name of the property, which will be checked.
	 * @return true if specified property exists in the current containter; false otherwise.
	 * @see #has(String, boolean)
	 */
	public boolean has(String property)
	{
		return has(property, true);
	}
	
	/**
	 * Checks if specified index exists in the current container. If own parameter is true method will scan only current
	 * container for an index, otherwise it will also search among inherited indexes from the prototype. If current
	 * container is a STRING LITERAL this method will return true if index is equal or greater than 0 and less than
	 * string length.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a wild combination of javascript standard method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/hasOwnProperty">
	 * Object.prototype.hasOwnProperty()</a> and two Lodash's methods <a href="https://lodash.com/docs#has">has()</a>
	 * and <a href="https://lodash.com/docs#hasIn">hasIn()</a>. As an example following expressions: <blockquote>
	 * <code>obj.hasOwnProperty(index);</code></blockquote><blockquote><code>_.has(obj, index);</code></blockquote> can
	 * be replaced with something like this: <blockquote><code>obj.has(index, true);</code></blockquote> And following
	 * expression: <blockquote> <code>_.hasIn(obj, index);</code></blockquote> can be replaced with something like this:
	 * <blockquote><code>obj.has(index, false);</code></blockquote>
	 * </p>
	 * 
	 * @param index position of value, which will be checked.
	 * @param own if true method will scan only current container for an index; otherwise it will also search among
	 *            inherited indexes from the prototype.
	 * @return true if index exists; false otherwise.
	 */
	public boolean has(int index, boolean own)
	{
		// if current container is a STRING LITERAL just check if index inside the string length boundaries
		if (isString())
		{
			return index >= 0 && index < getLength(0);
		}
		
		// otherwise call has(String, boolean) method
		else
		{
			return has(indexToProperty(index), own);
		}
	}
	
	/**
	 * Checks if specified property exists in the current container. If own parameter is true method will scan only
	 * current container for a property, otherwise it will also search among inherited properties from the prototype.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a wild combination of javascript standard method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/hasOwnProperty">
	 * Object.prototype.hasOwnProperty()</a> and two Lodash's methods <a href="https://lodash.com/docs#has">has()</a>
	 * and <a href="https://lodash.com/docs#hasIn">hasIn()</a>. As an example following expressions: <blockquote>
	 * <code>obj.hasOwnProperty(property);</code></blockquote><blockquote>
	 * <code>_.has(obj, property);</code></blockquote> can be replaced with something like this: <blockquote>
	 * <code>obj.has(property, true);</code></blockquote> And following expression: <blockquote>
	 * <code>_.hasIn(obj, property);</code></blockquote> can be replaced with something like this: <blockquote>
	 * <code>obj.has(property, false);</code></blockquote>
	 * </p>
	 * 
	 * @param property name of the property, which will be checked.
	 * @param own if true method will scan only current container for a property; otherwise it will also search among
	 *            inherited properties from the prototype.
	 * @return true if property exists; false otherwise.
	 */
	public boolean has(String property, boolean own)
	{
		// first check if property name is null and convert it to an empty string
		property = StringUtils.defaultString(property);
		
		// then if current container is a STRING LITERAL and property can be converted to index call has(int, boolean)
		// method
		if (isString() && canConvertPropertyToIndex(property))
		{
			return has(propertyToIndex(property), own);
		}
		
		// otherwise check if current container has own specified property
		else if (inner.isPropertyExists(property))
		{
			return true;
		}
		
		// if not call this method for prototype
		else if (!own && proto != null)
		{
			return proto.has(property, own);
		}
		
		return false;
	}
	
	/**
	 * This method has two different algorithms depending on the current container type:
	 * 
	 * <p>
	 * 1) If current container is a STRING LITERAL, then method will convert given value to a STRING LITERAL and will
	 * return the index in the current string of the first occurence of the specified substring. If substring won't be
	 * found, then method will return -1. This is a precise implementation of JS method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/indexOf">String.
	 * prototype.indexOf()</a>, except parameter fromIndex isn't implemented. To find substring method checks not only
	 * current container, but the whole prototype chain.
	 * </p>
	 * <p>
	 * 2) If current container is an ARRAY, then method will return first index at which a given value can be found in
	 * the ARRAY container. For comparison method {@link #equals(Object)} will be used. If value won't be found, then
	 * method will return -1. This is almost a precise implementation of JS method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/indexOf">Array.
	 * prototype.indexOf()</a>, except parameter fromIndex isn't implemented. To find the value method checks not only
	 * current container, but the whole prototype chain.
	 * </p>
	 * <p>
	 * 3) For all other containers this method will throw {@link ContainerException} with a description that this method
	 * is not supported.
	 * </p>
	 * 
	 * @param value any value that will be searched in the container.
	 * @return integer that represents index number in container or -1 if nothing is found.
	 * @throws ContainerException if current container is not STRING LITERAL or ARRAY container.
	 * @see #equals(Object)
	 */
	public int indexOf(Object value)
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("indexOf", true);
		
		// then depending on the container type call either indexOfString or indexOfArray method
		if (isString())
		{
			return indexOfString(value);
		}
		else
		{
			return indexOfArray(value);
		}
	}
	
	private int indexOfString(Object value)
	{
		if (value == null) return -1;
		
		return asString().indexOf(value.toString());
	}
	
	private int indexOfArray(Object value)
	{
		int length = getLength(0);
		
		for (int i = 0; i < length; i++)
		{
			if (has(i, false) && get(i).equals(value))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Returns a set of all own indexes/properties of the current container. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>keys(true);</code></blockquote>
	 * 
	 * @return unmodifiable set of all own container indexes/properties or empty set if there are no any own
	 *         indexes/properties.
	 * @see #keys(boolean)
	 */
	public Set<String> keys()
	{
		return keys(true);
	}
	
	/**
	 * Returns a set of all indexes/properties of the current container. If <b>own</b> parameter is false, all own and
	 * all inherited indexes/properties will be returned.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this method is a wild combination of javascript standard method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/keys"> Object.
	 * keys()</a> and Lodash's methods <a href="https://lodash.com/docs#keys">keys()</a> and
	 * <a href="https://lodash.com/docs#keysIn">keysIn()</a>. As an example following expressions: <blockquote>
	 * <code>Object.keys(obj);</code></blockquote><blockquote> <code>_.keys(obj);</code></blockquote> can be replaced
	 * with something like this: <blockquote> <code>obj.keys(true);</code></blockquote> And following expression:
	 * <blockquote> <code>_.keysIn(obj);</code></blockquote> can be replaced with something like this: <blockquote>
	 * <code>obj.keys(false);</code></blockquote>
	 * </p>
	 * 
	 * @param own if true only own indexes/properties will be returned, otherwise all indexes/properties will be
	 *            returned, including inhereted ones.
	 * @return unmodifiable set of all container indexes/properties (if <b>own</b> parameter is false, all inherited
	 *         indexes/properties also will be returned); if there are no any indexes/properties, empty set will be
	 *         returned.
	 */
	public Set<String> keys(boolean own)
	{
		// if current container is a STRING LITERAL just return a set with all indexes of each character
		if (isString())
		{
			Set<String> keys = new HashSet<String>();
			int length = getLength(0);
			for (int i = 0; i < length; i++)
			{
				keys.add(indexToProperty(i));
			}
			
			return Collections.unmodifiableSet(keys);
		}
		
		// if current container is an OBJECT, then check if own parameter is false and prototype isn't null and return
		// whole set of all properties, otherwise just return own properties
		else if (isObject() && !isNull())
		{
			if (own || (!own && proto == null))
			{
				return Collections.unmodifiableSet(inner.table.keySet());
			}
			else
			{
				Set<String> keys = new HashSet<String>(inner.table.keySet());
				keys.addAll(proto.keys(own));
				
				return Collections.unmodifiableSet(keys);
			}
		}
		
		return Collections.emptySet();
	}
	
	/**
	 * Removes the last value from the current container and returns removed value. This method will work only for ARRAY
	 * containers or containers, which have an ARRAY in their prototype chain, for all other containers this method will
	 * throw {@link ContainerException}. To get the value method checks not only current container, but the whole
	 * prototype chain.
	 * 
	 * <p>
	 * <b>Note:</b> this method mutates current container.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this is a precise implementation of javascript method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/pop">Array.
	 * prototype.pop()</a>.
	 * </p>
	 * 
	 * @return value, which is the last in the array; UNDEFINED container will be returned if current container is
	 *         empty.
	 * @throws ContainerException if current container is not ARRAY container or container, which doesn't have ARRAY in
	 *             its prototype chain.
	 * @see #push(Object...)
	 */
	public UniversalContainer pop()
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("pop", true);
		
		// get current length value
		int length = getLength(0);
		
		// set length, this will freeze current container if it was depended on the prototypes's length property
		setLength(length);
		
		// if array is not empty remove last value and return it
		if (length > 0)
		{
			UniversalContainer result = get(length - 1);
			inner.table.remove(indexToProperty(length - 1));
			dereaseLength();
			return result;
		}
		
		return ContainerFactory.undefinedContainer();
	}
	
	/**
	 * Adds one or more values to the end of the current ARRAY container and returns new length. This method will work
	 * only for ARRAY containers or containers, which have an ARRAY in their prototype chain, for all other containers
	 * this method will throw {@link ContainerException}.
	 * 
	 * <p>
	 * <b>Note:</b> this method mutates current container.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this is a precise implementation of javascript method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/push">Array.
	 * prototype.push()</a>.
	 * </p>
	 * 
	 * @param values variable number values that will be added to the end of the current ARRAY container, can be any
	 *            object (raw values, other containers etc).
	 * @return new length of the current container.
	 * @throws ContainerException if current container is not ARRAY container or container, which doesn't have ARRAY in
	 *             its prototype chain.
	 * @see #pop()
	 */
	public int push(Object... values)
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("push", true);
		
		// then check if values were passed to the method and immidiately exit if not
		if (values == null) return getLength(0);
		
		// get current length value
		int length = getLength(0);
		
		// set length, this will freeze current container if it was depended on the prototypes's length property
		setLength(length);
		
		// add all passed values one by one
		for (Object v : values)
		{
			inner.table.put(indexToProperty(length), wrapValue(v));
			increaseLength();
		}
		
		return getLength(0);
	}
	
	/**
	 * Removes the element at the zeroeth index and shifts the values at consecutive indexes down, then returns the
	 * removed value. If the length property is 0, UNDEFINED container is returned. This method will work only for ARRAY
	 * containers or containers, which have an ARRAY in their prototype chain, for all other containers this method will
	 * throw {@link ContainerException}. To get the value method checks not only current container, but the whole
	 * prototype chain.
	 * 
	 * <p>
	 * <b>Note:</b> this method mutates current container.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this is a precise implementation of javascript method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/shift">Array.
	 * prototype.shift()</a>.
	 * </p>
	 * 
	 * @return container, which was the first in current ARRAY container or will return UNDEFINED container if current
	 *         container is empty.
	 * @throws ContainerException if current container is not ARRAY container or container, which doesn't have ARRAY in
	 *             its prototype chain.
	 */
	public UniversalContainer shift()
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("shift", true);
		
		// get current length value
		int length = getLength(0);
		
		// set length, this will freeze current container if it was depended on the prototypes's length property
		setLength(length);
		
		// remove first value, descrease indexes of all values by one and return removed value
		if (length > 0)
		{
			UniversalContainer result = get(0);
			for (int i = 0; i < length - 1; i++)
			{
				if (has(i + 1, false))
				{
					set(i, get(i + 1));
				}
				else
				{
					delete(i);
				}
			}
			inner.table.remove(indexToProperty(length - 1));
			dereaseLength();
			
			return result;
		}
		
		return ContainerFactory.undefinedContainer();
	}
	
	/**
	 * Changes the content of th current ARRAY container by removing existing elements using <b>start</b> and
	 * <b>deleteCount</b> parameters. This method will work only for ARRAY containers or containers, which have an ARRAY
	 * in their prototype chain, for all other containers this method will throw {@link ContainerException}. To get the
	 * values method checks not only current container, but the whole prototype chain.
	 * 
	 * <p>
	 * <b>Note:</b> this method mutates current container.
	 * </p>
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this is almost a precise implementation of javascript method
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/splice">Array.
	 * prototype.splice()</a>. The only difference is that current implementation doesn't add new elements.
	 * </p>
	 * 
	 * @param start index at which to start changing the ARRAY container. If greater than the length property, actual
	 *            starting index will be set to the length of the ARRAY container. If negative, will begin that many
	 *            elements from the end.
	 * @param deleteCount an integer indicating the number of elements to remove from current ARRAY container.
	 * @return an ARRAY container with deleted elements.
	 * @throws ContainerException if current container is not ARRAY container or container, which doesn't have ARRAY in
	 *             its prototype chain.
	 */
	public UniversalContainer splice(int start, int deleteCount)
	{
		// first validate if method can be invoked and throw ContainerException if not
		canInvokeMethod("splice", true);
		
		// get current length value and create empty result
		int length = getLength(0);
		UniversalContainer result = ContainerFactory.createArray();
		
		// set length, this will freeze current container if it was depended on the prototypes's length property
		setLength(length);
		
		// remove all values that suit and return array with deleted values
		if (length > 0)
		{
			start = (start < 0 ? length + start : start);
			start = (start < 0 ? 0 : start);
			for (int i = start, j = 0; i < length; i++)
			{
				String property = indexToProperty(i);
				if (i < start + deleteCount)
				{
					result.set(j++, get(property));
					inner.table.remove(property);
					dereaseLength();
				}
				else if (i < length && deleteCount > 0)
				{
					set(indexToProperty(i - deleteCount), get(property));
					inner.table.remove(property);
				}
			}
		}
		
		return result;
	}
	
	// LODASH SHORTCUT METHODS ----------------------------------------------------------------------------------------
	
	/**
	 * A shortcut to the {@link Lodash#clone(UniversalContainer)} method. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>Lodash.clone(this);</code></blockquote>
	 * 
	 * @return cloned container.
	 */
	public UniversalContainer clone()
	{
		return Lodash.clone(this);
	}
	
	/**
	 * A shortcut to the {@link Lodash#isEqual(UniversalContainer, Object)} method. This method works as if invoking it
	 * were equivalent to evaluating the expression: <blockquote><code>Lodash.isEqual(this, other);</code></blockquote>
	 * 
	 * @return true if the current container and other value are equivalent; false otherwise.
	 */
	public boolean deepEquals(Object other)
	{
		return Lodash.isEqual(this, other);
	}
	
	/**
	 * A shortcut to the {@link Lodash#extend(UniversalContainer, UniversalContainer...)} method. This method works as
	 * if invoking it were equivalent to evaluating the expression: <blockquote>
	 * <code>Lodash.extend(this, sources);</code></blockquote>
	 * 
	 * <p>
	 * Note: This method mutates destination OBJECT container.
	 * </p>
	 * 
	 * @param sources containers, which will override properties in the destination container.
	 * @return current container with the overriden properties.
	 */
	public UniversalContainer extend(UniversalContainer... sources)
	{
		return Lodash.extend(this, sources);
	}
	
	/**
	 * A shortcut to the {@link Lodash#last(UniversalContainer)} method. This method works as if invoking it were
	 * equivalent to evaluating the expression: <blockquote><code>Lodash.last(this);</code></blockquote>
	 * 
	 * @return the last element of current container.
	 */
	public UniversalContainer last()
	{
		return Lodash.last(this);
	}
	
	/**
	 * A shortcut to the {@link Lodash#merge(UniversalContainer, UniversalContainer...)} method. This method works as if
	 * invoking it were equivalent to evaluating the expression: <blockquote>
	 * <code>Lodash.merge(this, sources);</code></blockquote>
	 * 
	 * <p>
	 * Note: This method mutates destination OBJECT container.
	 * </p>
	 * 
	 * @param sources containers, which properties will be merged into the destination container.
	 * @return current container with the merged properties.
	 */
	public UniversalContainer merge(UniversalContainer... sources)
	{
		return Lodash.merge(this, sources);
	}
	
	/**
	 * A shortcut to the {@link Lodash#values(UniversalContainer)} method. This method works as if
	 * invoking it were equivalent to evaluating the expression: <blockquote>
	 * <code>Lodash.values(this);</code></blockquote>
	 * 
	 * @return ARRAY container of property values.
	 */
	public UniversalContainer values()
	{
		return Lodash.values(this);
	}
	
	// OVERRIDED METHODS ----------------------------------------------------------------------------------------------
	
	/**
	 * Compares current container and passed value and returns true if they equal.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> this is a precise implementation of javascript strict equality. Loose equality usualy not
	 * in use and won't be implemented.
	 * </p>
	 * 
	 * @param other any value, that will be compared with the current container.
	 * @return true if current container and passed value equal; false otherwise.
	 * @see #deepEquals(Object)
	 */
	@Override
	public boolean equals(Object other)
	{
		if (inner instanceof PrimitiveLiteralValue && inner.literal == null && other == null) return true;
		if (other == null) return isUndefined();
		
		ContainerValue a = inner;
		ContainerValue b = null;
		
		if (other instanceof ContainerValue)
		{
			b = (ContainerValue) other;
		}
		else if (other instanceof UniversalContainer)
		{
			b = ((UniversalContainer) other).inner;
		}
		
		if (b != null)
		{
			if (a instanceof UndefinedValue && b instanceof UndefinedValue) return true;
			if (a instanceof NullValue && b instanceof NullValue) return true;
			if (ContainerValue.isNumber(a) && ContainerValue.isNumber(b) && ContainerValue.asNumber(
				a).doubleValue() == ContainerValue.asNumber(b).doubleValue()) return true;
			if (a instanceof PrimitiveLiteralValue && a.getClassName() == b.getClassName() && ((a.literal == null &&
				b.literal == null) || (a.literal != null && b.literal != null && a.literal.equals(b.literal))))
				return true;
			if (a == b) return true;
			
			return false;
		}
		
		if (a instanceof PrimitiveLiteralValue)
		{
			if (ContainerValue.isNumber(a) && ContainerValue.isNumber(other))
			{
				return ContainerValue.asNumber(a).doubleValue() == ContainerValue.asNumber(other).doubleValue();
			}
			return a.literal != null && a.literal.equals(other);
		}
		
		return false;
	}
	
	/**
	 * Creates iterator over current container values (if it's ARRAY or STRING) or keys (if OBJECT), which can be in for
	 * statement. Works on STRING, ARRAY and OBJECT containers and for all others it will return empty iterator. To get
	 * the values method checks not only current container, but the whole prototype chain. Returned iterator doesn't
	 * support remove method.
	 * 
	 * <p>
	 * <b>Javascript Note:</b> created to mimic javascript for, for in and each methods.
	 * </p>
	 * 
	 * @return iterator over current container values.
	 * @see #keys(boolean)
	 */
	@Override
	public Iterator<UniversalContainer> iterator()
	{
		if (has(BUILTIN_PROPERTY_LENGTH, false))
		{
			return new Iterator<UniversalContainer>()
				{
					private int index = 0;
					
					@Override
					public boolean hasNext()
					{
						return index < getLength(0);
					}
					
					@Override
					public UniversalContainer next()
					{
						if (has(index, false))
						{
							return get(index++);
						}
						else
						{
							index++;
							return ContainerFactory.undefinedContainer();
						}
					}
					
					@Override
					public void remove()
					{
						throw new UnsupportedOperationException();
					}
				};
		}
		
		if (isObject())
		{
			return new Iterator<UniversalContainer>()
				{
					private Iterator<String> iter = keys(false).iterator();
					
					@Override
					public boolean hasNext()
					{
						return iter.hasNext();
					}
					
					@Override
					public UniversalContainer next()
					{
						return wrapValue(iter.next());
					}
					
					@Override
					public void remove()
					{
						throw new UnsupportedOperationException();
					}
				};
		}
		
		return new Iterator<UniversalContainer>()
			{
				@Override
				public boolean hasNext()
				{
					return false;
				}
				
				@Override
				public UniversalContainer next()
				{
					return ContainerFactory.undefinedContainer();
				}
				
				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}
	
	/**
	 * Returns a string representation of the current container. It works almost the same as {@link #asString()} method,
	 * the only difference is that current method will return "undefined" and "null" strings for UNDEFINED and NULL
	 * containers accordingly.
	 * 
	 * @return string representation of current container.
	 * @see #asString()
	 */
	@Override
	public String toString()
	{
		if (isUndefined() || isNull()) return inner.toString();
		return asString();
	}
	
	// PROTECTED UTILITY METHODS --------------------------------------------------------------------------------------
	
	protected boolean canReadProperty(int index, boolean throwError)
	{
		return canReadProperty(indexToProperty(index), throwError);
	}
	
	protected boolean canReadProperty(String property, boolean throwError)
	{
		property = StringUtils.defaultString(property);
		
		if (isUndefined() || isNull())
		{
			if (throwError) throw new ContainerException(
				"Cannot get '" + property + "' property from " + inner.getClassName() + " container");
			return false;
		}
		
		return true;
	}
	
	protected boolean canWriteProperty(int index, boolean throwError)
	{
		return canWriteProperty(indexToProperty(index), throwError);
	}
	
	protected boolean canWriteProperty(String property, boolean throwError)
	{
		property = StringUtils.defaultString(property);
		
		if (isUndefined() || isNull())
		{
			if (throwError) throw new ContainerException(
				"Cannot set '" + property + "' property in " + inner.getClassName() + " container");
			return false;
		}
		
		return true;
	}
	
	protected boolean canInvokeMethod(String method, boolean throwError)
	{
		method = StringUtils.defaultString(method);
		
		if (!inner.isMethodSupported(method) && (proto == null || (proto != null && !proto.canInvokeMethod(method,
			false))))
		{
			if (throwError) throw new ContainerException(
				"Method '" + method + "' is not available for " + inner.getClassName() + " container");
			return false;
		}
		
		return true;
	}
	
	protected void dereaseLength()
	{
		if (length == null) throw new ContainerException("Cannot decrease length, because it doesn't exist");
		length--;
	}
	
	protected void increaseLength()
	{
		if (length == null) throw new ContainerException("Cannot increase length, because it doesn't exist");
		length++;
	}
	
	protected boolean canConvertPropertyToIndex(String property)
	{
		property = StringUtils.defaultString(property);
		
		return NumberUtils.isParsable(property);
	}
	
	protected String indexToProperty(int index)
	{
		return String.valueOf(index);
	}
	
	protected int propertyToIndex(String property)
	{
		property = StringUtils.defaultString(property);
		
		try
		{
			return Integer.parseInt(property);
		}
		catch (NumberFormatException e)
		{
			throw new ContainerException("Property '" + property + "' cannot be converted to index");
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static <T> T castValue(UniversalContainer value, Class<T> clazz)
	{
		if (value == null || clazz == null) return null;
		
		if (clazz.isAssignableFrom(Boolean.class))
		{
			return (T) Boolean.valueOf(value.asBoolean());
		}
		else if (clazz.isAssignableFrom(Double.class))
		{
			return (T) Double.valueOf(value.asDouble());
		}
		else if (clazz.isAssignableFrom(Integer.class))
		{
			return (T) Integer.valueOf(value.asInt());
		}
		else if (clazz.isAssignableFrom(String.class))
		{
			return (T) value.asString();
		}
		else
		{
			return (T) value.valueOf();
		}
	}
	
	protected void setArray()
	{
		constructor = ContainerConstructor.ARRAY;
		inner = new ArrayValue();
		setLength(0);
	}
	
	protected void setArray(Object[] array)
	{
		setArray();
		int i = 0;
		for (Object v : array)
		{
			set(i++, wrapValue(v));
		}
	}
	
	protected void setArray(Iterable<Object> list)
	{
		setArray();
		int i = 0;
		for (Object v : list)
		{
			set(i++, wrapValue(v));
		}
	}
	
	protected void setLiteral(Object literal)
	{
		constructor = ContainerConstructor.LITERAL;
		inner = new PrimitiveLiteralValue(literal);
		if (isString()) setLength(asString().length());
	}
	
	protected void setNull()
	{
		constructor = ContainerConstructor.NULL;
		inner = new NullValue();
	}
	
	protected void setObject()
	{
		constructor = ContainerConstructor.OBJECT;
		inner = new ObjectValue();
	}
	
	protected void setObject(Map<Object, Object> map)
	{
		setObject();
		for (Object k : map.keySet())
		{
			set(k.toString(), wrapValue(map.get(k)));
		}
	}
	
	protected void setUndefined()
	{
		constructor = ContainerConstructor.UNDEFINED;
		inner = new UndefinedValue();
	}
	
	protected static UniversalContainer wrapValue(Object value)
	{
		return wrapValue(null, value);
	}
	
	protected static UniversalContainer wrapValue(ContainerValue value)
	{
		return wrapValue(null, value);
	}
	
	protected static UniversalContainer wrapValue(UniversalContainer container)
	{
		return wrapValue(null, container);
	}
	
	protected static UniversalContainer wrapValue(Object[] array)
	{
		return wrapValue(null, array);
	}
	
	protected static UniversalContainer wrapValue(List<Object> list)
	{
		return wrapValue(null, list);
	}
	
	protected static UniversalContainer wrapValue(Map<Object, Object> map)
	{
		return wrapValue(null, map);
	}
	
	@SuppressWarnings("unchecked")
	protected static UniversalContainer wrapValue(UniversalContainer obj, Object value)
	{
		if (obj == null && value == null) return ContainerFactory.undefinedContainer();
		
		UniversalContainer wrap;
		
		if (value instanceof ContainerValue)
		{
			wrap = wrapValue(obj, (ContainerValue) value);
		}
		else if (value instanceof UniversalContainer)
		{
			wrap = wrapValue(obj, (UniversalContainer) value);
		}
		else if (value instanceof Object[])
		{
			wrap = wrapValue(obj, (Object[]) value);
		}
		else if (value instanceof Iterable)
		{
			wrap = wrapValue(obj, (Iterable<Object>) value);
		}
		else if (value instanceof Map)
		{
			wrap = wrapValue(obj, (Map<Object, Object>) value);
		}
		else
		{
			wrap = obj != null ? obj : new UniversalContainer();
			wrap.setLiteral(value);
		}
		
		return wrap;
	}
	
	protected static UniversalContainer wrapValue(UniversalContainer obj, ContainerValue value)
	{
		if (obj == null && value == null) return ContainerFactory.undefinedContainer();
		
		UniversalContainer wrap = obj != null ? obj : new UniversalContainer();
		wrap.inner = value;
		
		return wrap;
	}
	
	protected static UniversalContainer wrapValue(UniversalContainer obj, UniversalContainer container)
	{
		if (obj == null && container == null) return ContainerFactory.undefinedContainer();
		if (obj == null && container != null) return container;
		if (obj != null && container == null) return obj;
		
		obj.inner = container.inner;
		obj.proto = container.proto;
		obj.constructor = container.constructor;
		obj.length = container.length;
		
		return obj;
	}
	
	protected static UniversalContainer wrapValue(UniversalContainer obj, Object[] array)
	{
		if (obj == null && array == null) return ContainerFactory.undefinedContainer();
		
		UniversalContainer wrap = obj != null ? obj : new UniversalContainer();
		wrap.setArray(array);
		
		return wrap;
	}
	
	protected static UniversalContainer wrapValue(UniversalContainer obj, Iterable<Object> list)
	{
		if (obj == null && list == null) return ContainerFactory.undefinedContainer();
		
		UniversalContainer wrap = obj != null ? obj : new UniversalContainer();
		wrap.setArray(list);
		
		return wrap;
	}
	
	protected static UniversalContainer wrapValue(UniversalContainer obj, Map<Object, Object> map)
	{
		if (obj == null && map == null) return ContainerFactory.undefinedContainer();
		
		UniversalContainer wrap = obj != null ? obj : new UniversalContainer();
		wrap.setObject(map);
		
		return wrap;
	}
	
	// INNER CLASSES --------------------------------------------------------------------------------------------------
	
	protected static abstract class ContainerValue
	{
		public Object literal = null;
		public Map<String, UniversalContainer> table = null;
		public Set<String> builtinProperties = new HashSet<String>();
		public Set<String> supportedMethods = new HashSet<String>();
		
		public abstract String getClassName();
		
		public abstract String getType();
		
		public static boolean isString(Object value)
		{
			return value instanceof String || (value instanceof PrimitiveLiteralValue &&
				((PrimitiveLiteralValue) value).literal instanceof String) || (value instanceof UniversalContainer &&
					((UniversalContainer) value).inner.literal instanceof String);
		}
		
		public static boolean isNumber(Object value)
		{
			return value instanceof Number || (value instanceof PrimitiveLiteralValue &&
				((PrimitiveLiteralValue) value).literal instanceof Number) || (value instanceof UniversalContainer &&
					((UniversalContainer) value).inner.literal instanceof Number);
		}
		
		public static boolean isBoolean(Object value)
		{
			return value instanceof Boolean || (value instanceof PrimitiveLiteralValue &&
				((PrimitiveLiteralValue) value).literal instanceof Boolean) || (value instanceof UniversalContainer &&
					((UniversalContainer) value).inner.literal instanceof Boolean);
		}
		
		public static boolean asBoolean(Object value)
		{
			if (isBoolean(value))
			{
				if (value instanceof Boolean)
					return (Boolean) value;
				else if (value instanceof PrimitiveLiteralValue &&
					((PrimitiveLiteralValue) value).literal instanceof Boolean)
					return (Boolean) ((PrimitiveLiteralValue) value).literal;
				else if (value instanceof UniversalContainer &&
					((UniversalContainer) value).inner.literal instanceof Boolean)
					return (Boolean) ((UniversalContainer) value).inner.literal;
			}
			else if (isNumber(value) || isString(value))
			{
				return asString(value).equals("1");
			}
			
			return false;
		}
		
		public static Number asNumber(Object value)
		{
			if (isBoolean(value))
			{
				return asBoolean(value) ? 1 : 0;
			}
			else if (isNumber(value))
			{
				if (value instanceof Number)
					return (Number) value;
				else if (value instanceof PrimitiveLiteralValue &&
					((PrimitiveLiteralValue) value).literal instanceof Number)
					return (Number) ((PrimitiveLiteralValue) value).literal;
				else if (value instanceof UniversalContainer &&
					((UniversalContainer) value).inner.literal instanceof Number)
					return (Number) ((UniversalContainer) value).inner.literal;
			}
			else if (isString(value) && NumberUtils.isParsable(asString(value)))
			{
				return Double.parseDouble(asString(value));
			}
			
			return 0;
		}
		
		public static String asString(Object value)
		{
			Object literal = value;
			
			if (value instanceof PrimitiveLiteralValue)
				literal = ((PrimitiveLiteralValue) value).literal;
			else if (value instanceof ContainerValue)
				literal = null;
			else if (value instanceof UniversalContainer &&
				((UniversalContainer) value).inner instanceof PrimitiveLiteralValue)
				literal = ((UniversalContainer) value).inner.literal;
			else if (value instanceof UniversalContainer) literal = null;
			
			String s = literal != null ? literal.toString() : "";
			
			if (isNumber(value) && s.endsWith(".0"))
			{
				return s.replaceFirst(".0", "");
			}
			
			return s;
		}
		
		public boolean isPropertyExists(String property)
		{
			return (builtinProperties.contains(property) && !property.equals(BUILTIN_PROPERTY_CONSTRUCTOR)) ||
				(table != null && table.containsKey(property));
		}
		
		public boolean isMethodSupported(String method)
		{
			return supportedMethods.contains(method);
		}
		
		@Override
		public String toString()
		{
			if (table != null)
				return table.toString();
			else if (literal != null)
				return literal.toString();
			else
				return getType();
		}
	}
	
	protected static class UndefinedValue extends ContainerValue
	{
		@Override
		public String getClassName()
		{
			return BUILTIN_CLASS_UNDEFINED;
		}
		
		@Override
		public String getType()
		{
			return BUILTIN_TYPE_UNDEFINED;
		}
	}
	
	protected static class NullValue extends ContainerValue
	{
		public NullValue()
		{
			this.supportedMethods.add("create"); // implementation of static method Object.create()
		}
		
		@Override
		public String getClassName()
		{
			return BUILTIN_CLASS_NULL;
		}
		
		@Override
		public String getType()
		{
			return BUILTIN_TYPE_NULL;
		}
	}
	
	protected static class PrimitiveLiteralValue extends ContainerValue
	{
		public PrimitiveLiteralValue(Object value)
		{
			if (value instanceof ContainerValue) throw new ContainerException(
				"Literal value cannot hold other inner value class");
			if (value instanceof UniversalContainer) throw new ContainerException(
				"Literal value cannot hold container");
			if (value instanceof Object[]) throw new ContainerException(
				"Literal value cannot hold java array, use ArrayValue container instead");
			if (value instanceof Iterable) throw new ContainerException(
				"Literal value cannot hold Iterable class, use ArrayValue container instead");
			if (value instanceof Map) throw new ContainerException(
				"Literal value cannot hold Map class, use ObjectValue container instead");
			
			this.literal = value;
			
			this.builtinProperties.add(BUILTIN_PROPERTY_CONSTRUCTOR); // implemented
			
			this.supportedMethods.add("hasOwnProperty"); // implemented, but differently, method name is `has(property)`
			this.supportedMethods.add("isPrototypeOf");
			this.supportedMethods.add("propertyIsEnumerable");
			this.supportedMethods.add("toLocaleString");
			this.supportedMethods.add("toString"); // implemented, but differently, method name is the same
			this.supportedMethods.add("valueOf"); // implemented, but differently, method name is the same
			
			if (ContainerValue.isNumber(value))
			{
				// static methods
				this.supportedMethods.add("isNaN");
				this.supportedMethods.add("isFinite");
				this.supportedMethods.add("isInteger");
				this.supportedMethods.add("isSafeInteger");
				this.supportedMethods.add("parseFloat");
				this.supportedMethods.add("parseInt");
				
				// instance methods
				this.supportedMethods.add("toExponential");
				this.supportedMethods.add("toFixed");
				this.supportedMethods.add("toPrecision");
			}
			
			if (ContainerValue.isString(value))
			{
				this.builtinProperties.add(BUILTIN_PROPERTY_LENGTH); // implemented
				
				// static methods
				this.supportedMethods.add("fromCharCode");
				
				// instance standard methods
				this.supportedMethods.add("charAt");
				this.supportedMethods.add("charCodeAt");
				this.supportedMethods.add("codePointAt");
				this.supportedMethods.add("concat"); // implemented
				this.supportedMethods.add("includes");
				this.supportedMethods.add("endsWith");
				this.supportedMethods.add("indexOf"); // implemented
				this.supportedMethods.add("lastIndexOf");
				this.supportedMethods.add("localeCompare");
				this.supportedMethods.add("match");
				this.supportedMethods.add("normalize");
				this.supportedMethods.add("repeat");
				this.supportedMethods.add("replace");
				this.supportedMethods.add("search");
				this.supportedMethods.add("slice");
				this.supportedMethods.add("split");
				this.supportedMethods.add("startsWith");
				this.supportedMethods.add("substr");
				this.supportedMethods.add("substring");
				this.supportedMethods.add("toLocaleLowerCase");
				this.supportedMethods.add("toLocaleUpperCase");
				this.supportedMethods.add("toLowerCase");
				this.supportedMethods.add("toUpperCase");
				this.supportedMethods.add("trim");
				
				// instance HTML methods
				this.supportedMethods.add("anchor");
				this.supportedMethods.add("link");
			}
		}
		
		@Override
		public String getClassName()
		{
			if (ContainerValue.isBoolean(literal))
				return BUILTIN_CLASS_BOOLEAN;
			else if (ContainerValue.isNumber(literal))
				return BUILTIN_CLASS_NUMBER;
			else if (ContainerValue.isString(literal))
				return BUILTIN_CLASS_STRING;
			else
				return BUILTIN_CLASS_LITERAL;
		}
		
		@Override
		public String getType()
		{
			if (ContainerValue.isBoolean(literal))
				return BUILTIN_TYPE_BOOLEAN;
			else if (ContainerValue.isNumber(literal))
				return BUILTIN_TYPE_NUMBER;
			else if (ContainerValue.isString(literal))
				return BUILTIN_TYPE_STRING;
			else
				return BUILTIN_TYPE_UNKNOWN;
		}
	}
	
	protected static class ObjectValue extends ContainerValue
	{
		public ObjectValue()
		{
			this.table = new HashMap<String, UniversalContainer>();
			
			this.builtinProperties.add(BUILTIN_PROPERTY_CONSTRUCTOR); // implemented
			
			// static methods
			this.supportedMethods.add("assign");
			this.supportedMethods.add("create"); // implemented
			this.supportedMethods.add("defineProperty");
			this.supportedMethods.add("defineProperties");
			this.supportedMethods.add("freeze");
			this.supportedMethods.add("getOwnPropertyDescriptor");
			this.supportedMethods.add("getOwnPropertyNames");
			this.supportedMethods.add("getOwnPropertySymbols");
			this.supportedMethods.add("getPrototypeOf");
			this.supportedMethods.add("is");
			this.supportedMethods.add("isExtensible");
			this.supportedMethods.add("isFrozen");
			this.supportedMethods.add("isSealed");
			this.supportedMethods.add("keys"); // implemented, but differently, method name is the same
			this.supportedMethods.add("preventExtensions");
			this.supportedMethods.add("seal");
			this.supportedMethods.add("setPrototypeOf");
			
			// instance methods
			this.supportedMethods.add("hasOwnProperty"); // implemented, but differently, method name is `has(property)`
			this.supportedMethods.add("isPrototypeOf");
			this.supportedMethods.add("propertyIsEnumerable");
			this.supportedMethods.add("toLocaleString");
			this.supportedMethods.add("toString"); // implemented, but differently, method name is the same
			this.supportedMethods.add("valueOf"); // implemented, but differently, method name is the same
		}
		
		@Override
		public String getClassName()
		{
			return BUILTIN_CLASS_OBJECT;
		}
		
		@Override
		public String getType()
		{
			return BUILTIN_TYPE_OBJECT;
		}
	}
	
	protected static class ArrayValue extends ObjectValue
	{
		public ArrayValue()
		{
			this.builtinProperties.add(BUILTIN_PROPERTY_LENGTH); // implemented
			
			// static methods
			this.supportedMethods.add("from");
			this.supportedMethods.add("isArray"); // implemented as instance method
			this.supportedMethods.add("of");
			
			// instance mutator methods
			this.supportedMethods.add("copyWithin");
			this.supportedMethods.add("fill");
			this.supportedMethods.add("pop"); // implemented
			this.supportedMethods.add("push"); // implemented
			this.supportedMethods.add("reverse");
			this.supportedMethods.add("shift"); // implemented
			this.supportedMethods.add("sort");
			this.supportedMethods.add("splice"); // implemented
			this.supportedMethods.add("unshift");
			
			// instance accessor methods
			this.supportedMethods.add("concat"); // implemented
			this.supportedMethods.add("join");
			this.supportedMethods.add("slice");
			this.supportedMethods.add("indexOf"); // implemented
			this.supportedMethods.add("lastIndexOf");
			
			// instance iteration methods
			this.supportedMethods.add("forEach");
			this.supportedMethods.add("entries");
			this.supportedMethods.add("every");
			this.supportedMethods.add("some");
			this.supportedMethods.add("filter");
			this.supportedMethods.add("find");
			this.supportedMethods.add("findIndex");
			this.supportedMethods.add("keys");
			this.supportedMethods.add("map");
			this.supportedMethods.add("reduce");
			this.supportedMethods.add("reduceRight");
			this.supportedMethods.add("values");
		}
		
		@Override
		public String getClassName()
		{
			return BUILTIN_CLASS_ARRAY;
		}
	}
}