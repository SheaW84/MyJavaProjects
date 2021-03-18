let requestBody = document.querySelector('#request-table > tbody')

function getPending(){

    console.log (requestBody)
  
    let xhr = new XMLHttpRequest();

    xhr.open('GET','http://localhost:8080/ERSys/api/request/pending/employee')
    xhr.send()

    xhr.onreadystatechange = function(){

        if(xhr.readyState === 4  & xhr.status=== 200){
            let requests = JSON.parse(xhr.response)
            console.log(requests)
            

            for(let r of requests){

                let newRequest = document.createElement('tr')
                let reqId = document.createElement('td')
                let reqReason = document.createElement('td')
                let reqAmount = document.createElement('td')
                let reqResolved = document.createElement('td')


                reqId.innerText = r.id
                reqReason.innerText=r.reason
                reqAmount.innerText=r.amount
                reqResolved.innerText=r.isResolved


                newRequest.append(reqId)
                newRequest.append(reqReason)
                newRequest.append(reqAmount)
                newRequest.append(reqResolved)

                requestBody.append(newRequest)
            }

        }
    }
}

window.onload = () =>{
    getPending()
}