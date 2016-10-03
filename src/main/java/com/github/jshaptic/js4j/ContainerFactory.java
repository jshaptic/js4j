package com.github.jshaptic.js4j;

public class ContainerFactory
{
	protected static final UniversalContainer STATIC_UNDEFINED_CONTAINER = new UniversalContainer(ContainerConstructor.UNDEFINED);
	protected static final UniversalContainer STATIC_NULL_CONTAINER = new UniversalContainer(ContainerConstructor.NULL);
	
	private ContainerFactory()
	{
		
	}
	
	public static final UniversalContainer createArray()
	{
		return new UniversalContainer(ContainerConstructor.ARRAY);
	}
	
	public static final UniversalContainer createArrayIfFalse(UniversalContainer container)
	{
		return container != null && container.test() ? container : createArray();
	}
	
	public static UniversalContainer createArray(Object... values)
	{
		return new UniversalContainer(values);
	}
	
	public static final UniversalContainer createObject()
	{
		return new UniversalContainer(ContainerConstructor.OBJECT);
	}
	
	public static final UniversalContainer createObjectIfFalse(UniversalContainer container)
	{
		return container != null && container.test() ? container : createObject();
	}
	
	public static UniversalContainer createObject(Object... pairs)
	{
		if (pairs.length % 2 != 0) throw new ContainerException("Only even number of arguments is allowed");
		
		UniversalContainer oc = createObject();
		if (pairs != null)
		{
			String key = null;
			boolean even = true;
			for (Object o : pairs)
			{
				if (even)
				{
					key = o.toString();
				}
				else
				{
					oc.set(key, o);
				}
				even = !even;
			}
		}
		
		return oc;
	}
	
	public static UniversalContainer defaultContainerIfNull(UniversalContainer container, UniversalContainer defaultContainer)
	{
		return container != null ? container : defaultContainer;
	}
	
	public static UniversalContainer defaultContainerIfFalse(UniversalContainer container, UniversalContainer defaultContainer)
	{
		return container != null && container.test() ? container : defaultContainer;
	}
	
	public static final UniversalContainer nullContainer()
	{
		return STATIC_NULL_CONTAINER;
	}
	
	public static final UniversalContainer nullContainerIfNull(UniversalContainer container)
	{
		return container != null ? container : nullContainer();
	}
	
	public static final UniversalContainer nullContainerIfFalse(UniversalContainer container)
	{
		return container != null && container.test() ? container : nullContainer();
	}
	
	public static final UniversalContainer undefinedContainer()
	{
		return STATIC_UNDEFINED_CONTAINER;
	}
	
	public static final UniversalContainer undefinedContainerIfNull(UniversalContainer container)
	{
		return container != null ? container : undefinedContainer();
	}
	
	public static final UniversalContainer undefinedContainerIfFalse(UniversalContainer container)
	{
		return container != null && container.test() ? container : undefinedContainer();
	}
}