package com.naresh.m_concurrencyDefogTech;
/*
Asynchronous programing is about writing nonblocking code
Ex: This is synchronous programing
Here main thread is blocked & CPU is not utilized properly, we can't create more threads to use CPU efficiently because threads are expense,
So we need nonblocking operation so that CPU will be utilized properly for that we need async programming
Java Fibers(future projects of java) provides creating millions of lighweight threads run by limited OS threads, so that CPU will utilized properly
 main Thread ------------>-------->t.get()(Main thread is blocked)-------------------> Now main will proceed
                            |                                                           |
                            |--------->thread1---------------------------------------->results are ready
Ex: of synchronous programing
for(Employee emp:empList){
    Future<EmployeeDetails> fut1 = executor.submit(getEmpDetails(emp.getId));
    fut1.get();//blocking operation

    Future<EmployeeDetails> fut2 = executor.submit(getEmpSalary(emp.getId));
    fut2.get();//Blocking operation
}
Ex: Async programming
******************
ExecutorService threadpool = Executors.newCachedThreadPool();
Future<Long> futureTask = threadpool.submit(() -> factorial(number));
while (!futureTask.isDone()) {
    System.out.println("FutureTask is not finished yet...");
}
//do something else .....//we are not blocked
long result = futureTask.get();
threadpool.shutdown();
*******************
Java8 completable future provides a machanisum for async programming
Ex: callback chaining
for(Employee emp:empList){
    Future<EmployeeDetails> fut1 = CompletableFuture.supplyAsync(()->getEmpDetails(emp.getId))
                            .thenApply(emp-> executor.submit(getEmpSalary(emp.getId))
                            .thenAccept(sal-> sendEmails());
}
******************
Servlets, NIO's also support Async programming using callback methods.

Advantages of Async Programming:
    - Efficient CPU utilization
    - Scalability / High throughput
    - Data locality & less context switching
    - Reactive
        - Live/hot source of values
        - Backpressure
Disadvantages:
    - Hard to write and reason about
    - CPU bound flows
    - Hard to debug/stack trace
    - Hard to write tests
    - End to End async/ non blocking required
 */
public class _A1_AsyncPrograming {
    public static void main(String[] args) {

    }
}
