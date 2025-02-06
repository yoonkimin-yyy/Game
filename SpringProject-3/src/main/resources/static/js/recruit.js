
function showPopup() {
    document.querySelector('#popup').classList.remove('hide');
}

function closePopup() {
    document.querySelector('#popup').classList.add('hide');
}

// 외부영역 클릭 시 팝업 닫기
$(document).mouseup(function (e) {
    var popContent = $('#popup');
    if (popContent.has(e.target).length === 0) {
        popup.classList.add('hide');
      // popContent.addClass('hide');   제이쿼리

    }
});

// 선택된 카테고리만 색변경
document.addEventListener("DOMContentLoaded", function() {
    // 모든 포지션 버튼 가져오기
    const buttons = document.querySelectorAll(".position-button");
    const input = document.getElementById("my-position");

    // 버튼 클릭 시 이벤트 리스너 추가
    buttons.forEach((button, index) => {
        button.addEventListener("click", function() {
            // 포지션 목록
            const positions = ["탑", "정글", "미드", "바텀", "서폿"];
            
            // input 값 변경
            input.value = positions[index];

            // 모든 버튼 스타일 초기화
            buttons.forEach(btn => btn.classList.remove("selected"));
            
            // 선택된 버튼 스타일 추가
            this.classList.add("selected");

            console.log("나의 포지션:", input.value); // 콘솔에서 확인 가능
        });
    });
});


$(document).ready(function() {
    $('.filter-button').click(function() {
        // 모든 버튼에서 'selected' 클래스를 제거
        $('.filter-button').removeClass('selected');
        
        // 클릭된 버튼에 'selected' 클래스를 추가
        $(this).addClass('selected');
        console.log(this);
    });
});

// 찾는 포지션
document.addEventListener("DOMContentLoaded", function() {
    // 모든 포지션 버튼 가져오기
    const buttons = document.querySelectorAll(".find-position-button");
    const input = document.getElementById("find-position");

    // 버튼 클릭 시 이벤트 리스너 추가
    buttons.forEach((button, index) => {
        button.addEventListener("click", function() {
            // 포지션 목록
            const positions = ["탑", "정글", "미드", "바텀", "서폿"];
            
            // input 값 변경
            input.value = positions[index];

            // 모든 버튼 스타일 초기화
            buttons.forEach(btn => btn.classList.remove("selected"));
            
            // 선택된 버튼 스타일 추가
            this.classList.add("selected");

            console.log("찾는 포지션:", input.value); // 콘솔에서 확인 가능
        });
    });
});






