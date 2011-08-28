Sierpinski
----------

This [Sierpinski Gasket][sg] was [contributed by EPFL][neat]:

[neat]: http://www.scala-lang.org/node/3391
[sg]: http://en.wikipedia.org/wiki/Sierpinski_triangle

> For those of you who are mathematically minded or interested in self similar systems and fractals, Anthony Bagwell has provided us with the following code examples. The first is a concise way to create a representation of the popular Sierpinski gasket.

<!--[if !IE]> -->
<object classid="java:Sierpinski_Gasket.class" 
type="application/x-java-applet"
archive="/pictures/Sierpinski_Gasket-applet-0.1.2.jpgz"
width="400" height="400"
standby="Loading Processing software..." >
<param name="archive" value="/pictures/Sierpinski_Gasket-applet-0.1.2.jpgz" />
<param name="mayscript" value="true" />
<param name="scriptable" value="true" />
<param name="image" value="loading.gif" />
<param name="boxmessage" value="Loading Processing software..." />
<param name="boxbgcolor" value="#FFFFFF" />
<param name="test_string" value="outer" />
<!--<![endif]-->
<object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0_15-windows-i586.cab"
width="400" height="400"
standby="Loading Processing software..."  >
<param name="code" value="Sierpinski_Gasket" />
<param name="archive" value="/pictures/Sierpinski_Gasket-applet-0.1.2.jpgz" />
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
val d= 200
size(400, 400)
background(0, 10, 20)
var a=List(List(d/2,0,255,0,0),List(d,d,0,255,0),List(0,d,0,0,255))
var p=List(d,0,255,0,0)
def draw() {
  for(i<-1 to 10) {
    p=p zip a.random map{case (x,y)=>x/2+y}
    stroke(p(2),p(3),p(4))
    point(p(0), p(1))
  }
}
```
