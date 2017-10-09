import freestyle.FreestylePlugin
import sbt.Keys._
import sbt._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = FreestylePlugin

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      crossScalaVersions := Seq(sbtorgpolicies.model.scalac.`2.12`)
    )

}