package lab3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Museum {
    private boolean isOpen = false;
    private Lock lock = new ReentrantLock();
    private Enviroment musEnv = new Enviroment();

    public void startWork() {
        Thread eastThread = new Thread(new EastEntrance());
        Thread westThread = new Thread(new WestExit());
        Thread controlThread = new Thread(new Control());
        Thread directorThread = new Thread(new Director());

        eastThread.start();
        westThread.start();
        controlThread.start();
        directorThread.start();
    }

    class EastEntrance implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(musEnv.getTimeIn()); // Эмуляция времени прохода через восточный вход
                    lock.lock();
                    if (isOpen) {
                    	musEnv.newVisitorEntered();
                        System.out.println("Посетитель вошел --->");
                        System.out.println("Посетителей в музее: " + musEnv.getNumOfCurVisitors());
                    }
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class WestExit implements Runnable {
        public void run() {
            while (true) {
                try {
                	Thread.sleep(musEnv.getTimeOut()); // Эмуляция времени прохода через западный выход
                	lock.lock();
                	if (isOpen && musEnv.getNumOfCurVisitors() > 0) {
                		musEnv.visitorOuted();
                		System.out.println("Посетитель вышел <---");
                		System.out.println("Посетителей в музее: " + musEnv.getNumOfCurVisitors());
                		}
                	lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Control implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(musEnv.getCheckTime()); // Проверка состояния музея
                    lock.lock();
                    System.out.println("...Контроллер проверяет состояние музея...");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Director implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(musEnv.getWorkTime());
                    lock.lock();
                    isOpen = !isOpen; // Смена состояния музея
                    if (isOpen) {
                        System.out.println("Музей открыт.");
                    } else {
                        System.out.println("Музей закрыт.");
                        System.out.println("Запертых в музее посетителей: " + musEnv.getNumOfCurVisitors());
                    }
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
