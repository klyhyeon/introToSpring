package com.intro.spring.interfaces;

import java.io.IOException;
import java.net.URL;

public interface Resource extends InputStreamSource{
    boolean exists();
    boolean isReadable();
    boolean isOpen();
    URL getURL() throws IOException;
}
