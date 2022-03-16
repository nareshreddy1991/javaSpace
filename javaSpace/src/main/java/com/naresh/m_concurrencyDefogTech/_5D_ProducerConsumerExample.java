package com.naresh.m_concurrencyDefogTech;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _5D_ProducerConsumerExample {
    public static void main(String[] args) {

        Message msg = new Message();
        Thread messageProducer = new Thread(new MessageProducer(msg));
        Thread messageViewer = new Thread(new MessageViewer(msg));
        messageProducer.start();
        messageViewer.start();

    }
}


class Message {

    final private Lock lock = new ReentrantLock();
    final private Condition producedMsg = lock.newCondition();
    final private Condition consumedMsg = lock.newCondition();

    private String message;
    private boolean messageState;
    private boolean endIt;

    public void viewMessage() {
        //lock
        lock.lock();
        try {
            //no new message wait for new message
            while (!messageState)
                producedMsg.await();

            System.out.println("Here is the latest message : " + message);
            messageState = false;
            //message consumed, notify waiting thread
            consumedMsg.signal();

        } catch (InterruptedException ie) {
            System.out.println("Thread interrupted - viewMessage");
        } finally {
            lock.unlock();
        }
    }

    public void publishMessage(String message) {
        lock.lock();
        try {
            //last message not consumed, wait for it be consumed
            while (messageState)
                consumedMsg.await();

            System.out.println("adding latest message ");
            this.message = message;
            messageState = true;
            //new message added, notify waiting thread
            producedMsg.signal();

        } catch (InterruptedException ie) {
            System.out.println("Thread interrupted - publishMessage");
        } finally {
            lock.unlock();
        }

    }

    public boolean isEndIt() {
        return endIt;
    }

    public void setEndIt(boolean endIt) {
        this.endIt = endIt;
    }
}


class MessageProducer implements Runnable {
    private Message message;

    public MessageProducer(Message msg) {
        message = msg;
    }

    @Override
    public void run() {
        pusblishMessages();
    }

    private void pusblishMessages() {
        List<String> msgs = new ArrayList<String>();
        msgs.add("hello");
        msgs.add("current project is complete");
        msgs.add("here is the estimation for new project");

        for (String msg : msgs) {
            message.publishMessage(msg);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
            }
        }

        message.publishMessage("bye");
        message.setEndIt(true);
    }
}

class MessageViewer implements Runnable {
    private Message message;

    public MessageViewer(Message msg) {
        message = msg;
    }

    @Override
    public void run() {
        while (!message.isEndIt())
            message.viewMessage();
    }
}