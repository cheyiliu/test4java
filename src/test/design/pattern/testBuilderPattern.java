
package test.design.pattern;

import org.junit.Test;

/**
 * from http://www.cnblogs.com/moonz-wu/archive/2011/01/11/1932473.html
 * http://www.importnew.com/6605.html
 * http://blog.csdn.net/bboyfeiyu/article/details/24375481 构建对象时
 * ，如果碰到类有很多参数——其中很多参数类型相同而且很多参数可以为空时，我更喜欢Builder模式来完成。当参数数量不多、类型不同而且都是必须出现时
 * ，通过增加代码实现Builder往往无法体现它的优势
 * 。在这种情况下，理想的方法是调用传统的构造函数。再者，如果不需要保持不变，那么就使用无参构造函数调用相应的set方法吧。
 */
public class testBuilderPattern
{
    @Test
    public void test() {
        Person person = new Person.PersonBuilder("newFirstName", "newCity", "newState")
                .city("newCity")
                .isEmployed(true)
                .isFemale(false)
                .lastName("newLastName")
                .suffix("newSuffix")
                .createPerson();
        person.toString();

        String newLastName = "newLastName";
        String newFirstName = "newFirstName";
        String newMiddleName = "newMiddleName";
        String newSalutation = "newSalutation";
        String newSuffix = "newSuffix";
        String newStreetAddress = "newStreetAddress";
        String newCity = "newCity";
        String newState = "newState";
        boolean newIsFemale = true;
        boolean newIsEmployed = true;
        boolean newIsHomeOwner = true;
        Person person2 = new Person(newLastName, newFirstName, newMiddleName, newSalutation,
                newSuffix, newStreetAddress, newCity, newState, newIsFemale, newIsEmployed,
                newIsHomeOwner);
        person2.toString();

        DoDoContact ddc = new DoDoContact.Builder("Ace").age(10)
                .address("beijing").build();
        System.out.println("name=" + ddc.getName() + "age =" + ddc.getAge()
                + "address" + ddc.getAddress());
    }
}

class Person {

    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String salutation;
    private final String suffix;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final boolean isFemale;
    private final boolean isEmployed;
    private final boolean isHomewOwner;

    public Person(
            final String newLastName,
            final String newFirstName,
            final String newMiddleName,
            final String newSalutation,
            final String newSuffix,
            final String newStreetAddress,
            final String newCity,
            final String newState,
            final boolean newIsFemale,
            final boolean newIsEmployed,
            final boolean newIsHomeOwner)
    {
        this.lastName = newLastName;
        this.firstName = newFirstName;
        this.middleName = newMiddleName;
        this.salutation = newSalutation;
        this.suffix = newSuffix;
        this.streetAddress = newStreetAddress;
        this.city = newCity;
        this.state = newState;
        this.isFemale = newIsFemale;
        this.isEmployed = newIsEmployed;
        this.isHomewOwner = newIsHomeOwner;
    }

    public static class PersonBuilder
    {
        private String nestedLastName;
        private String nestedFirstName;
        private String nestedMiddleName;
        private String nestedSalutation;
        private String nestedSuffix;
        private String nestedStreetAddress;
        private String nestedCity;
        private String nestedState;
        private boolean nestedIsFemale;
        private boolean nestedIsEmployed;
        private boolean nestedIsHomeOwner;

        public PersonBuilder(
                final String newFirstName,
                final String newCity,
                final String newState)
        {
            this.nestedFirstName = newFirstName;
            this.nestedCity = newCity;
            this.nestedState = newState;
        }

        public PersonBuilder lastName(String newLastName)
        {
            this.nestedLastName = newLastName;
            return this;
        }

        public PersonBuilder firstName(String newFirstName)
        {
            this.nestedFirstName = newFirstName;
            return this;
        }

        public PersonBuilder middleName(String newMiddleName)
        {
            this.nestedMiddleName = newMiddleName;
            return this;
        }

        public PersonBuilder salutation(String newSalutation)
        {
            this.nestedSalutation = newSalutation;
            return this;
        }

        public PersonBuilder suffix(String newSuffix)
        {
            this.nestedSuffix = newSuffix;
            return this;
        }

        public PersonBuilder streetAddress(String newStreetAddress)
        {
            this.nestedStreetAddress = newStreetAddress;
            return this;
        }

        public PersonBuilder city(String newCity)
        {
            this.nestedCity = newCity;
            return this;
        }

        public PersonBuilder state(String newState)
        {
            this.nestedState = newState;
            return this;
        }

        public PersonBuilder isFemale(boolean newIsFemale)
        {
            this.nestedIsFemale = newIsFemale;
            return this;
        }

        public PersonBuilder isEmployed(boolean newIsEmployed)
        {
            this.nestedIsEmployed = newIsEmployed;
            return this;
        }

        public PersonBuilder isHomeOwner(boolean newIsHomeOwner)
        {
            this.nestedIsHomeOwner = newIsHomeOwner;
            return this;
        }

        public Person createPerson()
        {
            return new Person(
                    nestedLastName, nestedFirstName, nestedMiddleName,
                    nestedSalutation, nestedSuffix,
                    nestedStreetAddress, nestedCity, nestedState,
                    nestedIsFemale, nestedIsEmployed, nestedIsHomeOwner);
        }
    }
}

class DoDoContact {
    private final int age;
    private final int safeID;
    private final String name;
    private final String address;

    public int getAge() {
        return age;
    }

    public int getSafeID() {
        return safeID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static class Builder {
        private int age = 0;
        private int safeID = 0;
        private String name = null;
        private String address = null;

        // 构建的步骤
        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder safeID(int val) {
            safeID = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public DoDoContact build() { // 构建，返回一个新对象
            return new DoDoContact(this);
        }
    }

    private DoDoContact(Builder b) {
        age = b.age;
        safeID = b.safeID;
        name = b.name;
        address = b.address;

    }
}
