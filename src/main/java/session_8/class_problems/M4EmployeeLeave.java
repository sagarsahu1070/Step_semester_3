import java.time.LocalDate;

abstract class Employee {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 15;
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(
        Employee employee,
        LocalDate startDate,
        LocalDate endDate
    ) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void approve() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Request has already been processed."
            );
            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
            "Leave request for " +
            employee.getName() +
            " approved. Status: Approved."
        );
    }

    public void reject() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Request has already been processed."
            );
            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(
            "Leave request for " +
            employee.getName() +
            " rejected."
        );
    }

    public void changeToPending() {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                "Cannot change status: " +
                status +
                " request cannot revert to Pending."
            );

            return;
        }
    }
}

class LeaveManager {

    public LeaveRequest submitLeave(
        Employee employee,
        LocalDate start,
        LocalDate end
    ) {

        LeaveRequest request =
            new LeaveRequest(
                employee,
                start,
                end
            );

        System.out.println(
            "Leave request submitted by " +
            employee.getName() +
            " for " +
            start +
            " to " +
            end +
            ". Status: Pending."
        );

        return request;
    }

    public void approveLeave(
        LeaveRequest request
    ) {

        request.approve();
    }
}

public class M4EmployeeLeave {

    public static void main(String[] args) {

        Employee john =
            new FullTimeEmployee("John Doe");

        Employee jane =
            new PartTimeEmployee("Jane Smith");

        LeaveManager manager =
            new LeaveManager();

        LeaveRequest johnRequest =
            manager.submitLeave(
                john,
                LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12)
            );

        manager.approveLeave(johnRequest);

        LeaveRequest janeRequest =
            manager.submitLeave(
                jane,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5)
            );

        johnRequest.changeToPending();
    }
}