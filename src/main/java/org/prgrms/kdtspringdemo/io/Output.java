package org.prgrms.kdtspringdemo.io;

import org.springframework.stereotype.Component;

@Component
public interface Output {
    void printPrompt(String prompt);

    void printText(String s);

    void printError(Exception e);
}
