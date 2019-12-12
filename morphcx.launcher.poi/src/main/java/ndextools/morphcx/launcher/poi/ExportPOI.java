package ndextools.morphcx.launcher.poi;

import ndextools.morphcx.configuration.cli.*;

public class ExportPOI {

    public static void main(final String[] args) {
        String[] commandline = (args == null) ? new String[0] : args;
        String appName = ExportPOI.class.getSimpleName().toLowerCase();

        try {
            Configuration cfg = configureByCLI(commandline, appName);
            dispatchByOperation(cfg);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Configuration configureByCLI(final String[] commandline, final String appName)
            throws org.apache.commons.cli.ParseException {
        Template template = new TemplateImpl(commandline, appName);
        Builder builder = new BuilderImpl();
        return template.configure(builder);
    }

    private static void dispatchByOperation(final Configuration cfg) {
        if (!cfg.isShowHelpPrompt()) {
            // TODO
        }
    }

}
