package com.water.thread.wblClass26;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public class MR extends RecursiveTask<Map<String , Long>> {

    private String[] fc;
    private int start , end;
    //构造函数
    public MR(String[] fc , int fr ,int to){
        this.fc = fc;
        this.start = fr;
        this.end = to;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1){
            return calc(fc[start]);
        }else{
            int mid = (start + end)/2;
            MR mr1 = new MR(fc ,start ,mid);
            mr1.fork();
            MR mr2 = new MR(fc ,mid ,end);
            mr2.fork();
            //计算子任务，并返回合并的结果
            return merge(mr2.join() ,mr1.join());
        }
    }

    //合并结果
    private Map<String , Long> merge(Map<String , Long> r1 , Map<String ,Long> r2){
        Map<String ,Long> result = Maps.newHashMap();
        result.putAll(r1);
        //合并结果
        r2.forEach((k ,v)->{
            Long c = result.get(k);
            if (c != null){
                result.put(k ,c+v);
            }else {
                result.put(k ,v);
            }
        });
        return result;
    }

    //统计单词数量
    private Map<String ,Long> calc(String line){
        Map<String , Long> result = Maps.newHashMap();
        //分割单词(用空格分割)
        String[] words = line.split("\\s+");
        //统计单词数量
        for (String w : words) {
            Long v = result.get(w);
            if (v != null){
                result.put(w ,v+1);
            }else {
                result.put(w , 1L);
            }
        }
        return result;
    }

}
