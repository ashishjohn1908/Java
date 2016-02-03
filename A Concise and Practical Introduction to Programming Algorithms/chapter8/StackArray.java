//
//
//

class StackArray {
    int nbmax;
    int index;
    int[] array;

    // Constructors
    StackArray(int n) {
        this.nbmax = n;
        array = new int[nbmax];
        index = -1;
        System.out.println("Succesfully created a stack array object...");
    }

    // Methods
    void Push(int element) {
        if (index < nbmax - 1)
            array[++index] = element;
    }

    int Pull() {
        if (index >= 0) return array[index--];
        else return -1;
    }

}


class DemoStack {

    public static void main(String[] args) {
        StackArray myStack = new StackArray(10);
        int i;

        for (i = 0; i < 10; i++)
            myStack.Push(i);

        for (i = 0; i < 15; i++)
            System.out.println(myStack.Pull());

    }


}