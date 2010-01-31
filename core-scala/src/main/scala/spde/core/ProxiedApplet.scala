package spde.core;

import processing.core.PApplet;

/**
 * A proxied applet delegates drawing and event handling to its DrawProxy field (px).
 * This allows sketch setup code to be placed in the body of the proxy rather than
 * a particular method, because the proxy is not initialized until the applet setup
 * method is called.
 */
abstract class ProxiedApplet extends PApplet {
  javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName())
  
  val px: DrawProxy
  
  override def setup() { px.setup() }
  
  override def draw() { px.draw() }
  
  override def mousePressed() { px.mousePressed }

  override def mouseReleased() { px.mouseReleased() }

  override def mouseClicked() { px.mouseClicked() }

  override def mouseDragged() { px.mouseDragged() }

  override def mouseMoved() { px.mouseMoved() }

  override def keyPressed() { px.keyPressed() }

  override def keyReleased() { px.keyReleased() }

  override def keyTyped() { px.keyTyped() }

  override def focusGained() { px.focusGained() }

  override def focusLost() { px.focusLost() }
  
  override def displayable = px.displayable
    
  /** Callback from JavaScript */
  def scripty(message: String) = {
    // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6669818
    try { 
      val sb = new StringBuilder
      // Firefox Win needs this
      java.awt.EventQueue.invokeAndWait(new Runnable {
        def run { sb append px.scripty(message) }
      })
      sb.toString
    } catch {
      // FireFox Mac couldn't handle that
      case _ => px.scripty(message)
    }
  }
}
abstract class DrawProxy(
  /** A reference for the DrawPoxy to the containing ProxiedApplet instance */
  val applet: ProxiedApplet
) extends Randoms with Maths with Timeout with Colors with Futures {
  
  /** For setup that can't be in a constructor, like native code on Apple's JRE apparently. */
  def setup() { }

  def mouseX = applet.mouseX.toShort
  def mouseY = applet.mouseY.toShort

  /** @return true if a mouse button is currently pressed */
  def isMousePressed: Boolean = applet.mousePressed

  /** @return true if a key is currently pressed */
  def isKeyPressed: Boolean = applet.keyPressed

  def draw()
  /**
   * Mouse has been pressed, and should be considered "down"
   * until mouseReleased() is called. If you must, use
   * int button = mouseEvent.getButton();
   * to figure out which button was clicked. It will be one of:
   * MouseEvent.BUTTON1, MouseEvent.BUTTON2, MouseEvent.BUTTON3
   * Note, however, that this is completely inconsistent across
   * platforms.
   */
  def mousePressed() { }
  /**
   * Mouse button has been released.
   */
  def mouseReleased() { }

  /**
   * When the mouse is clicked, mousePressed() will be called,
   * then mouseReleased(), then mouseClicked(). Note that
   * mousePressed is already false inside of mouseClicked().
   */
  def mouseClicked() { }

  /**
   * Mouse button is pressed and the mouse has been dragged.
   */
  def mouseDragged() { }

  /**
   * Mouse button is not pressed but the mouse has changed locations.
   */
  def mouseMoved() { }


  /**
   * Called each time a single key on the keyboard is pressed.
   * Because of how operating systems handle key repeats, holding
   * down a key will cause multiple calls to keyPressed(), because
   * the OS repeat takes over.
   * <P>
   * Examples for key handling:
   * (Tested on Windows XP, please notify if different on other
   * platforms, I have a feeling Mac OS and Linux may do otherwise)
   * <PRE>
   * 1. Pressing 'a' on the keyboard:
   *    keyPressed  with key == 'a' and keyCode == 'A'
   *    keyTyped    with key == 'a' and keyCode ==  0
   *    keyReleased with key == 'a' and keyCode == 'A'
   *
   * 2. Pressing 'A' on the keyboard:
   *    keyPressed  with key == 'A' and keyCode == 'A'
   *    keyTyped    with key == 'A' and keyCode ==  0
   *    keyReleased with key == 'A' and keyCode == 'A'
   *
   * 3. Pressing 'shift', then 'a' on the keyboard (caps lock is off):
   *    keyPressed  with key == CODED and keyCode == SHIFT
   *    keyPressed  with key == 'A'   and keyCode == 'A'
   *    keyTyped    with key == 'A'   and keyCode == 0
   *    keyReleased with key == 'A'   and keyCode == 'A'
   *    keyReleased with key == CODED and keyCode == SHIFT
   *
   * 4. Holding down the 'a' key.
   *    The following will happen several times,
   *    depending on your machine's "key repeat rate" settings:
   *    keyPressed  with key == 'a' and keyCode == 'A'
   *    keyTyped    with key == 'a' and keyCode ==  0
   *    When you finally let go, you'll get:
   *    keyReleased with key == 'a' and keyCode == 'A'
   *
   * 5. Pressing and releasing the 'shift' key
   *    keyPressed  with key == CODED and keyCode == SHIFT
   *    keyReleased with key == CODED and keyCode == SHIFT
   *    (note there is no keyTyped)
   *
   * 6. Pressing the tab key in an applet with Java 1.4 will
   *    normally do nothing, but PApplet dynamically shuts
   *    this behavior off if Java 1.4 is in use (tested 1.4.2_05 Windows).
   *    Java 1.1 (Microsoft VM) passes the TAB key through normally.
   *    Not tested on other platforms or for 1.3.
   * </PRE>
   */
  def keyPressed() { }

  /**
   * See keyPressed().
   */
  def keyReleased() { }

  /**
   * Only called for "regular" keys like letters,
   * see keyPressed() for full documentation.
   */
  def keyTyped() { }

  //////////////////////////////////////////////////////////////

  // i am focused man, and i'm not afraid of death.
  // and i'm going all out. i circle the vultures in a van
  // and i run the block.

  def focusGained() { }

  def focusLost() { }
  
  def displayable = applet.g.displayable

  def scripty(message: String) = message
}
