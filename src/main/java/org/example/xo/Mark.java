package org.example.xo;

public enum Mark {
    X,
    O;

    public Mark other() {
        return this == X ? O : X;
    }
}
