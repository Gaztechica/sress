package ru.cbgr.qa.stress.mvd.simulation;


import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import ru.cbgr.qa.stress.mvd.property.Constants;
import ru.cbgr.qa.stress.mvd.scenario.mvdScenarios.LoginCalculationLogoutZUPScenario;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class Debug extends Simulation {

    // дебаг сценария
    // указать сценарий в exec()
    // включить логгер на отладку src/test/resources/logback-test.xml
    ScenarioBuilder debug =
            CoreDsl.scenario("Дебаг сценария на 1 пользователя").
                    exec(LoginCalculationLogoutZUPScenario.loginCalculationLogoutZUPChain);

    {
        setUp(
                debug.injectOpen(atOnceUsers(1))
        ).protocols(Constants.httpProtocol);
    }
}