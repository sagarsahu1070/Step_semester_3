package oop.assigment_problems;

class Employee {

    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(
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

class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(
            String empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);

        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        }

        return stipendCap;
    }
}

public class EmployeePaySystem {

    public static void main(String[] args) {

        Employee plain =
                new Employee(
                        "E001",
                        "Karan",
                        40000
                );

        ManagerEmployee manager =
                new ManagerEmployee(
                        "E002",
                        "Divya",
                        70000,
                        8000
                );

        InternEmployee intern =
                new InternEmployee(
                        "E003",
                        "Meera",
                        12000,
                        10000
                );

        if (plain instanceof ManagerEmployee) {

            ManagerEmployee managerEmployee =
                    (ManagerEmployee) plain;

            System.out.println(
                    "Manager effective pay: Rs "
                    + managerEmployee.effectiveSalary()
            );

        } else if (plain instanceof InternEmployee) {

            InternEmployee internEmployee =
                    (InternEmployee) plain;

            System.out.println(
                    "Intern effective pay: Rs "
                    + internEmployee.effectiveSalary()
            );

        } else {

            System.out.println(
                    "Plain employee pay: Rs "
                    + plain.getSalary()
            );
        }

        if (manager instanceof ManagerEmployee) {

            System.out.println(
                    "Manager effective pay: Rs "
                    + manager.effectiveSalary()
            );
        }

        if (intern instanceof InternEmployee) {

            System.out.println(
                    "Intern effective pay: Rs "
                    + intern.effectiveSalary()
            );
        }
    }
}