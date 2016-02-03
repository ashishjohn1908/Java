//listing 16
// Show public methods.

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionDemo21 {
    public static void main(String args[]) {
        try {
            A a = new A();
            Class c = a.getClass();
            System.out.println("Public Methods:");
            Method methods[] = c.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                int modifiers = methods[i].getModifiers();
                if (Modifier.isPublic(modifiers)) {
                    System.out.println(" " + methods[i].getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }


    static class A {
        public void a1() {
        }

        public void a2() {
        }

        protected void a3() {
        }

        private void a4() {
        }
    }
}