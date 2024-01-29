
/*
 * Primitive types have a fixed size in memory (e.g., int is always 4 bytes).
 * Accessed directly by their value.
 */
class PrimitiveDataTypes {
    
    public static void main(String[] args) {
        
        // 1. Integer Types(the integer types), in real app use int only

            // byte: 8-bit signed integer, 1 bit for the sign, 7  bits for the number, dor that it's 127
            // Range: -128 to 127
            byte exampleByte = 100;
            // This is a byte variable with a value of 100

            // short: 16-bit signed integer.
            // Range: -32,768 to 32,767
            short exampleShort = 1000;
            // This is a short variable with a value of 1000

            // int: 32-bit signed integer.
            // Range: -2^31 to 2^31-1
            int exampleInt = 100000;
            // This is an int variable with a value of 100000

            // long: 64-bit signed integer.
            // Range: -2^63 to 2^63-1
            long exampleLong = 1000000000L;
            // This is a long variable with a value of 1000000000
            
        // 2. Floating Point Types

            // float: 32-bit IEEE 754 floating point.
            // Range: approximately ±1.4E-45 to ±3.4028235E38
            float exampleFloat = 10.123f;
            // This is a float variable with a value of 10.123
            
            // double: 64-bit IEEE 754 floating point.
            // Range: approximately ±4.9E-324 to ±1.7976931348623157E308
            double exampleDouble = 100.123456;
            // This is a double variable with a value of 100.123456
            
            // 3. char Type
            
            // char: 16-bit Unicode character.
            // Chars must use single quote 'A'
            char exampleChar = 'A';
            // This is a char variable with a value of 'A'
            
            // 4. boolean Type
            
            // boolean: true or false.
            // Size: not precisely defined, but typically one bit of information
            boolean exampleBoolean = true;
            // This is a boolean variable with a value of true
            
            
            System.out.println(exampleByte);
            System.out.println(exampleShort);
            System.out.println(exampleInt);
            System.out.println(exampleLong);
            System.out.println(exampleFloat);
            System.out.println(exampleDouble);
            System.out.println(exampleChar);
            System.out.println(exampleBoolean);
        }
    }
    