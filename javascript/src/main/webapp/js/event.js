/**
 * event.js
 */

document.addEventListener('DOMContentLoaded', function() {
    
    const itemInput = document.querySelector('input#itemInput'); // 입력한 글자
    const btnInput = document.querySelector('button#btnInput');
    const itemList = document.querySelector('ul#itemList'); // 리스트
    const itemInput2 = document.querySelector('input#itemInput2');
    const itemList2 = document.querySelector('ul#itemList2');
    const username = document.querySelector('input#username');
    const age = document.querySelector('input#age');
    const result = document.querySelector('div#result');
    
    btnInput.addEventListener('click', function() {
        // input에 입력된 값을 읽음.    
        const value = itemInput.value;
        
        // input에 입력된 값으로 li 요소를 만듦.
        const item = `<li>${value}</li>`;
        
        // li 요소를 ul에 추가
        itemList.innerHTML += item;
        
        itemInput.value = "";
        
        // input에 포커스를 줌.
        itemInput.focus();
    });
    
    
    
    itemInput2.addEventListener('keydown', function(e) {
        // e: KeyboardEvent 객체
        // 모든 이벤트 핸들러 함수(콜백)은 이벤트 객체를 argument로 전달받음.
        if(e.key == 'Enter') { // 엔터 키가 눌렸을 때
            const value = itemInput2.value;
            const item = `<li>${value}</li>`;
            itemList2.innerHTML += item;
            itemInput2.value = "";
        }
    });
    
    
    
    username.addEventListener('change', function(e) {
        updateNameAndAge();
    }); 
    
    age.addEventListener('change', function(e) {
        updateNameAndAge();
    });
    
    function updateNameAndAge() {
        const name = username.value;
        const age2 = age.value;
        const text = `<b>이름: </b> ${name}, <b>나이: </b> ${age2}`;
        result.innerHTML = text;
    }
}); 