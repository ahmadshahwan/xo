package org.example.xo.local;

import org.example.xo.terminal.Terminal;

import java.util.Scanner;

public class LocalTerminal implements Terminal {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine() {
        return this.scanner.nextLine();
    }
}
