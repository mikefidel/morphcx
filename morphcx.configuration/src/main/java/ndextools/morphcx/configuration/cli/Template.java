package ndextools.morphcx.configuration.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public interface Template {

    Configuration configure(final Builder builder) throws ParseException;

    String toString();

    /**
     * When there are no extended command-line options to define,
     * only base options are processed by default
     */
    default Options defineExtendedOptions(Options options) throws ParseException {
        return options;
    }

    /**
     * When there are no extended command-line options to resolve,
     * only base options are processed by default.
     */
    default void resolveExtendedOptions(CommandLine parsedCommandline, Builder builder) {
        // There are no extended options to resolve, by default.
    }

}