package ru.cbgr.qa.stress.mvd.step.mvd.zup;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.group;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.time.Duration.ofSeconds;
import static ru.cbgr.qa.stress.mvd.property.Constant.MAX_PAUSE_ON_STEPS;
import static ru.cbgr.qa.stress.mvd.property.Constant.MIN_PAUSE_ON_STEPS;

public class CloseDialogFrameInformationStepZUP {
    public static ChainBuilder closeDialogFrameInformationChainZUP =
            group("Шаги").on(
                    exec(http("Диалоговое окно ОК")
                            .post("/api/xcom/view/sendAction").asJson()
                            .body(ElFileBody("json/prepare/closeDialogFrameInformation.json")).asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200)))
                            .pause(ofSeconds(MIN_PAUSE_ON_STEPS), ofSeconds(MAX_PAUSE_ON_STEPS)));
}
