package rxtd.rainmeter.core;

/**
 * Used for name generation.
 */
public interface NamePrefixProvider {
    String nextVariablesPrefix(String suggest);

    String nextMeasuresPrefix(String suggest);

    String nextMetersPrefix(String suggest);

    String nextIncludePrefix(String suggest);
}
