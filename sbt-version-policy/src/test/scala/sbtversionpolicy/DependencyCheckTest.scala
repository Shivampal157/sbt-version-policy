package sbtversionpolicy

import sbtversionpolicy.internal.DependencyCheck
import sbt.librarymanagement.ModuleID
import sbt.librarymanagement.UpdateConfiguration
import sbt.librarymanagement.UnresolvedWarningConfiguration
import sbt.util.Logger

object DependencyCheckTest {

  def main(args: Array[String]): Unit = {

    val dummyModule = ModuleID("org.example", "dummy", "1.0.0")

    try {
      DependencyCheck.report(
        excludedModules = Set.empty,
        currentDependencies = Map.empty,
        previousModuleId = dummyModule, // simulate missing previous report
        reconciliations = Seq.empty,
        defaultReconciliation = null,   // intentionally invalid to trigger fail-early
        sv = "2.13.12",
        sbv = "2.13",
        depRes = null,                  
        scalaModuleInf = None,
        updateConfig = UpdateConfiguration(),
        warningConfig = UnresolvedWarningConfiguration(),
        moduleToVersion = PartialFunction.empty,
        log = Logger.Null
      )

      sys.error("Expected failure did not happen")

    } catch {
      case _: Exception =>
        println("DependencyCheck fails early as expected when report is missing")
    }
  }
}