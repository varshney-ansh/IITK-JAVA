class C{
    public static Object obj = new Object();
    public static Object obj1 = new Object();

    public static void main(String...k){
       ThreadDemo obj = new ThreadDemo();
       ThreadDemo1 obj1 = new ThreadDemo1();
       obj.start();
       obj1.start();
    }

    private static class ThreadDemo extends Thread{
       public void run(){
        synchronized(obj){
            System.out.println("Thread 1 holding obj..");
            try{
               Thread.sleep(2000);
            }catch(Exception e){
               System.out.println(e) ;
            }
            System.out.println("Thread 1 waiting for obj 2");
            synchronized(obj1){
                System.out.println("Thread 1: Holding obj 1 and 2");
            }
        }
       } 
    }

    private static class ThreadDemo1 extends Thread{
        public void run(){
        synchronized(obj1){
            System.out.println("Thread 2 holding obj1..");
            try{
               Thread.sleep(2000);
            }catch(Exception e){
               System.out.println(e) ;
            }
            System.out.println("Thread 2 waiting for obj ");
            synchronized(obj){
                System.out.println("Thread 2: Holding obj 1 and 2");
            }
        }
       } 
    }

}
