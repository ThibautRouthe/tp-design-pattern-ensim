package fr.ensim.dp.cache.filter;

import java.io.IOException;

public class CountFilterCache implements IFilterCache{
    private IFilterCache next;
    private int addCount=0;
    private int retreiveCount =0;
    @Override
    public byte[] doAdd(String key, byte[] buf) throws IOException {
        addCount++;
        if(next!=null){
            return next.doAdd(key,buf);
        }
        return buf;
    }

    @Override
    public byte[] doRetreive(String key, byte[] buf) throws IOException {
        if(next!=null){
            return next.doRetreive(key,buf);
        }
        retreiveCount++;
        return buf;
    }

    @Override
    public IFilterCache setNext(IFilterCache next) {
        this.next=next;
        return this.next;
    }
}
