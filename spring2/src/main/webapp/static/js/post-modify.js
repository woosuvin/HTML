/**
 * post-modify.js
 * 
 */

document.addEventListener('DOMContentLoaded', function() {

    const form = document.querySelector('#postModifyForm');
    
    const inputId = document.querySelector('input#id');
    
    const inputTitle = document.querySelector('input#title');
    
    const textareaContent = document.querySelector('textarea#content');
    
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    const btnDelete = document.querySelector('button#btnDelete');
    
    // 삭제 버튼에 클릭 이벤트 핸드러(리스너) 등록.
    btnDelete.addEventListener('click', (e) => {
        e.preventDefault();
        
        const id = inputId.value;
        const result = confirm(`NO. ${id} 정말 삭제할까요?`);
        
        if (result) {
            form.action = 'delete'; // 폼 제출(요청) 주소
            form.method = 'post'; // 요청 방식
            form.submit(); // 폼 제출
        }
    });
    
    // 수정 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        
        const id = inputId.value;
        const title =inputTitle.value;
        const contet = textareaContent.value;
        
        if(title === '' || content === '') {
            alert('제목, 내용을 입력하세요');
            return;
        }
        const result = confirm(`NO. ${id} 포스트를 업데이트 할까요?`);
        
        if (result) {
            form.action = 'update';
            form.method = 'post';
            form.submit();
        }
    });


});
 
 
 
 