package org.ruhuna;

import java.util.List;

public interface NameStore {
    void save(String name);
    List<String> getNames();
}
