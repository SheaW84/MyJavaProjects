
let hasNoMessage = true

function validateUserPassword(event){

    let inputBoxes = document.getElementsByTagName('input')

    let password = inputBoxes[1].value

   

    if (password.length < 8){
        if (hasNoMessage){
        let form = document.getElementById('form')
        let errorMessage = document.createElement('p')
        errorMessage.innerText = 'Password must be at least 8 characters long'

        
        form.append(errorMessage)
        
        hasNoMessage = false
        }

        if(event.cancelable){
            event.preventDefault()
        }


    }

}


let button = document.querySelector('button')
button.addEventListener('click',validateUserPassword)
