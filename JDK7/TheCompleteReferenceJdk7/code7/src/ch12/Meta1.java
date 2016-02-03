package ch12;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno1 {
  String str();
  int val();
}

class Meta1 {

  // myMeth now has two arguments.
  @MyAnno1(str = "Two Parameters", val = 19)
  public static void myMeth(String str, int i)
  {
    Meta1 ob = new Meta1();

    try {
      Class<?> c = ob.getClass();

      // Here, the parameter types are specified.
      Method m = c.getMethod("myMeth", String.class, int.class);

      MyAnno1 anno = m.getAnnotation(MyAnno1.class);

      System.out.println(anno.str() + " " + anno.val());
    } catch (NoSuchMethodException exc) {
       System.out.println("Method Not Found.");
    }
  }

  public static void main(String args[]) {
    myMeth("test", 10);
  }
}