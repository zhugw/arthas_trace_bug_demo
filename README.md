# arthas_trace_bug_demo
After executing trace , there is NPE generation, this demo is just for reproduce this phenomenon

Reproduce step
1. Run ArthasTraceBugDemoApplication in idea
2. Open arthas and attach the process
3. Execute below command
```
[arthas@3740]$ trace com.example.arthas_trace_bug_demo.ArthasTraceBugDemoApplication process
[arthas@3740]$ stop
```
4. The phenomenon is reproduced. From Console of idea you will see
```
java.lang.NullPointerException
	at com.example.arthas_trace_bug_demo.ArthasTraceBugDemoApplication.process(ArthasTraceBugDemoApplication.java:65)
	at com.example.arthas_trace_bug_demo.ArthasTraceBugDemoApplication.lambda$run$1(ArthasTraceBugDemoApplication.java:48)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
java.lang.NullPointerException
	at com.example.arthas_trace_bug_demo.ArthasTraceBugDemoApplication.process(ArthasTraceBugDemoApplication.java:65)
	at com.example.arthas_trace_bug_demo.ArthasTraceBugDemoApplication.lambda$run$1(ArthasTraceBugDemoApplication.java:48)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
```

