public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    private static int count = 1;

    @Override
    public void go(Car c) {
        try {
            if (length == c.getRace().getStages().get(c.getRace().getStages().size()-1).length){
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                System.out.println(c.getName() + " закончил этап: " + description);
                System.out.println("!!! " + c.getName() + " занимает " + count + " место !!!");
                count++;
            }
            else{
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                System.out.println(c.getName() + " закончил этап: " + description);
            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}