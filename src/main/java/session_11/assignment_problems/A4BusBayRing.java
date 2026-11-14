
public class A4BusBayRing {
    static class BusBay {
        private final int[] buses;
        private final int capacity;
        private int front;
        private int size;

        public BusBay(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException(
                    "Capacity must be positive"
                );
            }

            this.capacity = capacity;
            buses = new int[capacity];
            front = 0;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean insertFront(int bus) {
            if (isFull()) {
                return false;
            }

            front = (front - 1 + capacity) % capacity;
            buses[front] = bus;
            size++;
            return true;
        }

        public boolean insertLast(int bus) {
            if (isFull()) {
                return false;
            }

            int rear = (front + size) % capacity;
            buses[rear] = bus;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            front = (front + 1) % capacity;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            size--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : buses[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }

            int rear = (front + size - 1) % capacity;
            return buses[rear];
        }
    }

    public static void main(String[] args) {
        BusBay b = new BusBay(3);

        System.out.println(b.insertLast(101));
        System.out.println(b.insertLast(102));
        System.out.println(b.insertFront(100));
        System.out.println(b.insertFront(99));
        System.out.println(b.getRear());
        System.out.println(b.isFull());
        System.out.println(b.deleteLast());
        System.out.println(b.insertFront(98));
        System.out.println(b.getFront());
    }
}
