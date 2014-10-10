
- Can be applied to 
1. strictfp - Class Method


- The abstract methods in type abstract class can only set a visibility modifier, one of public or protected
- Interface methods are implicitly public abstract
- method local inner classes can't be static


- Enum constructors can'be public, protected. 
- arrays implement clonable and seriable. clone is the only method not inherited from Object.


- compiler can't choose between primitive var-arg and boxed var-args
- Assertions
   - Don't Use Assertions to Validate Arguments to a Public Method
   - Do Use Assertions to Validate Arguments to a Private Method
   - Don't Use Assertions to Validate Command-Line Arguments
   - Do Use Assertions, Even in Public Methods, to Check for Cases that You Know Are Never, Ever Supposed to Happen
   - Don't Use Assert Expressions that Can Cause Side Effects!


- If a parent constructor throws a check or uncheck exception, the child needs write the consutructor throwing same exception atleast or a broader

- wait() throws InterruptedException

- Arrays.sort() throw Runtime Error is object is not comparable
- Collections.sort() give compile time error if object is not comparable

- advanced for can't be used over a iterator

- Integer parse gives NumberFormatException not NullPointerException if we pass null

- Thread MAX(10), NORM(5), MIN(1) priorities

