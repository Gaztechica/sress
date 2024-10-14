package ru.cbgr.qa.stress.mvd.step.prepare;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.group;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.time.Duration.ofSeconds;
import static ru.cbgr.qa.stress.mvd.property.Constant.MAX_PAUSE_ON_STEPS;
import static ru.cbgr.qa.stress.mvd.property.Constant.MIN_PAUSE_ON_STEPS;

public class LogoutStep {
    public static ChainBuilder logoutStepChainZUP =
            group("Шаги").on(
                    exec(http("Выйти из системы")
                            .post("/api/xcom/userauth/logout").asJson()
                            .requestTimeout(Duration.ofMinutes(1))
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200)))
                            .pause(ofSeconds(MIN_PAUSE_ON_STEPS), ofSeconds(MAX_PAUSE_ON_STEPS)));
}
