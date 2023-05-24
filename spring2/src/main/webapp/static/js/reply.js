/**
 * reply.js
 * 댓글 등록, 목록 검색, 수정, 삭제
 * /post/detail.jsp에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    // 댓글 개수 표시 영역(sapn)
    const replyCountSpan = document.querySelector('span#replyCount');
    
    // 댓글 목록 표시 영역(div)
    const replies = document.querySelector('div#replies');
    
    // 댓글 삭제 버튼의 이벤트 리스너 (콜백) 함수
    const deleteReply = (e) => {
        // console.log(e);
        console.log(e.target); // e.target: 이벤트가 발생한 타겟. 여기서는 삭제 버튼.
        
        if(!confirm('정말 삭제할까요?')) {
            return;
        }
        
        // 삭제할 댓글 아이디
        const id = e.target.getAttribute('data-id');
        
        // 삭제 요청 URL
        const reqUrl = `/spring2/api/reply/${id}`;
        
        // 삭제 요청을 Ajax 방식으로 보냄.
        axios.delete(reqUrl)
            .then((response) => {
                console.log(response);
                alert('댓글 삭제 성공!!!!!!!!!!!!!!!!!!!');
                getRepliesWithPostId(); // 댓글 목록 갱신.
            })
            .catch((error) => {
                console.log(error);
                
            });
    };
    
    // 댓글 수정 버튼의 이벤트 리스너 (콜백) 함수
    const showUpdateModal = (e) => {
        // console.log(e);
        console.log(e.target);
    };
    
    // 댓글 목록 HTML을 작성하고 replies 영역에 추가하는 함수
    // argument data: Ajax 요청의 응답으로 전달받은 데이터.
    const makeReplyElements = (data) => {
        // 댓글 개수 업데이트
        replyCountSpan.innerHTML = data.length; // 배열 길이(원소 개수)
        
        replies.innerHTML = ''; // <div>의 컨텐트를 지움.
        
        let htmlStr = ''; // 문자열을 계속 덧붙이려고 let
        // for (let i = 0; i < data.length; i++) { }
        // for (let x in data) { } -> 인덱스 iteration
        for (let reply of data) {
            console.log(reply);
            
            // Timestamp 타입 값을 날짜/시간 타입 문자열로 변환:
            const modified = new Date(reply.modifiedTime).toLocaleString();
            
            // 댓글 1개를 표시할 HTML 코드:
            htmlStr += `
                <div class="card">
                    <div>
                        <span class="d-none">${reply.id}</span>
                        <span class="fw-bold">${reply.writer}</span>
                        <span class="text-secondary">${modified}</span>
                    </div>
                    <div>
                        ${reply.replyText}
                    </div>   
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button class="btnDelete btn btn-outline-danger" data-id="${reply.id}">
                            삭제
                        </button>
                        <button class="btnModify btn btn-outline-success" data-id="${reply.id}">
                            수정
                        </button>
                    </div>             
                </div>
            `;             
        }  
        // 작성된 HTML 코드를 replies <div> 영역 안에 포함.
        replies.innerHTML = htmlStr; 
        
        // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 등록:
        const deleteButtons = document.querySelectorAll('button.btnDelete');
        
        for (let btn of deleteButtons) {
            btn.addEventListener('click', deleteReply);
        }        
        
        // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 등록:
        const modifyButtons = document.querySelectorAll('button.btnModify');
        
        for (let btn of modifyButtons) {
            btn.addEventListener('click', showUpdateModal);
        }
        
    };
    
    const getRepliesWithPostId = async () => {
        // 댓글 목록을 요청하기 위한 포스트 번호(아이디)
        const postId = document.querySelector('input#id').value;
        
        // 댓글 목록을 요청할 URL
        const reqUrl = `/spring2/api/reply/all/${postId}`;
        
        // Ajax 요청을 보내고 응답을 기다림.
        try {
            const response = await axios.get(reqUrl);
            console.log(response);
            // 댓글 개수 업데이트 & 댓글 목록 보여주기
            makeReplyElements(response.data);
        } catch (error) {
            console.log(error);
        }     
    };
    
    // bootstrap collapse 객체를 생성 - 초기 상태는 화면에서 안보이는 상태(collapse)로 시작할거임
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', {toggle: false});
    
    // 댓글 등록/목록 보이기/숨기기 토글 버튼에 이벤트 리스너 등록
    const btnToggleReply = document.querySelector('button#btnToggleReply');
    
    
    
    btnToggleReply.addEventListener('click', () => {
        bsCollapse.toggle();
        
        const toggle = btnToggleReply.getAttribute('data-toggle'); // 'data-toggle'의 값인 toggle-off 가 return
        
        if (toggle === 'toggle-off') {
            btnToggleReply.innerHTML = '숨기기';
            btnToggleReply.setAttribute('data-toggle', 'toggle-on'); 
            
            // 댓글 전체 목록을 서버에 요청하고, 응답이 오면 화면 갱신.
            getRepliesWithPostId();
            
        } else {
            btnToggleReply.innerHTML = '보이기';
            btnToggleReply.setAttribute('data-toggle', 'toggle-off');
        }
                
    });
    
    // 댓글 등록 버튼
    const btnAddReply = document.querySelector('button#btnAddReply');
    
    const createReply = (e) => {
        // axios 라이브러리를 사용해서 Ajax 요청을 보냄.
        
        const postId = document.querySelector('input#id').value;
        const replyText = document.querySelector('textarea#replyText').value;
        const writer = document.querySelector('input#writer').value;
        
        if (replyText ==='') { // 댓글 내용이 없으면
            alert('댓글 내용을 입력하세요.');
            return;
        }
        
        const data = { postId, replyText, writer };
        
        axios.post('/spring2/api/reply', data) // POST 방식의 Ajax 요청 보냄. ('요청 보낼 주소', 주소로 보낼 data)
            .then((response) => {
                alert(`댓글 등록 성공 (${response.data})`);
                                
                // 댓글 입력 창의 내용을 지움.
                document.querySelector('textarea#replyText').value = '';
                
                // 댓글 목록을 새로고침.
                getRepliesWithPostId();
                
            }) // 성공 응답이 왔을 때 실행 할 callback 함수 등록
            .catch((error) => {
                console.log(error);
            }); // 에러 응답이 왔을 때 실행 할 callback 함수 등록
    };
    btnAddReply.addEventListener('click', createReply); // 함수를 외부에서 만드는 방법. callback 함수
    
});
