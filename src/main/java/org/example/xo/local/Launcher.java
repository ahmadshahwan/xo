package org.example.xo.local;

import org.example.xo.Gameplay;
import org.example.xo.terminal.TerminalGameplay;

public class Launcher {

    public static void main(String[] args) {
        new TerminalGameplay(new LocalTerminal()).run();
    }
}
