pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val `sbt-frees-protogen` = project
  .in(file("."))
  .settings(sbtPlugin := true)
  .settings(crossScalaVersions := Seq(sbtorgpolicies.model.scalac.`2.12`))
  .settings(scalacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-unchecked"))
