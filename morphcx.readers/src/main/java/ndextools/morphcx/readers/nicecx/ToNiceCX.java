package ndextools.morphcx.readers.nicecx;

import org.ndexbio.model.cx.NiceCXNetwork;
import org.ndexbio.cxio.core.readers.NiceCXNetworkReader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import ndextools.morphcx.configuration.cli.base.*;

public class ToNiceCX {
    private final Configuration cfg;

    public ToNiceCX(Configuration cfg) {
        this.cfg = cfg;
    }

    public NiceCXNetwork makeNiceCX() throws IOException {
        NiceCXNetwork cx;

        if (cfg.usesInputFile()) {

            try (InputStream input = new FileInputStream(String.valueOf(cfg.getInputFilename()));
                 BufferedInputStream bufferedInput = new BufferedInputStream(input))
            {
                NiceCXNetworkReader reader = new NiceCXNetworkReader();
                cx = reader.readNiceCXNetwork(bufferedInput);
            }
            catch (Exception e) {
                String msg = this.getClass().getSimpleName() + ": " + e.getMessage();
                throw new IOException(msg);
            }

        } else {

            try (BufferedInputStream bufferedInput = new BufferedInputStream(System.in)) {
                NiceCXNetworkReader reader = new NiceCXNetworkReader();
                cx = reader.readNiceCXNetwork(bufferedInput);
            }
            catch (Exception e) {
                String msg = this.getClass().getSimpleName() + ": " + e.getMessage();
                throw new IOException(msg);
            }
        }

        return cx;
    }
}
