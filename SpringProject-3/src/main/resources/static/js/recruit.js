
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

$(document).ready(function() {
    let currentPage = 2; // 현재 페이지
    let isLoading = false; // 데이터 로딩 중인지 여부

    function loadMorePosts() {
        if (isLoading) return;
        isLoading = true;
		console.log("Loading more posts...");
        $('#loading').show(); // 로딩 표시

        $.ajax({
            url: '/MatchMyduo/api/recruit',
            type: 'GET',
            data: { currentPage: currentPage },
            success: function(response) {
				console.log(response);
                let posts = response.posts;
                let postContainer = $('.post-section');

                if (posts.length === 0) {
                    $(window).off("scroll"); // 더 이상 로드할 데이터가 없으면 스크롤 이벤트 제거
                    $('#loading').hide();
                    return;
                }

                posts.forEach(post => {
					console.log("Adding post:",post);
                    let postHtml = `
                        <div class="post">
                            <div class="post-header">
                                <div class="user-info">
                                    <img src="${post.profileIconUrl}" id="summoner-icon" alt="소환사 아이콘">
                                    <h3>${post.riotName} # ${post.riotTag}</h3>
                                </div>
                            </div>
                            <p>포지션: ${post.myPosition}</p>
                            <p>찾는 포지션: ${post.findPosition}</p>
                            <p>티어: ${post.lolTier} (${post.lolRank})</p>
                            <p>승률: ${((post.lolWin / (post.lolWin + post.lolLose)) * 100).toFixed(2)}%</p>
                            <p class="post-content">${post.partyContent}</p>
                            <p>작성일: ${post.createdDate}</p>
                        </div>
                    `;
                    postContainer.append(postHtml);
                });

                currentPage++; // 페이지 증가
                isLoading = false;
                $('#loading').hide(); // 로딩 숨김
            }
        });
    }

    // 스크롤 이벤트 리스너 추가
    $(window).scroll(function() {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            loadMorePosts();
        }
    });

    // 페이지 로드 시 첫 데이터 로드
    
});

// 소환사 프로필 이미지 가져오기







