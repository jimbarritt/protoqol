package org.protoqol.logging.io;

import java.io.*;

import static org.protoqol.logging.io.IoExecution.*;

public class Io {


    public static void closeQuietly(final InputStream inputStream) {
        executeIo(new IoOperation() {
            @Override public void execute() throws IOException {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        });
    }
}