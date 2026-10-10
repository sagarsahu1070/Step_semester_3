import java.util.*;

interface Reservable {
    void reserve(String member);
}

interface Downloadable {
    void download();
}

class Resource {
    String id;

    Resource(String id) {
        this.id = id;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id);
    }

    public void reserve(String member) {
        System.out.println(id + " reserved for " + member);
    }
}

class EBook extends Resource implements Downloadable {
    EBook(String id) {
        super(id);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class P5LibraryPlatform {
    static ArrayList<Resource> resources = new ArrayList<>();

    static void add(Resource resource) {
        int index = findIndex(resource.id);

        if (index >= 0) {
            System.out.println("duplicate rejected");
            return;
        }

        int position = -index - 1;
        resources.add(position, resource);
        System.out.println(resource.id + " added");
    }

    static int findIndex(String id) {
        int low = 0, high = resources.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = resources.get(mid).id.compareTo(id);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -low - 1;
    }

    static void reserve(String id, String member) {
        int index = findIndex(id);

        if (index < 0) {
            System.out.println(id + " not found");
            return;
        }

        Resource r = resources.get(index);

        if (r instanceof Reservable)
            ((Reservable) r).reserve(member);
        else
            System.out.println(id + " rejected: reserve unsupported");
    }

    static void download(String id) {
        int index = findIndex(id);

        if (index < 0) {
            System.out.println(id + " not found");
            return;
        }

        Resource r = resources.get(index);

        if (r instanceof Downloadable)
            ((Downloadable) r).download();
        else
            System.out.println(id + " rejected: download unsupported");
    }

    public static void main(String[] args) {
        add(new Book("B1"));
        add(new Book("B1"));
        add(new EBook("E1"));

        reserve("B1", "M1");
        download("B1");
        download("E1");

        int index = findIndex("E1");
        System.out.println("E1 found at index " + index);
    }
}