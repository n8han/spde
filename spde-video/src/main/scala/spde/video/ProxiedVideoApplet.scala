package spde.video

import spde.core._
import codeanticode.gsvideo._

abstract class ProxiedVideoApplet extends ProxiedApplet {
  val px: DrawVideoProxy
  def movieEvent(movie: GSMovie) { px.movieEvent(movie) }
}

  
abstract class DrawVideoProxy(applet: ProxiedVideoApplet) extends DrawProxy(applet) {
  def movieEvent(movie: GSMovie)
}
