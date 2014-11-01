BuilderDeluxe
=============

BuilderDeluxe is a plugin for IntelliJ IDEA that automates creation of builder classes for POJOs. Let's say you have a class Person with
fields age, firstName and lastName:
```java
public class Person {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
```
With BuilderDeluxe you can press ALT+Insert to generate builder class:
```java
public static class Builder {
    private String firstName;
    private String lastName;
    private int age;

    private Builder() {
    }

    public static Builder person() {
        return new Builder();
    }

    public Builder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Builder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Builder withAge(int age) {
        this.age = age;
        return this;
    }

    public Person build() {
        return new Person();
    }
}
```
and use it that way:
```java
Person person = person().withFirstName("John").withLastName("Doe").withAge(42).build();
```
