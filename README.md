# vertxChannel
Contain channel microservice with vertx

You can run the file EndPointChannelVerticle.java or you can configure the Run/Debug Configuration in your IDE:

Type: Application
Main Class = io.vertx.core.Launcher
Program arguments = run  com.fox.platform.contentserv.infra.serv.EndPointChannelVerticle —?redeploy=”target/classes/**/*.class”?—?launcher-class=io.vertx.core.Launcher

Use the following url for tests: POST http://localhost:9090/channel?countryId=MX
