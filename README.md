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
        String s2 = java.util.Optional.ofNullable(s1).orElseGet(() -> "JADEx");
        System.out.printf("s1 = %s\n", java.util.Optional.ofNullable(s1).orElseGet(() -> "null-value"));
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
        System.out.printf("the length of s1 : %d\n", java.util.Optional.ofNullable(s1).map(t0 -> t0.length()).orElse(null));
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
        String s2 = java.util.Optional.ofNullable(s1).orElseGet(() -> "JADEx");
        System.out.printf("the length of s1 : %d\n", java.util.Optional.ofNullable(s1).map(t0 -> t0.length()).orElseGet(() -> 0));
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

> The expression `s1?.length() ?: 0` is translated into a nested conditional check in Java:  
> `java.util.Optional.ofNullable(s1).map(t0 -> t0.length()).orElseGet(() -> 0)`, ensuring safe execution.

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

[Step-by-Step: Adding Null-Safety to Java with JADEx](./BasicExample.md)

## Open the example project in IntelliJ
- Go to `File > New > Project from Version Control`
- Enter `https://github.com/nieuwmijnleven/JADExExample`
- Click the `Clone` button

![image](https://github.com/user-attachments/assets/3235169a-dc70-472a-ba52-8e99d412690b)

---

## Create a JADEx file for User.java in the Project View
- Right-click User.java in the Project View
- Select Convert Java File to JADEx File from the menu

![image](https://github.com/user-attachments/assets/b0d50215-0746-41d2-b2e6-e60528d9da95)

---

#### User.jadex
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

## Making Java code null-safe with JADEx
 
#### (1) Assume the address field is nullable
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

**(3) Assume city in Address is nullable**
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
  
All nullability warnings disappear. Save the file `(Ctrl + S)` to generate new User.java.

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
    String getCity() {
        return address?.city ?: "No City";
    }
   //...
}
```

Also, be sure to press Ctrl + S to save. This ensures that the new Java code is saved in User.java.

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

### 📚 Real-World Examples

- Applying JADEx to larger, real-world Java codebases.

#### [drafting] ####


---

## FAQ

### Q: Why not just use Kotlin?

**A:** Kotlin is an excellent language but **it requires leaving Java**.

JADEx is for teams that:

* cannot rewrite existing Java code
* want Java syntax preserved
* need gradual, low-risk adoption
* generate readable Java code for audits/debugging

---

### Q: Can JADEx and Java coexist in the same project?

**A:** Yes.
Unmodified Java files compile as-is.
JADEx features can be enabled **incrementally**, file by file.

---

### Q: Is JADEx trying to replace Kotlin or Java?

**A:** No.

* Kotlin → another JVM language
* JADEx → **Java language extension**

---

### Q: What about performance?

**A:** JADEx generates standard Java constructs. There is **no runtime overhead beyond what you’d write manually.**










이 구조대로 하면:


Java Source
     │
     │  copy (no code changes)
     ▼
JADEx (.jadex)
     │
     ▼
JADEx Compiler
     │
     ▼
Plain Java Source



JADEx is a fully Java-compatible superset that brings compile-time null safety to your existing Java code — without rewriting it, without changing the JVM, and without leaving the familiar Java syntax behind.
        
With JADEx, development teams can gradually adopt null-safety at the type level, improving code reliability and reducing runtime null-pointer exceptions, just like TypeScript did for JavaScript.

        Key Benefits:
        - Seamless Integration: Works with existing Java projects with minimal changes.
        - Incremental Adoption: Apply null-safety only where you need it.
        - Enhanced Code Safety: Catch potential null-pointer issues at compile time, not runtime.
        - Familiar Syntax: No new language to learn, just safer Java.

        JADEx makes your Java code safer, more maintainable, and future-proof — without compromise.
 



JPlus is fully compatible with Java, offering modern language features like null safety, boilerplate code generation and other modern language features to reduce developer burden and maximize productivity.

Notably, **there is currently no ‘superset’ language that keeps Java syntax almost intact while extending the language with features like null checks at the language level.** JPlus aims to fill this gap, providing a language that existing **Java developers can naturally learn and adopt**.

### Why JPlus?

- **Maintains Java Compatibility**  
  Fully utilizes existing Java libraries and frameworks

- **Enhances Development Productivity**  
  Automates null checks, supports boilerplate code generating syntax and other modern features

- **Allows Gradual Adoption**  
  Existing Java code can remain unchanged while selectively adopting JPlus syntax

- **Compiles to Plain Java Code**  
  Converts JPlus syntax into clean, version-flexible Java code that runs on any standard Java compiler or JVM without extra dependencies.

---

## 🛠️ Key Features

| Feature                           | Description                                                                                |
|-----------------------------------|--------------------------------------------------------------------------------------------|
| **Strict Null Checking**          | Prevents null reference errors at compile time                                             |
| **Default Immutable Fields**      | Class fields are immutable by default; use **mutable keyword** when mutability is required |
| **Boilerplate Code Genererating** | Minimizes boilerplate code                                                                 |
| **Named Parameter (Planned)**     | Allows passing arguments by name                                                           |

---

## ✨ Tutorials

- [Make Your Java Code Null-Safe Without Rewriting](https://jplus.hashnode.dev/writing-java-code-safely-and-smartly-with-null-safety-using-jplus)
- [Try `apply` in JPlus with built-in boilerplate elimination](https://jplus.hashnode.dev/tired-of-lombok-try-apply-in-jplus-with-built-in-boilerplate-elimination)

---

## 🚀 IntelliJ Plugin 0.1 MVP Alpha Release!

We’re excited to announce the release of **JPlus IntelliJ Plugin (v0.1-mvp-alpha)**! 🎉

The JPlus IntelliJ plugin has been officially released on the JetBrains Marketplace.
- Go to **File > Settings > Plugins** and search for "JPlus" to install it.

<img width="658" height="214" alt="image" src="https://github.com/user-attachments/assets/51a7fad2-7a28-428f-8a75-82e80c985b4f" />

---

## ✨ Key Features
- **Write JPlus code directly in IntelliJ IDEA**
- **Full IDE Support**: syntax highlighting, code completion, and error checking
- **Nullability Checker**: ensures safe handling of null values at compile-time
- **Seamless Java Integration**: easily use JPlus alongside your existing Java projects

![jplus-intellij-plugin-demo](https://github.com/user-attachments/assets/2f65cbb3-e239-4d85-869f-8ed3e30c809a)

🎥 **Watch the demo video:**  [JPlus IntelliJ Plugin Demo](https://youtu.be/0z_aIyBpJso)

Your early feedback will be invaluable in helping us polish the plugin before the official launch. Stay tuned – the public release is coming soon!

---

## 🧩 Upcoming Feature: Named Parameters

We’re working on adding **Named Parameter** support to JPlus — a modern syntax feature that makes method calls more readable and expressive.

```java
// Example (planned syntax)
User.create(name: "Anouk", age: 25);
```

With named parameters, you can clearly indicate the meaning of each argument, reducing ambiguity and improving code readability — especially in methods with multiple parameters.

> 💡 Stay tuned!<br>
> This feature will be available in an upcoming release.

---

## 🚀 New Feature: Apply Syntax for Boilerplate Elimination

JPlus introduces a powerful new **`apply` syntax** to replace common boilerplate patterns previously handled by Lombok annotations. This declarative syntax helps you reduce repetitive code like getters, setters, constructors, equals/hashCode, and builders — all in a clean, compact form.

---

### 🔍 Overview
| JPlus `apply` Syntax                     | Equivalent Lombok Annotation          |
|------------------------------------------|--------------------------------------|
| `data`                                   | `@Data`                              |
| `getter`                                 | `@Getter`                            |
| `setter`                                 | `@Setter`                            |
| `equals`, `hashCode` or combined `equality` | `@EqualsAndHashCode`              |
| `toString`                               | `@ToString`                          |
| `constructors(no)`                       | `@NoArgsConstructor`                 |
| `constructors(all)`                      | `@AllArgsConstructor`                |
| `constructors(required)`                 | `@RequiredArgsConstructor`           |
| `builder`                                | `@Builder`                           |
| `value` or `immutable`                   | `@Value`                             | |
| `logger(slf4j)` / `logger(java)`         | `@Slf4j` / `@Log`                    |

> Additional method-level and delegation features are also supported.

---

### 📝 Example Usage

```java
apply data, builder, constructors(all, no);

apply {
    User.Profile: getter, setter, equality, constructor(all);
}

public class User {
    private String name;
    private int age;

    public class Profile {
        String nickname;
    }
}
```

For detailed example content, please refer to the following link.  
[Go to Example 6](#example6)

---


## 🚧 Project Status: MVP

Please note that **JPlus is currently in its MVP (Minimum Viable Product) stage**.  
This means the language is still under active development, features may be incomplete or subject to change, and bugs may exist.

We welcome feedback, bug reports, and contributions from the community to help shape and improve JPlus into a stable and powerful language.

[💸 Support me on Github](https://github.com/sponsors/nieuwmijnleven)

[💸 Support me on PayPal](https://paypal.me/nieuwmijnleven/20)

---

## ⚙️ Installation & Execution

Follow these steps to get started with JPlus and run example programs.

---

### 1. Prerequisites

- **Java Development Kit (JDK) 20 or higher**
  ```bash
  java -version
  ```

- **Gradle** installed (optional if using the provided Gradle wrapper)
  ```bash
  gradle -v
  ``` 
---

### 2. Download JPlus Compiler
Currently, the JPlus compiler is distributed as a Gradle project.
Clone the repository or download the latest release:
```bash
git clone https://github.com/nieuwmijnleven/JPlus.git
cd JPlus
```

---

### 3. Build the Project

Use the Gradle wrapper to build the compiler and run examples:
```bash
./gradlew app:build
```

---

### 4. Running Examples

To run a specific example file (e.g., NullableType1.jplus), use:
```bash
./gradlew run -Pargs="./src/test/samples/NullableType1.jplus"
```

Replace the argument path with any example you want to execute.

---
### ✅ Example ① - Compile-time null assignment check

JPlus explicitly defines nullability at the type system level and performs **compile-time checks**  
to eliminate the possibility of `NullPointerException` at runtime.

---

#### 📌 Basic Rules

- `Type` → **non-nullable** (cannot be assigned `null`)
- `Type?` → **nullable** (can be assigned `null`)
- `?.` → safely access methods or fields on nullable objects

---

#### 📄 Example – `NullableType1.jplus`

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = null;
    }
}
```

- `s1` is a **nullable** variable and can be assigned `null`.
- `s2` is a **non-nullable** variable, so assigning `null` causes a **compile-time error**.

---

#### ▶️ Run Command

```bash
./gradlew run -Pargs="./src/test/samples/NullableType1.jplus"
```

---

#### ❌ Output

```
Warning: (line:6, column:8) s2 is a non-nullable variable. But null value is assigned to it.
```

---

By enforcing **explicit type declarations** and **compile-time null checks**,  
JPlus helps prevent null-related bugs early in the development process.

This approach is similar to **strict null checking** found in languages like Kotlin and TypeScript,  
but with the key benefit of being **gradually adoptable while preserving Java syntax**.

---
### ✅ Example ② — Enforcing the null-safe operator (`?.`)

In JPlus, **you must use the `?.` operator when accessing nullable variables.**  
If you try to access fields or methods on a potentially-null object without the null-safe operator, a compile-time error will occur.

---

#### 📄 Example – `NullableType2.jplus`

```java
public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = "jplus";

        s1.length();
        s2.length();
    }
}
```

- `s1` is **nullable** but tries to call a method **without using the null-safe operator (`?.`)**  
  → **Compile-time error**

- `s2` is **non-nullable**, so method access is allowed without issue

---

#### ▶️ Run Command

```bash
./gradlew run -Pargs="./src/test/samples/NullableType2.jplus"
```

---

#### ❌ Output

```
Warning: (line:8, column:8) s1 is a nullable variable. But it direct accesses to length(). You must consider to use null-safe operator(?.)
```

---

#### ✅ Corrected Code (Works as Expected)

```java
s1?.length(); // null-safe access
s2.length();  // regular access
```

---

As shown above, JPlus **enforces safe access to nullable variables at the language level**,  
helping to eliminate null-related runtime errors before they happen.

---
### ✅ Example ③ — Elvis Operator (`?:`)

JPlus introduces the **Elvis operator (`?:`)**,  
which allows you to easily provide a default or fallback value when a nullable variable is `null`.

---

#### 📄 Example – `ElvisOperator.jplus`

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = s1 ?: "jplus";
        System.out.printf("s1 = %s\n", s1 ?: "null-value");
        System.out.printf("s2 = %s\n", s2);
    }
}
```

- `s1` is a nullable variable.
- `s1 ?: "jplus"` assigns `"jplus"` if `s1` is `null`.
- When printing, `s1 ?: "null-value"` safely handles null values.

---

#### ▶️ Run Command

```bash
./gradlew run -Pargs="./src/test/samples/ElvisOperator.jplus"
```

---

#### ✅ Output (Java code generated by JPlus)

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        @org.jspecify.annotations.Nullable String s1 = null;
        String s2 = java.util.Optional.ofNullable(s1).orElseGet(() -> "jplus");
        System.out.printf("s1 = %s\n", java.util.Optional.ofNullable(s1).orElseGet(() -> "null-value"));
        System.out.printf("s2 = %s\n", s2);
    }
}
```

