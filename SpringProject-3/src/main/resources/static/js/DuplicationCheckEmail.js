function checkEmailDuplication(){
    const getEmail = $("#emailInput").val(); // 입력 값 가져오기

    if (getEmail === "") {
        $("#emailMsg").css('color', 'red').text("이메일을 입력해주세요");
        return; // 값이 비었으면 함수 종료
    }

    // AJAX 요청
    $.ajax({
        type: "GET",
        url: "/register/checkEmail",
        data: { userEmail: getEmail }, // 서버에서 기대하는 파라미터 키 확인
        success: function (res) {
            // 반환값이 문자열로 전달되는 경우
			
            if (res === "true") {
                $("#emailMsg").css('color', 'red').text("사용 불가능한 이메일 입니다.");
				
            } else {
                $("#emailMsg").css('color', 'green').text("사용 가능한 이메일 입니다.");
            }
        },
        error: function (err) {
            console.error("AJAX 요청 실패:", err);
        }
    });
}

// 버튼 클릭 이벤트 등록
$("#checkEmailButton").on("click", checkEmailDuplication);