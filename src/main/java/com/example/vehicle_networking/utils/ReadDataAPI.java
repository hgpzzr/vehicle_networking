package com.example.vehicle_networking.utils;

import com.example.vehicle_networking.thread.ReadDataThread;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:08
 */
@Slf4j
public class ReadDataAPI {
    public final static String url = "http://tk.ipmph.com/exam/a/api/asyncUser";

    public static Thread findThread(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                log.info(" find ---> {} ",threads[i].getName());
                if(("collectDataThread-" + threadId).equals(threads[i].getName())) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }

//    public static native int getTid();
//
//    static {
//        System.loadLibrary("GetThreadID");
//    }

    public static boolean checkCollectThread(Thread.State expectState, long threadId){
        Thread thread = findThread(threadId);

        log.info(" thread. --> {}",thread);
        if (thread != null) {
            log.info(" thread.isAlive() --> {}",thread.isAlive());
        }
        return thread != null && thread.isAlive();
    }
}
