Fold
----

This sketch demonstrates a [fold][fold] function. Did you see it folding? If not, click to make it fold again.

[fold]: http://en.wikipedia.org/wiki/Fold_%28higher-order_function%29

<!--[if !IE]> -->
<object classid="java:Fold.class" 
type="application/x-java-applet"
archive="/pictures/Fold-applet-0.1.2.jpgz"
width="465" height="190"
standby="Loading Processing software..." >
<param name="archive" value="/pictures/Fold-applet-0.1.2.jpgz" />
<param name="mayscript" value="true" />
<param name="scriptable" value="true" />
<param name="image" value="loading.gif" />
<param name="boxmessage" value="Loading Processing software..." />
<param name="boxbgcolor" value="#FFFFFF" />
<param name="test_string" value="outer" />
<!--<![endif]-->
<object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0_15-windows-i586.cab"
width="465" height="190"
standby="Loading Processing software..."  >
<param name="code" value="Fold" />
<param name="archive" value="/pictures/Fold-applet-0.1.2.jpgz" />
<param name="mayscript" value="true" />
<param name="scriptable" value="true" />
<param name="image" value="loading.gif" />
<param name="boxmessage" value="Loading Processing software..." />
<param name="boxbgcolor" value="#FFFFFF" />
<param name="test_string" value="inner" />
<p>
<strong>
This browser does not have a Java Plug-in.
<br />
<a href="http://java.sun.com/products/plugin/downloads/index.html" title="Download Java Plug-in">
Get the latest Java Plug-in here.
</a>
</strong>
</p>
</object>
<!--[if !IE]> -->
</object>
<!--<![endif]-->

```scala
size(465, 190)
frameRate(25)

def random_seq = (0 until width) map { (_, random(height)) }
var h: Seq[(Int, Float)] = random_seq
override def mouseClicked { h = random_seq }

def draw() {
  background(255)
  h foreach { case (x, h) => line(x, 0, x, h) }
  h = (h :\ List[(Int, Float)]()) {
    case ((x1, h1), Nil) => (x1, h1) :: Nil
    case ((x1, h1), (x2, h2) :: t) =>
      (x1, (h1 + h2) /2) :: (x2, h2) :: t
  }
}
```
On initialization (or clicking) the sketch builds a sequence of *width* random numbers between 0 and *height*. The draw function (called 25 times per second, is the plan) paints a line for each element of the sequence, then reassigns it to a list created by right-folding ([:\\][rfold]) the sequence. Each element's height is the average of its last value and the new height to its right. The rightmost element retains its original value, which critically affects the output over time.

[rfold]: http://www.scala-lang.org/docu/files/api/scala/Iterable.html#%3A%5C%28B%29
