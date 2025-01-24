// 이름 입력받는쪽의 기능
function checkInputLength(){
    const inputName = document.getElementById('nameInput').value;
    const nameInputMsg = document.getElementById('nameInputMsg');
    const checkLanguage = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;


    let minLength = 3;
    let maxLength = 16;
    

    if (!checkLanguage.test(inputName)) {
        nameInputMsg.innerText = "한글만 입력 가능합니다.";
        nameInputMsg.style.marginBottom = "20px";
        nameInputMsg.style.marginRight = "140px";
        nameInputMsg.style.color ="red";
    } else if (inputName.length < minLength) {
        nameInputMsg.innerText = "3글자 이상 입력해야합니다.";
        nameInputMsg.style.marginBottom = "20px";
        nameInputMsg.style.marginRight = "110px";
    } else if (inputName.length > maxLength) {
        nameInputMsg.innerText = "입력가능한 글자수를 초과했습니다.";
        nameInputMsg.style.marginBottom = "20px";
        nameInputMsg.style.marginRight = "70px";
    } else {
        nameInputMsg.innerText = '';
    }
}
// 닉네임 입력받는쪽의 기능
function checkNickName() {
    const inputNickName = document.getElementById('nickNameInput').value;
    const nickNameMsg = document.getElementById('nickNameMsg');
    const checkNickName = /^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$/;

    const minLength = 2;
    const maxLength = 16;

    // 닉네임 유효성 검사
    if(inputNickName === ""){
        nickNameMsg.innerText= "";
    } else if (!checkNickName.test(inputNickName)) {
        nickNameMsg.innerText = "사용 불가능한 닉네임입니다.";
        nickNameMsg.style.marginRight = "120px";
        nickNameMsg.style.marginBottom = "10px";
        nickNameMsg.style.color = "red";
    } else if (inputNickName.length < minLength) {
        nickNameMsg.innerText = "두 글자 이상 입력해야 합니다.";
        nickNameMsg.style.marginBottom = "20px";
        nickNameMsg.style.marginRight = "100px";
    } else if (inputNickName.length > maxLength) {
        nickNameMsg.innerText = "입력 가능한 글자 수를 초과했습니다.";
        nickNameMsg.style.marginBottom = "20px";
        nickNameMsg.style.marginRight = "100px";
    } else if(inputNickName.length >= minLength && inputNickName.length <= maxLength) {
        nickNameMsg.innerText = "사용 가능한 닉네임입니다."; 
        nickNameMsg.style.color ="green";
        nickNameMsg.style.marginBottom = "10px";
        nickNameMsg.style.marginRight = "120px";
    }
}
// 아이디 입력반는쪽 기능
function checkId(){
    const inputId = document.getElementById('idInput').value;
    const idMsg = document.getElementById('idMsg');
    const checkId = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]*$/

    const minLength = 6;
    const maxLength = 16;


    if(inputId === ""){
        idMsg.innerText = ""
    }else if(!checkId.test(inputId)){
        idMsg.innerText = "사용 불가능한 아이디 입니다.";
        idMsg.style.marginRight = "120px";
        idMsg.style.marginBottom = "10px";
        idMsg.style.color = "red";
    }else if(inputId.length < minLength) {
        idMsg.innerText = "6 글자 이상 입력해야 합니다."
        idMsg.style.marginBottom = "20px";
        idMsg.style.marginRight = "120px";
    }else if(inputId.length > maxLength){
        idMsg.innerText = "입력 가능한 글자 수를 초과했습니다.";
        idMsg.style.marginBottom = "20px";
        idMsg.style.marginRight = "50px";
    }else{
        idMsg.innerText = "사용 가능한 아이디 입니다."
        idMsg.style.color ="green";
    }
	
	

    
}
function validatePassword() {
    const passwordInput = document.getElementById('passwordInput').value;
    const confirmInput = document.getElementById('checkPassWord').value;
    const pwdMsg = document.getElementById('pwMsg');
    const againMsg = document.getElementById('againMsg');

    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=]).+$/;
    const maxLength = 20;

    // 초기화
    pwdMsg.innerText = "";
    againMsg.innerText = "";

    // 비밀번호 입력 유효성 검사
    if (passwordInput === "") {
        pwdMsg.innerText = "";
    } else if (!passwordRegex.test(passwordInput)) {
        pwdMsg.innerText = "유효하지 않은 형식입니다.";
        pwdMsg.style.color = "red";
        pwdMsg.style.marginRight = "120px";
        pwdMsg.style.marginBottom = "10px";
    } else if (passwordInput.length < 8) {
        pwdMsg.innerText = "8 글자 이상 입력해야 합니다.";
        pwdMsg.style.color = "red";
        pwdMsg.style.marginBottom = "10px";
        pwdMsg.style.marginRight = "120px";
    } else if (passwordInput.length > maxLength) {
        pwdMsg.innerText = "입력 가능한 글자 수를 초과했습니다.";
        pwdMsg.style.color = "red";
        pwdMsg.style.marginBottom = "10px";
        pwdMsg.style.marginRight = "60px";
    } else {
        pwdMsg.innerText = "사용 가능한 비밀번호입니다.";
        pwdMsg.style.color = "green";
    }

    // 비밀번호와 확인 비밀번호 비교
    if (confirmInput !== "") {
        if (passwordInput !== confirmInput) {
            againMsg.innerText = "비밀번호가 일치하지 않습니다.";
            againMsg.style.color = "red";
            againMsg.style.marginRight = "100px";
            againMsg.style.marginBottom = "10px";
            
        } else {
            againMsg.innerText = "비밀번호가 일치합니다.";
            againMsg.style.color = "green";
            againMsg.style.marginRight = "150px";
            againMsg.style.marginBottom = "10px";
        }
    }
}

    

