package org.protoqol.logging.io;

import java.io.*;

public class IoRuntimeException extends RuntimeException {
    public IoRuntimeException(IOException e) {
        super(e);
    }
}