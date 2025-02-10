# Issue with Koin dependency injection

in routes/AllHeroes.kt I have the following code:
```kotlin
fun Route.getAllHeroes() {
    // Singleton instance of HeroRepo from koin
    val heroRepository: HeroRepo by inject() // The error is here

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e: NumberFormatException) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiResponse(success = false, message = "Only numbers allowed for page numbers")
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                status = HttpStatusCode.NotFound,
                message = ApiResponse(success = false, message = "Page number not found. ")
            )
        }
    }
}
```
When i open `localhost:8080/boruto/heroes` in the browser, I get code 500 (internal server error) and the following error in the console:
```
java.lang.NoClassDefFoundError: io/ktor/server/routing/RoutingKt
	at org.koin.ktor.ext.RouteExtKt.getKoin(RouteExt.kt:74)
	at com.example.routes.AllHeroesKt$getAllHeroes$$inlined$inject$default$1.invoke(RouteExt.kt:76)
	at kotlin.SynchronizedLazyImpl.getValue(LazyJVM.kt:83)
	at com.example.routes.AllHeroesKt.getAllHeroes$lambda$0(AllHeroes.kt:12)
	at com.example.routes.AllHeroesKt.access$getAllHeroes$lambda$0(AllHeroes.kt:1)
	at com.example.routes.AllHeroesKt$getAllHeroes$1.invokeSuspend(AllHeroes.kt:19)
	at com.example.routes.AllHeroesKt$getAllHeroes$1.invoke(AllHeroes.kt)
	at com.example.routes.AllHeroesKt$getAllHeroes$1.invoke(AllHeroes.kt)
	at io.ktor.server.routing.RoutingNode$buildPipeline$1$1.invokeSuspend(RoutingNode.kt:116)
	at io.ktor.server.routing.RoutingNode$buildPipeline$1$1.invoke(RoutingNode.kt)
	at io.ktor.server.routing.RoutingNode$buildPipeline$1$1.invoke(RoutingNode.kt)
	at io.ktor.util.pipeline.DebugPipelineContext.proceedLoop(DebugPipelineContext.kt:79)
	at io.ktor.util.pipeline.DebugPipelineContext.proceed(DebugPipelineContext.kt:57)
	at io.ktor.util.pipeline.DebugPipelineContext.execute$ktor_utils(DebugPipelineContext.kt:63)
	at io.ktor.util.pipeline.Pipeline.execute(Pipeline.kt:79)
	at io.ktor.server.routing.RoutingRoot$executeResult$$inlined$execute$1.invokeSuspend(Pipeline.kt:481)
	at io.ktor.server.routing.RoutingRoot$executeResult$$inlined$execute$1.invoke(Pipeline.kt)
	at io.ktor.server.routing.RoutingRoot$executeResult$$inlined$execute$1.invoke(Pipeline.kt)
	at io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode(ContextUtils.kt:17)
	at io.ktor.server.routing.RoutingRoot.executeResult(RoutingRoot.kt:193)
	at io.ktor.server.routing.RoutingRoot.interceptor(RoutingRoot.kt:66)
	at io.ktor.server.routing.RoutingRoot$Plugin$install$1.invokeSuspend(RoutingRoot.kt:143)
	at io.ktor.server.routing.RoutingRoot$Plugin$install$1.invoke(RoutingRoot.kt)
	at io.ktor.server.routing.RoutingRoot$Plugin$install$1.invoke(RoutingRoot.kt)
	at io.ktor.util.pipeline.DebugPipelineContext.proceedLoop(DebugPipelineContext.kt:79)
	at io.ktor.util.pipeline.DebugPipelineContext.proceed(DebugPipelineContext.kt:57)
	at io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1.invokeSuspend(BaseApplicationEngine.kt:112)
	at io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1.invoke(BaseApplicationEngine.kt)
	at io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1.invoke(BaseApplicationEngine.kt)
	at io.ktor.util.pipeline.DebugPipelineContext.proceedLoop(DebugPipelineContext.kt:79)
	at io.ktor.util.pipeline.DebugPipelineContext.proceed(DebugPipelineContext.kt:57)
	at io.ktor.util.pipeline.DebugPipelineContext.execute$ktor_utils(DebugPipelineContext.kt:63)
	at io.ktor.util.pipeline.Pipeline.execute(Pipeline.kt:79)
	at io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$invokeSuspend$$inlined$execute$1.invokeSuspend(Pipeline.kt:481)
	at io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$invokeSuspend$$inlined$execute$1.invoke(Pipeline.kt)
	at io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$invokeSuspend$$inlined$execute$1.invoke(Pipeline.kt)
	at io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode(ContextUtils.kt:17)
	at io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1.invokeSuspend(DefaultEnginePipeline.kt:123)
	at io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1.invoke(DefaultEnginePipeline.kt)
	at io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1.invoke(DefaultEnginePipeline.kt)
	at io.ktor.util.pipeline.DebugPipelineContext.proceedLoop(DebugPipelineContext.kt:79)
	at io.ktor.util.pipeline.DebugPipelineContext.proceed(DebugPipelineContext.kt:57)
	at io.ktor.util.pipeline.DebugPipelineContext.execute$ktor_utils(DebugPipelineContext.kt:63)
	at io.ktor.util.pipeline.Pipeline.execute(Pipeline.kt:79)
	at io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1$invokeSuspend$$inlined$execute$1.invokeSuspend(Pipeline.kt:481)
	at io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1$invokeSuspend$$inlined$execute$1.invoke(Pipeline.kt)
	at io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1$invokeSuspend$$inlined$execute$1.invoke(Pipeline.kt)
	at io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode(ContextUtils.kt:17)
	at io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1.invokeSuspend(NettyApplicationCallHandler.kt:140)
	at io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1.invoke(NettyApplicationCallHandler.kt)
	at io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1.invoke(NettyApplicationCallHandler.kt)
	at kotlinx.coroutines.intrinsics.UndispatchedKt.startCoroutineUndispatched(Undispatched.kt:20)
	at kotlinx.coroutines.CoroutineStart.invoke(CoroutineStart.kt:360)
	at kotlinx.coroutines.AbstractCoroutine.start(AbstractCoroutine.kt:124)
	at kotlinx.coroutines.BuildersKt__Builders_commonKt.launch(Builders.common.kt:52)
	at kotlinx.coroutines.BuildersKt.launch(Unknown Source)
	at io.ktor.server.netty.NettyApplicationCallHandler.handleRequest(NettyApplicationCallHandler.kt:41)
	at io.ktor.server.netty.NettyApplicationCallHandler.channelRead(NettyApplicationCallHandler.kt:33)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:444)
	at io.netty.channel.AbstractChannelHandlerContext.access$600(AbstractChannelHandlerContext.java:61)
	at io.netty.channel.AbstractChannelHandlerContext$7.run(AbstractChannelHandlerContext.java:425)
	at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:173)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:166)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:472)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:569)
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:1575)
Caused by: java.lang.ClassNotFoundException: io.ktor.server.routing.RoutingKt
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:528)
	... 69 common frames omitted
```
