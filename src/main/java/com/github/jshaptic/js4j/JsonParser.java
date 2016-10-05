package com.github.jshaptic.js4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.gson.Gson;

public class JsonParser
{
	public static UniversalContainer parse(String json) throws JsonParserException
	{	
		try
		{
			Gson gson = new Gson();
			return jsonValueToContainer(Json.parse(gson.toJson(gson.fromJson(json, Object.class))).asObject());
		}
		catch (Exception e)
		{
			throw new JsonParserException(e.getMessage());
		}
	}
	
	private static UniversalContainer jsonValueToContainer(JsonValue jv)
	{
		if (jv.isArray())
		{
			UniversalContainer c = ContainerFactory.createArray();
			int i = 0;
			for (JsonValue v : jv.asArray())
			{
				c.set(i++, jsonValueToContainer(v));
			}
			return c;
		}
		if (jv.isObject())
		{
			UniversalContainer c = ContainerFactory.createObject();
			for (JsonObject.Member m : jv.asObject())
			{
				c.set(m.getName(), jsonValueToContainer(m.getValue()));
			}
			return c;
		}
		if (jv.isBoolean())
		{
			return new UniversalContainer(jv.asBoolean());
		}
		if (jv.isNumber())
		{
			return new UniversalContainer(jv.asDouble());
		}
		if (jv.isString())
		{
			return new UniversalContainer(jv.asString());
		}
		if (jv.isNull())
		{
			return ContainerFactory.nullContainer();
		}
		
		return ContainerFactory.undefinedContainer();
	}
	
	public static String stringify(UniversalContainer container)
	{
		StringBuilder sb = new StringBuilder();
		baseStringify(container, sb, null);
		return sb.toString();
	}
	
	private static void baseStringify(UniversalContainer container, StringBuilder sb, List<UniversalContainer> stack)
	{
		if (container == null || container.isUndefined()) return;
		
		if (container.isArray())
		{
			baseStringifyArray(container, sb, stack);
		}
		else if (container.isObject() && !container.isNull())
		{
			baseStringifyObject(container, sb, stack);
		}
		else if (container.isString())
		{
			sb.append("\"" + container.toString() + "\"");
		}
		else
		{
			sb.append(container.toString());
		}
	}
	
	private static void baseStringifyArray(UniversalContainer array, StringBuilder sb, List<UniversalContainer> stack)
	{
		sb.append("[");
		
		stack = (stack != null ? stack : new ArrayList<UniversalContainer>());
		
		int index = stack.size();
		while(index-- > 0)
		{
			if (stack.get(index) == array)
			{
				throw new ContainerException("Cannot convert circular container to JSON string");
			}
		}
		
		stack.add(array);
		
		int length = array.getLength(0);
		for (int i = 0; i < length; i++)
		{
			UniversalContainer element = array.get(i);
			if (element.isUndefined())
			{
				sb.append("null");
			}
			else
			{
				baseStringify(element, sb, stack);
			}
			if (i != length-1) sb.append(",");
		}
		
		if (stack.size() > 0) stack.remove(stack.size()-1);
		
		sb.append("]");
	}
	
	private static void baseStringifyObject(UniversalContainer object, StringBuilder sb, List<UniversalContainer> stack)
	{
		Set<String> keys = object.keys();
		
		sb.append("{");
		
		stack = (stack != null ? stack : new ArrayList<UniversalContainer>());
		
		int index = stack.size();
		while(index-- > 0)
		{
			if (stack.get(index) == object)
			{
				throw new ContainerException("Cannot convert circular container to JSON string");
			}
		}
		
		stack.add(object);
		
		boolean hasPairs = false;
		for (String k : keys)
		{
			UniversalContainer objectValue = object.get(k);
			if (!objectValue.isUndefined())
			{
				sb.append("\"" + k + "\":");
				baseStringify(objectValue, sb, stack);
				sb.append(",");
				hasPairs = true;
			}
		}
		if (hasPairs) sb.deleteCharAt(sb.length()-1);
		
		if (stack.size() > 0) stack.remove(stack.size()-1);

		sb.append("}");
	}
}