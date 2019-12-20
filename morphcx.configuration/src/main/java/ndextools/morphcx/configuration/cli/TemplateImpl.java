package ndextools.morphcx.configuration.cli;

import org.apache.commons.cli.CommandLine;

public class TemplateImpl extends AbstractTemplate {

    public TemplateImpl(final String[] commandline, final String appName) {
        super(commandline, appName);
    }

    @Override
    protected Builder resolveExtendedOptions(CommandLine parsedCommandline, Builder builder) {
        return builder;
    }

    @Override
    public String toString() {
        return "Hello";
    }

}