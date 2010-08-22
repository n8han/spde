import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val databinder_repo = "Databinder Repository" at "http://databinder.net/repo"
  val spde_sbt = "us.technically.spde" % "spde-sbt-plugin" % "0.4.2-SNAPSHOT"

  val t_repo = "t_repo" at "http://tristanhunt.com:8081/content/groups/public/"
  val snuggletex_repo = "snuggletex_repo" at "http://www2.ph.ed.ac.uk/maven2"
  val posterous = "net.databinder" % "posterous-sbt" % "0.1.6"

  val sxr_publish = "net.databinder" % "sxr-publish" % "0.2.0"
}