> The Elvis operator expression `x ?: y` is translated to Java's ternary expression `(x != null) ? x : y`.

---
### ✅ Example ④ — Correct Usage of the Null-safe Operator (`?.`)

In JPlus, the use of the **null-safe access operator (`?.`) is enforced** for nullable variables.  
This prevents null reference errors and allows safe method calls on potentially null values.

---

#### 📄 Example – `NullsafeOperator.jplus`

```java
public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = "jplus";
        System.out.printf("the length of s1 : %d\n", s1?.length());
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

- `s1` is a nullable variable.
- `s1?.length()` safely returns `null` if `s1` is `null`.
- `s2` is non-nullable, so calling `length()` is valid without safety checks.

---

#### ▶️ Run Command

```bash
./gradlew run -Pargs="./src/test/samples/NullsafeOperator.jplus"
```

---

#### ✅ Output (Java code generated by JPlus)

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        @org.jspecify.annotations.Nullable String s1 = null;
        String s2 = "jplus";
        System.out.printf("the length of s1 : %d\n", java.util.Optional.ofNullable(s1).map(t0 -> t0.length()).orElse(null));
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

---

This example demonstrates how JPlus handles the null-safe operator for nullable variables  
and clearly shows how to write code that adheres to JPlus's null safety rules.

---
### ✅ Example ⑤ — Combining `?.` and `?:` Operators

JPlus supports **combining the null-safe access operator (`?.`) and the Elvis operator (`?:`)**  
to simplify complex null-handling logic into clean and concise expressions.

---

#### 📄 Example – `NullsafeWithElvisOperator.jplus`

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        String? s1 = null;
        String s2 = s1 ?: "jplus";
        System.out.printf("the length of s1 : %d\n", s1?.length() ?: 0);
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

- `s1` is a nullable variable.
- `s1 ?: "jplus"` → assigns `"jplus"` if `s1` is null.
- `s1?.length() ?: 0` → safely calls `length()` on `s1`, returns `0` if `s1` is null.
- By combining both operators, **null handling becomes safe and concise**.

---

#### ▶️ Run Command

```bash
./gradlew run -Pargs="./src/test/samples/NullsafeWithElvisOperator.jplus"
```

---

#### ✅ Output (Java code generated by JPlus)

```java
package jadex.example;

