package org.ruhuna;

import java.util.List;

public class CacheStore implements NameStore{

    private NameStore fileBasedNamesStore;

    public CacheStore(NameStore fileBasedNamesStore) {
        this.fileBasedNamesStore = fileBasedNamesStore;
    }
    NameStore inMemoryNameStore;
    @Override
    public void save(String name) {

        fileBasedNamesStore.save(name);
        if(inMemoryNameStore != null)
        {
            inMemoryNameStore.save(name);
        }

    }

    @Override
    public List<String> getNames() {
        if(inMemoryNameStore == null)
        {
            inMemoryNameStore = new InMemoryNameStore();
            List<String> names = fileBasedNamesStore.getNames();
            for (String name : names)
            {
                inMemoryNameStore.save(name);
            }
        }
        return inMemoryNameStore.getNames();
    }
}
