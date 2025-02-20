
function toggleAll(A){
    
    const checkBoxes = document.getElementsByName("items");
    

    // 1. 전체 동의하기 요소를 매개변수로 전달 받음
    // 2. 전체 동의하기가 클릭이 된 상태면 checkbox.checked에 true
    //    전체 동의하기가 클릭이 안된 상태면 checkbox.checked에 false
    checkBoxes.forEach((checkbox) =>{
        checkbox.checked = A.checked;

        
    });
    checkAnything();

}

let checkData =  {
			agreeAll: "N",
	        essentialAgree: "N",           // essentiaBox1
	        personalAgree: "N",            // essentiaBox2
	        realNameAgree: "N",            // anything1
	        locationAgree: "N",            // anything2
	        personalAgreeOptional: "N",    // anything3
	        marketingAgree: "N",           // anything4
	        userNo: 0    
};

function checkAnything(){
	
			const chAgreeAll    = document.getElementById("agreeAll");
	        const chEssential   = document.getElementById("essentiaBox1");
	        const chPersonal    = document.getElementById("essentiaBox2");
	        const chRealName    = document.getElementById("anything1");
	        const chLocation    = document.getElementById("anything2");
	        const chPersonalOpt = document.getElementById("anything3");
	        const chMarketing   = document.getElementById("anything4");

			

    // 전역 변수에 데이터 넣어야 함
			checkData.agreeAll               = chAgreeAll.checked    ? "Y" : "N";
	        checkData.essentialAgree         = chEssential.checked   ? "Y" : "N";
	        checkData.personalAgree          = chPersonal.checked    ? "Y" : "N";
	        checkData.realNameAgree          = chRealName.checked    ? "Y" : "N";
	        checkData.locationAgree          = chLocation.checked    ? "Y" : "N";
	        checkData.personalAgreeOptional  = chPersonalOpt.checked ? "Y" : "N";
	        checkData.marketingAgree         = chMarketing.checked   ? "Y" : "N";
}

 function essential(){
        const essentialBox1 = document.getElementById("essentiaBox1");
        const essentialBox2 = document.getElementById("essentiaBox2");
		
		
    
        if(!essentialBox1.checked || !essentialBox2.checked){
            swal({
                title : '경고',
                text : '필수 체크박스는 체크하여야 합니다.',
                icon: "warning",
                confirmButtonText : '확인'
            })
        }else if(essentialBox1.checked && essentialBox2.checked){
			
			checkAnything();
			
            const submitForm = document.getElementById('submitForm');
    
            // 1. 전역변수에 저장된 데이터 확인 ("Y", "N")
            console.log(Object.keys(checkData).length)

            // checkData 길이만큼 반복(for)
            for(let [key , value] of Object.entries(checkData)){
                // 2. input 요소 생성
                const inputElement = document.createElement("input");
                // 3. input 요소에 name, value주고 type은 hidden으로
                inputElement.type = "hidden";
                inputElement.name = key;
                // 4. 다 만들엇으면 form 자식요소로 input 요소 삽입
                inputElement.value = value;
                submitForm.appendChild(inputElement);


                console.log(`이름은 : ${inputElement.name}, 값은: ${inputElement.value}`);
            }
			
			
            submitForm.submit();
			
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	