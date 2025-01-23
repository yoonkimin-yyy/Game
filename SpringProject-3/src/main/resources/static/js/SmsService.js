
let authCode = ""; // 서버에서 인증받은 번호 저장
let isVerfied = false; // 인증 상태 확인


function sendSmsVerification() {
    const phoneNumber = $("#inputNumber").val(); // 입력된 전화번호 가져오기

	console.log(phoneNumber);
	
    if (phoneNumber === "") {
        $("#smsMsg").css('color', 'red').text("전화번호를 입력해주세요");
        return; 
    }

    // AJAX 요청
    $.ajax({
        type: "POST",
        url: "/register/sendSms",
        data: { phoneNumber: phoneNumber }, // 서버에서 기대하는 파라미터 키 확인
        success: function (res) {
            // 성공적으로 서버에서 인증번호가 발송된 경우
			console.log(res);
			authCode = res;
			console.log(authCode);
            $("#smsMsg").css('color', 'green').text(res); // 서버에서 보낸 메시지 출력
        },
        error: function (err) {
            console.error("AJAX 요청 실패:", err);
            $("#smsMsg").css('color', 'red').text("인증번호 발송 중 오류가 발생했습니다.");
        }
    });

}
function checkCodeNumber(){
	const userCode = $("#input-code").val();
	console.log(userCode);
	if(userCode === authCode){
		isVerified = true;
		$("#errorMsg").css("color", "green").text("인증번호가 일치합니다.");
	}else{
		isVerified = false;
		 $("#errorMsg").css("color", "red").text("인증번호가 일치하지 않습니다.");
	}
}
$(document).ready(function () {
    // 인증번호 발송 버튼 클릭 시
    $(".verify-button").on("click", sendSmsVerification);

    // 인증번호 입력란에서 입력 시마다 확인
    $("#input-code").on("input", checkCodeNumber);
});



