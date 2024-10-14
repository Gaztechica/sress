package ru.cbgr.qa.stress.mvd.step.prepare;

import io.gatling.javaapi.core.ChainBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class LoginStep {

    public static ChainBuilder loginAndOpenMainPageZUP =

            pause(ofMillis(100), ofMillis(100))
                    .exec(http("Логин")
                            .post("/Login/Login").asJson()
                            .body(ElFileBody("json/prepare/sendLoginZUP.json")).asJson()
                            .check(status().is(200))
                    )

                    .exec(http("Логин - установить роль")
                            .post("/Login/Roles").asJson()
                            .body(ElFileBody("json/prepare/setUserRoleZUP.json")).asJson()
                            .check(status().is(200))
                            .check(headerRegex("Set-Cookie", "SessionID=(.*?);").find(1).saveAs("sessionId"))
                    )

                    .exec(http("PreLogin")
                            .post("/api/xcom/userauth/preLogin").asJson()
                            .check(status().is(200)))
                    .pause(ofSeconds(2))

                    .exec(http("GetPostLoginForm")
                            .post("/api/xcom/view/getPostLoginForm").asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200)))
                    .pause(ofSeconds(2))

                    .exec(http("Роль - getUserRole")
                            .post("/api/xcom/view/getUserRole").asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200)))
                    .pause(ofSeconds(2))

                    .exec(http("Меню - getMenu")
                            .post("/api/xcom/view/getMenu").asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200)))
                    .pause(ofSeconds(2))

                    .exec(http("Тулбар - getToolbar")
                            .post("/api/xcom/view/getToolbar").asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200)))
                    .pause(ofSeconds(2))

                    .exec(http("Состояние приложения - getAppState")
                            .post("/api/xcom/view/getAppState").asJson()
                            .header("Sessionid", "#{sessionId}")
                            .check(status().is(200))
                            .check(jsonPath("$.result[1].resultAction.formId").saveAs("formId")))
                    .pause(ofSeconds(2));
}