// 이메일 입력창 기능
function inputEmail(){
    const email = document.getElementById('emailInput').value;
    const mailMsg = document.getElementById('emailMsg');
    const emailRegex =  /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const maxLength = 30;

    if(email === ""){
        mailMsg.innerText = "";
    }else if(!emailRegex.test(email)){
        mailMsg.innerText = "이메일 형식이 올바르지 않습니다.";
        mailMsg.style.marginBottom = "10px";
        mailMsg.style.marginRight = "80px";
        mailMsg.style.color ="red";
    }else if(email.length < 14){
        mailMsg.innerText = "사용불가능한 이메일 입니다.";
        mailMsg.style.color ="red";
    }else if(email.length > maxLength){
        mailMsg.innerText = "사용불가능한 이메일 입니다.";
        mailMsg.style.color ="red";
    }else{
        mailMsg.innerText = "사용가능한 이메일 입니다."
        mailMsg.style.marginBottom = "10px";
        mailMsg.style.marginRight = "120px";
        mailMsg.style.color ="green";
    }
}
//
function showCheckboxes() {
   
    const giveNumber = document.querySelector(".give-code");
    giveNumber.style.display ="block";

    const inputCode = document.getElementById('input-code').value;
    const erroMsg = document.getElementById('errorMsg');

    // 버튼눌렀을떄 통신사 선택하시오 라는 알람기능
    const textMsg = document.getElementById('phoneMsg');
    textMsg.innerHTML = "통신사를 선택해주세요."
    textMsg.style.display ="block";
    textMsg.style.marginBottom = "10px";
    textMsg.style.color = "red";
    textMsg.style.marginRight = "140px"
}
function putPhoneNumber(){
    const  inputNumber = document.getElementById('inputNumber').value
    const  annoyMsg = document.getElementById('annoyMsg');
    const  phoneRegex =/^0\d{1,2}\d{3,4}\d{4}$/;

    const maxLength = 11;
    const minLength = 9;

    if(inputNumber === ""){
        annoyMsg.innerText = "";
    }else if(!phoneRegex.test(inputNumber)){
        annoyMsg.innerText ="유효하지 않은 형식입니다.";
        annoyMsg.style.color ="red";
        annoyMsg.style.marginRight = "120px";
        annoyMsg.style.marginBottom = "10px"
    }else if(inputNumber.length < minLength){
        annoyMsg.innerText ="최소 9자리 이상입니다.";
    }else if(inputNumber.length > maxLength){
        annoyMsg.innerText ="11자를 넘을수 없습니다.";
    }else{
        annoyMsg.innerText = "";
    }
}
