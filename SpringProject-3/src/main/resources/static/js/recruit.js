

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
document.querySelectorAll('.position-button').forEach(button => {
    button.addEventListener('click', function() {
        // 모든 버튼에서 selected 클래스 제거
        document.querySelectorAll('.position-button').forEach(btn => {
            btn.classList.remove('selected');
        });
        
        // 클릭된 버튼에 selected 클래스 추가
        this.classList.add('selected');
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

$(document).ready(function(){
    $('.find-position-button').click(function(){
        $('.find-position-button').removeClass('selected');

        $(this).addClass('selected');
    });
});