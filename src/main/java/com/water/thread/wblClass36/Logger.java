package com.water.thread.wblClass36;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-28
 */
public class Logger {


    enum LEVEL{
        INFO ,ERROR
    }

    class LogMsg{
        LEVEL level;
        String msg;
        LogMsg(LEVEL lev , String msg){}

        @Override
        public String toString() {
            return "LogMsg{" +
                    "level=" + level +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    //任务队列
    final BlockingQueue<LogMsg> bq = new LinkedBlockingQueue(5);
    //flush 批量
    static final int batchSize = 500;
    //只需要一个线程写日志
    ExecutorService es = Executors.newFixedThreadPool(1);
    //启动写日志线程
    void start() throws Exception {
        File file = File.createTempFile("foo" ,".log");
        final FileWriter writer = new FileWriter(file);
        this.es.execute(()->{
            try {
                //未刷盘日志数量
                int curIdx = 0;
                long preFT = System.currentTimeMillis();
                while (true){
                    try {
                        LogMsg log = bq.poll(5 , TimeUnit.SECONDS);
                        //写日志
                        if (log != null){
                            writer.write(log.toString());
                            ++curIdx;
                        }
                        //如果不存在未刷盘数据,则无需刷盘
                        if (curIdx <= 0){
                            continue;
                        }
                        if (log != null &&
                            log.level ==LEVEL.ERROR ||
                            curIdx == batchSize ||
                            System.currentTimeMillis()-preFT > 500 ){
                            writer.flush();
                            curIdx = 0;
                            preFT = System.currentTimeMillis();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //写 info 级别日志
    void info(String msg) throws InterruptedException {
        bq.put(new LogMsg(LEVEL.ERROR , msg));
    }

    //写error 级别日志
    void error(String msg) throws InterruptedException {
        bq.put(new LogMsg(LEVEL.ERROR ,msg));
    }
}
