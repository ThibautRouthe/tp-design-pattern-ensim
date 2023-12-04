package fr.ensim.dp.cache.filter;

import fr.ensim.dp.cache.MemoryCache;
import fr.ensim.dp.util.GzipUtil;

import java.io.IOException;

public class CompressFilterCache implements IFilterCache{
    private IFilterCache next;
    @Override
    public byte[] doAdd(String key, byte[] buf) throws IOException {
        byte[] cbuf = GzipUtil.compress(buf);
        if(next!=null){
            return next.doAdd(key,cbuf);
        }
        return cbuf;
    }

    @Override
    public byte[] doRetreive(String key, byte[] buf) throws IOException {
        if(next!=null){
            return GzipUtil.uncompress(next.doRetreive(key,buf));
        }
        return GzipUtil.uncompress(buf);
    }

    @Override
    public IFilterCache setNext(IFilterCache next) {
        this.next=next;
        return this.next;
    }
}
