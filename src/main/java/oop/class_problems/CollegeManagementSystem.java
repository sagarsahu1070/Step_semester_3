package oop.class_problems;

class CapstoneFeeAccount {

    private double totalFee;
    private double amountPaid;

    CapstoneFeeAccount(double totalFee) {
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {

        if (amount <= 0) {
            System.out.println(
                    "Payment rejected: invalid amount"
            );
            return;
        }

        amountPaid += amount;

        if (amountPaid > totalFee) {
            amountPaid = totalFee;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class CapstoneHostelFeeAccount
        extends CapstoneFeeAccount {

    CapstoneHostelFeeAccount(double totalFee) {
        super(totalFee);
    }
}

class CapstoneHostelRoom {

    String roomNo;
    int beds;
    int occupied;

    CapstoneHostelRoom(
            String roomNo,
            int beds,
            int occupied) {

        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    boolean allot() {

        if (occupied < beds) {
            occupied++;
            return true;
        }

        return false;
    }
}

class SrmStudent {

    String name;
    String regNo;

    CapstoneHostelFeeAccount feeAccount;
    CapstoneHostelRoom room;

    static int totalStudents = 0;

    SrmStudent(
            String name,
            String regNo,
            CapstoneHostelFeeAccount feeAccount) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;

        totalStudents++;
    }

    String fullStatus() {

        String roomStatus;

        if (room == null) {
            roomStatus = "unallotted";
        } else {
            roomStatus = room.roomNo;
        }

        return name
                + " | Due: Rs "
                + feeAccount.getDue()
                + " | Room: "
                + roomStatus;
    }
}

public class CollegeManagementSystem {

    public static void main(String[] args) {

        CapstoneHostelFeeAccount raviFee =
                new CapstoneHostelFeeAccount(200000);

        CapstoneHostelFeeAccount anithaFee =
                new CapstoneHostelFeeAccount(200000);

        CapstoneHostelFeeAccount karthikFee =
                new CapstoneHostelFeeAccount(200000);

        SrmStudent ravi =
                new SrmStudent(
                        "Ravi",
                        "RA001",
                        raviFee
                );

        SrmStudent anitha =
                new SrmStudent(
                        "Anitha",
                        "RA002",
                        anithaFee
                );

        SrmStudent karthik =
                new SrmStudent(
                        "Karthik",
                        "RA003",
                        karthikFee
                );

        CapstoneHostelRoom room1 =
                new CapstoneHostelRoom(
                        "C-214", 2, 1
                );

        CapstoneHostelRoom room2 =
                new CapstoneHostelRoom(
                        "C-507", 2, 1
                );

        if (room1.allot()) {
            ravi.room = room1;
        }

        if (room2.allot()) {
            anitha.room = room2;
        }

        raviFee.pay(60000);

        anithaFee.pay(20000);

        karthikFee.pay(-5000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
                "Total students: "
                + SrmStudent.totalStudents
        );
    }
}