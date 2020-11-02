import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        ExecutorService service = Executors.newFixedThreadPool(CARS_COUNT);

        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
        CountDownLatch cdl2 = new CountDownLatch(CARS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            final int w = i;
            service.execute(()->{
                cars[w].waitingRace();
                cdl.countDown();
            });
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        for (int i = 0; i < cars.length; i++) {
            final int w = i;
            service.execute(()->{
                cars[w].run();
                cdl2.countDown();
            });
        }
        try {
            cdl2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        service.shutdown();
    }
}