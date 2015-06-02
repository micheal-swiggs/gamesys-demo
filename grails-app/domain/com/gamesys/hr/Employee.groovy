package com.gamesys.hr

class Employee {

    String fullName
    Date startDate
    Integer holidayAllowance //In days
    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName (blank: false, validator: { val, obj ->
            def oEmployee = Employee.findByFullNameIlike(val)
            if( !(!oEmployee || obj.id == oEmployee.id)) return ["fullNameNotUnique"]
        })
        startDate (validator: {
            if( !it.before(new Date())) return ['startDateInFuture']
        })
        holidayAllowance (min: 20, validator: { val, obj ->
            def oneYear = 365L * 24L * 60L * 60L * 1000L
            if (obj.startDate.getTime() <  (System.currentTimeMillis() - oneYear)) {
                if(val > 30) return ['allowanceTooLarge']
            } else {
                if(val > 25) return ['allowanceTooLarge']
            }
        })
    }
}
