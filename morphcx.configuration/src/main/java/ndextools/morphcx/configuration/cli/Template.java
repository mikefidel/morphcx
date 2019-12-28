package ndextools.morphcx.configuration.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public interface Template {

    Configuration configure(final Builder bldr) throws ParseException;

//    Options defineExtendedOptions(Options opts) throws ParseException;
//
//    Builder resolveExtendedOptions(final CommandLine parsedCommandline, final Builder bldr);

}