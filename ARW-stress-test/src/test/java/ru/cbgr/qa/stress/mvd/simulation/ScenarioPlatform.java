package ru.cbgr.qa.stress.mvd.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import ru.cbgr.qa.stress.mvd.scenario.mvdScenarios.LoginCalculationLogoutZUPScenario;
import ru.cbgr.qa.stress.mvd.property.Constants;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static java.time.Duration.ofSeconds;

public class ScenarioPlatform extends Simulation {

    // Время для входа юзеров
    public static final int TIME_TO_LOGIN = 1200; // секунд - время запуска

    public static final int LOGIN_USER_COUNT = 2300; // количество пользователей

    ScenarioBuilder firstScenarioBuilder =
            scenario("1. ").
                    exec(LoginCalculationLogoutZUPScenario.loginCalculationLogoutZUPChain);

    {
        setUp(
                firstScenarioBuilder.
                        injectOpen(rampUsers(LOGIN_USER_COUNT).
                                during(ofSeconds(TIME_TO_LOGIN))
                        )
        ).protocols(Constants.httpProtocol);
    }
}
