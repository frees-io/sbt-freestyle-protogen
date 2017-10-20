import freestyle.FreestylePlugin
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoPlugin.autoImport._
import sbtorgpolicies.OrgPoliciesPlugin.autoImport._
import sbtorgpolicies.templates.badges._
import sbtorgpolicies.model._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = FreestylePlugin

  object autoImport {

    lazy val pluginSettings: Seq[Def.Setting[_]] = Seq(
      moduleName := "sbt-frees-protogen",
      sbtPlugin := true,
      scalaVersion := scalac.`2.12`,
      crossScalaVersions := Seq(scalac.`2.12`),
      crossSbtVersions := Seq(sbtV.`0.13`, sbtV.`1.0`),
      scalacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-unchecked"),
      orgBadgeListSetting := List(
        TravisBadge.apply,
        ScalaLangBadge.apply,
        LicenseBadge.apply,
        // Gitter badge (owner field) can be configured with default value if we migrate it to the frees-io organization
        { info =>
          GitterBadge.apply(info.copy(owner = "47deg", repo = "freestyle"))
        },
        GitHubIssuesBadge.apply
      ),
      buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
      buildInfoPackage := "freestyle.rpc.protocol"
    )

    lazy val coreSettings: Seq[Def.Setting[_]] = scalaMetaSettings ++ Seq(
      moduleName := "frees-protogen-core",
      sbtPlugin := false,
      resolvers += Resolver.typesafeIvyRepo("releases"),
      crossScalaVersions := Seq(scalac.`2.10`, scalac.`2.12`),
      scalaVersion := {
        (sbtBinaryVersion in pluginCrossBuild).value match {
          case "0.13" => scalac.`2.10`
          case "1.0"  => scalac.`2.12`
        }
      },
      libraryDependencies ++= Seq(
        "io.frees" %% "frees-rpc-common" % "0.1.0",
        %%("cats-core"),
        %%("scalameta-contrib", "1.8.0"),
        %%("simulacrum")
      )
    )

  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    name := "sbt-freestyle-protogen",
    orgProjectName := "sbt-freestyle-protogen"
  )

}
