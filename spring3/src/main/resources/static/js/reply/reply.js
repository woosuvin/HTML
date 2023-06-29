/**
 * 댓글 영역 토글
 * 댓글 등록, 수정, 삭제, 검색
 */

document.addEventListener('DOMContentLoaded', () => {
    // 부트스트랩 Collapse 객체 생성, 초기 상태는 화면에 안보임.
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', {toggle: false});
    // 토글 버튼을 찾고 이벤트 리스너 등록
    const btnToggleReply = document.querySelector('button#btnToggleReply');
    
    btnToggleReply.addEventListener('click', (e) => {
        bsCollapse.toggle();
        if(e.target.innerText === '보이기') {
            e.target.innerText = '숨기기';
            getRepliesWithPostId();
        } else {
            e.target.innerText = '보이기';
        }
    });
    
    // 댓글 삭제 버튼들의 클릭을 처리하는 이벤트 처리 함수
    const deleteReply = (e) => {
        const check = confirm('정말 삭제하겠습니까?');
        if(!check) {
            return;
        }
        
        // 삭제할 댓글 id
        const id = e.target.getAttribute('data-id');
        
        // Ajax DELETE 방식 요청 주소
        const reqUrl = `/api/reply/${id}`;
        
        axios
            .delete(reqUrl) // Ajax DELETE 요청을 보냄
            .then((response) => {
                console.log(response);
                // 댓글 목록 새로고침
                getRepliesWithPostId();
                
            }) // 성공 응답일 때 실행할 콜백 등록
            .catch((error) => console.log(error)); // 실패 응답일 때 실행할 콜백 등록
    };
    
    const updateReply = (e) => {
        const id = e.target.getAttribute('data-id');
        const textAreaId = `#replyText_${id}`;
        const replyText = document.querySelector(textAreaId).value;
        const data = {id, replyText};
        
        const reqUrl = `/api/reply/update/${id}`;
        
        axios
            .put(reqUrl, data)
            .then((response) => {
                console.log(response);
                getRepliesWithPostId();
            })
            .catch((error) => console.log(error));
    };
    
    const makeReplyElements = (data) => {
        // 댓글 개수를 배열(data)의 원소 개수로 업데이트.
        document.querySelector('span#replyCount').innerText = data.length;
        
        // 댓글 목록을 삽입할 div 요소를 찾음.
        const replies = document.querySelector('div#replies');
        
        // div 안에 작성된 기존 내용을 삭제.
        replies.innerHTML = '';
        
        // div 안에 삽입할 HTML 코드
        let htmlStr = '';
        for (let reply of data) {
            htmlStr += `
                <div class="col-12">
                    <div class="card mb-2 px-2">
                        <div>
                            <span class="d-none">${reply.id}</span>
                            <small class="fw-bold">${reply.writer}</small>
                            <small>${reply.modifiedTime}</small>
                        </div>
                        
                        <div class="row mb-2">
                            <div class="form-floating col-10">
                                <textarea class="form-control" id="replyText_${reply.id}" >${reply.replyText}</textarea>
                            </div>
                            <div class="col-2 d-grid gap-2 d-md-flex justify-content-md-end">
                                <button class="btnModify btn btn-info btn-sm" data-id="${reply.id}">수정</button>
                                <button class="btnDelete btn btn-outline-info btn-sm" data-id="${reply.id}">삭제</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }
        // 작성된 HTML 문자열을 div 요소의 innerHTML로 설정.
        replies.innerHTML = htmlStr;
        
        // 모든 댓글 삭제 버튼들에게 이벤트 리스너를 등록
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        for(let btn of btnDeletes) {
            btn.addEventListener('click', deleteReply);
        }
        
        const btnModifies = document.querySelectorAll('button.btnModify');
        for (let btn of btnModifies) {
            btn.addEventListener('click', updateReply);
        }
    };
    
    
    // 포스트 번호에 달려있는 댓글 목록을 가져오는 함수 (Ajax 요청으로):
    const getRepliesWithPostId = async () => { // ajax 비동기 방식
        const postId = document.querySelector('#id').innerText; // 포스트 아이디 번호
        const reqUrl = `/api/reply/all/${postId}`; // Ajax 요청 주소
        
        // Ajax 요청을 보내고 응답을 기다림
        try {
            const response = await axios.get(reqUrl); // await 들어가는 구문이 있으면 함수에 async를 써야 함.
            makeReplyElements(response.data);            
        } catch(error) {
            console.log(error);
        }
    };
    
    // 댓글 등록 버튼을 찾고, 이벤트 리스너 등록.
    const btnReplyCreate = document.querySelector('button#btnReplyCreate');
    btnReplyCreate.addEventListener('click', () => {
        // 포스트 아이디, 댓글 내용 찾음.
        const postId = document.querySelector('#id').innerText;
        const replyText = document.querySelector('textarea#replyText').value;
        // TODO: 댓글 작성자는 admin, 나중에 로그인 아이디 사용.
        const writer = 'admin';
        
        // 댓글 내용이 비어 있으면 경고창을 보여주고 종료
        if (!replyText.split(' ').join('')) {
            alert('댓글 내용을 입력하세요.');
            return;
        }
        
        // Ajax 요청에서 보낼 데이터
        const data = {postId, replyText, writer}; // {} JavaScript object
        
        // Ajax 요청을 보낼 URL
        const reqUrl = '/api/reply';
        // Ajax POST 요청을 보냄
        axios
            .post(reqUrl, data)
            .then((response) => {
                console.log(response);
                document.querySelector('textarea#replyText').value = ''; // 댓글 작성란 내용 지움
                getRepliesWithPostId(); // 댓글 목록 새로고침
                
            }) // 성공 응답(response)일 때 실행할 콜백 등록
            .catch((error) => {
                console.log(error);
            }); // 실패(error)일 때 실행할 콜백 등록.
    });
        
    
}); 