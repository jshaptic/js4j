# js4j

[![Bintray](https://img.shields.io/bintray/v/jshaptic/maven/js4j.svg?style=flat-square)](https://bintray.com/jshaptic/maven/js4j/_latestVersion)
[![Javadocs](https://www.javadoc.io/badge/com.github.jshaptic/js4j.svg?style=flat-square)](https://www.javadoc.io/doc/com.github.jshaptic/js4j)
[![Travis](https://img.shields.io/travis/jshaptic/js4j.svg?style=flat-square)](https://travis-ci.org/jshaptic/js4j)
[![Coveralls](https://img.shields.io/coveralls/jshaptic/js4j.svg?branch=master&style=flat-square)](https://coveralls.io/github/jshaptic/js4j?branch=master)
[![License](https://img.shields.io/github/license/jshaptic/js4j.svg?style=flat-square)](https://opensource.org/licenses/MIT)

Wrapper for Java classes, which emulates Javascript variables and its API. Also includes ported Lodash library.
Currently not all functions and features are implemented, but most used are here, check [API documentation](https://www.javadoc.io/doc/com.github.jshaptic/js4j)
for a full list of supported features.

## Why

Most probably a question just poped up in your head: Why the hell it's needed for?? Well, for example it can be used as a tool for a quick libraries porting from javascript to java.
Another use case is that it can be as a tool for quick prototyping, when you don't want to bother about types and things like that. Also it can be used as a JSON container
with handy methods. But to tell the truth it was created just 4fun.

## Usage

To declare new variables (or containers in js4j terms) **UniversalContainer** class should be used:
```java
// this line will produce undefined container, which is analogue of "var c = undefined;" in javascript;
UniversalContainer c = ContainerFactory.undefinedContainer(); // var c = undefined;
// this line will also produce undefined container, but will create new instance of it, which is not good, so it's better to use above aproach
UniversalContainer c = new UniversalContainer(); // var c = undefined;

// this will produce null container
UniversalContainer c = ContainerFactory.nullContainer(); // var c = null;

// this will produce liter container with number
UniversalContainer c = new UniversalContainer(69); // var c = 69;

// this will produce empty object container
UniversalContainer c = ContainerFactory.createObject(); // var c = {};
// to add values to the object following methods can be used
UniversalContainer c = ContainerFactory.createObject(0, "a", 1, "b", 2, "c"); // var c = {0: "a", 1: "b", 2: "c"};
UniversalContainer c = ContainerFactory.createObject().set(0, "a").set(1, "b").set(2, "c"); // var c = {}; c[0] = "a"; c[1] = "b"; c[2] = "c"

// this will produce empty array container
UniversalContainer c = ContainerFactory.createArray(); // var c = [];
// to add values to the array following methods can be used
UniversalContainer c = ContainerFactory.createArray("a", "b", "c"); // var c = ["a", "b", "c"];
UniversalContainer c = ContainerFactory.createArray().set(0, "a").set(1, "b").set(2, "c"); // var c = []; c[0] = "a"; c[1] = "b"; c[2] = "c"
```

To compare containers method **equals** can be used, it works like === in JS and used to compare containers with each other
or pretty much any other objects:
```java
ContainerFactory.undefinedContainer().equals(null) // => true
new UniversalContainer(69).equals(69) // => true
new UniversalContainer(false).equals(0) // => false
ContainerFactory.createArray().equals(ContainerFactory.createArray()) // => false
```

To check if container is positive in "if" condition method **test** can be used:
```java
ContainerFactory.undefinedContainer().test() == false // if (undefined) ...
ContainerFactory.nullContainer().test() == false // if (null) ...
new UniversalContainer(0).test() == false // if (0) ...
new UniversalContainer("").test() == false // if ("") ...
new UniversalContainer(10).test() == true // if (10) ...
new UniversalContainer("false").test() == true // if ("false") ...
ContainerFactory.createArray().test() == true // if ([]) ...
```

In most cases API usage is quite relaxed without any errors, since JS is a generous language, but sometimes even JS throwing runtime errors
and to reflect the same behaviour **ContainerException** class was introduced. For example it will be thrown during runtime in the following cases:
```java
ContainerFactory.undefinedContainer().get(0) // => Cannot get '0' property from Undefined container
new UniversalContainer("abcde").pop() // => Method 'pop' is not available for String container
```

For all other methods and use cases please consult [javadoc](https://www.javadoc.io/doc/com.github.jshaptic/js4j).

## Backlog

* Implement all missing methods for boolean, number, string, object and array containers
* Implement infinity, nan, date
* Implement simple mathematical operations
* Implement more functions from Lodash library
* Implement own JSON parser or at least integrate any suitable open-source parser
* Make object constructors much more complex and useful
* Refactor value storage mechanism, currently it's implemented with Map, uses to much memory and can be slow 
