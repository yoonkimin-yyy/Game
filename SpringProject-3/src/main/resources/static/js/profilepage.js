function reviewFnc(){
    const review = document.getElementById('review-text').value;

    if(review === ""){
        alert("공백은 작성할 수 없습니다.");
    } else {
        alert("후기 작성이 완료되었습니다.");
    }
}