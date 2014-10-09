✅ interfaces, abstract classes
✅ declaration , class name , variable
✅ generics
✅ Overloaded and overridden methods
✅ Access Control
✅ Wrapper classes
✅ Flow control
✅ Exceptions
✅ Assertions
✅ Inner Classes
✅ Calendar, Datetime, locale
✅ String, Builder, Buffer
✅ Thread, synchronizing, wait, notify
✅ Inner classes
✅ Serialization
✅ Development - classpath 
✅ File Reader, Write, Buffered
✅ Coupling, Cohesion
Collections
Pattern, tokenizing



##Chap 1.

Legal Identifiers,
- can start with underscore or currency character or letter, but not with number
- after first character, any combination of letters/numbers/connecting-characters/currency-characters
- No limit on length of identifier
- can't be a keyword
- case sensitive foo and FOO are different


Source File Declaration Rules
- Only one public class per file name of which should match with filename.
- A file can have more than one non-public classes
- Files with no public classes can have a name that does not match any of the class in the file.

Access Modifier: public, protected, private
Non-access modifier: strictfp, final, abstract

Class can have final, abstract or strictfp

Methods in abstract class end in semicolon.

A class can't be marked abstract and final both.

Interfaces
- all methods are implicitly public and abstract
- all variable must be public, static and final i.e. can only declare constants, not instance variables
- must not be static
- Because interface methods are abstract, they cannot be marked final, strictfp or native.
- public modifier is required if we want interface to have public access

A private method can't be inherited hence can't be overridden by a subclass
Subclass can see protected members only through inheritance


http://www.coderanch.com/how-to/java/SCJP-FAQ#tripsTraps
  
Two top-level public classes cannot be in the same source file.  
main() cannot call an instance (non-static) method.  
Methods can have the same name as the constructor(s).  
Watch for thread initiation with classes that don't have a run() method.  
Local classes cannot access non-final variables.  
Case statements must have values within permissible range.  
Watch for Math class being an option for immutable classes.  
instanceOf is not the same as instanceof.  
Constructors can be private.  
Assignment statements can be mistaken for a comparison; e.g., if(a=true)...  
Watch for System.exit() in try-catch-finally blocks.  
Watch for uninitialized variable references with no path of proper initialization.  
Order of try-catch-finally blocks matters.  
main() can be declared final.  
-0.0 == 0.0 is true.  
A class without abstract methods can still be declared abstract.  
Map does not implement Collection.  
Dictionary is a class, not an interface.  
Collection (singular) is an Interface, but Collections (plural) is a helper class.  
Class declarations can come in any order (e.g., derived first, base next, etc.).  
Forward references to variables gives a compiler error.  
Multi-dimensional arrays can be "sparse" -- i.e., if you imagine the array as a matrix, every row need not have the same number of columns.  
Arrays, whether local or class-level, are always initialized  
Strings are initialized to null, not empty string.  
An empty string is not the same as a null reference.  
A declaration cannot be labelled.  
continue must be in a loop (e.g., for, do, while). It cannot appear in case constructs.  
Primitive array types can never be assigned to each other, even though the primitives themselves can be assigned. For example, ArrayofLongPrimitives = ArrayofIntegerPrimitives gives compiler error even though longvar = intvar is perfectly valid.  
A constructor can throw any exception.  
Initializer blocks are executed in the order of declaration.  
Instance initializers are executed only if an object is constructed.  
All comparisons involving NaN and a non-NaN always result in false.  
Default type of a numeric literal with a decimal point is double.  
int and long operations / and % can throw an ArithmeticException, while float and double / and % never will (even in case of division by zero).  
== gives compiler error if the operands are cast-incompatible.  
You can never cast objects of sibling classes (sharing the same parent).  
equals() returns false if the object types are different. It does not raise a compiler error.  
No inner class (non-static inner class) can have a static member.  
File class has no methods to deal with the contents of the file.  
InputStream and OutputStream are abstract classes  



Check   
http://suhrid.net/wiki/index.php?title=Instantiation  
http://www.javacoffeebreak.com/articles/toptenerrors.html  
http://www.coderanch.com/how-to/java/ScjpMockTests   
