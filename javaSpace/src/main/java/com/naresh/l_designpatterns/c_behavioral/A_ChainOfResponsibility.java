package com.naresh.l_designpatterns.c_behavioral;

import java.util.Scanner;

/*
Behavioral design patterns:
Behavioral design patterns are concerned with the interaction and responsibility of objects.
In these design patterns, the interaction between the objects should be in such a way that they can easily talk to each other and still should be loosely coupled.
That means the implementation and the client should be loosely coupled in order to avoid hard coding and dependencies.

Chain of responsibility pattern:
it is used to achieve loose coupling in software design where a request from client is passed to a chain of objects to process them.
 Then the object in the chain will decide themselves who will be processing the request and whether the request is required to be sent to the next object in the chain or not.
 Ex:
 try{

 }catch(){//if first blockk is not able to handle exception the send it to ext level
}catch(){//same as above
}etc...

Ex:2
ATM dispense machine
    ATM Dispenser
        |
    Dollar 50 dispense
        |
     Dollar 20 Dispense
        |
     Dollar 10 dispense

Chain of Responsibility Pattern Examples in JDK:
java.util.logging.Logger#log()
javax.servlet.Filter#doFilter()

//TODO similar to Decorator

 */
public class A_ChainOfResponsibility {
    public static void main(String[] args) {
        ATMDispenseChain dispenseChain = new ATMDispenseChain();
        DispenseChain chain = dispenseChain.getChain();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            // process the request
            chain.dispense(new Currency(amount));
        }

    }
}


class Currency {

    private int amount;

    public Currency(int amt) {
        this.amount = amt;
    }

    public int getAmount() {
        return this.amount;
    }
}

interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}

class Dollar50Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 50) {
            int num = cur.getAmount() / 50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Dispensing " + num + " 50$ note");
            if (remainder != 0) this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(cur);
        }
    }

}

class Dollar20Dispenser implements DispenseChain {
    // this is achieved through composition
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 20) {
            int num = cur.getAmount() / 20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Dispensing " + num + " 20$ note");
            if (remainder != 0) this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(cur);
        }
    }

}

class Dollar10Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 10) {
            int num = cur.getAmount() / 10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Dispensing " + num + " 10$ note");
            if (remainder != 0) this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(cur);
        }
    }
}

//order of chaining is important
class ATMDispenseChain {

    private DispenseChain c1;

    public ATMDispenseChain() {
        // initialize the chain
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    DispenseChain getChain() {
        return c1;
    }
}

