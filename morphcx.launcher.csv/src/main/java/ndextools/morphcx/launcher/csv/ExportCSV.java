package ndextools.morphcx.launcher.csv;

import ndextools.morphcx.configuration.cli.*;
import ndextools.morphcx.processors.csv.configuration.CSVBuilderImpl;
import ndextools.morphcx.processors.csv.configuration.CSVTemplateImpl;

public class ExportCSV {

    public static void main(final String[] args) {
        String[] commandline = (args == null) ? new String[0] : args;
        String appName = ExportCSV.class.getSimpleName().toLowerCase();

        try {
            Configuration cfg = configureByCLI(commandline, appName);
            dispatchByOperation(cfg);
        } catch (org.apache.commons.cli.ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Configuration configureByCLI(final String[] commandline, final String appName)
            throws org.apache.commons.cli.ParseException {
        Template template = new CSVTemplateImpl(commandline, appName);
        Builder builder = new CSVBuilderImpl();
        return template.configure(builder);
    }

    private static void dispatchByOperation(final Configuration cfg) {
        if (!cfg.isShowHelpPrompt()) {
            // TODO
            System.err.println(cfg.toString()); // TODO remove!
        }
    }

}
