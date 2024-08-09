package ru.cbgr.qa.stress.mvd.gatling;

import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {

  static final Path mavenSourcesDirectory;
  static final Path mavenResourcesDirectory;
  static final Path mavenBinariesDirectory;
  static final Path resultsDirectory;

  static {
    try {
      Path projectRootDir = Paths.get(Runner.class.getClassLoader().getResource("gatling.conf").toURI()).getParent().getParent().getParent();
      Path mavenTargetDirectory = projectRootDir.resolve("target");
      Path mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test");

      mavenSourcesDirectory = mavenSrcTestDirectory.resolve("java");
      mavenResourcesDirectory = mavenSrcTestDirectory.resolve("resources");
      mavenBinariesDirectory = mavenTargetDirectory.resolve("test-classes");
      resultsDirectory = mavenTargetDirectory.resolve("gatling");
    } catch (URISyntaxException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static void main(String[] args) {
    GatlingPropertiesBuilder props = new GatlingPropertiesBuilder()
      .resourcesDirectory(mavenResourcesDirectory.toString())
      .resultsDirectory(resultsDirectory.toString())
      .binariesDirectory(mavenBinariesDirectory.toString());

    Gatling.fromMap(props.build());
  }
}
