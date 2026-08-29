package oop.assigment_problems;

class CompanyEmployee {

    private String empId;
    private String empName;
    private double salary;

    CompanyEmployee(
            String empId,
            String empName,
            double salary) {

        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class CompanyManagerEmployee
        extends CompanyEmployee {

    private double teamBonus;

    CompanyManagerEmployee(
            String empId,
            String empName,
            double salary,
            double teamBonus) {

        super(empId, empName, salary);

        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class CompanyInternEmployee
        extends CompanyEmployee {

    private double stipendCap;

    CompanyInternEmployee(
            String empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);

        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {

        return Math.min(
                getSalary(),
                stipendCap
        );
    }
}

class CompanyParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    CompanyParkingSlot(
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
                    + " allotted to "
                    + slotNo
            );

            return true;
        }

        return false;
    }
}

class CompanyEmployeeRecord {

    String name;
    String empId;

    CompanyEmployee employee;
    CompanyParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(
            String name,
            String empId,
            CompanyEmployee employee) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof CompanyManagerEmployee) {

            CompanyManagerEmployee manager =
                    (CompanyManagerEmployee) employee;

            pay = manager.effectiveSalary();

        } else if (employee instanceof CompanyInternEmployee) {

            CompanyInternEmployee intern =
                    (CompanyInternEmployee) employee;

            pay = intern.effectiveSalary();

        } else {

            pay = employee.getSalary();
        }

        String slotStatus;

        if (slot == null) {
            slotStatus = "no parking assigned";
        } else {
            slotStatus = slot.slotNo;
        }

        return name
                + " | Pay: Rs "
                + pay
                + " | Slot: "
                + slotStatus;
    }
}

public class HRParkingManagementSystem {

    static CompanyParkingSlot findAvailableSlot(
            CompanyParkingSlot[] slots) {

        for (CompanyParkingSlot slot : slots) {

            if (slot != null
                    && slot.occupiedCount < slot.capacity) {

                return slot;
            }
        }

        return null;
    }

    static void safeAllot(
            CompanyParkingSlot[] slots,
            String vehicleNo,
            CompanyEmployeeRecord record) {

        CompanyParkingSlot slot =
                findAvailableSlot(slots);

        if (slot != null) {

            if (slot.allot(vehicleNo)) {
                record.slot = slot;
            }

        } else {

            System.out.println(
                    "No parking available for "
                    + vehicleNo
            );
        }
    }

    public static void main(String[] args) {

        CompanyEmployee manager =
                new CompanyManagerEmployee(
                        "E001",
                        "Divya",
                        70000,
                        8000
                );

        CompanyEmployee plain =
                new CompanyEmployee(
                        "E002",
                        "Karan",
                        40000
                );

        CompanyEmployee intern =
                new CompanyInternEmployee(
                        "E003",
                        "Meera",
                        12000,
                        10000
                );

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E001",
                        manager
                );

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E002",
                        plain
                );

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E003",
                        intern
                );

        CompanyParkingSlot[] slots = {
                new CompanyParkingSlot("A1", 1, 0),
                new CompanyParkingSlot("A2", 1, 0)
        };

        safeAllot(
                slots,
                "CAR-DIVYA",
                divya
        );

        safeAllot(
                slots,
                "CAR-KARAN",
                karan
        );

        // Third employee intentionally gets no parking.
        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println(
                "Total records: "
                + CompanyEmployeeRecord.totalRecords
        );
    }
}