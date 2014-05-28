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
