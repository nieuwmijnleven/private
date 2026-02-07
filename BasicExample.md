
Null-pointer exceptions are a common error in Java, causing inconvenience for many developers. Preventing null pointer exceptions in advance is important, but for developers who want to focus solely on logic, handling them can be tedious. Moreover, it’s not always easy to account for every scenario. Typically, static analysis tools are used to detect potential null pointer issues, but developers still have to find and fix the code themselves. JADEx reduces that burden. Let’s write null-safe Java code with JADEx.

---

## 1. JADEx GitHub repository

https://github.com/nieuwmijnleven/JADEx

---

## 2. JADEx IntelliJ plugin Installation

#### Installation From Jetbrains MarketPlace

<img width="1122" height="210" alt="Jadex-Plugin" src="https://github.com/user-attachments/assets/32afb71b-7e19-4a8f-a3f0-a1d5ec337b1d" />

- Open IntelliJ
- Go to `File > Settings > Plugins > Marketplace`
- Type **`JADEx`** in the search bar and locate the plugin
- Click Install and wait for the installation to complete
- Restart IntelliJ

#### Manual Installation

- Open IntelliJ
- Go to `File > Settings > Plugin > ⚙️ > Install Plugin from Disk`
- Select the downloaded `intellij-plugin-0.1-mvp-alpha.zip`
- Restart IntelliJ

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/hnrmyesd5xly51rewu66.png)

---

## 3. Open the example project in IntelliJ
- Go to `File > New > Project from Version Control`
- Enter `https://github.com/nieuwmijnleven/JADExExample`
- Click the `Clone` button

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/n8andr9th5wgv6om8faq.png)

---

## 4. Create a JADEx file for User.java in the Project View
- Right-click User.java in the Project View
- Select Convert Java File to JADEx File from the menu

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/1pkpjslk5n51eitd1sf1.png)

---

## 5. Making Java code null-safe with JADEx
#### User.JADEx
```java
package JADEx.example;

class User {
    // Name is required
    String name;        
    // Address can be null
    Address address;   

    User(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    //remain codes
}
```

Opening the generated User.JADEx file, you can see error messages in the Problems tab.

<img width="1031" height="130" alt="image" src="https://github.com/user-attachments/assets/2a311846-4b69-4f03-a462-d36edbb54a48" />

By default, JADEx assumes all reference types are non-nullable, which causes these errors. The problematic parts are where the constructors of the User and Address classes are called.

```java
User(String name, Address address) {
    this.name = name;
    this.address = address;
}

Address(String city) {
    this.city = city;
}

User user1 = new User("Jeroen", new Address("New Amsterdam"));
User user2 = new User("Jane Smith", null);
User user3 = new User(null, new Address(null));
```
- The constructor parameters for User and Address are all non-nullable. Passing null violates the null-safety rules.
- Therefore, errors occur for user2 and user3.

---

### Making Java code null-safe with JADEx
 
(1) Assume the name field is required
- Add final to the name field
- Use apply constructor(required) to auto-generate the required constructor
- Press Ctrl + S to save, so changes reflect in User.java.

#### User.JADEx
```java
package JADEx.example;

apply constructor(required);

class User {
    // Name is required
    final String name;        
    // Address can be null
    Address address;   

    User(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    //remain codes
}
```
- At the end, the generated User.java includes a constructor for the final name field.

---

#### (2) Assume the address field is nullable
- Add ? to the Address type
- Also mark the constructor parameter as nullable

```java
package JADEx.example;

apply constructor(required);

class User {
    // Name is required
    final String name;
    // Address can be null
    Address? address;

    User(String name, Address? address) {
        this.name = name;
        this.address = address;
    }
```

The Problems tab shows:
- address is a nullable variable. But it directly accesses city. Consider using null-safe operator(?.)


![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/3caybfqmk33ncv09ay4u.png)


- Use the null-safe operator ?.:
```java
    // Safely get the city name of the address
    String getCity() {
        return address?.city;
    }

```

Then only the constructor-related error for name remains. 

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/1mtxx36ggd5mwx2log08.png)

