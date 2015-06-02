package com.gamesys.hr

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional



@Transactional(readOnly = true)
class EmployeeController {


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", dateFilter: "POST"]

    def employeeService

    def toDate(year, month, day){
        def cal = new GregorianCalendar()
        cal.set(year.toInteger(), month.toInteger(), day.toInteger())
        return cal.getTime()
    }
    def dateFilter() {
        def empList = employeeService.startingBetween(
            toDate(params.startDate_year, params.startDate_month, params.startDate_day),
            toDate(params.endDate_year, params.endDate_month, params.endDate_day)
        )

        render(model:[ employeeList: empList, employeeCount: empList.size()], view: 'index')
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render(model:[ employeeList: Employee.list(params), employeeCount: Employee.count()], view: 'index')
    }

    def show(Employee employee) {
        if(employee == null) {
            notFound()
            return
        }
        render(model: [employee: employee], view: 'show')
    }

    def create() {
        respond new Employee(params)
    }

    @Transactional
    def save(Employee employee) {
        if (employee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (employee.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond employee.errors, view:'create'
            return
        }

        employee.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*' { respond employee, [status: CREATED] }
        }
    }

    def edit(Employee employee) {
        respond employee
    }

    @Transactional
    def update(Employee employee) {
        if (employee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (employee.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond employee.errors, view:'edit'
            return
        }

        employee.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*'{ respond employee, [status: OK] }
        }
    }

    @Transactional
    def delete(Employee employee) {

        if (employee == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        employee.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
