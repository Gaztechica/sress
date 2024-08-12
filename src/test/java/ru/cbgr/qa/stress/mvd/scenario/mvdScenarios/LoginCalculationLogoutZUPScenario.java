package ru.cbgr.qa.stress.mvd.scenario.mvdScenarios;

import io.gatling.javaapi.core.ChainBuilder;
import ru.cbgr.qa.stress.mvd.step.mvd.zup.ClickButtonCalculationForJanuary;
import ru.cbgr.qa.stress.mvd.step.mvd.zup.CloseDialogFrameInformationStepZUP;
import ru.cbgr.qa.stress.mvd.step.prepare.LoginStep;
import ru.cbgr.qa.stress.mvd.step.prepare.LogoutStep;
import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static java.time.Duration.ofMillis;
import static ru.cbgr.qa.stress.mvd.property.Constant.MAX_PAUSE_ON_CALCULATION;
import static ru.cbgr.qa.stress.mvd.property.Constant.MIN_PAUSE_ON_CALCULATION;
import static ru.cbgr.qa.stress.mvd.simulation.ScenarioPlatform.LOGIN_USER_COUNT;

public class LoginCalculationLogoutZUPScenario {

    public static ChainBuilder loginCalculationLogoutZUPChain =
            group("loginAndPassword").on(
                    feed(csv("csv/login/loginAndPassword.csv")).
                                    group("Логин в систему").on(
                                            exec(LoginStep.loginAndOpenMainPageZUP))
                    )

                    // Ждать Х пользователей
                    .rendezVous(LOGIN_USER_COUNT).pause(ofMillis(100), ofMillis(LOGIN_USER_COUNT * 1000))

                    .repeat(1).on(
                            pause(Duration.ofMillis(MIN_PAUSE_ON_CALCULATION), Duration.ofMinutes(MAX_PAUSE_ON_CALCULATION))
                            .exec(ClickButtonCalculationForJanuary.clickButtonCalculationForJanuaryChainZUP)
                            .exec(CloseDialogFrameInformationStepZUP.closeDialogFrameInformationChainZUP))
                    .exec(LogoutStep.logoutStepChainZUP);
}
