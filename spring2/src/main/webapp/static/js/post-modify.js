/**
 * post-modify.js
 * /post/modify.js에서 사용
 */

document.addEventListener('DOMContentLoaded', function() { // 익명 함수, callback 함수, 이벤트가 발생했을 때 브라우저에서 실행시켜주는 함수.
    
    console.log('test');    
    
    const form = document.querySelector('#postModifyForm');
    
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    const btnDelete = document.querySelector('button#btnDelete');
    
    // 삭제 버튼에 클릭 이벤트 핸드러(리스너) 등록.
    btnDelete.addEventListener('click', (e) => {
        e.preventDefault();
        
        const result = confirm(`NO. ${id} 정말 삭제할까요?`);
        
        if (result) {
            form.action = 'delete'; // 폼 제출(요청) 주소 '/post/delete' & 'delete' 차이? post/modify/?id=.. 인 주소에서 /post/delete로 바뀌는게 목적이므로 delete or ./delete 로 작성해야함.
            form.method = 'post'; // 폼 요청 방식
            form.submit(); // 폼 제출
        }
    });
    
    // 수정 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        
        const title = document.querySelector('input#title').value; // input에 입력된 값
        
        const content = document.querySelector('textarea#content').value; // textarea에 입력된 값
        
        if(title === '' || content === '') { // 둘 중 하나라도 공란이면
            alert('제목, 내용을 입력하세요');
            return; // 함수 종료
        }
        
        const result = confirm(`NO. ${id} 변경 내용을 저장할까요?`);
        if (result) {
            form.action = 'update'; // 폼 요청 주소 (./update)
            form.method = 'post';// 폼 요청 방식.
            form.submit(); // 폼 제출
        }
    });


});
 
 
 
 