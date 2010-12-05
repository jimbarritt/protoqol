package org.protoqol.logging.io;

import java.io.*;

public class IoExecution {

    public static void executeIo(IoOperation ioOperation) {
        try {
            ioOperation.execute();
        } catch (IOException e) {
            throw new IoRuntimeException(e);
        }
    }
}