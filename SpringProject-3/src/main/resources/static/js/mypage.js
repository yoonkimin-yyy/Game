const change1 = document.getElementById("card1");
const change2 = document.getElementById("card2");
const change11 = document.getElementById("card1-1");
const change3 = document.getElementById("card3");
const change4 = document.getElementById("card4");
const change5 = document.getElementById("card5");
const change6 = document.getElementById("card6");
const change7 = document.getElementById("card7");

function clickFnc1(){

    if(true){
        change1.style.display = "block";
        change2.style.display = "block";
        change11.style.display = "none";
        change3.style.display = "none";
        change4.style.display = "none";
        change5.style.display = "none";
        change6.style.display = "none";
        change7.style.display = "none";
        
    }
}

function clickFnc2(){

    

    if(true){
        change3.style.display = "block";
        change4.style.display = "block";
        change1.style.display = "none";
        change2.style.display = "none";
        change11.style.display = "none";
        change5.style.display = "none";
        change6.style.display = "none";
        change7.style.display = "none";
    }
}

function clickFnc3(){


    if(true){
        change5.style.display = "block";
        change6.style.display = "block";
        change1.style.display = "none";
        change2.style.display = "none";
        change11.style.display = "none";
        change3.style.display = "none";
        change4.style.display = "none";
        change7.style.display = "none";
    }
}

function clickFnc4(){


    if(true){
        change7.style.display = "block";
        change1.style.display = "none";
        change2.style.display = "none";
        change11.style.display = "none";
        change3.style.display = "none";
        change4.style.display = "none";
        change5.style.display = "none";
        change6.style.display = "none";
    }
}
function clickFnc5(){
    if(true){
        change11.style.display = "block";
        change1.style.display = "none";
        change2.style.display = "none";
        change7.style.display = "none";
        change3.style.display = "none";
        change4.style.display = "none";
        change5.style.display = "none";
        change6.style.display = "none";
    }
}



document.getElementById('uploadForm').addEventListener('submit', function (e) {
    e.preventDefault(); // 폼 제출 기본 동작을 막고, AJAX 요청을 보냄

    var formData = new FormData(this);

    fetch('/upload', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        // 서버에서 반환된 이미지 URL로 이미지를 표시
        var img = document.getElementById('uploadedImage');
        img.src = data;  // 서버에서 반환한 이미지 경로
        img.style.display = 'block';  // 이미지 표시
    })
    .catch(error => console.error('파일 업로드 실패:', error));
});
