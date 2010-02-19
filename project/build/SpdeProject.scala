import sbt._

import spde._

class SpdeProject(info: ProjectInfo) extends ParentProject(info) with posterous.Publish
{
  val databinder_repo = "Databinder Repository" at "http://databinder.net/repo"
  lazy val core_scala = project("core-scala", "Spde Core", new SpdeModule(_) {
    val core = "org.processing" % "core" % "1.0.9"
    val dispatch = "net.databinder" %% "dispatch-futures" % "0.7.0"
  })
  lazy val spde_video = project("spde-video", "Spde Video", new SpdeModule(_) with GSVideoProject, 
    core_scala)
  
  val license = path("license.txt")
  class SpdeModule(info: ProjectInfo) extends DefaultProject(info) {
    override def mainResources = super.mainResources +++ license
  }
  override def extraTags = "dispatch" :: super.extraTags
  override def managedStyle = ManagedStyle.Maven
  lazy val publishTo = Resolver.file("Databinder Repository Path", new java.io.File("/var/dbwww/repo"))
}
