import sbtorgpolicies.templates.badges._

pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val root = project
  .in(file("."))
  .settings(name := "sbt-frees-protogen")
  .settings(noPublishSettings)
  .dependsOn(plugin, core)
  .aggregate(plugin, core)

lazy val plugin = project
  .in(file("plugin"))
  .dependsOn(core)
  .settings(moduleName := "sbt-frees-protogen")
  .settings(sbtPlugin := true)
  .settings(scalacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-unchecked"))
  .settings(
    orgBadgeListSetting := List(
      TravisBadge.apply,
      CodecovBadge.apply,
      ScalaLangBadge.apply,
      LicenseBadge.apply,
      // Gitter badge (owner field) can be configured with default value if we migrate it to the frees-io organization
      { info => GitterBadge.apply(info.copy(owner = "47deg", repo = "freestyle")) },
      GitHubIssuesBadge.apply
    ))

lazy val core = project
  .in(file("core"))
  .settings(moduleName := "frees-protogen-core")
  .settings(scalaMetaSettings: _*)
  .settings(libraryDependencies += "io.frees" %% "frees-rpc" % "0.0.3")