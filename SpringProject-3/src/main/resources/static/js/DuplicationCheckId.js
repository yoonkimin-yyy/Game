function checkIdDuplicate() {
    const getId = $("#idInput").val(); // 입력 값 가져오기

    if (getId === "") {
        $("#idMsg").css('color', 'red').text("아이디를 입력해주세요");
        return; // 값이 비었으면 함수 종료
    }

    // AJAX 요청
    $.ajax({
        type: "GET",
        url: "/MatchMyduo/checkId",
        data: { userId: getId }, // 서버에서 기대하는 파라미터 키 확인
        success: function (res) {
            // 반환값이 문자열로 전달되는 경우
		
			
            if (res === "true") {
                $("#idMsg").css('color', 'red').text("사용 불가능한 아이디 입니다.");
				
            } else {
                $("#idMsg").css('color', 'green').text("사용 가능한 아이디 입니다.");
            }
        },
        error: function (err) {
            console.error("AJAX 요청 실패:", err);
        }
    });
}

// 버튼 클릭 이벤트 등록
$("#checkIdButton").on("click", checkIdDuplicate);