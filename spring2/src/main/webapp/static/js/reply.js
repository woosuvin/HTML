/**
 * reply.js
 * 댓글 등록, 목록 검색, 수정, 삭제
 * /post/detail.jsp에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    
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
        } else {
            btnToggleReply.innerHTML = '보이기';
            btnToggleReply.setAttribute('data-toggle', 'toggle-off');
        }
                
    });
    
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
                
                //TODO: 댓글 목록을 새로고침.
                
            }) // 성공 응답이 왔을 때 실행 할 callback 함수 등록
            .catch((error) => {
                console.log(error);
            }); // 에러 응답이 왔을 때 실행 할 callback 함수 등록

    };
    btnAddReply.addEventListener('click', createReply); // 함수를 외부에서 만드는 방법. callback 함수
    
});
