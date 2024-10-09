package lesson28.generic;

/*
Type Parameter Naming Conventions:
By convention, type parameter names are single, uppercase letters.
The most commonly used type parameter names are:
E - Element (used extensively by the Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
S,U,V etc. - 2nd, 3rd, 4th types
 */

public interface Pair<K, V> {
    K first();
    V second();
}
