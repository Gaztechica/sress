Gatling project for stress testing turbo solution for Sber
============================================

    При открытии новой формы formId передается в запрос на открытие этой формы и в ответе сразу присваивается новый 
    для последующих запросов. Новое взаимодействие начинается с formId этой формы


## Для запуска через maven необходимо выполнить следующую команду:
    mvn ru.cbgr.qa.stress.sber.gatling:test -Dgatling.simulationClass=ru.cbgr.qa.stress.sber.simulation.ScenarioLow

### Предполагается, что симуляции лежит по пути
    src/test/java/ru.cbgr.qa.stress.sber.simulation/CreateContractSimulation.java


номера присваиваются подряд
планы покупок для согласования 5 - 5920
договора 5930 - 30 800