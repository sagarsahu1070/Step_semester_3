package oop.class_problems;

class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {

        if (amount <= 0) {
            System.out.println("Payment rejected.");
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

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {

        pay(amount);
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(
            String regNo,
            double totalFee,
            double scholarshipPercent) {

        super(regNo, totalFee);

        if (scholarshipPercent < 0) {
            scholarshipPercent = 0;
        }

        if (scholarshipPercent > 100) {
            scholarshipPercent = 100;
        }

        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {

        return getDue()
                - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccountSystem {

    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("RA001", 150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("RA002", 200000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount(
                        "RA003", 180000, 20);

        plain.pay(150000);

        hostel.payInTwoInstallments(30000);

        System.out.println(
                "Plain account due: Rs "
                + plain.getDue()
        );

        System.out.println(
                "Hostel account due: Rs "
                + hostel.getDue()
        );

        if (scholarship instanceof ScholarshipFeeAccount) {

            System.out.println(
                    "Scholarship account effective due: Rs "
                    + scholarship.effectiveDue()
            );
        }
    }
}