version := "1.0"

resolvers += Resolver.bintrayRepo("beyondthelines", "maven")

libraryDependencies ++= Seq(
  "io.frees" %% "frees-rpc" % "0.3.0"
)

scalaMetaSettings
