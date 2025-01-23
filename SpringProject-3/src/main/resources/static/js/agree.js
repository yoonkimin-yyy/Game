
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
    checkbox1: "N",
    checkbox2: "N",
    checkbox3: "N",
    checkbox4: "N",
	agreeAll: "N"
};

function checkAnything(){
        
    const eleCheckbox1 = document.getElementById("anything1");
    const eleCheckbox2 = document.getElementById("anything2");
    const eleCheckbox3 = document.getElementById("anything3");
    const eleCheckbox4 = document.getElementById("anything4");
	const agreeAll = document.getElementById("agreeAll");


    // 전역 변수에 데이터 넣어야 함
    checkData.checkbox1 = (eleCheckbox1.checked ? "Y" : "N");
    checkData.checkbox2 = eleCheckbox2.checked ? "Y" : "N";
    checkData.checkbox3 = eleCheckbox3.checked ? "Y" : "N";
    checkData.checkbox4 = eleCheckbox4.checked ? "Y" : "N";
	checkData.agreeAll = agreeAll.checked ? "Y" : "N";
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	