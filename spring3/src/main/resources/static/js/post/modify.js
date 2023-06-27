/**
 * nmodify.html
 * 포스트 업데이트, 삭제
 */

document.addEventListener('DOMContentLoaded', () => {
    
    const form = document.querySelector('form#postModifyForm');
    
    const id = document.querySelector('input#id').value;
    
   
    
    const btnUpdate = document.querySelector('button#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
        
        const title = document.querySelector('input#title').value;
        const content = document.querySelector('textarea#content').value;
        // TODO: 포스트 업데이트
        const check = confirm('업데이트?');
        if(check) {
            form.action = './update?id=' + id;
            form.method = 'post';
            form.submit();
        }
        
    });
    
    const btnDelete = document.querySelector('button#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        // TODO: 포스트 삭제
        const check = confirm('삭제?');
        
        if(check) {
            form.action = './delete?id=' + id;
            form.method = 'post';
            form.submit();
        }
    });
    
    
    
    
    
    
});