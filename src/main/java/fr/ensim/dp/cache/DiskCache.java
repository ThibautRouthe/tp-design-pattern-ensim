package fr.ensim.dp.cache;

import fr.ensim.dp.cache.filter.IFilterCache;
import fr.ensim.dp.util.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class DiskCache implements ICache{
    static HashMap<String,DiskCache> diskCache = new HashMap<>();
    File dir;
    private IFilterCache filter;

    private DiskCache(String typeMap){
        dir=new File(typeMap);
        dir.mkdir();
    }

    public static DiskCache getInstance(String typeMap){
        DiskCache dC = diskCache.get(typeMap);
        if ( dC == null ) {
            dC = new DiskCache(typeMap);
            diskCache.put(typeMap,dC);
        }
        return dC;
    }

    @Override
    public long size() {
        int memorysize=0;
        for(File f : dir.listFiles()){
            memorysize+=f.length();
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
        File file = new File(dir, key);
        FileUtil.copy(new ByteArrayInputStream(buffilter), file);
        return true;
    }

    @Override
    public byte[] retreive(String key) throws IOException {
        File file = new File(dir, key);
        byte[] buf = FileUtil.readFile(file);
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
        FileUtil.deleteDirectory(dir);
        dir.mkdir();
    }

    @Override
    public void setFilterCache(IFilterCache filter) {
        this.filter=filter;
    }
}
