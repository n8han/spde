TI-81
-----

This one goes out to the best graphing calculator that ever was.

<div>
<!--[if !IE]> -->
<object classid="java:TI_81.class" 
    type="application/x-java-applet"
    archive="http://technically.us/applets/TI-81-applet-0.3.0.jpgz"
    width="500" height="500"
    standby="Loading Processing software..." >
<param name="archive" value="http://technically.us/applets/TI-81-applet-0.3.0.jpgz" />
<param name="mayscript" value="true" />
<param name="scriptable" value="true" />
<param name="boxmessage" value="Loading Processing software..." />
<param name="boxbgcolor" value="#FFFFFF" />
<param name="test_string" value="outer" />
<!--<![endif]-->
<object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
codebase="http://java.sun.com/update/1.6.0/jinstall-6u18-windows-i586.cab"
width="500" height="500"
standby="Loading Processing software..."  >
<param name="code" value="TI_81.class" />
<param name="archive" value="http://technically.us/applets/TI-81-applet-0.3.0.jpgz" />
<param name="mayscript" value="true" />
<param name="scriptable" value="true" />
<param name="boxmessage" value="Loading Processing software..." />
<param name="boxbgcolor" value="#FFFFFF" />
<param name="test_string" value="inner" />
<p>
<strong>
This browser does not have a Java Plug-in.
<br />
<a href="http://www.java.com/getjava" title="Download Java Plug-in">
Get the latest Java Plug-in here.
</a>
</strong>
</p>
</object>
<!--[if !IE]> -->
</object>
<!--<![endif]-->
</div> 

```scala
size(500, 500)
def sliding_sine = points(0, 10) { 
  x => sin( x * mouseX * 5 / width ) 
} map { 
  case (x, y) => (x, y * mouseY * 250 / height)
}

def draw {
  background(200)
  lineplot(sliding_sine)
}
```
