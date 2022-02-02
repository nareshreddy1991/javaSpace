package com.designpatterns.b_structural;

import java.io.IOException;

/*
Proxy Pattern:
The definition itself is very clear and proxy design pattern is used when we want to provide controlled access of a functionality.
Proxy means ‘in place of’, representing’ or ‘in place of’ or ‘on behalf of’ are literal meanings of proxy and that directly explains Proxy Design Pattern.

Let’s say we have a class that can run some command on the system. Now if we are using it, its fine but if we want to give this program to a client application,
 it can have severe issues because client program can issue command to delete some system files or change some settings that you don’t want.

Behavior:
As in the decorator pattern, proxies can be chained together. The client, and each proxy, believes it is delegating messages to the real server:
serviceCall1() ---> a proxy ---->serviceCallB()----> b proxy ---->

When to use this pattern?
Proxy pattern is used when we need to create a wrapper to cover the main object’s complexity from the client.

Benefits:
One of the advantages of Proxy pattern is security.
This pattern avoids duplication of objects which might be huge size and memory intensive. This in turn increases the performance of the application.
The remote proxy also ensures about security by installing the local code proxy (stub) in the client machine and then accessing the server with help of the remote code.

Interesting points: TODO didn't get it!!
There are few differences between the related patterns. Like Adapter pattern gives a different interface to its subject, while Proxy patterns provides the same interface from the original object but the decorator provides an enhanced interface. Decorator pattern adds additional behaviour at runtime.
Proxy used in Java API: java.rmi.*;
 */
public class C_ProxyPattern {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutorProxy("Pankaj", "wrong_pwd");
        try {
            executor.runCommand("ls -ltr");
            executor.runCommand(" rm -rf abc.pdf");
        } catch (Exception e) {
            System.out.println("Exception Message::" + e.getMessage());
        }
    }
}

interface CommandExecutor {

    public void runCommand(String cmd) throws Exception;
}

class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void runCommand(String cmd) throws IOException {
        //some heavy implementation
        Runtime.getRuntime().exec(cmd);
        System.out.println("'" + cmd + "' command executed.");
    }

}

//controlling the access by name & roles etc
class CommandExecutorProxy implements CommandExecutor {

    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd) {
        if ("Pankaj".equals(user) && "J@urnalD$v".equals(pwd)) isAdmin = true;
        executor = new CommandExecutorImpl();
    }

    @Override
    public void runCommand(String cmd) throws Exception {
        if (isAdmin) {
            executor.runCommand(cmd);
        } else {
            if (cmd.trim().startsWith("rm")) {
                throw new Exception("rm command is not allowed for non-admin users.");
            } else {
                executor.runCommand(cmd);
            }
        }
    }

}