public class Main {
    public static void main(String[] args) {
        @org.jspecify.annotations.Nullable String s1 = null;
        String s2 = java.util.Optional.ofNullable(s1).orElseGet(() -> "jplus");
        System.out.printf("the length of s1 : %d\n", java.util.Optional.ofNullable(s1).map(t0 -> t0.length()).orElseGet(() -> 0));
        System.out.printf("the length of s2 : %d\n", s2.length());
    }
}
```

> The expression `s1?.length() ?: 0` is translated into a nested conditional check in Java:  
> `java.util.Optional.ofNullable(s1).map(t0 -> t0.length()).orElseGet(() -> 0)`, ensuring safe execution.

---
<a name="example6"></a>
### ✅ Example ⑥ — Using apply for Data Class and Nested Class Boilerplate Elimination

JPlus introduces the apply keyword to replace common Java boilerplate code such as getters, setters, constructors, builders, and more.
It serves as a language-level alternative to Lombok annotations, offering a clean and declarative syntax.

---

#### 📄 Example – `ApplyStatement.jplus`

```java
package com.example;

apply data, constructor(required, all, no), builder;
apply {
    User.Profile: getter, setter, equality, constructor(all);
}

public class User {
    private String name;
    private int age;

    public class Profile {
        String nickname;
    }
}
```
- `data`:	Automatically generates getters, setters, equals(), hashCode(), and toString()
- `builder`: Generates a User.Builder class for fluent object creation
- `constructors(required, all, no)`:Generates a constructor with all/required fields and a no-argument constructor
- `equality`: Generates equals() and hashCode() methods
- `apply { User.Profile: ... }`: Applies boilerplate generation specifically to the Profile inner class
---

#### ▶️ Run Command

```bash
./gradlew run -Pargs="./src/test/samples/ApplyStatement.jplus"
```
---

#### ✅ Output (Java code generated by JPlus)

```java
package com.example;

