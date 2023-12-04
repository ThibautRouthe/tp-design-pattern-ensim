package fr.ensim.dp.cache;

public class FactoryCache {
    public ICache memoryCache(){
        return MemoryCache.getInstance();
    }

    public ICache diskCache(String typeMap){
        return DiskCache.getInstance(typeMap);
    }
}
