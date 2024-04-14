# Data validator

[![Actions Status](https://github.com/shamshaev/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/shamshaev/java-project-78/actions)
[![Actions Status](https://github.com/shamshaev/java-project-78/actions/workflows/self-check.yml/badge.svg)](https://github.com/shamshaev/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/a1fe7fbb11fadbe93d8a/maintainability)](https://codeclimate.com/github/shamshaev/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/a1fe7fbb11fadbe93d8a/test_coverage)](https://codeclimate.com/github/shamshaev/java-project-78/test_coverage)

It's the java library for validation of an input value using the configured set of assertions. It's possible to validate 3 types: String, Number and Map<?, ?> including nested validation for Map values (respectively available for String, Number and Map<?, ?> values).

## Getting started

Schema are comprised of assertions (tests) about the input value. Validate an input value to run the set of assertions. Chain together methods to build a schema.

String methods:
* required() - set a requirement that is not null or empty.
* minLength(length) - set a requirement of minimal length.
* contains(string) - set a requirement to contain string value.

Number methods:
* required() - set a requirement that is not null.
* positive() - set a requirement to be positive.
* range(min, max) - set a requirement to be between min and max.

Map methods:
* required() - set a requirement that is not null.
* sizeof(size) - set a requirement to have a specified size.
* shape(Map<?, BaseSchema<T>>) - set a requirement to Map value according to built schema.

Common method:
* isValid(inputValue) - return boolean value of validation according to built schema.

## Example of using with Map

```bash
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

var v = new Validator();
var schema = v.map();

schema.isValid(null); // true

schema.required();
schema.isValid(null); // false

var data = new HashMap<String, String>();
data.put("key1", "value1");
data.put("key2", "value2");
schema.sizeof(2);
schema.isValid(data); // true

Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));
schema.shape(schemas);
Map<String, String> human = new HashMap<>();
human.put("firstName", "Anna");
human.put("lastName", "B");
schema.isValid(human); // false
```
