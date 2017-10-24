pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val root = project
  .in(file("."))
  .settings(noPublishSettings)
  .dependsOn(plugin, core)
  .aggregate(plugin, core)

lazy val plugin = project
  .in(file("plugin"))
  .aggregate(core)
  .dependsOn(core)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "freestyle.rpc.protocol"
  )
  .settings(moduleName := "sbt-frees-protogen")
  .settings(sbtPlugin := true)
  .settings(scalacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-unchecked"))

lazy val core = project
  .in(file("core"))
  .settings(moduleName := "frees-protogen-core")
  .settings(scalaMetaSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      %%("frees-rpc-common", "0.1.1"),
      %%("cats-core", "0.9.0"),
      %%("scalameta-contrib", "1.8.0"),
      %%("simulacrum")
    )
  )
