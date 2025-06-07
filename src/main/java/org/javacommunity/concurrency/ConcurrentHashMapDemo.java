package org.javacommunity.concurrency;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    static ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
    
    public static void main(String[] args) {
        chm.put("a", "aaa");
        chm.put("b", "bbb");
        chm.put("c", "ccc");
        
        new Thread(){
            public void run(){
                Iterator<Entry<String, Object>> it = ConcurrentHashMapDemo.chm.entrySet().iterator();
                while(it.hasNext()){
                    Entry<String, Object> en = it.next();
                    if(en.getKey().equals("a") || en.getKey().equals("b")){
                    	   it.remove();
                    	try {
            				Thread.sleep(1000l);
            			} catch (InterruptedException e) {
            				e.printStackTrace();
            			}
                    }
                }
            }
        }.start();

        new Thread(){
            public void run(){
            	try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
                Iterator<Entry<String, Object>> it = ConcurrentHashMapDemo.chm.entrySet().iterator();
                while(it.hasNext()){
                    Entry<String, Object> en = it.next();
                    System.out.print(en.getKey()+", ");
                }
            }
        }.start();  
        
        try {
			Thread.sleep(5000l);
			System.out.print(chm.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}
