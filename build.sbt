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
  .dependsOn(core)
  .enablePlugins(BuildInfoPlugin)
  .settings(pluginSettings)

lazy val core = project
  .in(file("core"))
  .settings(coreSettings)
