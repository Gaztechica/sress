package ru.cbgr.qa.stress.mvd.step.mvd.zup;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.group;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.substring;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.time.Duration.ofSeconds;
import static ru.cbgr.qa.stress.mvd.property.Constant.MAX_PAUSE_ON_STEPS;
import static ru.cbgr.qa.stress.mvd.property.Constant.MIN_PAUSE_ON_STEPS;

public class ClickButtonCalculationForJanuary {

    public static ChainBuilder clickButtonCalculationForJanuaryChainZUP =
            group("Шаги").on(
                    exec(http("login")
                            .post("/account/login").asJson()
                            .body(ElFileBody("json/prepare/login.json")).asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200))
                            .check(jsonPath("$.result[0].resultAction.formId").saveAs("formId"))
                            .check(jsonPath("$.result[0].state[1].value").saveAs("value"))
                            .check(substring("Расчет завершен. Время исполнения")))
                            .pause(ofSeconds(MIN_PAUSE_ON_STEPS), ofSeconds(MAX_PAUSE_ON_STEPS)));
}