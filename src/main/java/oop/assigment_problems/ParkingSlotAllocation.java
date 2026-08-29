package oop.assigment_problems;

class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(
            String slotNo,
            int capacity,
            int occupiedCount) {

        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    boolean allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                    vehicleNo
                    + " allotted to slot "
                    + slotNo
            );

            return true;
        }

        return false;
    }
}

public class ParkingSlotAllocation {

    static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot != null
                    && slot.occupiedCount < slot.capacity) {

                return slot;
            }
        }

        return null;
    }

    static void safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot slot =
                findAvailableSlot(slots);

        if (slot != null) {

            slot.allot(vehicleNo);

        } else {

            System.out.println(
                    "No slots available for "
                    + vehicleNo
            );
        }
    }

    /*
     * Passing ParkingSlot[] does not create copies of the
     * ParkingSlot objects. The array contains references to
     * the actual objects, so changes made to a slot affect
     * the original object.
     */

    public static void main(String[] args) {

        ParkingSlot[] availableSlots = {
                new ParkingSlot("A1", 4, 3),
                new ParkingSlot("A2", 5, 5)
        };

        safeAllot(
                availableSlots,
                "TN09AB1234"
        );

        System.out.println();

        ParkingSlot[] fullSlots = {
                new ParkingSlot("A1", 4, 4),
                new ParkingSlot("A2", 5, 5)
        };

        safeAllot(
                fullSlots,
                "TN09AB1234"
        );
    }
}