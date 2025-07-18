class A extends Thread{
    String str;
    A(String name){
        str = name;
        Thread.currentThread().setName(str);
    }
  public  void run(){
   System.out.println("Thread Name: "+ Thread.currentThread().getName() + " Priority: "+ Thread.currentThread().getPriority());
   }
  public void start(){
    super.start();
   }
}

class B{
    public static void main(String...k){
        A obj = new A("heol");
        A obj1 = new A("lol");
        A obj2 = new A("oka");

        obj.setPriority(Thread.MAX_PRIORITY);
        obj1.setPriority(1);
        obj2.setPriority(5);

        obj.start();
        obj1.start();
        obj2.start();
    }
}
