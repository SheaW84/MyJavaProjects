function getPending(){

    let div = document.querySelector('.request-details')

    let xhr = new XMLHttpRequest()

    xhr.open('GET','http://localhost:8080/ERSys/api/request/pending/employee')

    xhr.onreadystatechange = function(){

        if(xhr.readyState === 4  & xhr.status=== 200){
            let requests = JSON.parse(xhr.response)

            console.log(requests)

            for(let r of requests){

                let newRequest = document.createElement('div')
                let reqId = document.createElement('p')
                let reqReason = document.createElement('p')
                let reqAmount = document.createElement('p')
                let reqResolved = document.createElement('p')


                reqId.innerText = r.id
                reqReason.innerText=r.reason
                reqAmount.innerText=r.amount
                reqResolved.innerText=r.isResolved


                newRequest.append(reqId)
                newRequest.append(reqReason)
                newRequest.append(reqAmount)
                newRequest.append(reqResolved)

                div.append(newRequest)
            }

        }
    }
}

window.onload = () =>{
    getPending()
}