@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  rating-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and RATING_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\rating-service-plain.jar;%APP_HOME%\lib\cloud.crosstraining.devstore.common-1.0.1.jar;%APP_HOME%\lib\spring-cloud-starter-config-4.1.1.jar;%APP_HOME%\lib\spring-cloud-starter-bootstrap-4.1.2.jar;%APP_HOME%\lib\spring-cloud-starter-netflix-eureka-client-4.1.1.jar;%APP_HOME%\lib\spring-boot-starter-actuator-3.2.6.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-3.2.6.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\spring-boot-starter-data-rest-3.2.6.jar;%APP_HOME%\lib\spring-boot-starter-webflux-3.2.6.jar;%APP_HOME%\lib\micrometer-tracing-bridge-brave-1.2.6.jar;%APP_HOME%\lib\zipkin-reporter-brave-2.16.3.jar;%APP_HOME%\lib\micrometer-registry-prometheus-1.12.6.jar;%APP_HOME%\lib\postgresql-42.6.2.jar;%APP_HOME%\lib\spring-cloud-starter-loadbalancer-4.1.2.jar;%APP_HOME%\lib\spring-cloud-starter-4.1.2.jar;%APP_HOME%\lib\spring-cloud-config-client-4.1.1.jar;%APP_HOME%\lib\spring-cloud-netflix-eureka-client-4.1.1.jar;%APP_HOME%\lib\eureka-core-2.0.2.jar;%APP_HOME%\lib\eureka-client-2.0.2.jar;%APP_HOME%\lib\spring-boot-actuator-autoconfigure-3.2.6.jar;%APP_HOME%\lib\spring-data-rest-webmvc-4.2.6.jar;%APP_HOME%\lib\spring-boot-starter-web-3.2.6.jar;%APP_HOME%\lib\spring-boot-starter-json-3.2.6.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.15.4.jar;%APP_HOME%\lib\spring-data-rest-core-4.2.6.jar;%APP_HOME%\lib\jackson-annotations-2.15.4.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.15.4.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.15.4.jar;%APP_HOME%\lib\jackson-core-2.15.4.jar;%APP_HOME%\lib\jackson-databind-2.15.4.jar;%APP_HOME%\lib\spring-boot-starter-aop-3.2.6.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-3.2.6.jar;%APP_HOME%\lib\spring-boot-starter-cache-3.2.6.jar;%APP_HOME%\lib\spring-boot-starter-3.2.6.jar;%APP_HOME%\lib\micrometer-jakarta9-1.12.6.jar;%APP_HOME%\lib\spring-webflux-6.1.8.jar;%APP_HOME%\lib\spring-webmvc-6.1.8.jar;%APP_HOME%\lib\spring-hateoas-2.2.2.jar;%APP_HOME%\lib\spring-web-6.1.8.jar;%APP_HOME%\lib\micrometer-tracing-1.2.6.jar;%APP_HOME%\lib\micrometer-core-1.12.6.jar;%APP_HOME%\lib\spring-data-jpa-3.2.6.jar;%APP_HOME%\lib\spring-boot-autoconfigure-3.2.6.jar;%APP_HOME%\lib\spring-boot-actuator-3.2.6.jar;%APP_HOME%\lib\spring-boot-3.2.6.jar;%APP_HOME%\lib\spring-context-support-6.1.8.jar;%APP_HOME%\lib\spring-plugin-core-3.0.0.jar;%APP_HOME%\lib\spring-context-6.1.8.jar;%APP_HOME%\lib\micrometer-observation-1.12.6.jar;%APP_HOME%\lib\hibernate-core-6.4.8.Final.jar;%APP_HOME%\lib\spring-aspects-6.1.8.jar;%APP_HOME%\lib\spring-boot-starter-reactor-netty-3.2.6.jar;%APP_HOME%\lib\httpclient5-5.2.3.jar;%APP_HOME%\lib\netflix-eventbus-0.3.0.jar;%APP_HOME%\lib\servo-core-0.12.21.jar;%APP_HOME%\lib\HikariCP-5.0.1.jar;%APP_HOME%\lib\spring-data-commons-3.2.6.jar;%APP_HOME%\lib\netflix-infix-0.3.0.jar;%APP_HOME%\lib\spring-boot-starter-logging-3.2.6.jar;%APP_HOME%\lib\logback-classic-1.4.14.jar;%APP_HOME%\lib\log4j-to-slf4j-2.21.1.jar;%APP_HOME%\lib\jul-to-slf4j-2.0.13.jar;%APP_HOME%\lib\json-path-2.9.0.jar;%APP_HOME%\lib\slf4j-api-2.0.13.jar;%APP_HOME%\lib\brave-context-slf4j-5.16.0.jar;%APP_HOME%\lib\brave-instrumentation-http-5.16.0.jar;%APP_HOME%\lib\brave-propagation-aws-0.23.5.jar;%APP_HOME%\lib\brave-5.16.0.jar;%APP_HOME%\lib\zipkin-reporter-2.16.3.jar;%APP_HOME%\lib\simpleclient_common-0.16.0.jar;%APP_HOME%\lib\checker-qual-3.31.0.jar;%APP_HOME%\lib\spring-cloud-loadbalancer-4.1.2.jar;%APP_HOME%\lib\spring-cloud-context-4.1.2.jar;%APP_HOME%\lib\spring-cloud-commons-4.1.2.jar;%APP_HOME%\lib\spring-security-rsa-1.1.2.jar;%APP_HOME%\lib\xstream-1.4.19.jar;%APP_HOME%\lib\jakarta.ws.rs-api-3.1.0.jar;%APP_HOME%\lib\jakarta.inject-api-2.0.1.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-3.2.6.jar;%APP_HOME%\lib\jakarta.annotation-api-2.1.1.jar;%APP_HOME%\lib\httpclient-4.5.3.jar;%APP_HOME%\lib\commons-configuration-1.10.jar;%APP_HOME%\lib\jettison-1.5.4.jar;%APP_HOME%\lib\woodstox-core-6.2.1.jar;%APP_HOME%\lib\evictor-1.0.0.jar;%APP_HOME%\lib\spring-aop-6.1.8.jar;%APP_HOME%\lib\spring-orm-6.1.8.jar;%APP_HOME%\lib\spring-jdbc-6.1.8.jar;%APP_HOME%\lib\spring-tx-6.1.8.jar;%APP_HOME%\lib\spring-beans-6.1.8.jar;%APP_HOME%\lib\spring-expression-6.1.8.jar;%APP_HOME%\lib\spring-core-6.1.8.jar;%APP_HOME%\lib\snakeyaml-2.2.jar;%APP_HOME%\lib\micrometer-commons-1.12.6.jar;%APP_HOME%\lib\aspectjweaver-1.9.22.jar;%APP_HOME%\lib\jakarta.persistence-api-3.1.0.jar;%APP_HOME%\lib\jakarta.transaction-api-2.0.1.jar;%APP_HOME%\lib\jboss-logging-3.5.3.Final.jar;%APP_HOME%\lib\hibernate-commons-annotations-6.0.6.Final.jar;%APP_HOME%\lib\jandex-3.1.2.jar;%APP_HOME%\lib\classmate-1.6.0.jar;%APP_HOME%\lib\byte-buddy-1.14.16.jar;%APP_HOME%\lib\jaxb-runtime-4.0.5.jar;%APP_HOME%\lib\jaxb-core-4.0.5.jar;%APP_HOME%\lib\jakarta.xml.bind-api-4.0.2.jar;%APP_HOME%\lib\antlr4-runtime-4.13.0.jar;%APP_HOME%\lib\reactor-netty-http-1.1.19.jar;%APP_HOME%\lib\reactor-extra-3.5.1.jar;%APP_HOME%\lib\reactor-netty-core-1.1.19.jar;%APP_HOME%\lib\reactor-core-3.6.6.jar;%APP_HOME%\lib\context-propagation-1.1.1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\zipkin-2.23.2.jar;%APP_HOME%\lib\HdrHistogram-2.1.12.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\simpleclient-0.16.0.jar;%APP_HOME%\lib\spring-security-crypto-6.2.4.jar;%APP_HOME%\lib\bcprov-jdk18on-1.77.jar;%APP_HOME%\lib\httpcore5-h2-5.2.4.jar;%APP_HOME%\lib\httpcore5-5.2.4.jar;%APP_HOME%\lib\commons-math-2.2.jar;%APP_HOME%\lib\mxparser-1.2.2.jar;%APP_HOME%\lib\guava-19.0.jar;%APP_HOME%\lib\httpcore-4.4.16.jar;%APP_HOME%\lib\commons-codec-1.16.1.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\stax2-api-4.2.1.jar;%APP_HOME%\lib\spring-jcl-6.1.8.jar;%APP_HOME%\lib\angus-activation-2.0.2.jar;%APP_HOME%\lib\jakarta.activation-api-2.1.3.jar;%APP_HOME%\lib\tomcat-embed-websocket-10.1.24.jar;%APP_HOME%\lib\tomcat-embed-core-10.1.24.jar;%APP_HOME%\lib\tomcat-embed-el-10.1.24.jar;%APP_HOME%\lib\evo-inflector-1.3.jar;%APP_HOME%\lib\netty-codec-http2-4.1.110.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.110.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.110.Final.jar;%APP_HOME%\lib\netty-resolver-dns-native-macos-4.1.110.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-classes-macos-4.1.110.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.110.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.110.Final-linux-x86_64.jar;%APP_HOME%\lib\reactive-streams-1.0.4.jar;%APP_HOME%\lib\simpleclient_tracer_otel-0.16.0.jar;%APP_HOME%\lib\simpleclient_tracer_otel_agent-0.16.0.jar;%APP_HOME%\lib\commons-jxpath-1.3.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\antlr-runtime-3.4.jar;%APP_HOME%\lib\gson-2.10.1.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\logback-core-1.4.14.jar;%APP_HOME%\lib\log4j-api-2.21.1.jar;%APP_HOME%\lib\txw2-4.0.5.jar;%APP_HOME%\lib\istack-commons-runtime-4.1.2.jar;%APP_HOME%\lib\netty-handler-4.1.110.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.110.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.110.Final.jar;%APP_HOME%\lib\netty-codec-4.1.110.Final.jar;%APP_HOME%\lib\netty-transport-classes-epoll-4.1.110.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.110.Final.jar;%APP_HOME%\lib\netty-transport-4.1.110.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.110.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.110.Final.jar;%APP_HOME%\lib\netty-common-4.1.110.Final.jar;%APP_HOME%\lib\simpleclient_tracer_common-0.16.0.jar;%APP_HOME%\lib\stringtemplate-3.2.1.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\json-smart-2.5.1.jar;%APP_HOME%\lib\accessors-smart-2.5.1.jar;%APP_HOME%\lib\asm-9.6.jar


@rem Execute rating-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %RATING_SERVICE_OPTS%  -classpath "%CLASSPATH%" cloud.crosstraining.devstore.rating.Main %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable RATING_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%RATING_SERVICE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
