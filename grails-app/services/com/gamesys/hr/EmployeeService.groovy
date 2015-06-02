package com.gamesys.hr

import grails.transaction.Transactional

@Transactional
class EmployeeService {

    def startingBetween(def sDate, def eDate) {
        return Employee.executeQuery(
            "from Employee emp where emp.startDate >= :sDate and emp.startDate <= :eDate ",
            [sDate: sDate , eDate: eDate]
        )
    }
}
