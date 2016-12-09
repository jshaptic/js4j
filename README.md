# js4j

[![Bintray](https://img.shields.io/bintray/v/jshaptic/maven/js4j.svg?style=flat-square)](https://bintray.com/jshaptic/maven/js4j/_latestVersion)
[![Travis](https://img.shields.io/travis/jshaptic/js4j.svg?style=flat-square)](https://travis-ci.org/jshaptic/js4j)
[![Coveralls](https://img.shields.io/coveralls/jshaptic/js4j.svg?style=flat-square)](https://coveralls.io/github/jshaptic/js4j)
[![License](https://img.shields.io/github/license/jshaptic/js4j.svg?style=flat-square)](https://opensource.org/licenses/MIT)

Wrapper for Java classes, which emulates Javascript variables and its API. Also includes ported Lodash library.

## Usage

To declare new variables (containers in js4j terms) UniversalContainer class should be used:
```java
// this line will produce undefined container, which is analogue of var c = undefined in javascript;
UniversalContainer c = ContainerFactory.undefinedContainer();
// this line will also produce undefined container, but will create new instance of it, which is not good, so it's better to use above aproach
UniversalContainer c = new UniversalContainer();

// this will produce null container, counterpart in JS is var c = null;
UniversalContainer c = ContainerFactory.nullContainer();

// this will produce liter container with number, counterpart in JS is var c = 69;
UniversalContainer c = new UniversalContainer(69);

// this will produce empty object container, counterpart in JS is var c = {};
UniversalContainer c = ContainerFactory.createObject();
// to add values to the object following methods can be used
UniversalContainer c = ContainerFactory.createObject(0, "a", 1, "b", 2, "c");
UniversalContainer c = ContainerFactory.createObject().set(0, "a").set(1, "b").set(2, "c");

// this will produce empty array container, counterpart in JS is var c = [];
UniversalContainer c = ContainerFactory.createArray();
// to add values to the array following methods can be used
UniversalContainer c = ContainerFactory.createArray("a", "b", "c");
UniversalContainer c = ContainerFactory.createArray().set(0, "a").set(1, "b").set(2, "c");
```