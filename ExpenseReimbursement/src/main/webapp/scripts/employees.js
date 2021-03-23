let employeeBody = document.querySelector('#employee-table > tbody')

function getEmployeeInfo(){

    let xhr = new XMLHttpRequest()

    xhr.open('GET','http://localhost:8080/ERSys/api/user/all')
    xhr.send()

    xhr.onreadystatechange = function(){

        if(xhr.readyState === 4 & xhr.status=== 200){

            let employees = JSON.parse(xhr.response)

            for(let e of employees){

                let newEmployee = document.createElement('tr')
                let empFirstname = document.createElement('td')
                let empLastname = document.createElement('td')
                let empAddress = document.createElement('td')
                let empEmail = document.createElement('td')
                let managerFirstname = document.createElement('td')
                let managerLastname = document.createElement('td')
                let managerEmail = document.createElement('td')

                empFirstname.innerText = e.firstName
                empLastname.innerText = e.lastName
                empAddress.innerText = e.address
                empEmail.innerText = e.email
                managerFirstname.innerText = e.manager.firstName
                managerLastname.innerText = e.manager.lastName
                managerEmail.innerText = e.manager.email

                newEmployee.append(empFirstname)
                newEmployee.append(empLastname)
                newEmployee.append(empAddress)
                newEmployee.append(empEmail)
                newEmployee.append(managerFirstname)
                newEmployee.append(managerLastname)
                newEmployee.append(managerEmail)

                employeeBody.append(newEmployee)  
            }          
        }
    }
}

window.onload = () =>{
    getEmployeeInfo() 
}