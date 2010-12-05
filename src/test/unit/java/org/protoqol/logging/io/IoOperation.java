package org.protoqol.logging.io;

import java.io.*;

public interface IoOperation {
    void execute() throws IOException;
}