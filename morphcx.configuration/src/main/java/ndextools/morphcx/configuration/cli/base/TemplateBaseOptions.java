package ndextools.morphcx.configuration.cli.base;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Implementation class for creating CSV-type Configuration objects.
 */
public final class TemplateBaseOptions extends TemplateAbstraction implements Template {

    /**
     * Constructor
     * @param commandline command-line when application was invoked
     * @param appName class used to launch this application
     */
    public TemplateBaseOptions(final String[] commandline, final String appName) {
        super(commandline, appName);
    }

    @Override
    public Configuration configure(Builder bldr) throws ParseException {

        // Apache Commons CLI Step 1
        Options optionDefinitions = defineBaseOptions();

        // Apache Commons CLI Step 2
        CommandLine parsedCommandline = parseCommandline(optionDefinitions, commandline);

        // Apache Commons CLI Step 3
        Builder builder = resolveBaseOptions(parsedCommandline, bldr);

        // Create instance of Configuration
        return builder.getInstance();
    }

}