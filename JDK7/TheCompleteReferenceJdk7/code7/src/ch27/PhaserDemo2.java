package ch27;
// Extend Phaser and override onAdvance() so that only a specific
// number of phases are executed.

import java.util.concurrent.*;

// Extend MyPhaser to allow only a specific number of phases
// to be executed.
class MyPhaser extends Phaser {
  int numPhases;

  MyPhaser(int parties, int phaseCount) {
    super(parties);
    numPhases = phaseCount - 1;
  }

  // Override onAdvance() to execute the specified
  // number of phases.
  protected boolean onAdvance(int p, int regParties) {
    // This println() statement is for illustration only.
    // Normally, onAdvance() will not display output.
    System.out.println("Phase " + p + " completed.\n");

    // If all phases have completed, return true
    if(p == numPhases || regParties == 0) return true;

    // Otherwise, return false.
    return false;
  }
}

class PhaserDemo2 {
  public static void main(String args[]) {

    MyPhaser phsr = new MyPhaser(1, 4);

    System.out.println("Starting\n");

    new MyThread3(phsr, "A");
    new MyThread3(phsr, "B");
    new MyThread3(phsr, "C");

    // Wait for the specified number of phases to complete..
    while(!phsr.isTerminated()) {
      phsr.arriveAndAwaitAdvance();
    }

    System.out.println("The Phaser is terminated");
  }
}

// A thread of execution that uses a Phaser.
class MyThread3 implements Runnable {
  Phaser phsr;
  String name;

  MyThread3(Phaser p, String n) {
    phsr = p;
    name = n;
    phsr.register();
    new Thread(this).start();
  }

  public void run() {

    while(!phsr.isTerminated()) {
      System.out.println("Thread " + name + " Beginning Phase " + phsr.getPhase());

      phsr.arriveAndAwaitAdvance();

      // Pause a bit to prevent jumbled output. This is for illustration
      // only. It is not required for the proper opration of the phaser.
      try {
        Thread.sleep(10);
      } catch(InterruptedException e) {
        System.out.println(e);
      }
    }

  }
}