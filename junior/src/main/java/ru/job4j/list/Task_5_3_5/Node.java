package ru.job4j.list.Task_5_3_5;

class Node<T> {

    /**
     * Значение элемента
     */
    private T value;


    /**
     * Указатель на следующий элемент
     */
    Node<T> next;

    /**
     * Конструктор
     *
     * @param value - значение элемента
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * @param root - указатель на первый элемент
     * @return - {@code = true} if has cycle
     */
    public static boolean hasCycle(Node root) {
        if(root == null) return false;

        Node turtle = root, rabbit = root;
        int taken = 0, limit = 2;

        while (rabbit.next != null) {
            rabbit = rabbit.next;
            taken++;
            if(turtle == rabbit) return true;

            if(taken == limit){
                taken = 0;
                limit *= 2;
                turtle = rabbit;
            }
        }
        return false;
    }
}
