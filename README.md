<p align="center">
  <img src="https://raw.githubusercontent.com/nieuwmijnleven/JPlus/refs/heads/master/intellij-plugin/src/main/resources/META-INF/pluginIcon.svg" alt="Logo" width="200">
</p>

[![License](https://img.shields.io/badge/License-GPL_v2_only_%2B_Commercial-blue.svg)](https://opensource.org/licenses/GPL-2.0-only)

# <img src="https://raw.githubusercontent.com/nieuwmijnleven/JPlus/refs/heads/master/intellij-plugin/src/main/resources/META-INF/pluginIcon.svg" alt="Logo" width="25" style="vertical-align: middle;"> JADEx - Same Java, Just safer

JADEx (Java Advanced Development Extension) is **a practical solution for Java null-safety**. It lets you enhance your code’s safety and **without rewriting it**, while **fully leveraging existing Java libraries and tools**.

---

## ✨ Project Overview

### Key Idea

* JADEx **strengthens Java’s null-related type system in a way similar to how TypeScript enhances JavaScript’s type system**. It brings compile-time null-safety to your existing Java code without requiring a complete rewrite.

          +---------------------------+
          |       Existing Java       |
          | (potentially null-prone)  |
          +------------+--------------+
                       |
                       v
       +-----------------------------------+
       |           JADEx Tools             |
       | (strengthens type system          |
       |  & applies null-safety operators) |
       +----------------+------------------+
                        |
                        v
          +---------------------------+
          |   Null-Safe Java Code     |
          | (enhanced compile-time    |
          |  null-safety guarantees)  |
          +---------------------------+

> Analogy: Java + JADEx  ≅  JavaScript + TypeScript

> **JADEx is not designed to replace Java. It is designed to strengthen Java's null-related type system without leaving it**

### Key Benefits

* Familiar Syntax: **No new language to learn, just safer Java.**
* Seamless Integration: Works with existing Java projects with minimal changes.
* Incremental Adoption: Apply null-safety only where you need it.
* Enhanced Code Safety: Catch potential null-pointer issues at compile time, not runtime.

> **JADEx makes your Java code safer, more maintainable, and future-proof without compromise**.
---

## 💡 How to make your java code null-safe

Making your existing Java code null-safe with JADEx is simple and straightforward. The process guides you from analyzing potential null-pointer issues to applying null-safety operators, and finally generating safe, standard Java code without rewriting your code or leaving the Java ecosystem.

### Workflow (with JADEx IntelliJ Plugin)

The typical JADEx workflow for producing Java source code with enhanced null-safety guarantees is as follows:

```
           << JAVA SOURCE >>
                   ↓
     << COPIED AS-IS TO .JADEX >>
                   ↓
           << JADEX SOURCE >>
                   ↓
     << JADEX NULL-SAFETY ANALYSIS >>
                   ↓
         << REVIEW THE RESULTS >>
                   ↓
   << APPLY JADEX NULL-SAFETY OPERATORS >>
                   ↓
           << JADEX COMPILER >>
                   ↓
   << JAVA SOURCE WITH ENHANCED NULL-SAFETY >>

```

* Existing Java source files are copied unchanged into `.jadex` files. JADEx null-safety operators are then applied to the JADEx sources to explicitly annotate and enforce null-safety semantics. These annotated JADEx sources are subsequently compiled by the JADEx compiler into Java source code with enhanced null-safety guarantees.

* Essentially, **all you need to do is review the null-safety analysis results and apply the null-safety operators** in your `.jadex` files. The JADEx IntelliJ plugin takes care of the rest automatically.

### JADEx IntelliJ Plugin Installation
The JADEx IntelliJ plugin allows you to easily analyze your Java code and apply null-safety operators without leaving your familiar IDE. Follow the steps below to install it.

**Prerequisites**

- above JDK(Java Development Kit) 21**
- above IntelliJ 2025.2

**Installation from Jetbrains Marketplace**

![image](https://github.com/user-attachments/assets/32afb71b-7e19-4a8f-a3f0-a1d5ec337b1d)

* Step1: Launch your IntelliJ IDEA IDE.
* Step2: Navigate to File → Settings → Plugins → Marketplace.
* Step3: Type **`JADEx`** in the search bar and locate the plugin.
* Step4: Click Install and wait for the installation to complete.
* Step5: Restart your IDE to activate the plugin.

**(Alternative) Manual Installation**

- Download [intellij-plugin-0.26.zip](https://github.com/user-attachments/files/25161928/intellij-plugin-0.26.zip)
- Open IntelliJ
- Go to `File > Settings > Plugin > ⚙️ > Install Plugin from Disk`
- Select the downloaded `intellij-plugin-0.26.zip`
- Restart IntelliJ

---

### 📚 Essential Examples
- Simple, focused examples showing how to use JADEx null-safety operators.

#### ✅ Example ① - Compile-time null assignment check

JADEx explicitly defines nullability at the type system level and performs **compile-time checks**  
to eliminate the possibility of `NullPointerException` at runtime.

#### 📌 Basic Rules

- `Type` → **non-nullable** (cannot be assigned `null`)
- `Type?` → **nullable** (can be assigned `null`)
- `?.` → safely access methods or fields on nullable objects
- `?:` → elvis operator: returns the left-hand side if non-null, otherwise the right-hand side (x ?: y ≡ (x != null) ? x : y)

#### 📄 Example – `NullableType1.jadex`

```java
public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = null;
    }
}
```

- `s1` is a **nullable** variable and can be assigned `null`.
- `s2` is a **non-nullable** variable, so assigning `null` causes a **compile-time error**.

#### ❌ Output

```
Warning: (line:6, column:8) s2 is a non-nullable variable. But null value is assigned to it.
```
---

### ✅ Example ② — Enforcing the null-safe operator (`?.`)

In JADEx, **you must use the `?.` operator when accessing nullable variables.**  
If you try to access fields or methods on a potentially-null object without the null-safe operator, a compile-time error will occur.


#### 📄 Example – `NullableType2.jadex`

```java
public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = "JADEx";

        s1.length();
        s2.length();
    }
}
```

- `s1` is **nullable** but tries to call a method **without using the null-safe operator (`?.`)**  
  → **Compile-time error**

- `s2` is **non-nullable**, so method access is allowed without issue


#### ❌ Output

```
Warning: (line:8, column:8) s1 is a nullable variable. But it direct accesses to length(). You must consider to use null-safe operator(?.)
```

#### ✅ Corrected Code (Works as Expected)

```java
s1?.length(); // null-safe access
s2.length();  // regular access
```

As shown above, JADEx **enforces safe access to nullable variables at the language level**,  
helping to eliminate null-related runtime errors before they happen.

---
### ✅ Example ③ — Elvis Operator (`?:`)

JADEx introduces the **Elvis operator (`?:`)**,  
which allows you to easily provide a default or fallback value when a nullable variable is `null`.

#### 📄 Example – `ElvisOperator.jadex`

```java
public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = s1 ?: "JADEx";
        System.out.printf("s1 = %s\n", s1 ?: "null-value");
        System.out.printf("s2 = %s\n", s2);
    }
}
```

- `s1` is a nullable variable.
- `s1 ?: "JADEx"` assigns `"JADEx"` if `s1` is `null`.
- When printing, `s1 ?: "null-value"` safely handles null values.

#### ✅ Output (Java code generated by JADEx)

```java
public class Main {
    public static void main(String[] args) {
        @org.jspecify.annotations.Nullable String s1 = null;
        String s2 = jadex.runtime.SafeAccess.ofNullable(s1).orElseGet(() -> "JADEx");
        System.out.printf("s1 = %s\n", jadex.runtime.SafeAccess.ofNullable(s1).orElseGet(() -> "null-value"));
        System.out.printf("s2 = %s\n", s2);
    }
}
```

---
### ✅ Example ④ — Correct Usage of the Null-safe Operator (`?.`)

In JADEx, the use of the **null-safe access operator (`?.`) is enforced** for nullable variables.  
This prevents null reference errors and allows safe method calls on potentially null values.

#### 📄 Example – `NullsafeOperator.jadex`

```java
public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = "JADEx";
        System.out.printf("the length of s1 : %d\n", s1?.length());
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

- `s1` is a nullable variable.
- `s1?.length()` safely returns `null` if `s1` is `null`.
- `s2` is non-nullable, so calling `length()` is valid without safety checks.

#### ✅ Output (Java code generated by JADEx)

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        @org.jspecify.annotations.Nullable String s1 = null;
        String s2 = "JADEx";
        System.out.printf("the length of s1 : %d\n", jadex.runtime.SafeAccess.ofNullable(s1).map(t0 -> t0.length()).orElse(null));
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

This example demonstrates how JADEX handles the null-safe operator for nullable variables  
and clearly shows how to write code that adheres to JADEx's null safety rules.

---
### ✅ Example ⑤ — Combining `?.` and `?:` Operators

JADEx supports **combining the null-safe access operator (`?.`) and the Elvis operator (`?:`)**  
to simplify complex null-handling logic into clean and concise expressions.

#### 📄 Example – `NullsafeWithElvisOperator.jadex`

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = s1 ?: "JADEx";
        System.out.printf("the length of s1 : %d\n", s1?.length() ?: 0);
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

- `s1` is a nullable variable.
- `s1 ?: "JADEx"` → assigns `"JADEx"` if `s1` is null.
- `s1?.length() ?: 0` → safely calls `length()` on `s1`, returns `0` if `s1` is null.
- By combining both operators, **null handling becomes safe and concise**.

#### ✅ Output (Java code generated by JADEx)

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        @org.jspecify.annotations.Nullable String s1 = null;
        String s2 = jadex.runtime.SafeAccess.ofNullable(s1).orElseGet(() -> "JADEx");
        System.out.printf("the length of s1 : %d\n", jadex.runtime.SafeAccess.ofNullable(s1).map(t0 -> t0.length()).orElseGet(() -> 0));
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

> The expression `s1?.length() ?: 0` is translated into a nested conditional check in Java:  
> `jadex.runtime.SafeAccess.ofNullable(s1).map(t0 -> t0.length()).orElseGet(() -> 0)`, ensuring safe execution.

#### 📌 Summary

This example demonstrates a **natural combination** of the following features:

- ✅ **Null-safe method call** (`s1?.length()`)  
  Ensures safe access to nullable variables without throwing `NullPointerException`.

- ✅ **Default fallback value** (`?: 0`)  
  Provides a default value when an expression evaluates to `null`, improving code safety and readability.

JADEx allows even complex null-handling logic to be expressed **safely and concisely** with its extended syntax.

---

### 📚 Basic Examples

- Hands-on examples that demonstrate the core JADEx null-safety features in everyday Java code.  
  These examples focus on common patterns such as nullable fields, safe access (`?.`), and evis operator (`?:`) to help you quickly understand how to write null-safe code with JADEx.

**Open the example project in IntelliJ**
- Go to `File > New > Project from Version Control`
- Enter `https://github.com/nieuwmijnleven/JADExExample`
- Click the `Clone` button

![image](https://github.com/user-attachments/assets/3235169a-dc70-472a-ba52-8e99d412690b)

---

**Create a JADEx file for User.java in the Project View**
- Right-click User.java in the Project View
- Select Convert Java File to JADEx File from the menu

![image](https://github.com/user-attachments/assets/b0d50215-0746-41d2-b2e6-e60528d9da95)

---

User.jadex
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

Opening the generated `User.jadex` file, you can see warning messages in the Problems tab.

![image](https://github.com/user-attachments/assets/944c5eb5-81dc-4389-97c7-8280b35d2548)

By default, **JADEx assumes all reference types are non-nullable**, which causes these warnings. The problematic parts are where the constructors of the User and Address classes are called.

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
- Therefore, warnings occur for user2 and user3.

---

**Making Java code null-safe with JADEx**

(1) Assume the address field is nullable
- Add ? to the Address type
- Also mark the constructor parameter as nullable

```java
package JADEx.example;

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
- The method(getCity) is declared to return a non-null value, but this return statement may return null.
- address is a nullable variable. But it directly accesses city. Consider using null-safe operator(?.)

![image](https://github.com/user-attachments/assets/f7b3ab34-26b5-4858-bac9-70617278cb6e)


- Use the null-safe operator ?.:
- Add ? to the return type (String) of method getCity.
```java
    // Safely get the city name of the address
    String? getCity() {
        return address?.city;
    }

```

Then only the constructor-related warning for name remains.

![image](https://github.com/user-attachments/assets/4b3c5a81-9667-4829-83d1-5b6c64485b00)

Replace null with "No Name" because name field is required.

```java
User user3 = new User("No Name", new Address(null));
```

Now only one warning remains:
- The 1st argument of the Address constructor is a non-nullable variable, but a null value is assigned to it.

![image](https://github.com/user-attachments/assets/148921bc-b542-4979-bf33-681799db5317)

---

(2) Assume city in Address is nullable
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

All nullability warnings disappear. Save the file `ctrl + s` to generate new User.java.

User.java made null-safe by JADEx

```java
package jadex.example;

class User {
    // Name is required
    String name;
    // Address can be null
    @org.jspecify.annotations.Nullable Address address;

    User(String name, @org.jspecify.annotations.Nullable Address address) {
        this.name = name;
        this.address = address;
    }

    // Safely get the city name of the address
    @org.jspecify.annotations.Nullable String getCity() {
        return jadex.runtime.SafeAccess.ofNullable(address).map(t0 -> t0.city).orElse(null);
    }

    // Get the display name of the user
    String getDisplayName() {
        return name;
    }

    // Address class
    static class Address {
        @org.jspecify.annotations.Nullable String city; // City can be null

        Address(@org.jspecify.annotations.Nullable String city) {
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

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/hhkyo3en31720gszchje.png)


No NullPointerException occurs, but some outputs are null. Use the Elvis operator ?: to provide default values.

Edit getCity() in User.jadex:

```java
class User {
    //...
    // Safely get the city name of the address
    String? getCity() {
        return address?.city ?: "No City";
    }
   //...
}
```

Also, be sure to press `ctrl + s` to save. This ensures that the new Java code is saved in User.java.

User.java made null-safe by JADEx

```java
package jadex.example;

class User {
    // Name is required
    String name;
    // Address can be null
    @org.jspecify.annotations.Nullable Address address;

    User(String name, @org.jspecify.annotations.Nullable Address address) {
        this.name = name;
        this.address = address;
    }

    // Safely get the city name of the address
    @org.jspecify.annotations.Nullable String getCity() {
        return jadex.runtime.SafeAccess.ofNullable(address).map(t0 -> t0.city).orElseGet(() -> "No City");
    }

    // Get the display name of the user
    String getDisplayName() {
        return name;
    }

    // Address class
    static class Address {
        @org.jspecify.annotations.Nullable String city; // City can be null

        Address(@org.jspecify.annotations.Nullable String city) {
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

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/rw7gytaxueue939mgd9n.png)


All null values are replaced with "No City"

---

## Summary

With JADEx, you can easily enforce null-safety in Java code. JADEx fully supports Java syntax, making it accessible for Java developers. The final code is converted to Java, allowing developers to review it. JADEx is still in its early stages and needs support from the Java community. Even small, regular contributions will help complete this project. Your support can make a real impact.

---

## FAQ

#### Q: Shouldn’t we just wait for Project Valhalla?

**A:** Valhalla is the future of the JVM and JADEx lets you experience Valhalla-style null-safety in today’s Java.

However, JADEx is not a temporary workaround while waiting for Valhalla. It is a tool that remains valuable even after Valhalla arrives.

From a null-safety perspective, the key difference is this:
- **Valhalla helps you avoid null**. it does not eliminate null.
- **JADEx makes NPEs structurally impossible to write**.

|                     | Valhalla               | JADEx                     |
| ------------------- | ---------------------- | ------------------------- |
| Default             | nullable               | **non-null**              |
| Primary goal        | Performance / modeling | **NPE elimination**       |
| Target code         | Mostly new code        | **Legacy code**           |
| Null-safe operators | ❌                      | **`?.`, `?:`**            |
| Gradual adoption    | Limited                | **Core design principle** |
| Output focus        | Bytecode               | **Human-readable Java**   |

Even after Valhalla ships:

```
String s = foo();
s.length();
```

This code will still:
- Compile successfully
- Potentially crash at runtime
- Exist across billions of lines of legacy Java

> **Valhalla does not infer or enforce null contracts in existing APIs.**

In JADEx,

```
String s = foo(); // compile-time error if foo() is nullable
```

> **The problem surfaces at authoring time, not in production.**


---

#### Q: How is this different from Valhalla’s non-null types?

**A:** ① Valhalla’s **non-null is opt-in and conservative**

```java
Point! p;
```

* Developers must explicitly opt in
* Data-flow analysis is limited
* It does not change Java as a whole

👉 **The default is still nullable**

> In other words, Valhalla introduces non-null selectively, without redefining Java’s baseline assumptions.

② JADEx is **non-null by default**

```java
String name;    // non-null
String? nick;   // nullable
```

This difference is immediately noticeable in daily development.

> **“Java, where every type must be doubted”**
> vs.
> **“JADEx, where you only add ? when you actually mean nullable”**

---

#### Q: Can Valhalla and JADEx Be Used Together?

**Absolutely. In fact, they are an ideal combination.**

**A:** Clear Separation of Responsibilities

* **Project Valhalla**

    * Improves the JVM’s runtime and memory model
    * Focuses on performance and value-based objects

* **JADEx**

    * Enhances null-safety at the source code level
    * Improves type safety, expressiveness, and developer experience

**Why They Work Well Together**

* Code written with JADEx remains **plain Java code**, even in the Valhalla era.
* There is no conflict between the two.
* Neither replaces the other.

> **Valhalla evolves how Java runs.
> JADEx improves how Java is written.**
> Used together, they create strong synergy.

---

#### Q: Can JADEx and Java coexist in the same project?

**A:** Yes.

Unmodified Java files compile as-is.
JADEx features can be enabled **incrementally**, file by file.

---

#### Q: Is JADEx trying to replace Kotlin or Java?

**A:** No.

* Kotlin → another JVM language
* JADEx → **Java language extension**

---

#### Q: What about performance?

**A:** JADEx generates standard Java constructs. There is **no runtime overhead beyond what you’d write manually.**

---

## 🤝 Sponsorship & Support

JADEx is currently in its early stage, and your support can make a big difference in shaping its future.

- **Report bugs, suggest features, and contribute code** : every contribution helps!
- **By sponsoring, you help accelerate development and gain early access to new features.**
- We are preparing exclusive perks for sponsors as a token of our gratitude.

If you want to support the project financially, please visit our sponsorship page:

[Become a Sponsor via Github](https://github.com/sponsors/nieuwmijnleven)

[Become a Sponsor via PayPal.me](https://paypal.me/nieuwmijnleven)

Thank you for helping us build a better, safer, and more productive programming tools!

---

## 🌟 Thanks to my sponsor!

Special thanks to [@shocklateboy92](https://github.com/shocklateboy92) for supporting this project through GitHub Sponsors!

---
