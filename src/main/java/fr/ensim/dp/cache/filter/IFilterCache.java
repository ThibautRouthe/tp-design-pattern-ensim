package fr.ensim.dp.cache.filter;

import java.io.IOException;

/**
 * Chain of responsbility
 * 
 */
public interface IFilterCache {

  /**
   * @param key
   * @param buf
   */
  byte[] doAdd(String key, byte[] buf) throws IOException;

  /**
   * @param key
   * @return <code>true</code>, s'il ne faut pas appele de suivant.
   */
  byte[] doRetreive(String key, byte[] buf) throws IOException;
  
  
  public IFilterCache setNext(IFilterCache next);

}
