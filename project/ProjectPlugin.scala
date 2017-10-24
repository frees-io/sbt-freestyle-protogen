import freestyle.FreestylePlugin
import sbt.Keys._
import sbt._
import sbtorgpolicies.OrgPoliciesPlugin.autoImport.orgBadgeListSetting
import sbtorgpolicies.templates.badges._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = FreestylePlugin

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      name := "sbt-freestyle-protogen",
      crossScalaVersions := Seq("2.12.3"),
      orgBadgeListSetting := List(
          TravisBadge.apply,
          CodecovBadge.apply,
          ScalaLangBadge.apply,
          LicenseBadge.apply,
          // Gitter badge (owner field) can be configured with default value if we migrate it to the frees-io organization
          { info =>
            GitterBadge.apply(info.copy(owner = "47deg", repo = "freestyle"))
          },
          GitHubIssuesBadge.apply
        )
    )

}