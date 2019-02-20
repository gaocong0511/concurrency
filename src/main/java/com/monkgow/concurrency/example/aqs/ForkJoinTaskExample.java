package com.monkgow.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: gaocong
 * @Date: 2019/02/19
 * @Description:
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {
    private int start;
    private int end;

    public static final int threadHold = 2;

    private ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threadHold;
        if(canCompute){
            for (int i =start;i<=end;i++){
                sum+=i;
            }
            //如果任务大于阈值  那么就分裂成两个子任务进行计算
        }else{
            int middle=(start+end)/2;
            ForkJoinTaskExample leftTask=new ForkJoinTaskExample(start,middle);
            ForkJoinTaskExample rightTask=new ForkJoinTaskExample(middle+1,end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            int leftResult=leftTask.join();
            int rightResult=rightTask.join();
            sum+=leftResult+rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTaskExample forkJoinTaskExample=new ForkJoinTaskExample(1,1000);

        Future<Integer> result=forkJoinPool.submit(forkJoinTaskExample);

        try {
            log.info("result:{}",result.get());
        }catch (Exception e){
            log.error("Exception e");
        }
    }
}
