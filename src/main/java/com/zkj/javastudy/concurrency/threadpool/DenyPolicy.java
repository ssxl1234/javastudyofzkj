package com.zkj.javastudy.concurrency.threadpool;
//定制线程池对新提交的任务的策略
public interface DenyPolicy {
    void reject(Runnable runnable,ThreadPool pool);
    class DiscardDenyPolice implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool pool) {
            //do nothing;直接丢弃
        }
    }
    class AbortDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool pool) throws RunableDenyException {
            throw new RunableDenyException("this runable"+ runnable +"will abort");
        }
    }
    class RunnerDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, ThreadPool pool) {
            if(!pool.isShutdown()){
                runnable.run();
            }
        }
    }
}
