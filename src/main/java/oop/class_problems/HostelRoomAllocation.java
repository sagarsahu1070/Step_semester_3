package oop.class_problems;

class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    boolean allot(String name) {

        if (occupied < beds) {

            occupied++;

            System.out.println(
                    name + " allotted to room " + roomNo
            );

            return true;
        }

        return false;
    }
}

public class HostelRoomAllocation {

    static HostelRoom findAvailableRoom(
            HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {

            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(
            HostelRoom[] rooms,
            String studentName) {

        HostelRoom room =
                findAvailableRoom(rooms);

        if (room != null) {

            room.allot(studentName);

        } else {

            System.out.println(
                    "No rooms available for "
                    + studentName
            );
        }
    }

    public static void main(String[] args) {

        HostelRoom[] availableRooms = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };

        safeAllot(
                availableRooms,
                "Divya"
        );

        System.out.println();

        HostelRoom[] fullRooms = {
                new HostelRoom("C-214", 3, 3),
                new HostelRoom("C-507", 2, 2)
        };

        safeAllot(
                fullRooms,
                "Divya"
        );
    }
}