pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val protogen = project
  .in(file("."))
  .settings(sbtPlugin := true)
  .settings(moduleName := "sbt-frees-protogen")
  .settings(scalacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-unchecked"))
