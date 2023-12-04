package fr.ensim.dp.cache;

import fr.ensim.dp.cache.filter.IFilterCache;

import java.io.IOException;
import java.util.HashMap;

public final class MemoryCache implements ICache {
    private static MemoryCache memoryCache = null;
    private HashMap<String,byte[]> cache = new HashMap<>();
    private IFilterCache filter;

    private MemoryCache(){
    }

    public static MemoryCache getInstance() {
        if ( memoryCache == null ) {
            memoryCache = new MemoryCache();
        }
        return memoryCache;
    }

    @Override
    public long size() {
        int memorysize=0;
        for(String key : cache.keySet()){
            memorysize+=key.length()*2+cache.get(key).length;
        }
        return memorysize;
    }

    @Override
    public boolean add(String key, byte[] buf) throws IOException {
        byte[] buffilter;
        if(filter!=null){
            buffilter=filter.doAdd(key, buf);
        }else {
            buffilter=buf;
        }
        cache.put(key,buffilter);
        return cache.get(key)==buffilter;
    }

    @Override
    public byte[] retreive(String key) throws IOException {
        byte[] buf = cache.get(key);
        byte[] buffilter;
        if(filter!=null){
            buffilter=filter.doRetreive(key, buf);
        }else {
            buffilter=buf;
        }
        return buffilter;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void setFilterCache(IFilterCache filter) {
        this.filter=filter;
    }
}
