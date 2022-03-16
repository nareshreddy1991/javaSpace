package com.naresh.l_designpatterns.b_structural;

/*
Structural Design Patterns:
Structural patterns provide different ways to create a class structure, for example using inheritance and composition to create a large object from small objects.

 1. Adapter Pattern
The adapter design pattern is one of the structural design patterns and it’s used so that two unrelated interfaces can work together.
 An Adapter wraps an existing class with a new interface so that it becomes compatible with the client’s interface.
 The object that joins these unrelated interfaces is called an Adapter. As a real-life example, we can think of a mobile charger as an adapter
  because the mobile battery needs 3 volts to charge but the normal socket produces either 120V (US) or 240V (India).
  So the mobile charger works as an adapter between the mobile charging socket and the wall socket. Check out Adapter Pattern for example program and it’s usage in Java.

>>When to Use Adapter Pattern
When an outside component provides captivating functionality that we'd like to reuse, but it's incompatible with our current application. A suitable Adapter can be developed to make them compatible with each other
When our application is not compatible with the interface that our client is expecting
When we want to reuse legacy code in our application without making any modification in the original code

>>>Adapter Design Pattern Example in JDK:
java.util.Arrays#asList()
java.io.InputStreamReader(InputStream) (returns a Reader)
java.io.OutputStreamWriter(OutputStream) (returns a Writer)

Other Example:
Converting miles to kpmh
 */
public class A_AdaptorPattern {
    public static void main(String[] args) {
        //class adaptor
        SocketAdaptor adaptor = new SocketAdaptorImpl();
        Volt volts2 = adaptor.get2Volts();
        Volt volts60 = adaptor.get60Volts();

        System.out.println(volts2.getVolts() + "/" + volts60.getVolts());
        //object adaptor
        SocketAdaptor objAdaptor = new SocketAdaptorImpl2();
        Volt objVolts2 = adaptor.get2Volts();
        Volt objVolts60 = adaptor.get60Volts();
        System.out.println(objVolts2.getVolts() + "/" + objVolts60.getVolts());
    }
}

class Volt {
    private Integer volts;

    public Volt(Integer volts) {
        this.volts = volts;
    }

    public Integer getVolts() {
        return volts;
    }

    public void setVolts(Integer volts) {
        this.volts = volts;
    }
}

//socket is returning 120 volts we need smaller volts so using adaptor we can convert it
class Socket {
    public Volt getVolts() {
        return new Volt(120);
    }
}

//now we need to convert higher volts to smaller volts
interface SocketAdaptor {
    Volt get60Volts();

    Volt get10Volts();

    Volt get2Volts();
}

//Class Adapter – This form uses java inheritance and extends the source interface, in our case Socket class.
class SocketAdaptorImpl extends Socket implements SocketAdaptor {

    @Override
    public Volt get60Volts() {
        return convert(getVolts(), 2);
    }

    @Override
    public Volt get10Volts() {
        return convert(getVolts(), 6);
    }

    @Override
    public Volt get2Volts() {
        return convert(getVolts(), 60);
    }

    private Volt convert(Volt volt, int divider) {
        return new Volt(Integer.valueOf(volt.getVolts() / divider));
    }
}

class SocketAdaptorImpl2 implements SocketAdaptor {

    Socket socket = new Socket();

    @Override
    public Volt get60Volts() {
        return convert(socket.getVolts(), 2);
    }

    @Override
    public Volt get10Volts() {
        return convert(socket.getVolts(), 6);
    }

    @Override
    public Volt get2Volts() {
        return convert(socket.getVolts(), 60);
    }

    private Volt convert(Volt volt, int divider) {
        return new Volt(Integer.valueOf(volt.getVolts() / divider));
    }
}