//apply data, constructor(required, all, no), builder;
//apply {
//    User.Profile: getter, setter, equality, constructor(all);
//}

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {}

    public class Profile {
        String nickname;

        public Profile(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Profile profile = (Profile) o;
            return java.util.Objects.equals(nickname, profile.nickname);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(nickname);
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name+ ", " + "age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age
                && java.util.Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, age);
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(name, age);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
```
---
#### 🎯 Benefits of This Approach

- ✅ Eliminates the need for manual method and constructor generation
- ✅ Clean, declarative syntax – no annotations or external dependencies
- ✅ Fine-grained control over nested types (e.g. inner classes)
- ✅ All features translate into standard Java code at compile time
---
#### 📘 Tip: Use apply {} block for Nested Classes

The apply block allows you to target specific nested or inner classes with scoped boilerplate logic:
```java
apply {
    Outer.Inner: getter, setter;
}
```

This allows developers to keep code DRY and expressive even with deeply nested structures.

---

### 📌 Summary

This example demonstrates a **natural combination** of the following features:

- ✅ **Null-safe method call** (`s1?.length()`)  
  Ensures safe access to nullable variables without throwing `NullPointerException`.

- ✅ **Default fallback value** (`?: 0`)  
  Provides a default value when an expression evaluates to `null`, improving code safety and readability.

- ✅ **Declarative boilerplate generation with `apply`**  
  The `apply` statement allows you to automatically generate common methods like `getters`, `setters`, `equals()`, `hashCode()`, `toString()`, and constructors without relying on annotations or third-party libraries like Lombok.

JPlus allows even complex null-handling logic to be expressed **safely and concisely** with its extended syntax.

---

### 📚 Summary of Examples

| Example File                         | Description                                                |
|-------------------------------------|------------------------------------------------------------|
| `NullableType1.jplus`               | Error when assigning `null` to a non-nullable variable     |
| `NullableType2.jplus`               | Error when accessing a nullable variable without `?.`      |
| `ElvisOperator.jplus`               | Handling null defaults using the Elvis operator `?:`       |
| `NullsafeOperator.jplus`            | Safe method call using the null-safe operator `?.`         |
| `NullsafeWithElvisOperator.jplus`  | Combining `?.` and `?:` for safe and concise null handling |
| `ApplyStatement.jplus`              | Using apply to generate boilerplate and builder logic cleanly|

---

## 🌟 Reference Examples

- **Kotlin:** The leading JVM Java superset language

- **Scala:** Combines functional and object-oriented paradigms, powerful but complex

- **Groovy:** Emphasizes dynamic typing and conciseness

- **Xtend:** Provides Java compatibility with concise syntax


---

## 🎯 JPlus Differentiators

- Maintains maximum similarity to Java’s standard syntax

- **No existing ‘superset’ language keeps Java syntax almost unchanged while extending language-level null checks; JPlus fills this role**

- Enforces strict null safety

- Supports gradual and flexible syntax extensions

- Offers a gentle learning curve for Java developers


---

## 🤝 Sponsorship & Support

JPlus is currently in its MVP stage, and your support can make a big difference in shaping its future.

- **Report bugs, suggest features, and contribute code** — every contribution helps!
- By sponsoring, you help accelerate development and gain early access to new features.
- We are preparing exclusive perks for sponsors as a token of our gratitude.

If you want to support the project financially, please visit our sponsorship page:

[Become a Sponsor via Github](https://github.com/sponsors/nieuwmijnleven)

[Become a Sponsor via PayPal.me](https://paypal.me/nieuwmijnleven)

Thank you for helping us build a better, safer, and more productive programming language!

---

## 🌟 Thanks to my sponsor!

Special thanks to [@shocklateboy92](https://github.com/shocklateboy92) for supporting this project through GitHub Sponsors!

---
