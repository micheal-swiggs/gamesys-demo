import com.gamesys.hr.Employee

class BootStrap {

    def init = { servletContext ->
        def oneYear = 365L * 24L * 60L * 60L * 1000L
        def now = System.currentTimeMillis()
        new Employee(fullName: "Harry Potter", startDate: new Date(now - oneYear), holidayAllowance: 28).save()
        new Employee(fullName: "Laura Smith", startDate: new Date(now), holidayAllowance: 21).save()
        new Employee(fullName: "Green Face", startDate: new Date(now - 3L * oneYear), holidayAllowance: 29).save()
        new Employee(fullName: "Blue Sand", startDate: new Date(now - 2L * oneYear), holidayAllowance: 29).save()
        new Employee(fullName: "Red Mountain", startDate: new Date(now - 4L * oneYear), holidayAllowance: 24).save()
    }

    def destroy = {
    }
}
