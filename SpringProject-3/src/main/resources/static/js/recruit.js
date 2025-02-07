
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

function showPopup2(abs) {
    document.querySelector('#popup2').classList.remove('hide');
	
	const riotNameElement = abs.getElementsByClassName('riot-name')[0];	
	document.getElementById('input-riot-name').textContent = riotNameElement.textContent;
	
	const myPositionElement = abs.getElementsByClassName('my-position')[0];
	document.getElementById('input-my-position').textContent = myPositionElement.textContent;
	
	const findPositionElement = abs.getElementsByClassName('find-position')[0];
	document.getElementById('input-find-position').textContent = findPositionElement.textContent;
	
	const summonerImg = abs.getElementsByClassName('summoner-icon')[0];
	document.getElementsByClassName('input-summoner-icon')[0].src = summonerImg.src;

	const myPositionImg = abs.getElementsByClassName('my-position-img')[0];
	document.getElementsByClassName('input-my-position-img')[0].src = myPositionImg.src;
	
	const findPositionImg = abs.getElementsByClassName('find-position-img')[0];
	document.getElementsByClassName('input-find-position-img')[0].src = findPositionImg.src;
	
	const contentText = abs.getElementsByClassName('post-content')[0];
	document.getElementById('input-post-content').textContent = contentText.textContent;
	
	const tierImg = abs.getElementsByClassName('tier')[0];
	document.getElementsByClassName('input-tier-img')[0].src = tierImg.src;
	
	
	
	
	
	
	
}

function closePopup2() {
    document.querySelector('#popup2').classList.add('hide');
}


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

// 그래프 기능


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
                        <div onclick="showPopup2(this)" class="post wow fadeInUp" data-wow-delay=".6s">
                            <div class="post-header">
                                <div class="user-info">
                                    <img id="summoner-icon" class="summoner-icon" src="${post.profileIconUrl}" id="summoner-icon" alt="소환사 아이콘">
                                    <h3 class="riot-name">${post.riotName} # ${post.riotTag}</h3>
                                </div>
                            </div>
							<div class="position-div">
								<div class="position-sub-div">
									<img id="position" src="/img/${post.myPosition}라인.svg"/>
                            		<p id="position">${post.myPosition}</p>
								</div>
							</div>
							<div id="tier-div">
								<img class="tier" id="tierImg" src="/img/${post.lolTier}.png" alt="Rank Tier" />	
                            	<p class="tier">${post.lolTier}</p>
								<p class="tier">${post.lolRank}</p>
							</div>
							<div class="position-div">
								<div class="position-sub-div">
									<img id="position" src="/img/${post.findPosition}라인.svg"/>
                           			<p id="position">${post.findPosition}</p>
								</div>
							</div>
							<div >
								<canvas id="winLose" width="200" height="50"></canvas>
                            	<p id="winLose1">${((post.lolWin / (post.lolWin + post.lolLose)) * 100).toFixed(0)}%</p>
							</div>
                            <div class="post-div">
								<p class="post-content">${post.partyContent}</p>
							</div>
							<div class="date">
                            	<p>${post.createdDate}</p>
							</div>
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









