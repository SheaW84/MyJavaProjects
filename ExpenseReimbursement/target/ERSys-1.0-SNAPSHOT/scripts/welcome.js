function getEmployeeInfo(){

    let div = document.querySelector('.employee-details')

    let xhr = new XMLHttpRequest()

    xhr.open('GET', 'http://localhost:8080/ERSys/api/employee/details',true)
    xhr.send()

    xhr.onreadystatechange = function(){

        if(xhr.readyState === 4  & xhr.status=== 200){

            let employees = JSON.parse(xhr.response)
            console.log(employees)
            console.log('employee info')

                let newEmployee = document.createElement('div')
                let empFirstname = document.createElement('p')
                let empLastname = document.createElement('p')
                let empAddress = document.createElement('p')
                let empEmail = document.createElement('p')
                let managerFirstname = document.createElement('p')
                let managerLastname = document.createElement('p')
                let managerEmail = document.createElement('p')

                empFirstname.innerText = employees.firstName
                empLastname.innerText = employees.lastName
                empAddress.innerText = employees.address
                empEmail.innerText = employees.email
                managerFirstname.innerText = employees.manager.firstName
                managerLastname.innerText = employees.manager.lastName
                managerEmail.innerText = employees.manager.email

                newEmployee.append(empFirstname)
                newEmployee.append(empLastname)
                newEmployee.append(empAddress)
                newEmployee.append(empEmail)
                newEmployee.append(managerFirstname)
                newEmployee.append(managerLastname)
                newEmployee.append(managerEmail)

                div.append(newEmployee)            
            
        }
    }
}

window.onload = () =>{
    getEmployeeInfo() 
}

