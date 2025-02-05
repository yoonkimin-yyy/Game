function putIdEmail(){
    const putIdEmail  = document.getElementById('onId').value;
    const sendMessage = document.getElementById("messageAalram");
    let maxLength = 16;

    if (putIdEmail === "") {
        sendMessage.innerText = "Id 또는 Email를 입력해주세요";
        sendMessage.style.color = "white";
        sendMessage.style.marginRight = "120px";
        sendMessage.style.display = "block";
    } else if (putIdEmail.length > maxLength) {
        sendMessage.innerText = "입력가능한 문자수를 초과했습니다.";
        sendMessage.style.color = "white";
        sendMessage.style.marginRight = "90px";
        sendMessage.style.display = "block";
    } else {
        sendMessage.innerText = ""; 
        sendMessage.style.display = "none"; 
    }
}
function putPassword(){
    const  inputPassword = document.getElementById("onPassword").value;
    const  textMessage = document.getElementById("loginPassword");
    let maxLength = 20;
    let minLength = 12;
    console.log(inputPassword);
    console.log(textMessage);

    if(inputPassword === ""){
        textMessage.innerText = "Password를 입력해주세요";
        textMessage.style.color = "white";
        textMessage.style.marginRight = "140px";
        
    }else if(inputPassword.length>maxLength){
        textMessage.innerText = "최대 20자까지만 입력가능합니다";
        textMessage.style.color = "red";
        textMessage.style.marginRight = "80px";
    }else if(inputPassword.length < minLength){
        textMessage.innerText = "12자리 이상입력해주세요.";
        textMessage.style.color = "red";
        textMessage.style.marginRight = "140px";
    }else{
        textMessage.innerText= "";
        textMessage.style.display = "none";
    }


    
}
function pushButton(){
    const putIdEmail = document.getElementById('onId').value;
    const inputPassword =  document.getElementById("onPassword").value;

    if(putIdEmail === "" && inputPassword === ""){
        swal({
            title : '경고',
            text : '입력창을 입력해주세요',
            icon: "warning",
            confirmButtonText : '확인'
        })
    }else if(putIdEmail === ""|| inputPassword === ""){
        swal({
            title : '경고',
            text : '입력창을 입력해주세요',
            icon: "warning",
            confirmButtonText : '확인'
        })
    } 
    else {
        // submit 이벤트 실행
        const submitForm = document.getElementById("loginForm");
        submitForm.submit();
    }

    //
}