package org.ruhuna;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

public class InMemoryNameStore implements NameStore{

    private List<String> namelist = new ArrayList<>();

    @Override
    public void save(String name) {
        namelist.add(name);
    }

    @Override
    public List<String> getNames() {
       return namelist;
    }
}
