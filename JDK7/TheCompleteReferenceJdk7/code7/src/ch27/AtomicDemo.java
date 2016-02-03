package ch27;
// A simple example of Atomic.

import java.util.concurrent.atomic.*;

class AtomicDemo {

  public static void main(String args[]) {
    new AtomThread("A");
    new AtomThread("B");
    new AtomThread("C");
  }
}

class Shared {
  static AtomicInteger ai = new AtomicInteger(0);
}

// A thread of execution that increments count.
class AtomThread implements Runnable {
  String name;

  AtomThread(String n) {
    name = n;
    new Thread(this).start();
  }

  public void run() {

    System.out.println("Starting " + name);

    for(int i=1; i <= 3; i++)
      System.out.println(name + " got: " +
             Shared.ai.getAndSet(i));
  }
}
