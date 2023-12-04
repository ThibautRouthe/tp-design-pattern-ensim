package fr.ensim.dp.cache.filter;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogFilterCache implements IFilterCache{
    private IFilterCache next;
    private static final Logger log = LogManager.getLogManager().getLogger("FilterLog");
    @Override
    public byte[] doAdd(String key, byte[] buf) throws IOException {
        log.info("doAdd "+key+" "+buf);
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
        log.info("doRetreive "+key+" "+buf);
        return buf;
    }

    @Override
    public IFilterCache setNext(IFilterCache next) {
        this.next=next;
        return this.next;
    }
}
