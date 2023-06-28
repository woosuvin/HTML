/**
 * nmodify.html
 * 포스트 업데이트, 삭제
 */

document.addEventListener('DOMContentLoaded', () => {
    
    const form = document.querySelector('form#postModifyForm');
    const id = document.querySelector('input#id').value;
    
    const btnUpdate = document.querySelector('button#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
        
        // 버튼 클릭한 후에 변수값을 찾아야 하므로 함수 안에서 찾아야 함. 변수 찾는 위치 중요.
        const title = document.querySelector('input#title').value;
        const content = document.querySelector('textarea#content').value;
        const splitTitle = title.split(' ').join('');
        const splitContent = content.split(' ').join('');
        
        if (!splitTitle || !splitContent) {
            alert('제목과 내용을 입력하세요.');
            return;
        }
        
        // TODO: 포스트 업데이트
        const check = confirm('업데이트?');
        if(check) {
            form.action = 'update';
            form.method = 'post';
            form.submit();
        }
        
    });
    
    const btnDelete = document.querySelector('button#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        // TODO: 포스트 삭제
        const check = confirm('삭제?');
        
        if(check) {
            form.action = 'delete'; // submit 요청 주소
            form.method = 'post'; // submit 요청 방식
            form.submit(); // 폼 제출(submit), 요청 보내기
        }
    });
    
    
    
    
    
    
});