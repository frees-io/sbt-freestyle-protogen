import sbtorgpolicies.templates.badges._

pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val `sbt-frees-protogen` = project
  .in(file("."))
  .aggregate(core)
  .dependsOn(core)
  .settings(sbtPlugin := true)
  .settings(crossScalaVersions := Seq(sbtorgpolicies.model.scalac.`2.12`))
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
  .settings(crossScalaVersions := Seq(sbtorgpolicies.model.scalac.`2.12`))
  .settings(scalaMetaSettings: _*)
  .settings(libraryDependencies += "io.frees" %% "frees-rpc" % "0.0.3")