Replace null with "No Name" because name field is required.

```java
User user3 = new User("No Name", new Address(null));
```

Now only one error remains:
- The 1st argument of the Address constructor is a non-nullable variable, but a null value is assigned to it.


![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/t419qh9nvmfafr3du97p.png)

---

(3) Assume city in Address is nullable
- Add ? to String type and constructor parameter

```java
static class Address {
    // City can be null
    String? city;

    Address(String? city) {
        this.city = city;
    }
}
```
  
All nullability errors disappear. Save the file (Ctrl + S) to generate new User.java.

User.java made null-safe by JADEx

```java
package JADEx.example;

//apply constructor(required);

class User {
    // Name is required
    final String name;
    // Address can be null
    Address address;    
    
    public User(String name) {
        this.name = name;
    }

    User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Safely get the city name of the address
    String getCity() {
        return (((address)!=null)?(address.city):null);
    }

    // Get the display name of the user
    String getDisplayName() {
        return name;
    }

    // Address class
    static class Address {
        // City can be null
        String city;

        Address(String city) {
            this.city = city;
        }
    }

    public static void main(String[] args) {
        // Null-safe object creation
        User user1 = new User("Jeroen", new Address("New Amsterdam"));
        User user2 = new User("Jane Smith", null);
        User user3 = new User("No Name", new Address(null));

        // Null-safe access
        System.out.println(user1.getDisplayName() + "'s city: " + user1.getCity()); // Jeroen's city: New Amsterdam
        System.out.println(user2.getDisplayName() + "'s city: " + user2.getCity()); // Jane Smith's city: No Address
        System.out.println(user3.getDisplayName() + "'s city: " + user3.getCity()); // No Name's city: No Address
    }
}

```

Run the program:
- Go to User.java
- Select Run > 'Run User.java'

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/hhkyo3en31720gszchje.png)

No NullPointerException occurs, but some outputs are null. Use the Elvis operator ?: to provide default values.

Edit getCity() in User.JADEx:

```java
class User {
    //...
    // Safely get the city name of the address
    String getCity() {
        return address?.city ?: "No City";
    }
   //...
}
```

Also, be sure to press Ctrl + S to save. This ensures that the new Java code is saved in User.java.

User.java made null-safe by JADEx

```java
package JADEx.example;

//apply constructor(required);

class User {
    // Name is required
    final String name;
    // Address can be null
    Address address;    
    
    public User(String name) {
        this.name = name;
    }

    User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Safely get the city name of the address
    String getCity() {
        return ((((((address)!=null)?(address.city):null))!=null)?((((address)!=null)?(address.city):null)):("No City"));
    }

    // Get the display name of the user
    String getDisplayName() {
        return name;
    }

    // Address class
    static class Address {
        // City can be null
        String city;

        Address(String city) {
            this.city = city;
        }
    }

    public static void main(String[] args) {
        // Null-safe object creation
        User user1 = new User("Jeroen", new Address("New Amsterdam"));
        User user2 = new User("Jane Smith", null);
        User user3 = new User("No Name", new Address(null));

        // Null-safe access
        System.out.println(user1.getDisplayName() + "'s city: " + user1.getCity()); // Jeroen's city: New Amsterdam
        System.out.println(user2.getDisplayName() + "'s city: " + user2.getCity()); // Jane Smith's city: No Address
        System.out.println(user3.getDisplayName() + "'s city: " + user3.getCity()); // No Name's city: No Address
    }

}

```

Run the program:
- Go to User.java
- Select Run > 'Run User.java'

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/rw7gytaxueue939mgd9n.png)


All null values are replaced with "No City"

---

## Summary

With JADEx, you can easily enforce null-safety in Java code. JADEx fully supports Java syntax, making it accessible for Java developers. The final code is converted to Java, allowing developers to review it. JADEx is still in its early stages and needs support from the Java community. Even small, regular contributions will help complete this project. Your support can make a real impact.

[Become a Sponsor via Github](https://github.com/sponsors/nieuwmijnleven)

[Become a Sponsor via PayPal.me](https://paypal.me/nieuwmijnleven)









