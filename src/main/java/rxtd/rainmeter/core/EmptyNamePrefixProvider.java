package rxtd.rainmeter.core;

public class EmptyNamePrefixProvider implements NamePrefixProvider {
    private String generate(String suggest) {
        if (suggest == null) {
            throw new RuntimeException("suggested name can'n be null");
        }
        return suggest;
    }

    @Override
    public String nextVariablesPrefix(String suggest) {
        return this.generate(suggest);
    }

    @Override
    public String nextMeasuresPrefix(String suggest) {
        return this.generate(suggest);
    }

    @Override
    public String nextMetersPrefix(String suggest) {
        return this.generate(suggest);
    }

    @Override
    public String nextIncludePrefix(String suggest) {
        return this.generate(suggest);
    }
}
